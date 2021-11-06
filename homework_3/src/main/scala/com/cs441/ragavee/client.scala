package com.cs441.ragavee

import com.typesafe.config.{Config, ConfigFactory}
import com.ragavee.cs441.protobuf.time.{Inputtime}
import java.time.LocalTime

/***
 * Client Class which invokes the respective
 * GRPC and REST classes by passing the AWS Lambda function Endpoints as parameters
 * which is assigned in application.conf file and
 * For GRPC function checkTimeStampexsists, TimeStamp and dt the the time interval are passed
 * For REST fucntion highlightCode , lower and upper boudn time intervals are passed as inputs
 * Finally the result from both functions are printed
 */

object client {
  def main(args: Array[String]) {

    val conf: Config = ConfigFactory.load("application.conf")

 // Create objects for the GRPC and REST classes by passing their corresponding Endpoints
    val client_grpc = new TimeStampCheck_GRPC(conf.getString("TimeStamp" + "." + "grpc_url"))
    val client_rest = new TimeStampCheck_REST (
                          conf.getString("TimeStamp" + "." + "rest_url"),
                          conf.getString("TimeStamp" + "." + "host_url"))

// TimeStamp  to be checked and interval to be applied
    val Time = conf.getString("TimeStamp" + "." + "Time")
    val DT = conf.getString("TimeStamp" + "." + "interval")

    val dt  = LocalTime.parse(DT)
    val T =  LocalTime.parse(Time)

//  Calculates the intervals using LocalTime functionalities
    val lowerbound_time  = T.plusHours(dt.getHour()).plusMinutes(dt.getMinute()).plusNanos(dt.getNano())
    val upperbound_time  = T.minusHours(dt.getHour()).minusMinutes(dt.getMinute()).minusNanos(dt.getNano());

//// Call to GRPC function which requests the server to check the timestamp exists in the  Logfile
    val result_grpc =  client_grpc.checkTimeStampexsists(Inputtime(Time, DT))
    println(s"\nResult_GRPC = $result_grpc")

// Function call to REST services which requests the server to fetch the log messages from the specified
// time interval in MD5 format if exists else HTTP 400 is returned
    val result_rest = client_rest.highlightCode(lowerbound_time,upperbound_time)
    println(s"\nResult_REST = $result_rest")
  }
}
