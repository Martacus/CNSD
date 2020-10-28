from __future__ import print_function
import json
import boto3 
import base64

def lambda_handler(event, context):
    s3 = boto3.resource('s3')
    sns = boto3.client('sns')
    
    for record in event['Records']:
        print (f"{record} {type(record)}")
        body=record['body']
        print (f"{body} {type(body)}")
        extmessage = json.loads(body)
        print (f"{extmessage} {type(extmessage)}")
        bucketname = extmessage[1]["s3BucketName"]
        itemname = extmessage[1]["s3Key"]
        print (f"Extended message body at S3 {bucketname}:{itemname}")
        srcObj = s3.Object(bucketname, itemname)
        base64_img = srcObj.get()['Body'].read()
        decoded_image_data = base64.decodebytes(base64_img)
        print (f"Retrieved the message body")
        
        destBucketname="martswabbucket"
        destItemname=itemname+".png"
        destObj = s3.Object(destBucketname, destItemname)
        destObj.put(Body=decoded_image_data, ACL="public-read")
        objectUrl = f"https://{destBucketname}.s3.amazonaws.com/{destItemname}"
        print (f"Image processed and stored at {objectUrl}" )
        
        srcObj.delete();
    
    # TODO implement
    return {
        'statusCode': 200,
        'body': json.dumps('Hello from Lambda!')
    } 
