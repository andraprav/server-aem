# server-aem
This small project instantiates a local server which serves messages.

It uses a thread pool of configurable number of threads to process requests.

Requests and responses are sent in a RESTful manner.

Currently it exposes only a GET on https://localhost:8080/message , but can be easily extended.

Runs with spring boot and uses maven to resolve dependencies.



