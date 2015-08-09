package controller

import javax.inject.Inject

import play.api.mvc._

import jp.t2v.lab.play2.auth.AuthElement
import service.UserAccountService
import util.security.AuthConfigLike

class SampleController @Inject()(val userAccountService: UserAccountService)
  extends Controller with AuthElement with AuthConfigLike {

  def index = StackAction(AuthorityKey -> None) { implicit request =>
    Redirect(routes.SampleController.dashboard())
  }

  def dashboard = StackAction(AuthorityKey -> None) { implicit request =>
    Ok(view.html.dashboard())
  }

  def blank = StackAction(AuthorityKey -> None) { implicit request =>
    Ok(view.html.blank())
  }

  def buttons = StackAction(AuthorityKey -> None) { implicit request =>
    Ok(view.html.buttons())
  }

  def forms = StackAction(AuthorityKey -> None) { implicit request =>
    Ok(view.html.forms())
  }

  def grid = StackAction(AuthorityKey -> None) { implicit request =>
    Ok(view.html.grid())
  }

  def icons = StackAction(AuthorityKey -> None) { implicit request =>
    Ok(view.html.icons())
  }

  def notifications = StackAction(AuthorityKey -> None) { implicit request =>
    Ok(view.html.notifications())
  }

  def panels = StackAction(AuthorityKey -> None) { implicit request =>
    Ok(view.html.panels())
  }

  def tables = StackAction(AuthorityKey -> None) { implicit request =>
    Ok(view.html.tables())
  }

  def typography = StackAction(AuthorityKey -> None) { implicit request =>
    Ok(view.html.typography())
  }
}
