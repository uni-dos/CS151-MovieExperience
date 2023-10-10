## Movie Enjoyment Calculator

Group 3: Robert, Cuong, Rod

Draft written by Robert

Going to the movies is often thought as being a good experience, but it does not always work out. 
Sometimes, there is a line at concessions, other times the movie hasn't finished and sometimes you run late. 
There is no data to help you calculate if you will have a good time. 

We assume that people who enjoy going to movies will use this information to make an educated guess before they make a movie purchase. 
At this time, the service will only be available on desktop usage. While it maybe a limiting factor, we do not have much experience with Graphical User Interfaces on mobile devices. 

We plan on using AMC API to retrieve the data for the amount of tickets sold. We may use a database to store that information to save on wait time later on. 
There is a library called [Retrofit](https://github.com/square/retrofit) which will assist us with authenticating and retrieving the data. We might add a gui to make it easier to interact with the API. 
Basically, a user will give a time, and it will fetch movies that are playing within an hour time range along with a message of whether or not it might be a good time to see it.

This possible solution allows people to decide whether they should spend their valuable time waiting or come back at another time to have a better experience.

Users should be able to select the movie theater location and select a time and date. At this time, I cannot think of any other users. Maybe we can add a superuser role which has the ability to cancel shows and move them around. This will first need to be added to a database or else the data will be overridden.  

