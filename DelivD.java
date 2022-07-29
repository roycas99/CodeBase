import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class DelivD {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;

	// Constructor - DO NOT MODIFY
	public DelivD(File in, Graph gr) {
		inputFile = in;
		graph = gr;

		// Set up for writing to a file
		try {
			// Use input file name to create output file in the same location
			String inputFileName = inputFile.toString();
			String outputFileName = inputFileName.substring(0, inputFileName.length() - 4).concat("_out.txt");
			outputFile = new File(outputFileName);

			// A Printwriter is an object that can write to a file
			output = new PrintWriter(outputFile);
		} catch (Exception x) {
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}

		// Calls the method that will do the work of deliverable D
		runDelivD();

		output.flush();
	}

	// *********************************************************************************
	// This is where your work starts

	private void runDelivD() {
		// sort nodes by their values
		NodeSorted ns = new NodeSorted();
		Collections.sort(graph.getNodeList(), ns);
		int size = graph.getNodeList().size();

		// golden rule: i < j
		int i = size - 2;
		int j = size - 1;

		// last city
		// before last city
		// System.out.println(graph.getNodeList().get(i));
		// System.out.println(graph.getNodeList().get(j));

		// distance for Bitonic.
		System.out.print("Shortest bitonic tour has distance ");
		System.out.println(bitonic(i, j) + dist(graph.getNodeList().get(i), graph.getNodeList().get(j)));

		// print tour path
		print_path();

	} // end of runDelivD method

	// Bitonic method

	public int bitonic(int i, int j) {

		// base case: if we only have 2 point(i,j) then we return their distance
		if (i == 0 && j == 1) {
			// predecessor is graph.getNodeList().get(i))
			graph.getNodeList().get(j).setPreviousNode(graph.getNodeList().get(i));
			return dist(graph.getNodeList().get(i), graph.getNodeList().get(j));

		}

		// second case if i is less than j-1
		else if (i < j - 1) {

			// predecessor is Pj = graph.getNodeList().get(j-1)

			graph.getNodeList().get(j).setPreviousNode(graph.getNodeList().get(j - 1));

			return bitonic(i, j - 1) + dist(graph.getNodeList().get(j - 1), graph.getNodeList().get(j));

		}

		// third case if i and j are equal or i = j-1

		else {
			int min = Integer.MAX_VALUE;
			int temp;
			for (int k = 0; k < j - 1; k++) {

				temp = (bitonic(k, j - 1) + dist(graph.getNodeList().get(k), graph.getNodeList().get(j)));
				// predecssor=graph.getNodeList().get(k)

				// ternary Operator
				if (temp < min) {
					min = temp;
					graph.getNodeList().get(j).setPreviousNode(graph.getNodeList().get(k));

				} // end of If

			} // end of for loop;

			return min;

		} // end of Else

	} // end of Bitonic_Travel

	// distance method between 2 nodes

	public int dist(Node p, Node p2) {

		int f = 0;
		for (Edge e : p.getOutgoingEdges()) {

			if (e.getHead().equals(p2)) {

				return f = e.getDistance();

			}

		}

		return f;

	} // end of distance method

	// tour path
	public void print_path() {
		int size = graph.getNodeList().size();

		// find the path/ tour : I just hardcode
		System.out.print("Tour is ");

		// building/ getting tour of upper path

		Node cursor = graph.getNodeList().get(size - 1);
		while (!cursor.equals(graph.getNodeList().get(0))) {

			cursor.setColor("RED");  // just flag
			System.out.print(cursor.formatMSP());
			cursor = cursor.getPreviousNode();  // acting like link

		}
         // print the lower path/ rest of tour
		for (Node n : graph.getNodeList()) {

			if (n.getColor() == null) {
				System.out.print(n.formatMSP());
			}

		}
       // coming home!!!
		System.out.print(graph.getNodeList().get(size - 1).getAbbrev());

	} // end of print_Path

} // end of DelivC class
