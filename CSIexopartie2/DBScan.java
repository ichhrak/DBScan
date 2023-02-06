import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;


/* So this class is the DBScan class, in it you're going to find all the DBScan methods that are originally implemented 
in the algorithm. A brief description of the DBScan is that it's a data clustering algorithm 'DBSCAN' -
Density-Based Spatial Clustering of Applications with Noise. Given a large set of data points in a space
of arbitrary dimension and given a distance metric, this algorithm can discover clusters of different
shapes and sizes, marking as outliers isolated points in low-density regions (i.e. points whose nearest
neighbors are too far away).*/

public class DBScan{

    private List<Point3D> DB, N; //DB is the original sequence of point3d that we will analyze and N is the one that stores
                        // the neighboring points 
    Point3D Q;
    double eps; // the radius for the points to be considered neighbors
    int minPts, C; // minpts is the minimum number to form a group and C is the number of clusters
    NearestNeighbors nb; // we initialize a new Neighbor instance that we're going to use later on
    Stack<Point3D> S = new Stack<Point3D>(); // the stack we will use in the method findcluster
    
    public DBScan(List<Point3D> DB){ // the constructor where we initialize DB and C
        this.DB = DB; 
        this.C = 0; // C is 0 at the begining 
    }

    public void setEps(double eps){ //the setter for eps where we set the value of eps
        this.eps=eps;}

    public void setMinPts(int minPts){ // setter for minpts where we set the valdue of minpts
        this.minPts=minPts; }

    /* The following method is basically the core of this algorithm, it is through this method that the grouping
     of points occurs I will try my best to describe each step of the method but in general, what it does is go 
     through our sequence and analyze every point, then label it either noise or attribute it to a certain cluster.*/

    public void findClusters(){ 

        nb = new NearestNeighbors(DB); //we give the Neighbor instance that we initialized earlier DB as a parameter
        
        for ( Point3D P: DB){// we're gonna loop through all the sequence and analyze every point
            int cluster = P.getcluster(); // cluster is basically the label of P and it when its cluster=0, it means 
                              //that the label is still undefined and cluster=-1 means its noise
            if(cluster > 0){ //if the label is not undefined (aka if the label is defined) then just continue the loop
                continue;}

             N = nb.rangeQuery(P, eps); // we create a new sequence that we initialez in the begining

            if (N.size() < minPts){ // this condition is to remove noise
                P.cluster= -1; // -1 means noise
                continue;}

            C ++; 
            P.cluster= C; // we add our point to our group by labeling it
            
            for(Point3D i : N){ // we create a stack where we add all the elements of N
                S.push(i);
            }
            while (!S.empty()){
                Q = S.pop();
                int cl = Q.getcluster(); // we get the label of each element 

                if (cl== -1){
                    cl = C;} // if it's noise  we're gonna label it the cluster of the group

                if (cl != 0){ // if its defined we just continue
                    continue;}

                Q.cluster = C;
                N = nb.rangeQuery(Q, eps);

                if (N.size() >= minPts){ // we check if the number of points in the sequence is greater than min pts
                    for(Point3D i : N){
                        S.push(i);} // we add the pts in the stack
                }
            }
        }
    }


    public int getNumberOfClusters(){ // this method gets us the number of clusters
        return C; }

    public List<Point3D> getPoints(){ // this method gets us the sequence containing our 3d points
        return DB;}

    /*The following method simply reads our csv file, it first dismisses the first line since it doesnt contain data
    * and then it starts reading the file line by line and then analyzes each line where we take the x,y and z 
    * coordinated that are separated by a comma.*/

    public static List<Point3D> read(String filename) throws IOException 
    {			
          List<Point3D> reading = new ArrayList<Point3D>();

          try{
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                reader.readLine(); // here wwe skip the first line
                reader.readLine(); // now were on to the second
                String line = reader.readLine(); // we store our lines in a string

                while (line!= null){ 
                    String[] cord = line.split(","); // we take every coordinate seperated by a comma 
                    reading.add(new Point3D(Double.parseDouble(cord[0]), Double.parseDouble(cord[1]), Double.parseDouble(cord[2])));
                    line = reader.readLine(); // we cast our numbers into doubles and add them in our sequence then 
                    // we move on to the next line    
                }
            }
            catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();}
          }

          catch (FileNotFoundException e){// when the file isnt found you get an exception
            throw e;
          }
          return reading;
    }

    /*This method here generates a random float number so that we can assign it to the RGB values */

    private double[][] randomcolor(){ 
        int nbofclusters = getNumberOfClusters(); // we store the number of clusters in an integer
        double[][] valeur = new double[nbofclusters][3]; // we create a double array where we're gonna assign each cluster
        // to an RGB value 
        Random rd = new Random(); // creating Random object
        
        for (int i =0; i< nbofclusters; i++){ // this loop generates a random float for the clusters
            valeur[i][0] = Math.round(rd.nextDouble()*100.0)/100.0;
            valeur[i][1] = Math.round(rd.nextDouble()*100.0)/100.0;
            valeur[i][2] = Math.round(rd.nextDouble()*100.0)/100.0;
        }     
        return valeur;
    }

/*This method saves our data that we read with our read method in a new csv file, except this time the file is gonna
 * be well color coordinated and organized by clusters. */
    
 public void save(String filename) throws FileNotFoundException{
     
        double[][] RGB = randomcolor(); // here is s double array where we have every cluster assigned to a random
        //combination of colors red green and blue
        OutputStreamWriter out = null;
        
        try{
            int x; // all were doing here is first naming our file that were gonna create 
            out = new OutputStreamWriter(new FileOutputStream(filename+"_clusters_"+eps+"_"+minPts+"_"+C+ ".csv"));
            out.write("  x  ,  y  ,  z  ,  C  ,  R  ,  G  ,  B\n"); // we print the first line

            for (Point3D pt3d : DB){
                if (pt3d.getcluster() == -1){ // if its noise its gonna be white
                    out.write(pt3d.toString()+",0.0,0.0,0.0\n");
                }
                else {
                    x = pt3d.getcluster()-1; 
                    out.write(pt3d.toString()+","+RGB[x][0]+","+RGB[x][1]+","+RGB[x][2]+"\n"); // we assign the colors
                    // to the points
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();}
    }

    
     public static void main(String[] args) throws IOException {

        List<Point3D> DB = new ArrayList<>();
        long startTime = System.nanoTime();
        String outputfilename = args[0]; // we get the first args of main 
        DB = (DBScan.read(outputfilename)); // we read our file
        DBScan scanner;
         scanner = new DBScan(DB); // we create a new dbscan instance and we get all the information we need
         String neweps = args[1]; // we get the second args of main and assign it to eps
         String newminpts = args[2]; // we get the third args of main and assign it to minpts
         scanner.setEps(Double.parseDouble(neweps));
         scanner.setMinPts(Integer.parseInt(newminpts));
         scanner.findClusters();
         scanner.save(outputfilename); // then we save it into our new file
         long endTime = System.nanoTime();
         long duration = (endTime - startTime)/1000000; // in milliseconds
        
         System.out.println("the time the DBScan takes using the linear method is "+ duration + " milliseconds");
        
     }
 } 


