# MazeMVC
***
MazeMVC is a maze solver application that generates a maze from a file and proceeds to solve the maze using the Dijkstra algorithm. This generation is visualized using JSwing and allows the user to interact with the maze, enabling them to choose the size of the maze, the maze start/end points and obstacles.

## Getting Started
***
To get started with MazeVMC, you'll need to have the following installed:

    Java Development Kit (JDK) version 19 build 19.0.2 or later
    Git

## Installation
***
To compile and run MazeVMC, follow these steps:

    $ git clone https://gitlab.telecom-paris.fr/2022inf103/groupe6/gastineau-arthur/
    $ cd ../gastineau-arthur/tp04
    $ javac -cp src src/MainMVC.java
    $ java -cp src MainMVC

## Usage
***
Once the application is running, you can generate a maze by opening a file containing the maze data or generetae one with a custom size. 

After loading the file, you can interact with the maze by clicking on the cells to create or remove obstacles. You can also modify the start and end points.

To solve the maze, click the "Solve" button. The solution path will be displayed in yellow, and the shortest path will be displayed in yellow.

![Maze Solver Demo](maze_demo.gif)

## File Format

The maze file must have the extension .maze .The maze file should be a text file containing a rectangular grid of characters. Each character represents a cell in the maze, and the characters should be separated by spaces. The following characters are recognized:

- E : an empty cell
- W : a wall
- D : the departure point
- A : the arrival point

Examples of maze files are provided in the data directory.

## Credits

MazeMVC was created by Arthur Gastineau.

