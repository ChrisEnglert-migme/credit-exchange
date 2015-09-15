package me.mig.cxb.web

import akka.actor.{Actor, ActorRef, Props}
import akka.io.IO
import me.mig.cxb.actor.{ActorNames, SubSystem}
import me.mig.cxb.service.TestServiceWorker
import spray.can.Http

import scala.concurrent.ExecutionContext

object TestAPI extends SubSystem {

  override def start() = {
    val service = system.actorOf(Props[TestAPIActor], ActorNames.testAPI)
    val interface:String =  "localhost"
    val port:Int = 8080

    IO(Http) ! Http.Bind(service, interface, port)
  }

}

class TestAPIActor extends Actor with TestCxService {

  override implicit val ec: ExecutionContext = ExecutionContext.global

  def receive: Receive = runRoute(routes)
  def actorRefFactory = context

  override val ds: ActorRef = context.actorOf( TestServiceWorker.props )
}
