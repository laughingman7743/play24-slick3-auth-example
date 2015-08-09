package service

import java.util.UUID
import javax.inject.Inject

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import form.LoginForm
import org.apache.commons.codec.digest.DigestUtils
import repository.UserAccountRepository
import viewmodel.UserAccountViewModel

class UserAccountService @Inject()(val userAccountRepository: UserAccountRepository) {
  import UserAccountService._

  def findByEmail(email: String): Future[Option[UserAccountViewModel]] = {
    userAccountRepository.findByEmail(email).map(
      user => user match {
        case Some(user) => Some(new UserAccountViewModel(user))
        case _ => None
      }
    )
  }

  def authenticate(form: LoginForm): Future[Option[UserAccountViewModel]] = {
    userAccountRepository.findByEmail(form.email).map(
      user => user match {
        case Some(user) => {
          if (hashAndStretch(form.password, user.passwordSalt, STRETCH_LOOP_COUNT) == user.password)
            Some(new UserAccountViewModel(user))
          else
            None
        }
        case _ => None
      }
    )
  }
}

object UserAccountService {

  val STRETCH_LOOP_COUNT = 1000

  def hashAndStretch(plain: String, salt: String, loopCnt: Int): String = {
    var hashed: String = ""
    (1 to STRETCH_LOOP_COUNT).foreach(i =>
      hashed = DigestUtils.sha256Hex(hashed + plain + salt)
    )
    hashed
  }

  def createPasswordSalt(): String = {
    DigestUtils.sha256Hex(UUID.randomUUID().toString())
  }
}
