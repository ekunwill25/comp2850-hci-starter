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