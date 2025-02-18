# Sabres-Stats-App

Android app that utilizes the official nhl api to lookup previous buffalo sabres rosters and have the roster displayed with key information about them.

Also have the ability to click onto a player and view there career stats in the NHL year by year.

Back End

Graphql Apollo server, cd into the graphl folder and use node index.js to run it. 
The server will will use localHost 4000. Need to have the backend running on your computer for the app to work.

App

Android app using jetpack compose for the front end ui and used MVVM architecture to design the apps structure.
Queries graphql server to populate the roster and player information. 

How to Run

Need to replace local computer ip address in the repo with your computers specific address. Or else the app won't be able to access the locally run server.
App must be on a phone that is using the same network as your computer that is running the backend server
