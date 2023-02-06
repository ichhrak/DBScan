import java.util.ArrayList;
import java.util.List;

/* This class helps us define neighbors, in other words it checks all the radius around our original point and from there
 it gathers all the neighboring points that belong to the circle of the radius eps.*/

public class NearestNeighbors{

    private List<Point3D> liste; 
   
    public NearestNeighbors(List<Point3D> liste){ // here is the constructor of our class it takes a liste of point3d as a 
                                          // parameter
        this.liste = liste;}

 /* the following method goes through the elements of our list and compares the value of eps to the distance between
 the point in the sequence and the one in the parameter of our function */       
    public List<Point3D> rangeQuery(Point3D Q, double eps){
        
        List<Point3D> N = new ArrayList<>();
        for (Point3D p : liste) { // Scan all points in DB 
            if(p.distance(Q) <= eps){// Compute distance 
                N.add(p); //Add to result 
            }
        }
        return N;
    }
}
