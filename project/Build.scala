import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "Crawalyzer"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "uk.co.panaxiom" %% "play-jongo" % "0.4",
    "org.jsoup" % "jsoup" % "1.7.2"        
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers += Resolver.url("My GitHub Play Repository", url("http://alexanderjarvis.github.com/releases/"))(Resolver.ivyStylePatterns)      
  )
  
}
