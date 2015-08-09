package util.security

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.{ClassTag, classTag}

import play.api.mvc.Results._
import play.api.mvc._

import controller.routes
import jp.t2v.lab.play2.auth._
import service.UserAccountService
import viewmodel.UserAccountViewModel

trait AuthConfigLike extends AuthConfig {

  type Id = String
  type User = UserAccountViewModel
  type Authority = None.type // TODO role type

  val idTag: ClassTag[Id] = classTag[Id]
  val sessionTimeoutInSeconds: Int = 3600

  val userAccountService: UserAccountService

  def resolveUser(id: Id)(implicit ctx: ExecutionContext): Future[Option[UserAccountViewModel]] = {
    userAccountService.findByEmail(id)
  }

  def loginSucceeded(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
    Future.successful(Redirect(routes.SampleController.index()))

  def logoutSucceeded(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
    Future.successful(Redirect(routes.LoginLogoutController.index()))

  def authenticationFailed(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
    Future.successful(Redirect(routes.LoginLogoutController.index()))

  override def authorizationFailed(request: RequestHeader, user: User, authority: Option[Authority])(implicit context: ExecutionContext): Future[Result] = {
    Future.successful(Forbidden("no permission"))
  }

  def authorize(user: User, authority: Authority)(implicit ctx: ExecutionContext): Future[Boolean] = Future.successful {
    // TODO check role
    (user.roleId, authority) match {
      case _ => true
    }
  }

  // TODO cookie configuration
  /*override lazy val tokenAccessor = new CookieTokenAccessor(
    cookieSecureOption = play.api.Play.isProd(play.api.Play.current),
    cookieMaxAge = Some(sessionTimeoutInSeconds)
  )*/
}
