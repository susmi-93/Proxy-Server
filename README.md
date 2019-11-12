HttpProxy Service
_________________________________________

This is a web service that acts as HTTP proxy for the GET and POST requests to the url given in the request.

For GET requests:\
curl  http://localhost:8080/proxy/{url}

For POST requests:\
curl -X POST -d <Form_Data>  http://localhost:8080/proxy/{url}