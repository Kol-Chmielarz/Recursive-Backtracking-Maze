import java.util.*;
import java.io.File; 
import java.io.FileNotFoundException; 

class Main {
  public static void main(String[] args) {
    MagicMaze maze = new MagicMaze("maze2.txt", 11, 15);
   System.out.println(maze.solveMagicMaze());
  }
}