# aconex

#Pre-requisites
* java 1.6
* ant 1.9

#Steps
* Clone the repository. It includes tomcat along as part of it
* Go to the aconex directory
* Run the command "ant deploy"
* This will do the following
  * Download dependecies specified in maven
  * Compile the source
  * Execute the unit tests
  * Deploy the aconex.war to tomcat
  * Restart tomcat server
* Once done, the website will be available at http://localhost/aconex
