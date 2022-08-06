import java.util.Comparator;

public class NodeSortedB  implements Comparator<Node>
{
	// sorted by names
	public int compare(Node n, Node n1) {
		
			return n.getName().compareTo(n1.getName());

	}
	
}
