From ubuntu 

RUN apt-get update
RUN apt-get install openjdk-8-jdk -y
RUN apt-get install maven -y

COPY pom.xml  . 
RUN mvn verify

COPY . .
RUN mvn verify

WORKDIR /target
CMD exec java -Xmx8m -Xms8m -jar words.jar

