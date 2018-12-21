# springboot oauth2 security jwt h2 bootstrap4 jquery
Spring Boot OAuth2 Security with h2, JWT, Bootstrap4 and JQuery

To generate the private and public key

openssl genrsa -out jwt.pem 2048  
openssl rsa -in jwt.pem : to generate private key from jwt.pem file
openssl rsa -in jwt.pem -pubout

Steps:

Authorization server:
go to Authorization - -> select Basic Auth - > give clientid and clientsecret(given in application.yml file) as username and password in Basic authorization, and in Body section select x-www-form-urlencoded and put username=william_j@gmail.com, password=WilliamJohn, and grant_type=password.

hit the URL 
PUT --> localhost:7000/oauth/token   get the access token

Now run the Resource Server

Resource server:
in the application.yml file jwt: key-value:put the public key which we have already generated
once the resource server is up and running then access the bellow URL
GET - > localhost:8000/users
GET - > localhost:8000/roles
GET - > localhost:8000/permission

here we will get the full authentication error.

for this
go to Header - > Authorization as key and Bearer <access token which we got from authorization server>

now hit the URL 
GET - > localhost:8000/users
GET - > localhost:8000/roles
GET - > localhost:8000/permission

we will get the proper response.





