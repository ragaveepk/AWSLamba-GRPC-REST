# import json
# import re
# import hashlib
# import boto3
# import sys
# import os
# import linecache
# import base64
# import time
# import datetime
# from json import dumps, loads, JSONEncoder, JSONDecoder
#
# # Lambda function which handles REST client calls which loads file from S3 bucket
# # and performs modified binary search operation for th specified time interval range
# # and returns the list of MD% message format.
#
# def lambda_handler(event, context):
#
#
#     BUCKET_NAME = 'logfilegeneratorbucket'
#     KEY = 'LogFileGenerator.2021-10-19.log'
#     s3 = boto3.resource('s3')
#     bucket = s3.Bucket('logfilegeneratorbucket')
#     pattern = "^[A-Za-z0-9]+"
#     matched = []
#     hash_list= []
#     statusCode = 400
#
#     for obj in bucket.objects.all():
#         key = obj.key
#         body = obj.get()['Body'].read()
#         decode_str =  body.decode('UTF-8')
#         split_lines = decode_str.split("\n")
#
#     inputlist = event['rawQueryString']
#     print(inputlist)
#     time = inputlist.split("?")
#
#
#     upperbound_interval = BinarySearch(1,len(split_lines)-1,split_lines,1,time)
#     lowerbound_interval = BinarySearch(1,len(split_lines)-1,split_lines,2,time)
#
#     print("upb",upperbound_interval)
#     print("lb",lowerbound_interval)
#     single_line_list= []
#     single_line_list = split_lines[upperbound_interval:lowerbound_interval]
#
#     log_msg  = [i.split(" ")[(len(i.split(" "))-1)] for i in single_line_list]
#
#     for j in log_msg:
#         if bool(re.match(pattern, j)) :
#             matched.append(j)
#
#
#     for i in matched:
#         h = hashlib.md5(i.encode())
#         hash_list.append(h.hexdigest())
#
#     if (len(hash_list) > 0):
#         statusCode =200
#
#     return {
#         'statusCode': statusCode,
#         'body': json.dumps(hash_list)
#     }
#
#
#
#
# def BinarySearch(start,end,split_lines,flag,time):
#
#     time_format = '%H:%M:%S'
#     index = -1
#
#     while(start < end):
#
#         mid = (start + end ) // 2
#         log_list = split_lines[mid -1].split(" ")
#
#         time1 =log_list[0].split(".")[0]
#         mesg = log_list[len(log_list)-1]
#
#         up = time [0].split(".")[0]
#         lb = time [1].split(".")[0]
#
#         upperbound_interval = datetime.datetime.strptime(up,time_format)
#         lowerbound_interval =  datetime.datetime.strptime(lb,time_format)
#         element = datetime.datetime.strptime(time1,time_format)
#
#         if ( element  < lowerbound_interval) and (element >  upperbound_interval) :
#             index =  mid
#             if flag == 1 :
#                 end = mid -1
#             elif flag == 2 :
#                 start= mid +1
#         elif element > lowerbound_interval :
#             end = mid -1
#         elif element < upperbound_interval :
#             start = mid + 1
#
#     return index
