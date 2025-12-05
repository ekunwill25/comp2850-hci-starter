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
            val fragment = """<li id="task-${task.id}"><span>${task.title}</span><form action="/tasks/${task.id}/delete" method="post" style="display: inline;" hx-post="/tasks/${task.id}/delete" hx-target="#task-${task.id}" hx-swap="outerHTML"><button type="submit">Delete</button></form></li>"""
            val status = """<div id="status" hx-swap-oob="true">Task added.</div>"""
            return@post call.respondText(fragment + status, ContentType.Text.Html, HttpStatusCode.Created)
        }

        call.respondRedirect("/tasks")

        get("/tasks/{id}/edit") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.NotFound)
        val task = TaskRepository.get(id) ?: return@get call.respond(HttpStatusCode.NotFound)
        val errorParam = call.request.queryParameters["error"]

        val errorMessage = when (errorParam) {
            "blank" -> "Title is required. Please enter at least one character."
            else -> null
        }

        if (call.isHtmx()) {
            // HTMX path: return edit fragment
            val html = call.renderTemplate(
                "tasks/_edit.peb",
                mapOf("task" to task, "error" to (errorMessage ?: ""))
            )
            call.respondText(html, ContentType.Text.Html)
        } else {
            // No-JS path: full-page render with editingId
            val html = call.renderTemplate(
                "tasks/index.peb",
                mapOf(
                    "title" to "Tasks",
                    "tasks" to TaskRepository.all(),
                    "editingId" to id,
                    "errorMessage" to (errorMessage ?: "")
                )
            )
            call.respondText(html, ContentType.Text.Html)
        }
        post("/tasks/{id}/edit") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@post call.respond(HttpStatusCode.NotFound)
        val task = TaskRepository.get(id) ?: return@post call.respond(HttpStatusCode.NotFound)

        val newTitle = call.receiveParameters()["title"].orEmpty().trim()

        // Validation
        if (newTitle.isBlank()) {
            if (call.isHtmx()) {
                // HTMX path: return edit fragment with error
                val html = call.renderTemplate(
                    "tasks/_edit.peb",
                    mapOf(
                        "task" to task,
                        "error" to "Title is required. Please enter at least one character."
                    )
                )
                return@post call.respondText(html, ContentType.Text.Html, HttpStatusCode.BadRequest)
            } else {
                // No-JS path: redirect with error flag
                return@post call.respondRedirect("/tasks/${id}/edit?error=blank")
            }
        }

        // Update task
        val updatedTask = TaskRepository.update(id, newTitle)
        if (updatedTask == null) {
            return@post call.respond(HttpStatusCode.NotFound, "Task not found")
        }

        if (call.isHtmx()) {
            // HTMX path: return view fragment + OOB status
            val viewHtml = call.renderTemplate(
                "tasks/_item.peb",
                mapOf("task" to updatedTask)
            )

            val status = """<div id="status" hx-swap-oob="true">Task "${updatedTask.title}" updated successfully.</div>"""

            return@post call.respondText(viewHtml + status, ContentType.Text.Html)
        }

        // No-JS path: PRG redirect
        call.respondRedirect("/tasks")
    }

        post("/tasks/{id}/edit") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@post call.respond(HttpStatusCode.NotFound)
        val task = TaskRepository.get(id) ?: return@post call.respond(HttpStatusCode.NotFound)

        val newTitle = call.receiveParameters()["title"].orEmpty().trim()

        // Validation
        if (newTitle.isBlank()) {
            if (call.isHtmx()) {
                // HTMX path: return edit fragment with error
                val html = call.renderTemplate(
                    "tasks/_edit.peb",
                    mapOf(
                        "task" to task,
                        "error" to "Title is required. Please enter at least one character."
                    )
                )
                return@post call.respondText(html, ContentType.Text.Html, HttpStatusCode.BadRequest)
            } else {
                // No-JS path: redirect with error flag
                return@post call.respondRedirect("/tasks/${id}/edit?error=blank")
            }
        }

        // Update task
        val updatedTask = TaskRepository.update(id, newTitle)
        if (updatedTask == null) {
            return@post call.respond(HttpStatusCode.NotFound, "Task not found")
        }

        if (call.isHtmx()) {
            // HTMX path: return view fragment + OOB status
            val viewHtml = call.renderTemplate(
                "tasks/_item.peb",
                mapOf("task" to updatedTask)
            )

            val status = """<div id="status" hx-swap-oob="true">Task "${updatedTask.title}" updated successfully.</div>"""

            return@post call.respondText(viewHtml + status, ContentType.Text.Html)
        }

        // No-JS path: PRG redirect
        call.respondRedirect("/tasks")
    }

        get("/tasks/{id}/view") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.NotFound)
        val task = TaskRepository.get(id) ?: return@get call.respond(HttpStatusCode.NotFound)

        // HTMX path only (cancel is just a link to /tasks in no-JS)
        val html = call.renderTemplate(
            "tasks/_item.peb",
            mapOf("task" to task)
        )
        call.respondText(html, ContentType.Text.Html)
    }
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
}

