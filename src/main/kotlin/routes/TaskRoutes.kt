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
                "tasks" to TaskRepository.all()
            )
        )
        call.respondText(html, ContentType.Text.Html)
    }

    post("/tasks") {
        val title = call.receiveParameters()["title"].orEmpty().trim()

        if (title.isBlank()) {
            if (call.isHtmx()) {
                val error = """<div id="status" hx-swap-oob="true" role="alert" aria-live="assertive">Title is required.</div>"""
                return@post call.respondText(error, ContentType.Text.Html, HttpStatusCode.BadRequest)
            } else {
                return@post call.respondRedirect("/tasks?error=required")
            }
        }

        val task = TaskRepository.add(title)

        if (call.isHtmx()) {
            // Use the template instead of hardcoded HTML
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

    get("/tasks/{id}/edit") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.NotFound)
        val task = TaskRepository.get(id) ?: return@get call.respond(HttpStatusCode.NotFound)
        val errorParam = call.request.queryParameters["error"]

        val errorMessage = when (errorParam) {
            "blank" -> "Title is required. Please enter at least one character."
            else -> null
        }

        if (call.isHtmx()) {
            val model: Map<String, Any> = if (errorMessage != null) {
                mapOf("task" to task, "error" to errorMessage)
            } else {
                mapOf("task" to task, "error" to "")
            }
            val html = call.renderTemplate("tasks/_edit.peb", model)
            call.respondText(html, ContentType.Text.Html)
        } else {
            val model: Map<String, Any> = if (errorMessage != null) {
                mapOf(
                    "title" to "Tasks",
                    "tasks" to TaskRepository.all(),
                    "editingId" to id,
                    "errorMessage" to errorMessage
                )
            } else {
                mapOf(
                    "title" to "Tasks",
                    "tasks" to TaskRepository.all(),
                    "editingId" to id,
                    "errorMessage" to ""
                )
            }
            val html = call.renderTemplate("tasks/index.peb", model)
            call.respondText(html, ContentType.Text.Html)
        }
    }

    post("/tasks/{id}/edit") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@post call.respond(HttpStatusCode.NotFound)
        val task = TaskRepository.get(id) ?: return@post call.respond(HttpStatusCode.NotFound)

        val newTitle = call.receiveParameters()["title"].orEmpty().trim()

        if (newTitle.isBlank()) {
            if (call.isHtmx()) {
                val html = call.renderTemplate(
                    "tasks/_edit.peb",
                    mapOf(
                        "task" to task,
                        "error" to "Title is required. Please enter at least one character."
                    )
                )
                return@post call.respondText(html, ContentType.Text.Html, HttpStatusCode.BadRequest)
            } else {
                return@post call.respondRedirect("/tasks/${id}/edit?error=blank")
            }
        }

        val updatedTask = TaskRepository.update(id, newTitle)
        if (updatedTask == null) {
            return@post call.respond(HttpStatusCode.NotFound, "Task not found")
        }

        if (call.isHtmx()) {
            val viewHtml = call.renderTemplate(
                "tasks/_item.peb",
                mapOf("task" to updatedTask)
            )

            val status = """<div id="status" hx-swap-oob="true">Task "${updatedTask.title}" updated successfully.</div>"""

            return@post call.respondText(viewHtml + status, ContentType.Text.Html)
        }

        call.respondRedirect("/tasks")
    }

    get("/tasks/{id}/view") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.NotFound)
        val task = TaskRepository.get(id) ?: return@get call.respond(HttpStatusCode.NotFound)

        val html = call.renderTemplate(
            "tasks/_item.peb",
            mapOf("task" to task)
        )
        call.respondText(html, ContentType.Text.Html)
    }
}