import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
*This is experiment 3 my name is Ichrak and my student number is 300217634
*/ 

public class Exp3 {
    public static void main(String[] args) throws IOException {
        
         List<Point3D> DB = new ArrayList<>();
         long startTime = System.nanoTime();
         String outputfilename = args[0]; // we get the first args of main 
         DB = (DBScanKD.read(outputfilename)); // we read our file
         DBScanKD scanner;
          scanner = new DBScanKD(DB); // we create a new dbscan instance and we get all the information we need
          String neweps = args[1]; // we get the second args of main and assign it to eps
          String newminpts = args[2]; // we get the third args of main and assign it to minpts
          scanner.setEps(Double.parseDouble(neweps));
          scanner.setMinPts(Integer.parseInt(newminpts));
          scanner.findClusters();
          scanner.save(outputfilename); // then we save it into our new file
         
          long endTime = System.nanoTime();
          long duration = (endTime - startTime)/1000000; // in milliseconds
         
          System.out.println("the time the DBScan takes using the KDTree method is "+ duration + " milliseconds");
     }
    
}
