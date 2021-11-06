package com.cs441.ragavee
import com.typesafe.scalalogging.LazyLogging
import com.ragavee.cs441.protobuf.time.{Inputtime,Response}
import scalaj.http.Http

/**
 * Client program for invoking the Server_GRPC lambda function on AWS that uses Protobuf as the data
 * interchange format.
 * @param url URL for the API Gateway that triggers the lambda function.
 */
class TimeStampCheck_GRPC(private var url: String)  {

  def checkTimeStampexsists(expression: Inputtime): Boolean = {

    // Make POST request to Server_GRPC API Gateway
    val request = Http(url)
      .headers(Map(
        "Content-Type" -> "application/grpc+proto",
        "Accept" -> "application/grpc+proto"
      ))
      .timeout(connTimeoutMs = 20000, readTimeoutMs = 10000)
      .postData(expression.toByteArray)

    // Parse response from API to protobuf Response object
    val responseMessage = Response.parseFrom( request.asBytes.body)
    responseMessage.result
  }
}