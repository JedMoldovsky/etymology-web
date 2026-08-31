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

### Dataset Setup

The application requires `etymology.csv` to construct the etymology graph.

Because the dataset is approximately 420 MB, GitHub may not provide the actual CSV file directly when the repository is cloned. Instead, the file should be downloaded using GitHub's **Raw** option.

1. Open `etymology.csv` in the GitHub repository.
2. Click **Raw** to open the raw dataset.
3. Download the raw file to your computer.
4. The downloaded file may have a `.txt` extension. Rename it to:

```text
etymology.csv
```

5. Replace the existing `etymology.csv` in your local project with the downloaded file.
6. Make sure the file is located at:

```text
src/main/resources/etymology.csv
```

Your local project should contain:

```text
src/
└── main/
    └── resources/
        └── etymology.csv
```

Make sure that `etymology.csv` is the actual dataset and not a Git LFS pointer file.

### Run the Application

Once the dataset has been downloaded and placed in the correct location, run:

```bash
mvn spring-boot:run
```

The application will run on the configured local port.

## Dataset

The application uses a large etymological dataset containing relationships between words across languages.

The dataset is approximately 420 MB. The application loads the dataset when it starts and uses it to construct the graph used for pathfinding.

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
