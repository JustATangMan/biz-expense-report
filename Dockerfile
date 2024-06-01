# do set up in CLI only (done)

# comment each line with what it means/does (done?)

# reference mysql instance by hostname instead of IP (pass in hostname when creating container?) (done)

# externalize sql datasource (don't need to rebuild each time it changes) read environment variables (done)

# create a build stage
# can set the name and tag of base image
# can set platform (linux, windows, etc.)
# can call variables defined in ARG instruction before
# can assign a name to the build stage to be referenced in later FROM, COPY, RUN instructions
FROM eclipse-temurin:17-jdk-alpine

# mounts the volume to this location, serves as a mirror into the container's file system
VOLUME /tmp

# copies the contents of the first file (bizexpense) to the container file system at the provided path (./app.jar)
COPY target/biz-expense-report-0.0.1-SNAPSHOT.jar app.jar

# configure containers that run as executables
# running in exec form
# command, param1, param2
# since the container is built on a java jar, it would be ran in CLI like java -jar ./app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
