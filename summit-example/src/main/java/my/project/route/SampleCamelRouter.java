package my.project.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */

@Component
public class SampleCamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
        	.component("servlet")
    		.bindingMode(RestBindingMode.json);
    
        rest().get("/hello")
        	.route().routeId("get-hello")
    		.to("direct:hello");
        
        from("direct:hello")
        	.routeId("log-hello")
        	.log(LoggingLevel.INFO, "Hello World")
        	.transform().simple("Hello World");

    }

}
