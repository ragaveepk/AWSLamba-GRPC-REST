## CS 441  Homework 3

Ragavee Poosappagounder Kandavel

## Introduction

The project is about creating a  GRPC and REST service - deployed in AWS Lambda which performs search on Logfile to
return log messages in the specified time interval when client call is made.


AWS Deployment video -[https://youtu.be/v-6qXG-t8iE](https://youtu.be/v-6qXG-t8iE)

##Documentation
After Cloning the project , please find the Scaladoc documentation in docs folder.

## Setup Requirements
- Create AWS Account as root user and create IAM role which has Administrator policy.
- Install SBT
## How to Install

Please follow the following steps to run the project
- Use IntelliJ IDEA with the Scala plugin installed along with sbt.
- Enter the following URL and click “Clone”: https://github.com/ragaveepk/cs441_homework2.git

- When prompted click “Yes” in the dialog box

- The SBT import screen will appear, so proceed with the default options and click on “OK”

- Go to homework3 folder- src/main/scala/ and open the client class.

- Open the terminal nad navigate to homework3 path and run sbt clean  and sbt compile in the terminal

- Open the terminal and navigate to homework3_Service folder and run sbt clean compile assembly command.

- After successful compilation, enter 'sbt run' in the  client terminal

## Steps to  create Jar File for GRPC - Server

A Jar file needs to be created for the Homework3_Server project which need to be uploaded to AWS lambda function to act as server for GRPC
Please follow these steps:

1) Enter git clone https://github.com/ragaveepk/homework3.git

2) Run the following command: "sbt clean compile assembly"

3) A Jar will be created under the target/scala-2.13/ folder with the following name: homework3_Server.jar

4) Upload the Jar file into lambda function.

5) Next, open a Terminal navigate to client project and enter 'sbt run' to get the output.

## Run the TEST file
- Open Terminal in your Intellij and type 'sbt  clean compile test' and run

## Project Structure
- **homework3_Server** 
  - This project structure consists of  lambda function using GRPC and  REST.
    - Server_GRPC.scala -- lambda function which receives the request from the client and decodes the message and converts according
                          to the protobuf Input format  and call the TimeStampService.scala function and awaits for the result and encode the result 
                          to bytearray before sending back the response to client.
  - Under Python folder -- RESTLambda.py - Lambda function receives the client call made from rest API which uses Finagle framework and 
                           sends the result in MD5 hash format if the message is presesnt else HTTP 400 response is returned.
  - time.proto -- This is protobuf file which defines the service , input and output format which can be understood by both server and client.
  
- **homework_3  - Client side**
- This project consists of the client side implementation where the request is made using GRPC and REST services.
- time.proto -- This is protobuf file which defines the service , input and output format which can be understood by both server and client.
- client.scala - Main function where the call made using GRPC and REST services through their respective functions 
                  by passing the Timestamp values, API endpoints to trigger the lambda functions.
- TimeStampCheck_GRPC.scala -- This class makes request to service using GRPC by passing the expression as bytearray.
                               Once it returns the response as bytes it converts to response format as mentioned in protobuf file
- TimeStampCheck_REST.scala -- This class used Twitter Finagle to make rest calls to lambda function by appending 
                                the inputs to the endpoint url.

## Client - homework_3  project
The project's starting point is `client` class which provides the interface where the ouput of b=GRPC and REST API are  executed.
Each service as separate classes which is created by passing API Gateway endpoints.
Using the object created for the classes their functionalities are invoked and the result are returned.


## Service - homework3_Service
In the service part, request from the client is received and processed using lambda function deployed in AWS 
which takes the input file from S3 which is automatically updated from EC2 using batch process added in the EC2 instance. 
'TimeStampService_GRPC' - lambda function where the request is handles from client, and it invokes the  
`TimeStampService` where the Log files loaded from the S3 bucket, and it passed to the binary search function.
In Binary Search, line count of the file and the file sent and using recursion, the mid of the file is read to check
if the given timestamp exists in the Logfile.Binary Search increases the search efficiency instead of searching
line by line in the file. Finally, boolean value is returned from server side in the proto return type  and encoded 
using Base64 Technique. 
`RESTLambda.py` - Lambda function which will be invoked when REST API makes  a call from client where the Log file is loaded
from S3 bucket using boto library and the inputs- upper bound and lower bound time interval is fetched from the event 
and each is passed to modified binary search which returns the closest index of the timestamp if that exact timestamp
doesn't exist. Based on the bounds list of log messages which matches injected string pattern is converted into MD5 format 
and return as json dumps response. in case the list is empty then the HTTP 404 message is returned as response.

This project uses [ScalaPB](https://scalapb.github.io/) to generate the stubs for the `CheckTimeStamp` service and the related protobuf messages.
These stubs are generated automatically when  this project or any of the dependent projects are compiled using `sbt <project-name>/compile`.


## References
- [Twitter Finagle](https://twitter.github.io/finagle/guide/Quickstart.html)
- [Loading file content from AWS S3 Bucket](https://docs.aws.amazon.com/code-samples/latest/catalog/java-s3-src-main-java-aws-example-s3-GetObject2.java.html)  
