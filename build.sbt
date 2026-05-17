ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.7"

val javaFXVersion = "21"
val javaFXModules = Seq("controls", "fxml")
val os = System.getProperty("os.name").toLowerCase match {
  case n if n.contains("win")   => "win"
  case n if n.contains("mac")   => "mac"
  case _                        => "linux"
}

lazy val root = (project in file("."))
  .settings(
    name := "konane-ppm",
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-parallel-collections" % "1.2.0"
    ) ++ javaFXModules.map(m =>
      "org.openjfx" % s"javafx-$m" % javaFXVersion classifier os
    )
  )