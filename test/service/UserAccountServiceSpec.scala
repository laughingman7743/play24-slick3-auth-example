package service

import java.sql.Timestamp
import java.util.Date

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

import play.api.test.PlaySpecification

import form.LoginForm
import model.Tables.UserAccountRow
import org.junit.runner.RunWith
import org.specs2.mock.Mockito
import org.specs2.runner.JUnitRunner
import repository.UserAccountRepository
import viewmodel.UserAccountViewModel

@RunWith(classOf[JUnitRunner])
class UserAccountServiceSpec extends PlaySpecification with Mockito {

  "UserAccountService" should {

    val mockRepository = mock[UserAccountRepository]
    mockRepository.findByEmail("hoge.fuga@foo.bar") returns
      Future(Some(UserAccountRow(1, "hoge.fuga@foo.bar",
        "83627ffa5ab48ecae9d0aafe55940d3d19040319e4b26a94f92d56b6734962e1", // password
        "f911422a271cf8a986b8a19d1e948c4d8ac4050bf090dd00d0d3e982c57647b8",
        "hoge", "fuga", 1, 0, true, true, false,
        Some(new Timestamp(new Date().getTime())), Some(new Timestamp(new Date().getTime)))))
    mockRepository.findByEmail("fuga.hoge@bar.foo") returns
      Future(None)

    val service = new UserAccountService(mockRepository)

    "find user by email if exist" in {
      val user = Await.result(service.findByEmail("hoge.fuga@foo.bar"), 1.seconds)
      user must beSome[UserAccountViewModel]
      user.get.email must equalTo("hoge.fuga@foo.bar")
    }

    "find user by email if not exist" in {
      val user = Await.result(service.findByEmail("fuga.hoge@bar.foo"), 1.seconds)
      user must beNone
    }

    "authenticate success" in {
      val user = Await.result(service.authenticate(LoginForm("hoge.fuga@foo.bar", "password")), 1.seconds)
      user must beSome[UserAccountViewModel]
      user.get.email must equalTo("hoge.fuga@foo.bar")
      user.get.password must equalTo("83627ffa5ab48ecae9d0aafe55940d3d19040319e4b26a94f92d56b6734962e1")
      user.get.passwordSalt must equalTo("f911422a271cf8a986b8a19d1e948c4d8ac4050bf090dd00d0d3e982c57647b8")
    }

    "authenticate fail" in {
      val user = Await.result(service.authenticate(LoginForm("hoge.fuga@foo.bar", "aaaaaaaa")), 10.seconds)
      user must beNone
    }
  }
}
