import java.util.ArrayList;
import java.util.List;

public class NearestNeighborsKD{

    private List<Point3D> neighbors; 
    private KDTree kdtree;


    NearestNeighborsKD(List<Point3D> liste) { // our constructor
        kdtree = new KDTree();
        for (Point3D p : liste) {
            kdtree.add(p); 
        }
    }

    private void rangeQuery(Point3D p, double eps,List<Point3D> neighbors, KDnode node){//the rangequery that we have in 
        //pseudo code

        if (node == null){
            return;
        }
        if (p.distance(node.point) < eps){
            neighbors.add(node.point);
        }
        if (p.get(node.axis) - eps <= node.value){
        rangeQuery(p, eps, neighbors, node.left);
        }
        if (p.get(node.axis) + eps > node.value){
        rangeQuery(p, eps, neighbors, node.right);
        }
        return;
    }

    public List<Point3D> rangeQuery(Point3D p, double eps) {// the rangequery that starts to run and then it runs the other 
        //rangequery above
        neighbors = new ArrayList<>();
        rangeQuery(p, eps, neighbors, kdtree.getroot());// the other rangequery
        return neighbors;
        }
        

}