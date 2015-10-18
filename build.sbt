import com.typesafe.config.ConfigFactory

name := """play24-slick3-auth-example"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  /*jdbc,*/ // use slick
  /*evolutions,*/ // use slick evolutions
  cache,
  filters,
  ws,
  "com.typesafe.play" %% "play-slick" % "1.1.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.1.0",
  "com.typesafe.slick" % "slick-codegen_2.11" % "3.1.0",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "jp.t2v" %% "play2-auth" % "0.14.1",
  "commons-codec" % "commons-codec" % "1.10",
  specs2 % Test,
  "jp.t2v" %% "play2-auth-test" % "0.14.1" % "test",
  "com.h2database" % "h2" % "1.4.187" % "test"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

// Slick code generator
slickCodeGen <<= slickCodeGenTask // register sbt command
//(compile in Compile) <<= (compile in Compile) dependsOn (slickCodeGenTask) // register automatic code generation on compile
lazy val config = ConfigFactory.parseFile(new File("./conf/application.conf"))
lazy val slickCodeGen = taskKey[Seq[File]]("slick-codegen")
lazy val slickCodeGenTask = (sourceManaged, dependencyClasspath in Compile, runner in Compile, streams) map { (dir, cp, r, s) =>
  val slickDriver = config.getString("slick.dbs.default.driver").init
  val jdbcDriver = config.getString("slick.dbs.default.db.driver")
  val url = config.getString("slick.dbs.default.db.url")
  val username = config.getString("slick.dbs.default.db.username")
  val password = config.getString("slick.dbs.default.db.password")
  val outputDir = "app/"
  val pkg = "model"
  toError(r.run("slick.codegen.SourceCodeGenerator", cp.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg), s.log))
  val fname = outputDir + "/Tables.scala"
  Seq(file(fname))
}
