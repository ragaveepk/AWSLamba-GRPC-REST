import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.funsuite.AnyFunSuite
import com.typesafe.config.{Config, ConfigFactory}
import org.scalatest.matchers.should.Matchers._

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import com.cs441.ragavee.TimeStampCheck_REST
import java.net.URI

class TestCases extends AnyFlatSpec with Matchers{

  val config = ConfigFactory.load("application.conf")

  it should "Check Timestamp " in {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
    val startTime = LocalTime.parse(config.getString("TimeStamp" + "." + "Time"), formatter)

    val startTime_test = LocalTime .parse("18:43:00.251", formatter)

    assert(startTime ==  startTime_test)
  }

  it should "Check grpc endpoint " in {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
    val timeinterval = LocalTime.parse(config.getString("TimeStamp" + "." + "interval"), formatter)

    val timeinterval_test = LocalTime .parse("02:00:02.000", formatter)

    assert(timeinterval ==  timeinterval_test)
  }

  it should " Check if the append URL " in {
    val url = "https://ev9h0qfu21.execute-api.us-east-1.amazonaws.com/default/test"
    val host_url = "ev9h0qfu21.execute-api.us-east-1.amazonaws.com"
    val rest = new TimeStampCheck_REST(url , host_url)

    val uri = rest.appendUri(url,"12:43:00.251","01:01:00.001")
    val test_uri = new URI ("https://ev9h0qfu21.execute-api.us-east-1.amazonaws.com/default/test?12:43:00.251?01:01:00.001")
    assert(uri == test_uri)
  }
}

class Test extends AnyFunSuite {
  val config = ConfigFactory.load("application.conf")
  test("Test the correctness of name of the grpc url from application config") {
    val config = ConfigFactory.load("application.conf")
    config shouldBe a[Config]
    config.getString("TimeStamp" + "." + "grpc_url") shouldBe ("https://bsapo5p250.execute-api.us-east-1.amazonaws.com/default/LambaFunction1")
  }

  test("Test the correctness of name of the host url from application config") {
    val config = ConfigFactory.load("application.conf")
    config shouldBe a[Config]
    config.getString("TimeStamp" + "." + "host_url") shouldBe ("ev9h0qfu21.execute-api.us-east-1.amazonaws.com")
  }

}