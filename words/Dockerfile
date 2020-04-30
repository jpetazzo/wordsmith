FROM ubuntu

# Install the JDK and Maven, as instructed.
RUN apt-get update
RUN apt-get install maven openjdk-8-jdk -y

# Copy the file describing the dependencies first.
COPY pom.xml .

# Use maven to install the dependencies.
RUN mvn verify

# Now, copy the source files for our application.
# Remember that when copying a directory, we generally
# *do not* to do "COPY src ." otherwise the content of
# "src" gets copied to the directory (instead of
# creating a subdirectory named "src").
COPY src src

# Build the code. Note: this will place multiple
# JAR files in a directory named "target". There
# will be our compiled code ("words.jar") but also
# its dependencies.
RUN mvn verify

# Start a new stage.
FROM ubuntu

# Install just the JRE.
RUN apt-get update
RUN apt-get install openjdk-8-jre -y

# Copy the "target" directory.
COPY --from=0 /target /target

# We use the "exec" shell built-in here, to make
# sure that the program executes as PID 1 and
# therefore receives signals correctly.
CMD exec java -Xmx8m -Xms8m -jar target/words.jar
