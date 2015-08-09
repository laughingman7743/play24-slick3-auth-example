package model
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.PostgresDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema = UserAccount.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table UserAccount
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param email Database column email SqlType(varchar), Length(255,true)
   *  @param password Database column password SqlType(varchar), Length(64,true)
   *  @param passwordSalt Database column password_salt SqlType(varchar), Length(64,true)
   *  @param firstName Database column first_name SqlType(varchar), Length(50,true)
   *  @param lastName Database column last_name SqlType(varchar), Length(50,true)
   *  @param roleId Database column role_id SqlType(int8)
   *  @param failureCount Database column failure_count SqlType(int4), Default(0)
   *  @param isAvailable Database column is_available SqlType(bool), Default(true)
   *  @param isDeleted Database column is_deleted SqlType(bool), Default(false)
   *  @param isPasswordChange Database column is_password_change SqlType(bool), Default(false)
   *  @param createAt Database column create_at SqlType(timestamptz)
   *  @param updateAt Database column update_at SqlType(timestamptz) */
  case class UserAccountRow(id: Long, email: String, password: String, passwordSalt: String, firstName: String, lastName: String, roleId: Long, failureCount: Int = 0, isAvailable: Boolean = true, isDeleted: Boolean = false, isPasswordChange: Boolean = false, createAt: Option[java.sql.Timestamp], updateAt: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching UserAccountRow objects using plain SQL queries */
  implicit def GetResultUserAccountRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Int], e3: GR[Boolean], e4: GR[Option[java.sql.Timestamp]]): GR[UserAccountRow] = GR{
    prs => import prs._
    UserAccountRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String], <<[String], <<[Long], <<[Int], <<[Boolean], <<[Boolean], <<[Boolean], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table user_account. Objects of this class serve as prototypes for rows in queries. */
  class UserAccount(_tableTag: Tag) extends Table[UserAccountRow](_tableTag, "user_account") {
    def * = (id, email, password, passwordSalt, firstName, lastName, roleId, failureCount, isAvailable, isDeleted, isPasswordChange, createAt, updateAt) <> (UserAccountRow.tupled, UserAccountRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(email), Rep.Some(password), Rep.Some(passwordSalt), Rep.Some(firstName), Rep.Some(lastName), Rep.Some(roleId), Rep.Some(failureCount), Rep.Some(isAvailable), Rep.Some(isDeleted), Rep.Some(isPasswordChange), createAt, updateAt).shaped.<>({r=>import r._; _1.map(_=> UserAccountRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12, _13)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column email SqlType(varchar), Length(255,true) */
    val email: Rep[String] = column[String]("email", O.Length(255,varying=true))
    /** Database column password SqlType(varchar), Length(64,true) */
    val password: Rep[String] = column[String]("password", O.Length(64,varying=true))
    /** Database column password_salt SqlType(varchar), Length(64,true) */
    val passwordSalt: Rep[String] = column[String]("password_salt", O.Length(64,varying=true))
    /** Database column first_name SqlType(varchar), Length(50,true) */
    val firstName: Rep[String] = column[String]("first_name", O.Length(50,varying=true))
    /** Database column last_name SqlType(varchar), Length(50,true) */
    val lastName: Rep[String] = column[String]("last_name", O.Length(50,varying=true))
    /** Database column role_id SqlType(int8) */
    val roleId: Rep[Long] = column[Long]("role_id")
    /** Database column failure_count SqlType(int4), Default(0) */
    val failureCount: Rep[Int] = column[Int]("failure_count", O.Default(0))
    /** Database column is_available SqlType(bool), Default(true) */
    val isAvailable: Rep[Boolean] = column[Boolean]("is_available", O.Default(true))
    /** Database column is_deleted SqlType(bool), Default(false) */
    val isDeleted: Rep[Boolean] = column[Boolean]("is_deleted", O.Default(false))
    /** Database column is_password_change SqlType(bool), Default(false) */
    val isPasswordChange: Rep[Boolean] = column[Boolean]("is_password_change", O.Default(false))
    /** Database column create_at SqlType(timestamptz) */
    val createAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("create_at")
    /** Database column update_at SqlType(timestamptz) */
    val updateAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("update_at")

    /** Uniqueness Index over (email) (database name uq_user_account_email) */
    val index1 = index("uq_user_account_email", email, unique=true)
  }
  /** Collection-like TableQuery object for table UserAccount */
  lazy val UserAccount = new TableQuery(tag => new UserAccount(tag))
}
