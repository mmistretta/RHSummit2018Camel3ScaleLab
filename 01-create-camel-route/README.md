# Create Your Camel RestDSL Route

## Download the project with Git
Go to a terminal that has git available and do the following:
```
  $ git clone https://github.com/mmistretta/RHSummit2018Camel3ScaleLab/tree/master/summit-example
```
Using your favorite editor open up the project.
  
## Review the Code
Take note of the Application class and the `ServletRegistrationBean`.  This is necessary to use the Camel Servlet component to write your APIs using the Camel RestDSL.

TIP: In Fuse 7 onwards this is no longer nessasary.

```java
    @Bean
    public ServletRegistrationBean camelServletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(),"/camel/*");
        registration.setName("CamelServlet");
        return registration;
    }
```
The `src/main/faric8/deployment.yml` file configures the deployment parameters, such as how much memory to give a pod:

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

Take note of the Fabric8 Maven plugin.  This allows for you to deploy to your Open Shift container right from your command line.

```xml
      <plugin>
        <groupId>io.fabric8</groupId>
        <artifactId>fabric8-maven-plugin</artifactId>
        <version>${fabric8.maven.plugin.version}</version>
        <executions>
          <execution>
            <goals>
              <goal>resource</goal>
              <goal>build</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
```
  
## Write Your Camel Route
1. Before writing your own route you will need to create a new package for it.  Create the package/folder `my.project.route`.
2. Then create a class for your route.  You can name it whatever you would like such as `MyRoute`. 
3. Make this class extend the `RouteBuilder` class from Camel. 
4. Also add the Spring `@Component` annotation to the class itself. 
5. Finally, you will need to write your Camel route inside the `configure()` method.  The following route can be used, but you can also feel free to write your own.  Directions will go off of this route.
```java
        restConfiguration()
        	.component("servlet")
    		  .bindingMode(RestBindingMode.json);
          
        rest().get("/hello")
        	.route().routeId("get-hello")
    		  .to("direct:hello");
          
        from("direct:hello")
        	.routeId("log-hello")
        	.log("Hello World, <Your Name>")
        	.transform().simple("Hello World, <Your Name>");
```

## Run and Test Your Camel Route Using Standalone Spring Boot
To initially test your Camel route you can run it using standalone Spring Boot.  This will ensure everything compiles and that your Rest API is working as expected. To do this go to your terminal, browse to your project folder, and run the following:
```
mvn spring-boot:run
```
Leaving your route running in the terminal and using a separate terminal or browser try and hit your API.  If using a terminal run the following command:
```
curl http://localhost:8080/camel/hello
```
You should get a 200 OK response and a text response of "Hello World, <Your Name>"
