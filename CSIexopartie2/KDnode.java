/*
 * This class is KDnode, I seperated it from KDTRee for organnisational reasons but it shouldnt change anything in
 * the functionning of the code. MY name Is IChrak and my student num is 300217634
 */
public class KDnode {
    /*here we intialize all the attributes */
    public Point3D point;
    public int axis;
    public double value;
    public KDnode left; 
    public KDnode right;

    public KDnode(Point3D pt, int axis) {//here is our constructor
        this.point= pt; 
        this.axis= axis;
        this.value= pt.get(axis);
        left= null;
        right= null;
        }
    }
