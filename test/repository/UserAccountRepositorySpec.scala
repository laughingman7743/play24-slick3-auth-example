package repository

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

import play.api.Application
import play.api.test.{FakeApplication, PlaySpecification, WithApplication}

import model.Tables.UserAccountRow
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class UserAccountRepositorySpec extends PlaySpecification {

  "UserAccountRepository" should {
    "work as expected" in new WithApplication(FakeApplication(additionalConfiguration = inMemoryDatabase("test"))) {
      val app2repo = Application.instanceCache[UserAccountRepository]
      val repository = app2repo(app)

      val existUser = Await.result(repository.findByEmail("hoge.fuga@foo.bar"), 1.seconds)
      existUser must beSome[UserAccountRow]
      existUser.get.email must equalTo("hoge.fuga@foo.bar")

      val notExistUser = Await.result(repository.findByEmail("fuga.hoge@bar.foo"), 1.seconds)
      notExistUser must beNone
    }
  }
}
