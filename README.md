# Movies-Recommender-System

How does Netflix fill your initial dashboard with all those great films you want to see? How does they do so good predictions and show you that Vikings last season or that Star Wars trilogy? How does Spotify reads your mind and knows exactly which song do you want to listen next? How this app recommends you those artist that play the genres you like? This "magic" is achieved thanks to Recommender Systems. If you arrive here thanks to my blog you know a little theory about this topic, if not you can find a short explanation [here](https://leantechblog.wordpress.com/2018/01/03/how-does-netflix-or-spotify-knows-what-you-like-a-briefing-on-recommender-systems/).

In this repository you will find the implementation of two recommender systems: one with collaborative filtering in Java and a content based recommender in Python.

## About the files used

The files movies-data.csv and ratings.csv are prepocessed from original ones. The original files belongs to [Kaggle](https://www.kaggle.com/), specifically to [The Movies Dataset](https://www.kaggle.com/rounakbanik/the-movies-dataset) so the intellectual property of this files and all right reserved belongs to Kaggle.

I removed some columns and reduced the size of the ratings file so it can be loaded on memory, you could try other approaches or try with your own preprocessed files.	

## Collaborative Filtering

What tools did I use:

* [Apache Mahout](http://mahout.apache.org/)
* [Spring Boot](https://projects.spring.io/spring-boot/)
* [Swagger](https://swagger.io/)

### Execution

Just download the code, you need to have Java 1.8 installed and maven configured. Once you clone the project in the root folder (where you find the pom.xml file) run:

```
mvn clean install
```

Then run:

```
java -jar target/movies-recommender-1.0.0.jar
```

This will start the application, it will take about one minute to be up. Now you can try to obtain movies recommendations.

### Getting Recommendations

This app have Swagger configured so you can interact with the endpoint on this URL:

```
http://localhost:8080/recommender/swagger-ui.html
```

Try the endpoint on recommender-controller sending a number as userId.

![alt text](https://github.com/cjcarvajal/movies-recommender-system/blob/master/images/response.png)


You could also request recommendations using CURL:

```
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/recommender/user/1/recommendation'
```

In this request we are sending 1 as the userId.

### The Movie Recommender System Project

This is a maven project, you can import it in your favourite IDE, it exposes an endpoint on the class **RecommenderController**. In the core package you will find **RecommenderTrainedModel** which use Mahout implementation to load the ratings.csv file, this file contains tuples of : userId, movieId, rating. I build a model of type User-User so I am finding similar users to make predictions. Mahout also let you to use Item-Item. The metric to find neighbours is Cosine Similarity. I use a fixed number of 100 neighbours.

The class **PersistenceManager** just simply load the movies.csv file which contains tuples of: movieId, movieName. The class expose a method which receives a movieId and returns it's name.

## Content Based Recommender

Still working on the Python project. Come back soon!!