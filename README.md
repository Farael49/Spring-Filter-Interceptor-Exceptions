# Spring-Filter-Interceptor-Exceptions
## How to handle exceptions within Spring Filters and Interceptors 


Filters and Interceptors have quite a different behaviour as they do not act at the same level. 
The project shows how to simply handle an exception and forward an according response. 

- For an Interceptor, we only need an Exception Handler as we have access to the Spring MVC stack

- For a Filter, we need to catch the exception and handle it on the response (response.setStatus/response.getWriter().write(...))


This example was initially made to answer the following use case :
An existing filter/interceptor does some processing involving an external HTTP call with WebClient, and when the WebClient throws a WebClientResponseException indicating a specific issue & status code (400, 404, ...) it is not properly forwarded and always results in an Internal Error 500.
