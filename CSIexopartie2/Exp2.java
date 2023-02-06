import java.util.List;
import java.util.ArrayList;

import java.io.*;  
import java.util.Scanner;

/*
*This is experiment 2 my name is Ichrak and my student number is 300217634
*/ 

public class Exp2 {
    

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
  
        String types = args[0]; // type of method wanted
        double eps= Double.parseDouble(args[1]);
        // reads the csv file
        List<Point3D> points= Exp2.read(args[2]);
        int stepparam = Integer.parseInt(args[3]);
        long startTime = System.nanoTime();
        NearestNeighborsKD nn= new NearestNeighborsKD(points);// nearest neighbors using the KDtree
        NearestNeighbors nn1= new NearestNeighbors(points);// nearest neighbors using the linear method
        for(int i = 0; i< points.size(); i+= stepparam){
                    Point3D query= new Point3D(points.get(i).getX(),points.get(i).getY(),points.get(i).getZ());
                    if (types.length()==2){// if its kd
                    // creates the NearestNeighbor instance
                        nn.rangeQuery(query,eps);}
                    else if (types.length()==3){ // if its lin
                        nn1.rangeQuery(query,eps);
                }    
            
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000; // in milliseconds
        System.out.println("the time rangeQuery takes using the "+ types +" method is "+ duration + " milliseconds");
        
       }
      } 
      



