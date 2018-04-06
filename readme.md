You should have maven installed on your computer.
In cmd:

1) Change working directory to project root directory

2) mvn clean package

3) java -jar target/test-0.0.1-SNAPSHOT.jar

4) You can set route to your XSD file as parameter "xsdLocation" like:

 java -jar target/test-0.0.1-SNAPSHOT.jar --xsdLocation=C:\Users\konstantin_evstafev\Desktop\pu.xsd
 

If you will not set route to your XSD file, application will use default XSD.


The app will star on localhost:8080


Use credentials:
Login: admin
Password: admin

There is two endpoint:

1) You can send GET request without parameters on localhost:8080/fact.

2) You can send POST request on localhost:8080/fact with  JSON contains list of 
Strings.


Example:

request:
![Image alt](https://git.epam.com/Konstantin_Evstafev/test/blob/master/1.PNG)

response:
![Image alt](https://git.epam.com/Konstantin_Evstafev/test/blob/master/2.PNG)
