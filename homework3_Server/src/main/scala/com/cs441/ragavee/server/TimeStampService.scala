package com.cs441.ragavee.server


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.GetObjectRequest
import com.amazonaws.{AmazonServiceException, SdkClientException}
import com.ragavee.cs441.protobuf.time.{CheckTimeStampGrpc, Inputtime, Response}
import java.io.{BufferedReader,InputStream, InputStreamReader}
import java.time.LocalTime
import java.util.stream.{Collectors}
import scala.concurrent.Future
import java.nio.charset.StandardCharsets

/**
 * GRPC lambda function which exnteds the protobuf stub clsese and its methods to perform
 * file loading from S3 bucket and performs binary search for the specified timestamp.
 */

object TimeStampService extends CheckTimeStampGrpc.CheckTimeStamp {

  override def isTimeStampexists(request: Inputtime): Future[Response] = {

    print("-----------------Inside Loadfilefrom S3-----------------------")

    val clientRegion = "us-east-1"
    val bucketName = "lamba1"
    val key = "LogFileGenerator.2021-10-19.log"

    try {
//   Creates the s3 object using builder class
      val s3Client = AmazonS3ClientBuilder.standard.withRegion(clientRegion)
        .withCredentials(DefaultAWSCredentialsProviderChain.getInstance()).build

      System.out.println("Downloading an object")
//Loads the file from the S3 bucket using bukect and key (file) name
      val fullObject = s3Client.getObject(new GetObjectRequest(bucketName, key))

      print("Content-Type: " + fullObject.getObjectMetadata.getContentType)

//Reads the inputstream into bufferred reader object
      val logFile = new BufferedReader( new InputStreamReader(fullObject.getObjectContent , StandardCharsets.UTF_8)).lines().
                    collect(Collectors.joining("\n"))

//Invokes serach operation on the file
      val return_response = Search(logFile,request)

      fullObject.close()

      Future.successful(Response(return_response))
    }

    catch {
      case e: AmazonServiceException =>
        e.printStackTrace()
        Future.successful(Response(false))
      case e: SdkClientException =>
        e.printStackTrace()
        Future.successful(Response(false))
    }
  }

  private def Search(file : String,request : Inputtime): Boolean = {

    val splitlines = file.split("\n")
    val time = LocalTime.parse(request.time)
    val start = 1
    val index_1 = binarySearch_Recursive(splitlines,time,start,splitlines.length)

    if(index_1 != -1)
      return  true
    false
  }

  /**
   * Performs REcurisive Binary search and returns the index
   * @param list --Logfile
   * @param target - Timestamp to be searched
   * @param start - starting index
   * @param end - file length - end index
   * @return index of the timestamp in the file if found
   */
  def binarySearch_Recursive(list: Array[String], target: LocalTime,start: Int , end: Int  ): Int = {

    if (start > end) {
      val index = -1
      return index
    }

    val mid = start + (end - start + 1) / 2
    val line = list(mid).split(" ")
    val localtime = line(0)
// compares the localtime with target time using LocalTime parser and based ont he output, mid anf start and end are calculated.
    val compared_value = LocalTime.parse(localtime).compareTo(target)
    if (compared_value == 0) {
      mid+1
    }
    else if (compared_value > 0)
      binarySearch_Recursive(list, target,start, mid - 1)
    else
      binarySearch_Recursive(list, target,mid + 1, end)

  }

}
