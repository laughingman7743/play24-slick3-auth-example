package controller

import java.sql.Timestamp
import java.util.Date

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import play.api.test._
import play.filters.csrf.CSRF

import jp.t2v.lab.play2.auth.test.Helpers._
import org.junit.runner.RunWith
import org.specs2.mock.Mockito
import org.specs2.runner.JUnitRunner
import service.UserAccountService
import util.security.AuthConfigLike
import viewmodel.UserAccountViewModel

@RunWith(classOf[JUnitRunner])
class SampleControllerSpec extends PlaySpecification with Mockito {

  // sequential requirement (Error in custom provider, java.lang.IllegalStateException: The CacheManager has been shut down. It can no longer be used.)
  sequential

  "SampleController" should {

    val mockService = mock[UserAccountService]
    mockService.findByEmail("email") returns
      Future(Some(UserAccountViewModel(1, "email", "password", "passwordSalt", "firstName", "lastName",
        1, 0, true, true, false,
        Some(new Timestamp(new Date().getTime())), Some(new Timestamp(new Date().getTime)))))

    object authConfig extends AuthConfigLike {
      override val userAccountService = mockService
    }

    val controller = new SampleController(mockService)

    "dashboard page redirect login page if no login session" in new WithApplication {
      val result = controller.dashboard(FakeRequest())
      status(result) must equalTo(SEE_OTHER)
      redirectLocation(result) must beSome.which(_ == "/login")
    }

    "render dashboard page" in new WithApplication() {
      val result = controller.dashboard(FakeRequest()
        .withLoggedIn(authConfig)("email")
        .withSession("csrfToken" -> CSRF.SignedTokenProvider.generateToken))
      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "text/html")
    }

    "blank page redirect login page if no login session" in new WithApplication {
      val result = controller.blank(FakeRequest())
      status(result) must equalTo(SEE_OTHER)
      redirectLocation(result) must beSome.which(_ == "/login")
    }

    "render blank page" in new WithApplication() {
      val result = controller.blank(FakeRequest()
        .withLoggedIn(authConfig)("email")
        .withSession("csrfToken" -> CSRF.SignedTokenProvider.generateToken))
      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "text/html")
    }

    "buttons page redirect login page if no login session" in new WithApplication {
      val result = controller.buttons(FakeRequest())
      status(result) must equalTo(SEE_OTHER)
      redirectLocation(result) must beSome.which(_ == "/login")
    }

    "render buttons page" in new WithApplication() {
      val result = controller.buttons(FakeRequest()
        .withLoggedIn(authConfig)("email")
        .withSession("csrfToken" -> CSRF.SignedTokenProvider.generateToken))
      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "text/html")
    }

    "forms page redirect login page if no login session" in new WithApplication {
      val controller = new SampleController(mockService)
      val result = controller.forms(FakeRequest())
      status(result) must equalTo(SEE_OTHER)
      redirectLocation(result) must beSome.which(_ == "/login")
    }

    "render forms page" in new WithApplication() {
      val result = controller.forms(FakeRequest()
        .withLoggedIn(authConfig)("email")
        .withSession("csrfToken" -> CSRF.SignedTokenProvider.generateToken))
      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "text/html")
    }

    "grid page redirect login page if no login session" in new WithApplication {
      val controller = new SampleController(mockService)
      val result = controller.grid(FakeRequest())
      status(result) must equalTo(SEE_OTHER)
      redirectLocation(result) must beSome.which(_ == "/login")
    }

    "render grid page" in new WithApplication() {
      val result = controller.grid(FakeRequest()
        .withLoggedIn(authConfig)("email")
        .withSession("csrfToken" -> CSRF.SignedTokenProvider.generateToken))
      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "text/html")
    }

    "icons page redirect login page if no login session" in new WithApplication {
      val controller = new SampleController(mockService)
      val result = controller.icons(FakeRequest())
      status(result) must equalTo(SEE_OTHER)
      redirectLocation(result) must beSome.which(_ == "/login")
    }

    "render icons page" in new WithApplication() {
      val result = controller.icons(FakeRequest()
        .withLoggedIn(authConfig)("email")
        .withSession("csrfToken" -> CSRF.SignedTokenProvider.generateToken))
      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "text/html")
    }

    "notifications page redirect login page if no login session" in new WithApplication {
      val controller = new SampleController(mockService)
      val result = controller.notifications(FakeRequest())
      status(result) must equalTo(SEE_OTHER)
      redirectLocation(result) must beSome.which(_ == "/login")
    }

    "render notifications page" in new WithApplication() {
      val result = controller.notifications(FakeRequest()
        .withLoggedIn(authConfig)("email")
        .withSession("csrfToken" -> CSRF.SignedTokenProvider.generateToken))
      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "text/html")
    }

    "panels page redirect login page if no login session" in new WithApplication {
      val controller = new SampleController(mockService)
      val result = controller.panels(FakeRequest())
      status(result) must equalTo(SEE_OTHER)
      redirectLocation(result) must beSome.which(_ == "/login")
    }

    "render panels page" in new WithApplication() {
      val result = controller.panels(FakeRequest()
        .withLoggedIn(authConfig)("email")
        .withSession("csrfToken" -> CSRF.SignedTokenProvider.generateToken))
      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "text/html")
    }

    "tables page redirect login page if no login session" in new WithApplication {
      val controller = new SampleController(mockService)
      val result = controller.tables(FakeRequest())
      status(result) must equalTo(SEE_OTHER)
      redirectLocation(result) must beSome.which(_ == "/login")
    }

    "render tables page" in new WithApplication() {
      val result = controller.tables(FakeRequest()
        .withLoggedIn(authConfig)("email")
        .withSession("csrfToken" -> CSRF.SignedTokenProvider.generateToken))
      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "text/html")
    }

    "typography page redirect login page if no login session" in new WithApplication {
      val controller = new SampleController(mockService)
      val result = controller.typography(FakeRequest())
      status(result) must equalTo(SEE_OTHER)
      redirectLocation(result) must beSome.which(_ == "/login")
    }

    "render typography page" in new WithApplication() {
      val result = controller.typography(FakeRequest()
        .withLoggedIn(authConfig)("email")
        .withSession("csrfToken" -> CSRF.SignedTokenProvider.generateToken))
      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "text/html")
    }
  }
}
