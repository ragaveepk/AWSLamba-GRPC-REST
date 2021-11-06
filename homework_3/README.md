## CS 441  Homework 3

## Introduction

The project is about creating a  GRPC and REST service -deployed in AWS Lambda which performs search on Logfile to return log messages in the specified time interval when client call is made.


AWS Deployment video -[]

##Documentation
After Cloning the project , please find the Scaladoc documentation in docs folder.

## Setup Requirements
- Create AWS Account as root user and create IAM role which has Administrator policy.
- 
## How to Install

Please follow the following steps to run the project
- Use IntelliJ IDEA with the Scala plugin installed along with sbt.
- Enter the following URL and click “Clone”: https://github.com/ragaveepk/cs441_homework2.git

- When prompted click “Yes” in the dialog box

- The SBT import screen will appear, so proceed with the default options and click on “OK”

- Go to src/main/scala/ and open the client class.

- run sbt clean  and sbt compile in the terminal

- After successful compilation, enter 'sbt run' in the terminal

## Steps to  create Jar File for GRPC - Server

A Jar file needs to be created for the Homework3_Server project which need to be uploaded to AWS lambda function to act as server for GRPC
Please follow these steps:

1) Enter git clone https://github.com/ragaveepk/homework3.git

2) Run the following command: "sbt clean compile assembly"

3) A Jar will be created under the target/scala-2.13/ folder with the following name: homework3_Server.jar

## Run the TEST file
- Open Terminal in your Intellij and type 'sbt  clean compile test' and run

## Project Structure
-
## Client 

## Server 



