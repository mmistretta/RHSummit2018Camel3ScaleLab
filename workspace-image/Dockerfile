FROM jboss/base-jdk:8

# Switch to root
USER root

# Download Maven from Apache
RUN curl https://archive.apache.org/dist/maven/maven-3/3.3.3/binaries/apache-maven-3.3.3-bin.tar.gz | \
    tar -xzf - -C /opt \
 && ln -s /opt/apache-maven-3.3.3 /opt/maven \
 && ln -s /opt/maven/bin/mvn /usr/bin/mvn

# Download oc client from github
RUN curl -L https://github.com/openshift/origin/releases/download/v3.9.0/openshift-origin-client-tools-v3.9.0-191fece-linux-64bit.tar.gz | \
   tar -xzf - -C /opt \
 && ln -s /opt/openshift-origin-client-tools-v3.9.0-191fece-linux-64bit /opt/openshift-origin-client-tools \
 && ln -s /opt/openshift-origin-client-tools/oc /usr/bin/oc

# Install git
RUN yum install -y git nano emacs

#
# Checout the summit-example  project build it then delete it.  This will populate the ~/.m2/repository with the needed
# dependencies of the project, making subsequent builds of the project using this image faster.
#
RUN cd /tmp \
  && git clone https://github.com/mmistretta/RHSummit2018Camel3ScaleLab.git \
  && cd RHSummit2018Camel3ScaleLab \
  && git checkout f2f \ 
  && cd summit-example \
  && mvn clean install -Dfabric8.skip=true \
  && rm -Rf /tmp/RHSummit2018Camel3ScaleLab \
  && chmod -R "a+rwX" /root

# Let the user work in the /root directory
WORKDIR /root
ENV HOME="/root" \
    GIT_COMMITTER_NAME=anonymous \
    GIT_COMMITTER_EMAIL=anonymous@anonymous.com \
    _JAVA_OPTIONS=-Duser.home=/root
