# Sabres-Stats-App

Sabres Stats App is an Android application that leverages the official NHL API to provide detailed historical roster information for the Buffalo Sabres. Users can view team rosters from previous seasons, along with key player details. Tapping on a player reveals their year-by-year NHL career statistics.

📦 Backend

The backend is powered by an Apollo GraphQL server.
To start the server:

Navigate to the graphql directory.

Run the command: node index.js.

The server runs locally on http://localhost:4000 and must be running for the app to function correctly.

📱 Android App

Built with Jetpack Compose for a modern, responsive UI.

Follows MVVM (Model-View-ViewModel) architecture for clean code organization and separation of concerns.

Communicates with the backend using GraphQL to fetch roster and player data.

🚀 How to Run

Replace the placeholder IP address in the app’s code with your computer’s local IP address (the one hosting the GraphQL server).

Ensure your Android device is connected to the same local network as your computer.

Launch the app and start exploring Buffalo Sabres rosters and player stats!
