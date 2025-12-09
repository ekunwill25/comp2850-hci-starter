package routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import model.Task
import storage.TaskStore
import renderTemplate

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

        val fragment = call.renderTemplate("tasks/_item.peb", mapOf("task" to task))
        val status = """<p id="status" hx-swap-oob="innerText">Task "${task.title}" added</p>"""

        call.respondText(fragment + status, ContentType.Text.Html, HttpStatusCode.Created)
    }

    // PUT /tasks/{id}
    put("/tasks/{id}") {
        val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
        val params = call.receiveParameters()
        val title = params["title"].orEmpty().trim()

        if (title.isBlank()) {
            return@put call.respond(HttpStatusCode.BadRequest, "Title cannot be blank")
        }

        val task = store.update(id, title)
            ?: return@put call.respond(HttpStatusCode.NotFound)

        val fragment = call.renderTemplate("tasks/_item.peb", mapOf("task" to task))

        call.respondText(fragment, ContentType.Text.Html)
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

    // GET /tasks/{id}/edit
    get("/tasks/{id}/edit") {
        val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.NotFound)
        val task = store.getById(id) ?: return@get call.respond(HttpStatusCode.NotFound)

        val html = call.renderTemplate("tasks/_edit.peb", mapOf("task" to task))

        call.respondText(html, ContentType.Text.Html)
    }
}
