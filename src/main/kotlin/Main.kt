import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.plugins.callloging.*
import io.pebbletemplates.pebble.PebbleEngine
import utils.PebbleEngineKey
import storage.TaskStore
import routes.taskRoutes

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureLogging()
        configureTemplating()
        configureSessions()
        configureRouting()
    }.start(wait = true)
}

fun Application.configureLogging() {
    install(CallLogging)
}

fun Application.configureTemplating() {
    val loader = io.pebbletemplates.pebble.loader.ClasspathLoader().apply {
        prefix = "templates"
    }

    val engine = PebbleEngine.Builder()
        .loader(loader)
        .cacheActive(false)
        .build()

    attributes.put(PebbleEngineKey, engine)
}

fun Application.configureSessions() {
    install(Sessions) {
        cookie<utils.SessionData>("COMP2850_SESSION") {
            cookie.path = "/"
            cookie.httpOnly = true
            cookie.extensions["SameSite"] = "Strict"
        }
    }
}

fun Application.configureRouting() {
    val store = TaskStore()
    routing {
        taskRoutes(store)
    }
}
