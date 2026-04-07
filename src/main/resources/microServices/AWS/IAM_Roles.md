# Amazon Resource Name
**arn:aws:iam::{account id}:user/arntestuser**\

**arn** = Amazon Resource Name \
**aws** = AWS Regions \
**iam** = Identifies the namespace it is associated with. It could be S3 or RDS etc.. \
:**:** = Here it is empty but it contains region name like us-east-2. \
<pre>
     since IAM is a global resource we leave it blank.
</pre>    
**account id** = Account number identifier \
**user** = Resource type, eg user or group \
**arntestuser** = The resource identifier. arnyestuser is a username in this example. \


# Root user

* Permanent identify, can't be removed
* It can access everything.