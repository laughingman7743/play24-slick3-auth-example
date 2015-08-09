package viewmodel

import model.Tables.UserAccountRow

case class UserAccountViewModel(id: Long,
                                email: String,
                                password: String,
                                passwordSalt: String,
                                firstName: String,
                                lastName: String,
                                roleId: Long,
                                failureCount: Int = 0,
                                isAvailable: Boolean = true,
                                isDeleted: Boolean = false,
                                isPasswordChange: Boolean = false,
                                createAt: Option[java.sql.Timestamp],
                                updateAt: Option[java.sql.Timestamp]) {
  def this(row: UserAccountRow) = this(
    row.id,
    row.email,
    row.password,
    row.passwordSalt,
    row.firstName,
    row.lastName,
    row.roleId,
    row.failureCount,
    row.isAvailable,
    row.isDeleted,
    row.isPasswordChange,
    row.createAt,
    row.updateAt
  )
}
