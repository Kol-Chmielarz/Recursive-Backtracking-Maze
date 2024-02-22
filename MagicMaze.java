
import java.io.*;
import java.util.*;
import java.io.File; 
import java.io.FileNotFoundException; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MagicMaze{
  int p;
  String fname;
boolean yay;
  String mazenum;
  int rows;
  int cols;
  char maze[][];
  String mazestring;
  int max;
  int counter;
  int num;
  int fakenum, fakenum2;


   public MagicMaze(String fname, int fakenum, int fakenum2){//got rows and cols from file text
    this.fname=fname;
   this.fakenum=fakenum;
     this.fakenum2=fakenum2;
    readmazeparams(fname);
  

  //System.out.println(yay);
      //System.out.println("columns " + cols);
        //    System.out.println("rows " + rows);
     //System.out.println("\n");
   //  System.out.println(mazestring);

    //for(int i=0;i<rows;i++){
      //for(int j=0;j<cols;j++){
        // System.out.print(maze[i][j]);

      //}
       //System.out.println();
    //}

   
  //}


  }


  public void readmazeparams(String fname){
      try {

        
            File file = new File(fname);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String firstLine = br.readLine();
            cols = firstLine.length();
            rows = 1;
            while (br.readLine() != null) {
                rows++;
            }
           
           // System.out.println("columns " + cols);
            //System.out.println("rows " + rows);
            br.close();
        
        } catch (IOException e) {
            e.printStackTrace();
        }

    readmazetostring(fname);
  }


  public void readmazetostring(String fname){//getting file text to char maze array
    this.fname=fname;

      try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            mazestring = sb.toString();

           //System.out.println(mazestring);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
     maze=new char[rows][cols];//setting size of maze

    mazestringtoarray(mazestring, maze, rows, cols);

    
  }

  public void mazestringtoarray(String mazestring, char maze[][], int rows, int cols){
    this.maze=maze;
    this.rows=rows;
    this.cols=cols;
   
    this.mazestring=mazestring;
    max=rows*cols;
    counter=0;
    
while(counter<max){
    for(int i=0;i<rows;i++){
      for(int j=0;j<cols;j++){
        maze[i][j]=mazestring.charAt(counter);
        counter++;
      }
      counter++;
    }
}

    maze[rows-1][0]='*';
    
  }

  boolean solveMagicMaze(){
    


    if(solveMagicMazerec(rows-1,0))return true;
    else{return false;}
  }

  

  public boolean solveMagicMazerec(int krow, int kcol){


    if(maze[krow][kcol]=='X'){//base case 
   
      return true;
    }

    

    if(maze[krow][kcol]=='*'|Character.isDigit(maze[krow][kcol])){//not used check

      
        if(Character.isDigit(maze[krow][kcol])){//number scenario teleport
           num=maze[krow][kcol];

      maze[krow][kcol]='v';
       for(int x=0;x<rows;x++){
      for(int y=0;y<cols;y++){
        if((maze[x][y]==num)&&(x!=krow|y!=kcol)){//bounds
        if(solveMagicMazerec(x,y))return true;//update
            x=999;
          y=999;
    }
      }
          
        }
        }

      maze[krow][kcol]='v';
   
     if(krow>0 && maze[krow-1][kcol]!='@' && maze[krow-1][kcol]!='v')//moving up bounds
      { 
        if(solveMagicMazerec(krow-1,kcol))return true;//update
       
       
      }

     if(krow<rows-1 && maze[krow+1][kcol]!='@' && maze[krow+1][kcol]!='v')//moving down bounds
      {
        if(solveMagicMazerec(krow+1,kcol))return true;//update
        
  
      }

        if(kcol>0 && maze[krow][kcol-1]!='@' && maze[krow][kcol-1]!='v')//moving left bounds
      { 
        if(solveMagicMazerec(krow,kcol-1))return true;//update
         
       
      }

      if(kcol<cols-1 && maze[krow][kcol+1]!='@' && maze[krow][kcol+1]!='v')//moving right bounds
      {
        if(solveMagicMazerec(krow,kcol+1))return true;//update
       
       
      }
          
     maze[krow][kcol]='*';//backtrack
    }
    
      
      return false;//if cant reach end return false
      
  


  }


 
}
    







    
  

  





