lazy val fun = (project in file("."))
  .settings(
    name := "fun",
    organization := "com.test",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.8",
    libraryDependencies ++= {
      val akkaVersion = "2.5.20"
      val akkaHttpVersion = "10.1.6"
      val circeVersion = "0.9.3"
      val catsVersion = "1.6.0"
      Seq(
        "com.typesafe.akka" %% "akka-actor" % akkaVersion,
        "com.typesafe.akka" %% "akka-stream" % akkaVersion,

        "org.typelevel" %% "cats-effect" % "1.3.0",
//      "org.typelevel" %% "cats-effect" % "2.0.0",
        //"org.typelevel" %% "cats-core" % "2.1.1",
        //"com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
        "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
        "io.vavr" % "vavr" % "1.0.0-alpha-3"
      ).map(_ withSources()) ++ Seq(
        "io.circe" %% "circe-core",
        "io.circe" %% "circe-generic",
        "io.circe" %% "circe-generic-extras",
        "io.circe" %% "circe-java8",
        "io.circe" %% "circe-optics",
        "io.circe" %% "circe-parser"
      ).map(_ % circeVersion).map(_ withSources())
    }
  )

scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps",
  "-Xlog-reflective-calls", "-Xlint",
  "-Ypartial-unification" // for cats
)

javacOptions in Compile ++= Seq("-Xlint:unchecked", "-Xlint:deprecation", "-encoding", "UTF-8")

javaOptions in run ++= Seq("-Xms128m", "-Xmx1024m", "-Djava.library.path=./target/native")
