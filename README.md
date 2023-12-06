# CS151-MovieExperience

Team 3, Robert, Rod Jaron, Cuong

I (Robert) have written this readme file and the initial project proposal.

Rod Jaron and I gave the presentation. He did have slides prepared, but we did not end up using them as I thought it was unnecessary. He also talked about the GUI as that is what he was tasked to do for the project. I gave the presentation on how we made network calls to the omdb API. I also talked about how we saved data using Java's serializable interface and object writers.

Rod Jaron was tasked to write the GUI. He used javafx and use FXML to generate the UI. He wrote the controller class to switch between scenes when needed. I wrote code that interfaced with the API using a third party library, Retrofit. I had previous experience using this library, and it was not too difficult to adjust it for movie fetching. I was also trying to avoid hard coding the API key and came across dotenv which loads items from a .env file. I was able to integrate it into a Retrofit call easily. Cuong was supposed to work on the database, so we could store the network calls. He did provide code that created the database but nothing could be saved to it. Instead, I suggested that we use the Java serializable interface and he implemented that and used an object writer to save the arraylist.

At first, the problem we were trying to solve was to suggest optimal movie times for movie goers, but we scrapped that idea because the APIs I found did not provide seating information for any movie theater. I was considering scraping a website for movie data, but I did not want to be banned for doing so on the websites. So we settled on a movie chooser similar to a movie theater. There would be two types of users: an admin and a customer/guest. The admin has the ability to find movies and add it to the database and the guest can see the movies in the database and select the one they want to watch.

Functionally, our project does the things we set out to do. The admin is able to search for movies and select the ones that are the most interesting to store. Even though we could not get a database, we are still able to store the data. I would suspect that this is slower than a database but that is all we could achieve.

Operations Admin: add and remove movies. User: select movie to see.

To run it all you would need to do is have a maven plugin installed and initialized and it should provide a run command. There needs to be a .env in the root of the project. I will not list it here for obvious reasons.
