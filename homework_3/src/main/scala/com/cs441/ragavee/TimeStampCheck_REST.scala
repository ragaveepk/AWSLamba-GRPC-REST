package com.cs441.ragavee

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http
import com.twitter.util.{Await}
import java.time.LocalTime
import scala.language.postfixOps
import java.net.{URI, URISyntaxException}

/**
 * Client program for invoking the RESTLambda function on AWS.
 * @param url  URL for the API Gateway that triggers the lambda function.
 * @param host_url  host url for host nanme verification.
 */

class TimeStampCheck_REST(url : String, host_url: String)  {
  def highlightCode(uB_time: LocalTime , lb_time : LocalTime): String = {

    val ub = uB_time.toString
    val lb = lb_time.toString
    val uri = appendUri(url,lb,ub)

    //Makes POST request using REST -finagle framework

    val client: Service[http.Request, http.Response] = Http.client
      .withTransport.tls(host_url).newService(s"$host_url:443")
    val result = Await.result(client(http.Request(uri.toString)))

    result.contentString
    }


  /**
   *
   * @param uri - endpoint url
   * @param time1 - upper bound time interval
   * @param time2 - lowerbound time interval
   * @throws
   * @return  -returns the input appended url
   */
  @throws[URISyntaxException]
  def appendUri(uri: String, time1: String, time2: String): URI = {
    val oldUri = new URI(uri)
    var newQuery = oldUri.getQuery
    if (newQuery == null) newQuery = time1 + "?" + time2
    else newQuery += "?" + time1 + "?" + time2

    new URI(oldUri.getScheme, oldUri.getAuthority, oldUri.getPath, newQuery, oldUri.getFragment)
  }

  }