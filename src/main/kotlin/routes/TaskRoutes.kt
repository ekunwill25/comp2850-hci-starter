package routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import model.Task
import storage.TaskStore
import renderTemplate
import isHtmxRequest     // <-- REQUIRED

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

    // POST /tasks (create)
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

        // return only fragment (HTMX)
        val fragment = call.renderTemplate("tasks/_item.peb", mapOf("task" to task))
        val status = """<p id="status" hx-swap-oob="innerText">Task "${task.title}" added</p>"""

        call.respondText(fragment + status, ContentType.Text.Html, HttpStatusCode.Created)
    }

    // GET /tasks/{id}/edit  → return edit form
    get("/tasks/{id}/edit") {
        val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.NotFound)
        val task = store.getById(id) ?: return@get call.respond(HttpStatusCode.NotFound)

        val html = call.renderTemplate("tasks/_edit.peb", mapOf("task" to task))
        call.respondText(html, ContentType.Text.Html)
    }

    // POST /tasks/{id}/edit → update and return updated item
    post("/tasks/{id}/edit") {
        val id = call.parameters["id"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val title = call.receiveParameters()["title"].orEmpty().trim()

        val updated = store.update(id, title)
            ?: return@post call.respond(HttpStatusCode.NotFound)

        // If request came from HTMX, return only the <li> partial
        if (call.isHtmxRequest()) {
            val fragment = call.renderTemplate("tasks/_item.peb", mapOf("task" to updated))
            return@post call.respondText(fragment, ContentType.Text.Html)
        }

        // fallback full redirect
        call.respondRedirect("/tasks")
    }

    // DELETE /tasks/{id}
    delete("/tasks/{id}") {
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)

        store.delete(id)

        call.respondText(
            """<p id="status" hx-swap-oob="innerText">Task deleted.</p>""",
            ContentType.Text.Html
        )
    }
}
