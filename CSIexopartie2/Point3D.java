/* the class point3d is a pretty generic class, its not really related to our dbscan but is still necessary for its 
 correct functionning. Our function creates an instance which is a point in 3d, which means it has 3 coordinates
 x,y and z and it also  computes the distance between 2 points.*/
public class Point3D {

    private double X; 
    private double Y;  
    private double Z;  
    public int cluster;  // the cluster is just the label and 0 means undefined, -1 means noise

    public Point3D(double X, double Y, double Z){ // the constructor 
        this.X = X;
        this.Y = Y;
        this.Z = Z;
        cluster=0;
    }
// all these methods are getter for our variables 
    public double getX(){
        return X;}

    public double getY(){
        return Y;}
    
    public double getZ(){
        return Z;}

    public int getcluster(){
        return cluster;}
    
     
  //a distance method that computes the Euclidean distance between two points
  public double distance(Point3D pt){    
    double deltaX = getX() - pt.getX();
    double deltaY = getY() - pt.getY();
    double deltaZ = getZ() - pt.getZ();
    
    return Math.sqrt((Math.pow(deltaX, 2) + Math.pow(deltaY , 2)+Math.pow(deltaZ , 2)));}

    public double get(int axis){
        double value = X;
        switch(axis) {
            case 0:
            value = X;
              break;
            case 1:
            value = Y;
              break;
            case 2:
            value = Z;
                break;
          }
        return value;  
    }
    
    // the toString java method
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.valueOf(getX())+","+String.valueOf(getY())+","+String.valueOf(getZ());
    }
}
