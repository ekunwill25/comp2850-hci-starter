package routes

import data.TaskRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import renderTemplate

fun ApplicationCall.isHtmx(): Boolean =
    request.headers["HX-Request"]?.equals("true", ignoreCase = true) == true

fun Route.taskRoutes() {

    get("/tasks") {
        val html = call.renderTemplate(
            "tasks/index.peb",
            mapOf(
                "title" to "Tasks",
                "tasks" to TaskRepository.all(),
                "editingId" to 0,
                "errorMessage" to ""
            )
        )
        call.respondText(html, ContentType.Text.Html)
    }

    post("/tasks") {
        val title = call.receiveParameters()["title"].orEmpty().trim()

        // Handle empty title
        if (title.isBlank()) {
            if (call.isHtmx()) {
                // Just send an out-of-band status message
                val status = """<div id="status" hx-swap-oob="true">Title cannot be blank.</div>"""
                return@post call.respondText(status, ContentType.Text.Html, HttpStatusCode.BadRequest)
            } else {
                // Re-render full page with error message
                val html = call.renderTemplate(
                    "tasks/index.peb",
                    mapOf(
                        "title" to "Tasks",
                        "tasks" to TaskRepository.all(),
                        "editingId" to 0,
                        "errorMessage" to "Title cannot be blank."
                    )
                )
                return@post call.respondText(html, ContentType.Text.Html, HttpStatusCode.BadRequest)
            }
        }

        // Title is OK → create task
        val task = TaskRepository.add(title)

        if (call.isHtmx()) {
            val fragment = call.renderTemplate(
                "tasks/_item.peb",
                mapOf("task" to task)
            )
            val status = """<div id="status" hx-swap-oob="true">Task "${task.title}" added successfully.</div>"""
            return@post call.respondText(fragment + status, ContentType.Text.Html, HttpStatusCode.Created)
        }

        call.respondRedirect("/tasks")
    }

    post("/tasks/{id}/delete") {
        val id = call.parameters["id"]?.toIntOrNull()
        val removed = id?.let { TaskRepository.delete(it) } ?: false

        if (call.isHtmx()) {
            val status = """<div id="status" hx-swap-oob="true">Task deleted.</div>"""
            return@post call.respondText(status, ContentType.Text.Html)
        }

        call.respondRedirect("/tasks")
    }

    // GET /tasks/{id}/edit
    get("/tasks/{id}/edit") {
        val id = call.parameters["id"]?.toIntOrNull()
        val task = id?.let { TaskRepository.get(it) }

        if (task == null) {
            call.respond(HttpStatusCode.NotFound, "Task not found")
            return@get
        }

        if (call.isHtmx()) {
            val html = call.renderTemplate("tasks/_edit.peb", mapOf("task" to task))
            call.respondText(html, ContentType.Text.Html)
        } else {
            val html = call.renderTemplate(
                "tasks/index.peb",
                mapOf(
                    "title" to "Edit Task",
                    "tasks" to TaskRepository.all(),
                    "editingTaskId" to id
                )
            )
            call.respondText(html, ContentType.Text.Html)
        }
    }

    // POST /tasks/{id}/edit
    post("/tasks/{id}/edit") {
        val id = call.parameters["id"]?.toIntOrNull()
        val newTitle = call.receiveParameters()["title"]?.trim()

        if (id == null || newTitle.isNullOrBlank()) {
            call.respond(HttpStatusCode.BadRequest, "Invalid input")
            return@post
        }

        val updated = TaskRepository.update(id, newTitle)

        if (updated == null) {
            call.respond(HttpStatusCode.NotFound, "Task not found")
            return@post
        }

        if (call.isHtmx()) {
            val item = call.renderTemplate("tasks/_item.peb", mapOf("task" to updated))
            val status = """<div id="status" hx-swap-oob="true">Task updated to "${updated.title}".</div>"""
            call.respondText(item + status, ContentType.Text.Html)
        } else {
            call.respondRedirect("/tasks")
        }
    }

    // GET /tasks/{id}/view
    get("/tasks/{id}/view") {
        val id = call.parameters["id"]?.toIntOrNull()
        val task = id?.let { TaskRepository.get(it) }

        if (task == null) {
            call.respond(HttpStatusCode.NotFound)
            return@get
        }

        val html = call.renderTemplate("tasks/_item.peb", mapOf("task" to task))
        call.respondText(html, ContentType.Text.Html)
    }
}
