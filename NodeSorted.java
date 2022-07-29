
import java.util.Comparator;

public class NodeSorted implements Comparator<Node> {

	@Override
	// sorted by names
	/*
	 * public int compare(Node node1, Node node2) { return
	 * node1.getName().compareTo(node2.getName()); }
	 */
	// sorted by values

	public int compare(Node n, Node n1) {

		// string variables
		String s1 = (n.getValue());
		String s2 = (n1.getValue());

		// convert String into Double
		double valueNumber1 = srtIntoInt(s1);
		double valueNumber2 = srtIntoInt(s2);

		if (valueNumber1 < valueNumber2)
			return -1;
		else if (valueNumber1 > valueNumber2)
			return 1;

		else
			return n.getName().compareTo(n1.getName());

	}

	// convert string into double
	private Double srtIntoInt(String s) {
		double num = 0;
		try {

			num = Double.parseDouble(s);

		} // end of try
		catch (NumberFormatException e) {
			e.printStackTrace();

		} // end of catch

		return num;

	} // end of method

}// end of Class
