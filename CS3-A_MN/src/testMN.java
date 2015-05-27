import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class testMN {
	public static void main(String[] args) {
		//tree();
		//listtester();
		//arraycopytester();
		//compare();
		//listSize();
		//randomTester();
		charValues("K");
	}
	public static void charValues(String c){
		System.out.println(Integer.parseInt(c));
	}
	public static void randomTester(){
		Random gen = new Random();
		for (int i = 0; i < 50; i++) {
			System.out.println(gen.nextInt(100));
		}
	}
	public static void arraycopytester(){
		int[] test1 = {
			1, 2, 3, 4, 5, 6, 7, 8, 9, 10	
		};
		int[] test2 = new int[10]; 
		System.arraycopy(test1, 0, test2, 0, test1.length);
		for (int i = 0; i < test2.length; i++) {
			System.out.print(test2[i] + ", ");
		}
		int[][] jesus = new int[3][6];
		System.out.println(jesus.length);
	}
	
	public static void listtester(){
		List<Integer> test = new LinkedList<Integer>();
		test.add(10);
		test.add(0, 1);
		test.add(0, 100);
		System.out.println(test);
	}
	
	public static void tree(){
		double starter = 48;
		double height = 72;
		double interval = 3;
		double spaces = height/interval;
		double inspace = starter/spaces;
		double acc = 0;
		for (int i = 0; i < spaces; i++) {
			acc = acc + 2*(starter/2)*3.1415;
			starter = starter - inspace;
		}
		System.out.println(acc/12);
		
	}
	public void checker(){
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		String[] values = input.split(", ");
		int[] intvals = new int[values.length];
		int troll = 0;
		for (int i = 0; i < values.length; i++) {
			intvals[i] = Integer.parseInt(values[i]);
		}for (int i = 0; i < intvals.length - 1; i++) {
			int checker = intvals[i] + 1;
			if(intvals[i+1] != checker){
				System.out.println(checker);
				troll++;
			}
		}
		if(troll == 0) System.out.println("nothing is missing");
	}
	public static void compare(){
		String jesus = "jesus";
		String jesuz = "jesuz";
		System.out.println(jesus.compareToIgnoreCase(jesuz));
	}
	public static void listSize(){
		List<String> test = new ArrayList<String>();
		test.add("moo");
		test.add("tuna");
		test.add("cake");
		for (int i = 0; i < test.size(); i++) {
			System.out.println(test.get(i));
			test.remove(i);
		}
	}
}
