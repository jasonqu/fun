package demo.scala.web

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import akka.stream.scaladsl.Sink

import scala.concurrent.Future
import scala.concurrent.duration._

object WebExample extends App {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  val requestHandler: HttpRequest => HttpResponse = { r: HttpRequest =>
    val req = r.entity.toStrict(100.millis).value.get.get.getData().utf8String
    HttpResponse(entity = HttpEntity(ContentTypes.`text/plain(UTF-8)`, Functional.registerCustomer(req)))
  }

  val serverSource = Http().bind(interface = "localhost", port = 8080)
  val bindingFuture: Future[Http.ServerBinding] =
    serverSource.to(Sink.foreach { connection => connection handleWithSyncHandler requestHandler
    }).run()

  println("open http://localhost:8080/")
}
