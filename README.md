# Etymology Web

A Java application that finds the shortest etymological path between two words using a large linguistic dataset.

For example:

```text
beknight → knight → night
```

## Features

* Finds connections between two words
* Represents etymological relationships as a graph
* Uses Breadth-First Search (BFS) to find the shortest path
* Processes a large etymological dataset
* Provides a simple interface for searching word relationships

## How It Works

The application converts etymological relationships from the dataset into a graph.

* Each word is represented as a node.
* Each etymological relationship is represented as an edge.
* Breadth-First Search is used to find the shortest path between two words.

For example, if the dataset contains relationships connecting `beknight` to `knight` and `knight` to `night`, the application can find:

```text
beknight → knight → night
```

## Technologies

* Java
* Spring Boot
* Maven
* HTML
* CSS
* JavaScript

## Project Structure

```text
etymology-web/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/jedmo/etymology_web/
│       │       ├── EtymologyApplication.java
│       │       ├── EtymologyController.java
│       │       ├── EtymologyGraph.java
│       │       └── WebController.java
│       └── resources/
│           ├── templates/
│           └── etymology.csv
├── pom.xml
└── README.md
```

## Running Locally

Clone the repository:

```bash
git clone https://github.com/JedMoldovsky/etymology-web.git
cd etymology-web
```

Run the application using Maven:

```bash
mvn spring-boot:run
```

The application will run locally on the configured port.

## Dataset

The application uses a large etymological dataset containing relationships between words across languages.

The dataset is processed when the application starts and used to construct the graph used for pathfinding.

## Algorithm

The application uses Breadth-First Search (BFS) to find the shortest path between two words.

BFS explores neighboring nodes level by level. This guarantees that when the destination is reached, the resulting path contains the minimum number of connections between the starting and ending words.

## Future Improvements

* Optimize memory usage for the large dataset
* Improve graph construction and search performance
* Add support for additional languages
* Provide more detailed information about individual etymological relationships
* Improve visualization of word connections

## Author

Jed Moldovsky
