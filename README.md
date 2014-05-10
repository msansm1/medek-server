# medek-server #

JEE7 backend for medek. It provides a REST API.

# installation #

It's configured to run on Wildfly 8.

# functional tests with Arquillian #

There are 2 profiles for arquillian tests, so choose the one you want :

* ``mvn clean install -Parq-wildfly-managed``
    It will install a Wildfly 8 server and start up the server.

* ``mvn clean install -Parq-wildfly-remote``
    It requires you to start up a Wildfly server outside of the build.
	You must adapt the arquillian.xml file (in src/test/resources) to your configuration.

# build and run #

git clone https://github.com/msansm1/medek-server.git

build project with: ``mvn clean install wildfly:deploy``

open url in browser: http://localhost:8080/medek-server-0.0.1-SNAPSHOT/

then you can test the different components of the API