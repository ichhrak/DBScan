import java.util.List;
import java.util.ArrayList;

import java.io.*;  
import java.util.Scanner;  

/*
*This is experiment 1 my name is Ichrak and my student number is 300217634
*/ 

public class Exp1 {
  
  // reads a csv file of 3D points (rethrow exceptions!)
  public static List<Point3D> read(String filename) throws Exception {
	  
    List<Point3D> points= new ArrayList<Point3D>(); 
	double x,y,z;
	
	Scanner sc = new Scanner(new File(filename));  
	// sets the delimiter pattern to be , or \n \r
	sc.useDelimiter(",|\n|\r");  

	// skipping the first line x y z
	sc.next(); sc.next(); sc.next();
	
	// read points
	while (sc.hasNext())  
	{  
		x= Double.parseDouble(sc.next());
		y= Double.parseDouble(sc.next());
		z= Double.parseDouble(sc.next());
		points.add(new Point3D(x,y,z));  
	}   
	
	sc.close();  //closes the scanner  
	
	return points;
  }
  
  public static void main(String[] args) throws Exception {  
  
    String types = args[0]; //type of rangequery
	double eps= Double.parseDouble(args[1]);
  
    // reads the csv file
    List<Point3D> points= Exp1.read(args[2]);
	
	Point3D query= new Point3D(Double.parseDouble(args[3]),
								Double.parseDouble(args[4]),
								Double.parseDouble(args[5]));
	
	// creates the NearestNeighbor instance
    if (types.length() == 3){ // if the type is lin it has 3 letters (len == 3)
	    NearestNeighbors nn= new NearestNeighbors(points); // we create a . new instance nearestneighbors
        List<Point3D> neighbors= nn.rangeQuery(query,eps);

        System.out.println("number of neighbors using the linear method= "+neighbors.size());//it prints the number of neighbors using the linear method
        for(int i = 0; i< neighbors.size(); i++){    
            System.out.println("("+neighbors.get(i)+")\n");
             }
    }
    else if (types.length() == 2){// if the type is kd it has 2 letters (len == 2)
        NearestNeighborsKD nn= new NearestNeighborsKD(points);// we create a . new instance nearestneighborsKD
        List<Point3D> neighbors= nn.rangeQuery(query,eps);

        System.out.println("number of neighbors using the KDTree= "+neighbors.size()+"\n"); //it prints the number of neighbors using KDtree
        for(int i = 0; i< neighbors.size(); i++){    
            System.out.println("("+neighbors.get(i)+")\n");
             }
    }
    else System.out.println(" Please choose either lin or kd."); // in case you put the wrong type

  }   
}