package util.error

import scala.concurrent.Future

import play.api.Logger
import play.api.http.HttpErrorHandler
import play.api.mvc._
import play.api.mvc.Results._

class CustomErrorHandler extends HttpErrorHandler {

  override def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    // TODO return custom error page
    Logger.error("A client error occurred: " + message)
    Future.successful(
      Status(statusCode)("A client error occurred: " + message)
    )
  }

  override def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    // TODO return custom error page
    Logger.error("A server error occurred", exception)
    Future.successful(
      InternalServerError("A server error occurred: " + exception.getMessage)
    )
  }
}
