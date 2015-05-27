import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class RoutingTablesMN {
	public static List<Node> nodes = new ArrayList<Node>();
	public static void main(String[] args) {
		testing();
		for (int i = 0; i < nodes.size(); i++) {
			List<List<String>> listolists = new ArrayList<List<String>>();
			System.out.println(shortestPath(nodes.get(i), listolists, -1));
		}
	}//close main
	static List<List<String>> shortestPath(Node A, List<List<String>> listolists, int depth){
		depth++;
		//System.out.println(depth);
		//checking if listolists already contains A
		for (int i = 0; i < listolists.size(); i++) {
			if(listolists.get(i).contains(A.position)) return listolists;
		}
		if(depth == listolists.size()){
			List<String> holder = new ArrayList<String>();
			listolists.add(holder);
		}
		listolists.get(depth).add(A.position);
		if(A.connections.size() == 0) return listolists;
		for (int i = 0; i < A.connections.size(); i++) {
			listolists = shortestPath(A.connections.get(i), listolists, depth);
		}
		return listolists;
	}
	static void initialize(String input){
		Node newNode = new Node(input.split(": ")[0]);
		String[] connections = input.split(": ")[1].split(" ");
		for (int i = 0; i < nodes.size(); i++) {
			if(nodes.get(i).position.equals(input.split(": ")[0])){
				links(nodes.get(i), connections);
				return;
			}
		}
		links(newNode, connections);
		nodes.add(newNode);
	}
	static void links(Node A, String[] connections){
		for (int i = 0; i < connections.length; i++) {
			Node temp = new Node(connections[i]);
			nodes.add(temp);
			A.connections.add(temp);
		}
	}
	static void testing(){
		Path source = Paths.get("/Users/student/Documents/workspace/CS3-A_MN/RoutingTableData");
		try {
			BufferedReader bf = Files.newBufferedReader(source, StandardCharsets.UTF_8);
			for(String s = bf.readLine(); s != null; s = bf.readLine()){
				initialize(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class Node {
	String position;
	public Node(String c) {
		position = c;
	}
	List<Node> connections = new ArrayList<Node>();
}