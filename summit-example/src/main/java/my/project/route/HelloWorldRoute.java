/*
 * Copyright 2018 Red Hat, Inc.
 * <p>
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 *
 */
package my.project.route;

import my.project.model.ResponseObject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * Camel route with Rest DSL that hosts a simple hello world REST service.
 * Notice how we must have the Spring <tt>@Component</tt> annotation on the class-level,
 * this makes Camel being able to auto-detect this route during startup via Spring Boot.
 */
@Component
public class HelloWorldRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

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
    }

    /**
     * Method that creates a POJO with the response
     */
    public ResponseObject createResponse() {
        ResponseObject response = new ResponseObject();
        response.setResponse("Hello World");
        response.setName("your name");
        return response;
    }

}