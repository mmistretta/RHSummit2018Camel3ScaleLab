# Create Your Camel RestDSL Route

## Download the project with Git
Go to a terminal that has git available and do the following:
```
  $ git clone https://github.com/mmistretta/RHSummit2018Camel3ScaleLab.git
```
Using your favorite IDE, open up the summit-example project.
  
## Review the Code

Take note of the `Application` class which is a standard Spring Boot application class.

### OpenShift deployment

The `src/main/faric8/deployment.yml` file configures the deployment parameters, such as how much memory to give the application when running in OpenShift (we deploy to OpenShift in follow lab - 02):

```spec:
  template:
    spec:
      containers:
        - 
          resources:
            requests:
              cpu: "0.2"
              memory: 256Mi
            limits:
              cpu: "1.0"
              memory: 256Mi
```
  
### Using Fuse 6.3

Take note of the `ServletRegistrationBean`.  This is necessary to use the Camel Servlet component to write your APIs using the Camel RestDSL.

In Fuse 7 onwards this is no longer nessasary, and therefore its not nessasary to write this code in this lab, because we are using Fuse 7.

```java
    @Bean
    public ServletRegistrationBean camelServletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(),"/camel/*");
        registration.setName("CamelServlet");
        return registration;
    }
```

## Write Your Camel Route

1. Before writing your own route, you will need to create a new package for it.  Create the package/folder `my.project.route` and another one called `my.project.model`.  These packages will house your route and java object accordingly. 
2. Then create your POJO.  You can call it whatever you wish.  For the directions we will use the name `ResponseObject`. 
```java
package my.project.model;

public class ResponseObject {
	
	private String response;
	private String name;
	
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
```

3. Then create a class for your route.  You can name it whatever you would like such as `MyRoute`. 
4. Make this class extend the `RouteBuilder` class from Camel. 
5. Also add the Spring `@Component` annotation to the class itself.
6. Create a method to create your response object like below. 
```java
    public ResponseObject createResponse() {
        ResponseObject response = new ResponseObject();
        response.setResponse("Hello World");
        response.setName("your name");
        return response;
    }
```
    
7. Finally, you will need to write your Camel route inside the `configure()` method.  The following route can be used, but you can also feel free to write your own.  Directions will go off of this route.
```java
        // configures rest-dsl to use servlet component and in JSon mode
        restConfiguration()
          .component("servlet")
          .bindingMode(RestBindingMode.json);

        // rest-dsl with a single GET /hello service
        rest()
          .get("/hello")
    	      .to("direct:hello");

        // route called from REST service that builds a response message
        from("direct:hello")
          .log("Hello World")
          .bean(this, "createResponse");
```

## Run and Test Your Camel Route Using Standalone Spring Boot

To initially test your Camel route, you can run it using standalone Spring Boot.  This will ensure everything compiles and that your Rest API is working as expected. To do this go to your terminal, browse to your project folder, and run the following:
```
mvn spring-boot:run
```
Leaving your route running in the terminal and using a separate terminal or browser, try and hit your API.  If using a terminal run the following command:
```
curl http://localhost:8080/camel/hello
```
You should get a 200 OK response and a text response of "Hello World, <Your Name>"
