package repository

import javax.inject.Inject

import scala.concurrent.Future

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}

import com.google.inject.ImplementedBy
import model.Tables
import model.Tables.UserAccountRow
import slick.driver.JdbcProfile

@ImplementedBy(classOf[UserAccountRepository])
trait UserAccountRepositoryLike
  extends HasDatabaseConfigProvider[JdbcProfile] {
  def findByEmail(email: String): Future[Option[UserAccountRow]]
}

class UserAccountRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends UserAccountRepositoryLike {
  import driver.api._

  def findByEmail(email: String): Future[Option[UserAccountRow]] = {
    db.run(Tables.UserAccount.filter(_.email === email.bind).result.headOption)
  }
}
