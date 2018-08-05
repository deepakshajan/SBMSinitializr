# SBMSinitializr

An application to automate the springboot microservices cluster deployment.

<h4>Prerequisites</h4>
     The following softwares are required inorder to deploy the application : 
 <ul>
  <li>Maven</li>
  <li>Java(version 9)</li>
  <li>Node</li>
  <li>npm</li>
  </ul>
 
 <h4>Steps to deploy</h4>
 
 <ul>
  <li>Clone the repository into local</li>
  <li>Run the run.bat file</li>
  <li>This will deploy the application</li>
  <li>Wait until the browser window opens up and the application User Interface is loaded in a new browser tab</li>
  <b>Note :</b> If the file is being run for the first time, there could be a delay in start(may take upto 5 mins). You will have to      refresh the browser tab until the game loads.
  <li>As usual you can always access the UI using the URL -> localhost:8080</li>
 </ul>
 
 <h4>Application Details</h4>
 
  This application aims at automating springboot microservice cluster deployment. The same can be used to clean,test and deploy springboot microservice clusters. However each microservice that can be deployed using this application requires some conditions to be met, which are listed below
  
<ul>
  <li>The deployable microservice should be written using the maven build tool. Gradle build tool is supported through rest service but support in UI can be added later.</li>
  <li>The deployable microservice should be made such any resource preperation and custom actions to be done is done on the clean lifecycle of maven.</li>
  <li>Any unit tests or integration tests to be performed within the deployable microservice should be done within the test maven life cycle.</li>
  <li>Should be able to deploy the microservice using the "spring-boot:run" command</li>
</ul>

<h4>Managing dependent services</h4>

  This application also allows each individual micro service to be dependent on each other. For example if you have two microservices namely <i>service1</i> and <i>service2</i>, and if <i>service2</i> has a depandancy on <i>service1</i> then only after the <i>service1</i> finishes its deployment can the deployment of <i>service2</i> start. This application makes sure that all the dependant microservices are already up and running before attempting to deploy each microservice.
  
  In order to specify the dependant microservices for any particular microservice, we simply have to add a file named <b>deps.txt</b>  to the root path of the microservice. In this file we can list all the dependancies the microservice has. The format of the file could be found in the examples provided.
  
  <h4>Examples</h4>
 
 Various examples for deployable microservice clusters can be found in the folder <i>ExampleClusters</i> in the root of this repository.
  
  <h4>Notes</h4>
  
 Supports only Chrome and Firefox browsers.
