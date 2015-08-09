package util.validation

import play.api.data.validation.{Valid, ValidationError, Invalid, Constraint}

object CustomConstraints extends CustomConstraints

trait CustomConstraints {

  private val emailRegex = """^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r
  def emailAddress: Constraint[String] = Constraint[String]("constraint.custom.email") { e =>
    if ((e == null) || (e.trim.isEmpty)) Valid // use nonEmpty or custom nonEmpty constraints
    else emailRegex.findFirstMatchIn(e)
      .map(_ => Valid)
      .getOrElse(Invalid(ValidationError("validation.error.invalid.email")))
  }
}
