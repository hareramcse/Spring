# springboot oauth2 security jwt h2 bootstrap4 jquery
Spring Boot OAuth2 Security with h2, JWT, Bootstrap4 and JQuery

To generate the private and public key

openssl genrsa -out jwt.pem 2048  
openssl rsa -in jwt.pem : to generate private key from jwt.pem file
openssl rsa -in jwt.pem -pubout

Steps:

Authorization server:
go to Authorization - -> select Basic Auth - > give clientid and clientsecret(given in application.yml file) as username and password in Basic authorization, and in Body section select x-www-form-urlencoded and put username=harerambgcs@gmail.com, password=<passwordofemail>, and grant_type=password.

hit the URL 
PUT --> localhost:6000/oauth/token   get the access token

Now run the Resource Server

Resource server:
in the application.yml file jwt: key-value:put the public key which we have already generated
once the resource server is up and running then access the bellow URL
GET - > localhost:7000/users
GET - > localhost:7000/roles
GET - > localhost:7000/permission

here we will get the full authentication error.

for this
go to Header - > Authorization as key and Bearer <access token which we got from authorization server>

now hit the URL 
GET - > localhost:7000/users
GET - > localhost:7000/roles
GET - > localhost:7000/permission

we will get the proper response.

now run the two factor authentication application and hit the url http://localhost:8000/users/1/emails/harerambgcs@gmail.com/tfa
it will generate the otp and will send it to email provided on the above email.

to verify the otp hit the url http://localhost:8000/users/1/codes/<otp>
if you pass wrond otp then it will not return success status
  
now run the web application and hit the url http://localhost:9000 and enter the email and password then it will ask for the otp. put the otp and acess the resource




