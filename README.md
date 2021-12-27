#Book author App <br>
To run the Appplication <br>
  Build and Start the application <br>
The fly way core migration tool will create and populate the database. <br>
Please configure application.proterties file to  <br>
change database name, url, username and passport as per your <br>
environment. <br>
I have included all the apis as per request, added auditing using  <br>
JPA auditing feature <br>
You can also view the api defination in url <br>
http://{localhost (if running locally)}:{port}/swagger-ui/index.html 
<br> 
Integration testing is done for Author Controller. For time constraint and repetitive task I only did for author controller. <br>
For Book Controller, its more or less the same. <br>
<br>
For logging I wanted to use Log4j <br> But Regarding recent vulnerabilities issues with it, I chose to not use it.
