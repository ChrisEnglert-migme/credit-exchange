object Versions {
  val scala = sys.props.get("cxb.scalaVersion").getOrElse("2.11.7")
  val scalatestVersion = sys.props.get("cxb.scalatestVersion").getOrElse("2.2.5")

  val akkaVersion = sys.props.get("cxb.akkaVersion").getOrElse("2.3.13")
  val sprayVersion = sys.props.get("cxb.sprayVersion").getOrElse("1.3.3")
  val sprayJsonVersion = sys.props.get("cxb.sprayVersion").getOrElse("1.3.2")

  val slf4jVersion = sys.props.get("cxb.slf4jVersion").getOrElse("1.7.12")
  val log4j2Version = sys.props.get("cxb.log4j2Version").getOrElse("2.3")
  val testNgVersion = sys.props.get("cxb.testNgVersion").getOrElse("6.9.6")
  val typesafeConfigVersion = sys.props.get("cxb.typesafeConfigVersion").getOrElse("1.3.0")


  val fissionVersion = sys.props.get("cxb.fissionVersion").getOrElse("1.0.20")
}