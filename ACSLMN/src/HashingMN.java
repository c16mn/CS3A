import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * 
 * @author Makoto Nara
 * 
 * Hashing FACSL
 * 
 * Test Data: 46, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z
 *
 */
public class HashingMN {
	public static void main(String[] args) {
		//declarations
		Scanner scan = new Scanner(System.in);
		while(true){
			String input = scan.nextLine();
			String[] inputArray = input.split(", ");
			int size = inputArray.length - 1;
			int hashsize = Integer.parseInt(inputArray[0]);
			Node[] hash = new Node[hashsize];
			int[] depths = new int[hashsize];
			for(int i = 0; i < size; i ++){
				int place = hFunc(inputArray[i+1]) % hashsize;
				depths[place] ++;
				if(hash[place] == null){
					hash[place] = new Node(inputArray[i+1]);
				}else{
					add(hash[place], inputArray[i+1]);
				}
			}
			//Collision Count
			NumberFormat df1 = new DecimalFormat("#######0");
			df1.setRoundingMode(RoundingMode.HALF_UP);
			double collisions = 0;
			for(int i=0; i<depths.length; i++){
				if(depths[i] > 1) collisions = collisions + depths[i] - 1;
			}
			System.out.println("1. " + df1.format(collisions));
			
			//Collision Rate
			NumberFormat df2 = new DecimalFormat(".00");
			df2.setRoundingMode(RoundingMode.HALF_UP);
			double inputlength = inputArray.length-1;
			//System.out.println(inputlength);
			System.out.println("2. " + df2.format(collisions/inputlength));
			
			//Table Mask
			System.out.print("3. ");
			double spaces = 0;
			for(int i=0; i<depths.length; i++){
				if(depths[i] > 0) {System.out.print("x"); spaces++;}
				else System.out.print("-");
			}System.out.println("");
			
			//Max chain depth
			int max = 0;
			for (int i = 0; i < depths.length; i++) {
				if(1 < depths[i]) max = depths[i] - 1;
			}
			System.out.println("4. " + max);
			
			//Load Percentage
			NumberFormat df3 = new DecimalFormat("###");
			df3.setRoundingMode(RoundingMode.HALF_UP);
			System.out.println("5. " + df3.format(spaces/hashsize*100) + "%");
		}
		
	}
	//hash function
	public static int hFunc(String x){
		//checks for upper case (despite it not being in the "flavor"
		x.toLowerCase();
		//converts to char and then to int. Probably works from String to int but I know this way works
		char val = x.charAt(0);
		int ret = val;
		ret = ret-97;
		return ret;
	}
	
	public static void add(Node start, String c){
		if(start.link == null){
			start.link = new Node(c);
		}else{
			add(start.link, c);
		}
	}
}

class Node {
	public Node(String c) {
		value = c;
	}
	String value;
	Node link;
}
