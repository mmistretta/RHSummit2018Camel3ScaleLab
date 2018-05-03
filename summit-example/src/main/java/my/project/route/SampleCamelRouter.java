package my.project.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import my.project.model.ResponseObject;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */

@Component
public class SampleCamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    	
    	final ResponseObject response = new ResponseObject();
    	response.setResponse("Hello World");
    	response.setName("your name");
        restConfiguration()
        	.component("servlet")
    		.bindingMode(RestBindingMode.json);
    
        rest().produces("application/json")
        	.get("/hello")
        	.outType(ResponseObject.class)
        	.route().routeId("get-hello")
    		.to("direct:hello");

        from("direct:hello")
        	.routeId("log-hello")
            .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    exchange.getOut().setBody(response);
                }
            })
        	.log(LoggingLevel.INFO, "Hello World");

    }

}