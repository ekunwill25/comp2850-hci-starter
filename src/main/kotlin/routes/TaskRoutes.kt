package routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import model.Task
import storage.TaskStore
import utils.isHtmxRequest   
import utils.renderTemplate


fun Route.taskRoutes(store: TaskStore) {

    // ---------------- GET list ------------------
    get("/tasks") {
        val tasks = store.getAll()

        val html = call.renderTemplate(
            "tasks/index.peb",
            mapOf("tasks" to tasks)
        )

        call.respondText(html, ContentType.Text.Html)
    }


    // ---------------- CREATE ------------------
    post("/tasks") {
        val title = call.receiveParameters()["title"].orEmpty().trim()
        if (title.isBlank()) return@post call.respond(HttpStatusCode.BadRequest)

        val task = Task(title = title)
        store.add(task)

        val fragment = call.renderTemplate(
            "tasks/_item.peb",
            mapOf("task" to task)
        )

        call.respondText(fragment, ContentType.Text.Html)
    }


    // ---------------- EDIT FORM ------------------
    get("/tasks/{id}/edit") {
        val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.NotFound)
        val task = store.getById(id) ?: return@get call.respond(HttpStatusCode.NotFound)

        val html = call.renderTemplate(
            "tasks/_edit.peb",
            mapOf("task" to task)
        )

        call.respondText(html, ContentType.Text.Html)
    }


    // ---------------- SAVE EDIT ------------------
    post("/tasks/{id}/edit") {
        val id = call.parameters["id"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val title = call.receiveParameters()["title"].orEmpty().trim()

        val existing = store.getById(id) ?: return@post call.respond(HttpStatusCode.NotFound)
        val updated = existing.copy(title = title)

        store.update(updated)

        val fragment = call.renderTemplate(
            "tasks/_item.peb",
            mapOf("task" to updated)
        )

        call.respondText(fragment, ContentType.Text.Html)
    }


    // ---------------- DELETE ------------------
    post("/tasks/{id}/delete") {
        val id = call.parameters["id"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        store.delete(id)
        call.respond(HttpStatusCode.OK)
    }
}
