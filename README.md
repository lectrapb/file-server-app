# FILE SERVER APP
Uploading files is a common task on a website; it will be implemented in different ways, such as storage in a file system, or database, consuming a web service, etc. This project uses Mongo Database and the functionality of <a href="https://www.mongodb.com/docs/manual/core/gridfs/">GridFS </a>; this specification allows the storage of files larger than 16 MB. This project also uses reactive features in Spring Boot, like Spring WebFlux and Reactive MongoDB.

Project to store files and play the videos; It is developed in java v17 and Angular @angular/cli@12.2.16.

## DEPLOY

## Requirements to deploy 
1. You have to be installed the applications <a href="https://docs.docker.com/get-docker/"> Docker</a> and <a href="https://docs.docker.com/compose/install/"> Docker-compose </a>.
2. The backend was developed in java with the Ide <a href="https://www.jetbrains.com/idea/download/#section=windows"> IntelliJ IDEA</a> (optional).
3. The frontend was developed in Angular 12 with Visual Studio Code editor(optional).
4. Any web browser..

## Get started
Use the command <font color="green"> docker-compose up </font>. then both projects run at the same time; the backend is a java app, and the second one is an Angular app after a few minutes you could test all applications.

## TEST
### Backend: 
Use the <a href="https://github.com/lectrapb/tokenServer/Arquitecture/main/Postman" target="_blank">Postman</a>
collection attached in the project.

### Front-end 
1. First, go into the web browser and put this URL http://localhost:4200; you will see the main dashboard.

2. Select the option load file, choose the file, and upload. 


3. To download a file choose the file and the option download. 

3. To play a video choose the option with the camera icon in the leftside of the file, afterward the video player show (the app is tested with google chrome only) and choose the option play!  

## Documentation
Please check this post about backend app:
<a href="https://thinksprograms.blogspot.com/2023/03/file-server.html" target="_blank">File Server</a>

