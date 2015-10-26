package simulateurDeFoule;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 Graph g = new Graph(48, 19);	  
		// Graph g = new Graph(4, 4);	        
	        g.setNodeInArray();
	       // g.setWall();
	        //System.out.println(g.node[1][1].toString());
	        //System.out.println(g.node[0][0].toString());
	        /*if(g.node[18][0].getChar() == '*')
	        	System.out.println("yep");
	        else System.out.println("nope");*/
	        //System.out.println(g.node[47][0]);
	        //System.out.println(g.node[0][18].toString());
	        List<GenericNode> path = g.findPath(17,5,5,2);
	       // List<GenericNode> path = g.findPath(0,0,3,3);
        for (int i = 0; i < path.size(); i++) {
           // System.out.print("(" + path.get(i).getXValue() + ", " + path.get(i).getYValue() + ") -> ");
            System.out.print("(" + path.get(i).getXValue() + ", " + path.get(i).getYValue() + ") -> ");
            //System.out.print("(" + path.get(i).toString());
        }
	}

}
