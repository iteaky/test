You shoud have maven installed on your computer.
In cmd:
1) Change working directory to project root directory
2) mvn clean package
3) java -jar target/test-0.0.1-SNAPSHOT.jar

The app will star on localhost:8080

There is two endpoint:
1) You can send GET request without pararametrs on localhost:8080/fact.
2) You can send POST reques on localhost:8080/fact with  JSON contains list of 
Strings.

Example:

request

response