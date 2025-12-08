package routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import storage.TaskStore
import model.Task
import renderTemplate

fun ApplicationCall.isHtmx(): Boolean =
    request.headers["HX-Request"]?.equals("true", ignoreCase = true) == true

/**
 * Main CRUD routes using TaskStore (UUID tasks)
 */
fun Route.taskRoutes(store: TaskStore = TaskStore()) {

    // GET /tasks
    get("/tasks") {
        val tasks = store.getAll()

        val html = call.renderTemplate(
            "tasks/index.peb",
            mapOf(
                "title" to "Tasks",
                "tasks" to tasks
            )
        )

        call.respondText(html, ContentType.Text.Html)
    }

    // POST /tasks
    post("/tasks") {
        val title = call.receiveParameters()["title"].orEmpty().trim()

        if (title.isBlank()) {
            return@post call.respondText(
                """<p id="status" hx-swap-oob="innerText">Title cannot be blank.</p>""",
                ContentType.Text.Html,
                HttpStatusCode.BadRequest
            )
        }

        val task = Task(title = title)
        store.add(task)

        // HTMX partial response
        if (call.isHtmx()) {
            val fragment = call.renderTemplate("tasks/_item.peb", mapOf("task" to task))
            val status = """<p id="status" hx-swap-oob="innerText">Task "${task.title}" added</p>"""
            return@post call.respondText(fragment + status, ContentType.Text.Html, HttpStatusCode.Created)
        }

        // non-HTMX fallback
        call.respondRedirect("/tasks")
    }

    // DELETE /tasks/{id}
    delete("/tasks/{id}") {
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)

        store.delete(id)

        call.respondText(
            """<p id="status" hx-swap-oob="innerText">Task deleted</p>""",
            ContentType.Text.Html,
            HttpStatusCode.OK
        )
    }

    // GET /tasks/{id}/edit
    get("/tasks/{id}/edit") {
        val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.NotFound)
        val task = store.getById(id) ?: return@get call.respond(HttpStatusCode.NotFound)

        if (call.isHtmx()) {
            val html = call.renderTemplate("tasks/_edit.peb", mapOf("task" to task))
            return@get call.respondText(html, ContentType.Text.Html)
        }

        // non-HTMX
        val html = call.renderTemplate(
            "tasks/index.peb",
            mapOf(
                "title" to "Edit Task",
                "tasks" to store.getAll(),
                "editingId" to id
            )
        )
        call.respondText(html, ContentType.Text.Html)
    }

    // POST /tasks/{id}/edit
    post("/tasks/{id}/edit") {
        val id = call.parameters["id"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val newTitle = call.receiveParameters()["title"]?.trim()

        if (newTitle.isNullOrBlank()) {
            return@post call.respond(HttpStatusCode.BadRequest, "Invalid title")
        }

        val updated = store.getById(id) ?: return@post call.respond(HttpStatusCode.NotFound)
        updated.title = newTitle
        store.update(updated)

        if (call.isHtmx()) {
            val fragment = call.renderTemplate("tasks/_item.peb", mapOf("task" to updated))
            val status = """<p id="status" hx-swap-oob="innerText">Updated "${updated.title}"</p>"""
            return@post call.respondText(fragment + status, ContentType.Text.Html)
        }

        call.respondRedirect("/tasks")
    }

    // GET /tasks/{id}/view
    get("/tasks/{id}/view") {
        val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.NotFound)
        val task = store.getById(id) ?: return@get call.respond(HttpStatusCode.NotFound)

        val html = call.renderTemplate("tasks/_item.peb", mapOf("task" to task))
        call.respondText(html, ContentType.Text.Html)
    }
}
