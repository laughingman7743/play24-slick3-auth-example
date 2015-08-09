package controller

import javax.inject.Inject

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import play.api.Play.current
import play.api.data.Forms._
import play.api.data._
import play.api.i18n.Messages
import play.api.i18n.Messages.Implicits._
import play.api.mvc._
import play.filters.csrf.CSRFCheck

import form.LoginForm
import jp.t2v.lab.play2.auth.LoginLogout
import service.UserAccountService
import util.security.AuthConfigLike
import util.validation._

class LoginLogoutController @Inject()(val userAccountService: UserAccountService)
  extends Controller with LoginLogout with AuthConfigLike {

  import LoginLogoutController._

  def index() = Action { implicit request =>
    Ok(view.html.login(loginForm))
  }

  def login() = CSRFCheck {
    Action.async { implicit request =>
      loginForm.bindFromRequest.fold(
        formWithErrors => {
          Future.successful(BadRequest(view.html.login(formWithErrors)))
        },
        form => {
          userAccountService.authenticate(form).flatMap {
            user => user match {
              case Some(user) =>
                gotoLoginSucceeded(user.email)
              case _ =>
                Future.successful(Unauthorized(view.html.login(loginForm.fill(form)
                  .withGlobalError("message", Messages("login.fail")))))
            }
          }
        }
      )
    }
  }

  def logout() = CSRFCheck {
    Action.async { implicit request =>
      gotoLogoutSucceeded
    }
  }
}

object LoginLogoutController {
  val loginForm = Form(
    mapping(
      "email" -> text.verifying(Messages("validation.error.required"), {!_.isEmpty})
        .verifying(CustomConstraints.emailAddress),
      "password" -> text.verifying(Messages("validation.error.required"), {!_.isEmpty})
    )(LoginForm.apply)(LoginForm.unapply)
  )
}
