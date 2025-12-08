package routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*      // needed for call.sessions
import renderTemplate

import model.Task            // <- your Task data class
import storage.TaskStore     // <- your TaskStore

// if you have Page and UserSession imports, enable them here
// import pagination.Page
// import sessions.UserSession
// import ext.toPebbleContext

fun ApplicationCall.isHtmx(): Boolean =
    request.headers["HX-Request"]?.equals("true", ignoreCase = true) == true

fun Routing.configureTaskRoutes(store: TaskStore = TaskStore()) {

    // GET /tasks
    get("/tasks") {

        val query = call.request.queryParameters["q"].orEmpty()
        val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1

        val tasks = store.search(query)

        // Later: paginate tasks
        // val data = Page.paginate(tasks.map { it.toPebbleContext() }, page, pageSize = 5)
        val data = tasks  // TEMP until Page exists

        // NEW session footer data
        val sessionId = call.sessions.get<UserSession>()?.id ?: "guest"
        val isHtmx = call.request.headers["HX-Request"]?.equals("true", ignoreCase = true) ?: false

        val model = mapOf(
            "title" to "Tasks",
            "page" to data,     // or tasks list for now
            "query" to query,
            "sessionId" to sessionId,
            "isHtmx" to isHtmx
        )

        val html = call.renderTemplate("tasks/index.peb", model)
        call.respondText(html, ContentType.Text.Html)
    }

    // POST /tasks (create)
    post("/tasks") {
        val title = call.receiveParameters()["title"].orEmpty().trim()

        if (title.isBlank()) {
            val status = """
                <p id="status" hx-swap-oob="innerHTML">
                  Title cannot be blank.
                </p>
            """.trimIndent()

            return@post call.respondText(status, ContentType.Text.Html, HttpStatusCode.BadRequest)
        }

        val task = Task(title = title)
        store.add(task)

        if (call.isHtmx()) {
            val fragment = call.renderTemplate("tasks/_item.peb", mapOf("task" to task))
            val status = """
                <p id="status" hx-swap-oob="innerHTML">
                  Task "${task.title}" added successfully.
                </p>
            """.trimIndent()

            return@post call.respondText(fragment + status, ContentType.Text.Html, HttpStatusCode.Created)
        }

        call.respondRedirect("/tasks")
    }

    // DELETE /tasks/{id}
    delete("/tasks/{id}") {
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)

        store.delete(id)

        val status = """
            <p id="status" hx-swap-oob="innerHTML">
              Task deleted.
            </p>
        """.trimIndent()

        call.respondText(status, ContentType.Text.Html)
    }

    // GET /tasks/{id}/edit
    get("/tasks/{id}/edit") {
        val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.NotFound)
        val task = store.getById(id) ?: return@get call.respond(HttpStatusCode.NotFound)

        if (call.isHtmx()) {
            val html = call.renderTemplate("tasks/_edit.peb", mapOf("task" to task))
            return@get call.respondText(html, ContentType.Text.Html)
        }

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

        val existing = store.getById(id) ?: return@post call.respond(HttpStatusCode.NotFound)
        val updatedTask = existing.copy(title = newTitle)

        if (!store.update(updatedTask)) {
            return@post call.respond(HttpStatusCode.NotFound)
        }

        if (call.isHtmx()) {
            val item = call.renderTemplate("tasks/_item.peb", mapOf("task" to updatedTask))
            val status = """
                <p id="status" hx-swap-oob="innerHTML">
                  Task updated to "${updatedTask.title}".
                </p>
            """.trimIndent()

            return@post call.respondText(item + status, ContentType.Text.Html)
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
