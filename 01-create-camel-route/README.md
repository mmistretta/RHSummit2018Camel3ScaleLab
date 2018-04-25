# Create Your Camel RestDSL Route

## Download the project with Git
Go to a terminal that has git available and do the following:

....
  $ git clone <TODO insert url here>
....
Using your favorite editor open up <TODO file path to Application file or Route file>

## Write Your Camel Route
You will need to write your Camel route inside the configure() method.  The following route can be used, but you can also feel free to write your own.  Directions will go off of this route.
....
        restConfiguration()
        	.component("servlet")
    		  .bindingMode(RestBindingMode.json);
        rest().get("/")
        	.route().routeId("get-hello")
    		  .to("direct:hello");
        from("direct:hello")
        	.routeId("log-hello")
        	.log(LoggingLevel.INFO, "Hello World <Your Name>")
        	.transform().simple("Hello World <Your Name>");
....

## Run Your Camel Route Using Standalone Spring Boot
To initially test your Camel route you can run it using standalone spring-boot.  This will ensure everything compiles and that your Rest API is working as expected. To do this go to your terminal, browse to your project folder, and run the following:

....
mvn spring-boot:run
....
