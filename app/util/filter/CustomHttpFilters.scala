package util.filter

import javax.inject.Inject

import play.api.http.HttpFilters
import play.filters.csrf.CSRFFilter

class CustomHttpFilters @Inject()(log: LoggingFilter, csrf: CSRFFilter) extends HttpFilters{

  override val filters = Seq(log, csrf)

}
