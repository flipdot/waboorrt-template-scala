package org.flipdot.waboorrt

import com.github.arteam.simplejsonrpc.server.JsonRpcServer
import com.sun.net.httpserver.{HttpExchange, HttpServer}

import java.net.InetSocketAddress
import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    val rpcServer = new JsonRpcServer()

    val server = HttpServer.create(new InetSocketAddress("0.0.0.0", 4000), 0)
    server.createContext("/jsonrpc", (exchange: HttpExchange) => {
      val request = Source.fromInputStream(exchange.getRequestBody).getLines().mkString("\n")
      val response = rpcServer.handle(request, Bot)
      println("request = " + request)
      println("response = " + response)
      exchange.getResponseHeaders.add("Content-Type", "application/json")
      exchange.sendResponseHeaders(200, response.length)
      exchange.getResponseBody.write(response.getBytes)
      exchange.close()
    })
    server.start()
    println("Server started!")
  }
}
