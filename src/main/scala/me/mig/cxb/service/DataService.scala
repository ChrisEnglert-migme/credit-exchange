package me.mig.cxb.service

import akka.actor._
import me.mig.cxb.web.TestModel
import me.mig.fission.dao.OAuth2DAO

import scala.util.Success

object TestServiceWorker {
  def props: Props = Props[TestServiceWorker]
}

class TestServiceWorker extends Actor with ActorLogging {

  implicit val ec = context.dispatcher

  override def receive: Receive = {
    case x: TestModel => test(sender, x)
  }

  def test(s:ActorRef, x:TestModel) = {
    OAuth2DAO().getToken("852fe10712f44658b5d3598e03f24ce8981911189fb547ed9ce30260070a6026").onComplete {
      case Success(pt) => s ! TestModel("hi " + x.name)
      case _ => s ! Status.Failure(new RuntimeException("fail"))
    }
  }

}
