public class KDTree {
    
    private KDnode root;

    // construct empty tree
    public KDTree() {
        root= null;
        }   
    public KDnode getroot(){
        return root;
    }
    public KDnode insert(Point3D P, KDnode node, int axis) { //the insert method that inserts nuumbers in our tree
        if (node == null){            //if the node is null
            node = new KDnode(P, axis);//create new node
        }
        else if (P.get(axis) <= node.value){ // gets value
            node.left = insert(P, node.left, (axis+1) % 3);
        }
        else node.right = insert(P, node.right, (axis+1) % 3);
        
        return node;
    }

    public void add(Point3D point){
        root = insert(point, root, 0); // we start inserting our numbers from the root node
    }
}
