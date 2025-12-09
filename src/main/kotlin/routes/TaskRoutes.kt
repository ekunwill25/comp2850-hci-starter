package routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import storage.TaskStore
import model.Task

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

        // HTMX partial response
        if (call.isHtmxRequest()) {
            val fragment = call.renderTemplate("tasks/_item.peb", mapOf("task" to task))
            val status = """<p id="status" hx-swap-oob="innerText">Task "${task.title}" added</p>"""
            return@post call.respondText(fragment + status, ContentType.Text.Html, HttpStatusCode.Created)
        }

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


    // GET /tasks/{id}/edit
    get("/tasks/{id}/edit") {
        val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.NotFound)
        val task = store.getById(id) ?: return@get call.respond(HttpStatusCode.NotFound)

        if (call.isHtmxRequest()) {
            val html = call.renderTemplate("tasks/_edit.peb", mapOf("task" to task))
            return@get call.respondText(html, ContentType.Text.Html)
        }

        val html = call.renderTemplate(
            "tasks/index.peb",
            mapOf(
                "title" to "Edit Task",
                "tasks" to store.getAll(),
                "editingId" to id,
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

        val task = store.getById(id) ?: return@post call.respond(HttpStatusCode.NotFound)
        task.title = newTitle
        store.update(task)

        if (call.isHtmxRequest()) {
            val fragment = call.renderTemplate("tasks/_item.peb", mapOf("task" to task))
            val status = """<p id="status" hx-swap-oob="innerText">Updated "${task.title}"</p>"""
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

    get("/tasks") {
        val query = call.request.queryParameters["q"].orEmpty()
        val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
        val tasks = store.search(query)
        val data = Page.paginate(tasks.map { it.toPebbleContext() }, page, pageSize = 10)
        val model = mapOf("title" to "Tasks", "page" to data, "query" to query)
        val html = call.renderTemplate("tasks/index.peb", model)
        call.respondText(html, ContentType.Text.Html)
    }

    get("/tasks/fragment") {
        val query = call.request.queryParameters["q"].orEmpty()
        val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
        val tasks = store.search(query)
        val data = Page.paginate(tasks.map { it.toPebbleContext() }, page, pageSize = 10)
        val list = call.renderTemplate("tasks/_list.peb", mapOf("page" to data, "query" to query))
        val pager = call.renderTemplate("tasks/_pager.peb", mapOf("page" to data, "query" to query))
        val status = """<div id="status" hx-swap-oob="true">Found ${data.totalItems} tasks.</div>"""
        call.respondText(list + pager + status, ContentType.Text.Html)
    }

}
