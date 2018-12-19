# springboot oauth2 security jwt h2 bootstrap4 jquery
Spring Boot OAuth2 Security with h2, JWT, Bootstrap4 and JQuery

Steps:

Authorization server:
give clientid and clientsecret as username and password in Basic authorization section
Body section select x-www-form-urlencoded and put username, password, grant_type as key and william_j@gmail.com, WilliamJohn, password as value

in the response we get access_token, copy it it will be used to access the resource

Resource server:
Header take Authorization as key and Bearer <access-token> as value and the rest api to access the resource.

openssl genrsa -out jwt.pem 2048  
openssl rsa -in jwt.pem : to generate private key from jwt.pem file

openssl rsa -in jwt.pem -pubout




