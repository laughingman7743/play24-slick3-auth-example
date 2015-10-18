package service

import java.util.UUID
import javax.inject.Inject

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import com.google.inject.ImplementedBy
import form.LoginForm
import org.apache.commons.codec.digest.DigestUtils
import repository.UserAccountRepositoryLike
import viewmodel.UserAccountViewModel

@ImplementedBy(classOf[UserAccountService])
trait UserAccountServiceLike {
  def findByEmail(email: String): Future[Option[UserAccountViewModel]]

  def authenticate(form: LoginForm): Future[Option[UserAccountViewModel]]
}

class UserAccountService @Inject()(val userAccountRepository: UserAccountRepositoryLike)
  extends UserAccountServiceLike {
  import UserAccountService._

  def findByEmail(email: String): Future[Option[UserAccountViewModel]] = {
    userAccountRepository.findByEmail(email).map( userOpt =>
      userOpt.flatMap { user =>
        Some(new UserAccountViewModel(user))
      }
    )
  }

  def authenticate(form: LoginForm): Future[Option[UserAccountViewModel]] = {
    userAccountRepository.findByEmail(form.email).map( userOpt =>
      userOpt.flatMap { user =>
        if (hashAndStretch(form.password, user.passwordSalt, STRETCH_LOOP_COUNT) == user.password)
          Some(new UserAccountViewModel(user))
        else
          None
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
