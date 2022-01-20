# Othello

Othello is a desktop app for the Othello board-game. It is implemented using Java and JavaFX.

![Alt text](https://i.imgur.com/3LmGzKv.png)

## About
This project was developed with 4 other classmates for the Software Design (CSC207) project. We colloborated using the scrum agile-framework and implemented object-oriented design patterns to develop several features of the app.  

## Design patterns

For the overall game, we used the Model-View-Controller (MVC) software design pattern.

For the game functions, we used the following object-oriented design patterns:

- Observer / Observable: Used with MVC to facilitate communication between the view and model
- Command: Restart game, redo / undo move functionality, set player / computer, and set timers
- Visitor: Certain game algorithms such as get score, get winner, move disc etc.
- Iterator: Iterating over the board squares

## Features

- Player vs Player and Random / Greedy / Advanced Computer
- Game move hints for the different modes
- Restart game, undo / redo move
- Set timers

## Installation
Before you can install this app, you need download / configure the following:

 - [OpenJDK](https://www.oracle.com/java/technologies/downloads/) (15)
 - [JavaFX](https://openjfx.io) (15)

First clone the repository:

    git clone https://github.com/OwaisSiddiqui/othello

 Then go to the `othello` directory and run the following commands (with replacing `path/to/javafx-sdk-17/lib` with your path):

    export PATH_TO_FX=path/to/javafx-sdk-17/lib
    javac --module-path $PATH_TO_FX --add-modules javafx.controls -cp src src/viewcontroller/OthelloApplication.java
    java --module-path $PATH_TO_FX --add-modules javafx.controls -cp src viewcontroller.OthelloApplication
