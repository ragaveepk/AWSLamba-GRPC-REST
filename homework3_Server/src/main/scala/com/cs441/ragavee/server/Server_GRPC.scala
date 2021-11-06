package com.cs441.ragavee.server


import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import com.amazonaws.services.lambda.runtime.events.{APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent}
import com.ragavee.cs441.protobuf.time.Inputtime
import java.util.Base64
import scala.collection.JavaConverters._
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

/**
 * This is the Lambda function which handle the incoming client requests and returns the response
 */
class Server_GRPC extends RequestHandler[APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent] {

  override def handleRequest(input: APIGatewayProxyRequestEvent, context: Context): APIGatewayProxyResponseEvent = {

    val logger = context.getLogger
    logger.log("Request Body:\n" + input.toString)

    val message = if (input.getIsBase64Encoded)
      Base64.getDecoder.decode(input.getBody.getBytes)
    else
      input.getBody.getBytes

    logger.log(s"message: (${message.mkString(", ")})")

    val Time_input = Inputtime.parseFrom(message)
    logger.log(s"Expression: $Time_input")

    val result = Await.result(TimeStampService.isTimeStampexists(Time_input),  30 seconds)
    logger.log(s"Result1--: $result")


    val output = Base64.getEncoder.encodeToString(result.toByteArray)
    logger.log(s"Output: $result.toByteArray")

//returns the API gateway response event along with status code,header and output in encoded format
    new APIGatewayProxyResponseEvent()
      .withStatusCode(200)
      .withHeaders(Map("Content-Type" -> "application/grpc+proto").asJava)
      .withIsBase64Encoded(true)
      .withBody(output)
  }
}
