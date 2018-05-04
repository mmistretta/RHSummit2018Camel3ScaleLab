# Spring-Boot and Camel Rest DSL QuickStart

Use this Camel Project as a template to get you started. 
This example demonstrates how to configure Camel routes in Spring Boot via a Spring Annotation configuration

### Running standalone

You can run this example local standalone via

    mvn spring-boot:run
    
And then call the REST service from a web browser or via command line `curl` command:

    http://localhost:8080/camel/hello
    
    curl http://localhost:8080/camel/hello
        

### Building

The example can be built with

    mvn clean install

### Running the example in OpenShift

It is assumed that:
- OpenShift platform is already running, if not you can find details how to [Install OpenShift at your site](https://docs.openshift.com/container-platform/3.3/install_config/index.html).
- Your system is configured for Fabric8 Maven Workflow, if not you can find a [Get Started Guide](https://access.redhat.com/documentation/en/red-hat-jboss-middleware-for-openshift/3/single/red-hat-jboss-fuse-integration-services-20-for-openshift/)

The example can be built and run on OpenShift using a single goal:

    mvn fabric8:deploy

To list all the running pods:

    oc get pods

Then find the name of the pod that runs this quickstart, and output the logs from the running pods with:

    oc logs <name of pod>

You can also use the OpenShift [web console](https://docs.openshift.com/container-platform/3.3/getting_started/developers_console.html#developers-console-video) to manage the running pods, and view logs and much more.
