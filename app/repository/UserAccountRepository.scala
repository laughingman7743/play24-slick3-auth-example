package repository

import javax.inject.Inject

import scala.concurrent.Future

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}

import model.Tables
import model.Tables.UserAccountRow
import slick.driver.JdbcProfile

class UserAccountRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  def findByEmail(email: String): Future[Option[UserAccountRow]] = {
    db.run(Tables.UserAccount.filter(_.email === email).result.headOption)
  }
}
