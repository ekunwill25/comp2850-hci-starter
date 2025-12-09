package utils

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.sessions.*
import io.pebbletemplates.pebble.PebbleEngine
import io.ktor.util.*
import java.io.StringWriter


val PebbleEngineKey = AttributeKey<PebbleEngine>("PebbleEngine")


suspend fun ApplicationCall.renderTemplate(
    templateName: String,
    context: Map<String, Any> = emptyMap(),
): String {
    val engine = application.attributes[PebbleEngineKey]
    val writer = StringWriter()
    val template = engine.getTemplate(templateName)

    val sessionData = sessions.get<SessionData>()
    val enrichedContext = context + mapOf(
        "sessionId" to (sessionData?.id ?: "anonymous"),
        "isHtmx" to isHtmxRequest()
    )

    template.evaluate(writer, enrichedContext)
    return writer.toString()
}


fun ApplicationCall.isHtmxRequest(): Boolean =
    request.headers["HX-Request"] == "true"
