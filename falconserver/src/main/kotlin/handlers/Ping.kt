package handlers

import io.javalin.Context

data class Pong(
    var container: String,
    var host: String
)

object PingController {
    fun pong(ctx: Context) {
        val host: String = System.getenv("PARENT_HOSTNAME") ?: ""
        val container: String = System.getenv("HOSTNAME") ?: ""
        val pongResponse = Pong(container, host)
        ctx.json(pongResponse)
    }
}