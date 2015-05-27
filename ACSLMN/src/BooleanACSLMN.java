import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BooleanACSLMN {
	public static void main(String[] args) {
		//declarations
		testing();
		Scanner scan = new Scanner(System.in);
//		while(true){
//			for (int i = 1; i <= 5; i++) {
//				String[] input = real(scan);
//				analyze(input, i);
//			}
//		}
	}//close main
	static void analyze(String[] input, int numinputs){
		int[] indices = {
				0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4	
		};
		List<Integer> zero = new ArrayList<Integer>();
		List<Integer> one = new ArrayList<Integer>();
		List<Integer> two = new ArrayList<Integer>();
		List<Integer> three = new ArrayList<Integer>();
		List<Integer> four = new ArrayList<Integer>();
		List<String> cero = new ArrayList<String>();
		List<String> uno = new ArrayList<String>();
		List<String> dos = new ArrayList<String>();
		List<String> tres = new ArrayList<String>();
		List<String> cuatro = new ArrayList<String>();
		List<String> simpairs = new ArrayList<String>();
		List<Boolean> boolcero = new ArrayList<Boolean>();
		List<Boolean> booluno = new ArrayList<Boolean>();
		List<Boolean> booldos = new ArrayList<Boolean>();
		List<Boolean> booltres = new ArrayList<Boolean>();
		List<Boolean> boolcuatro = new ArrayList<Boolean>();
		List<List<Integer>> simpairnums = new ArrayList<List<Integer>>();
		List<String> extsimpairs = new ArrayList<String>();
		for (int i = 0; i < input.length - 1; i++) {
			if(indices[Integer.parseInt(input[i])] == 0) zero.add(Integer.parseInt(input[i]));
			if(indices[Integer.parseInt(input[i])] == 1) one.add(Integer.parseInt(input[i]));
			if(indices[Integer.parseInt(input[i])] == 2) two.add(Integer.parseInt(input[i]));
			if(indices[Integer.parseInt(input[i])] == 3) three.add(Integer.parseInt(input[i]));
			if(indices[Integer.parseInt(input[i])] == 4) four.add(Integer.parseInt(input[i]));
		}
		for (int h = 0; h < zero.size(); h++) {
			for (int l = 0; l < one.size(); l++) {
				if(onePlaceDiff(toBinary(zero.get(h), numinputs), toBinary(one.get(l), numinputs)) == true){
					simpairs.add(combine(toBinary(zero.get(h), numinputs), toBinary(one.get(l), numinputs)));
					List<Integer> temp = new ArrayList<Integer>();
					temp.add(zero.get(h));
					temp.add(one.get(l));
					simpairnums.add(temp);
				}
			}
		}
		for (int i = 0; i < one.size(); i++) {
			for (int l = 0; l < two.size(); l++) {
				if(onePlaceDiff(toBinary(one.get(i), numinputs), toBinary(two.get(l), numinputs)) == true){
					simpairs.add(combine(toBinary(one.get(i), numinputs), toBinary(two.get(l), numinputs)));
					List<Integer> temp = new ArrayList<Integer>();
					temp.add(one.get(i));
					temp.add(two.get(l));
					simpairnums.add(temp);
				}
			}
		}
		for (int j = 0; j < two.size(); j++) {
			for (int l = 0; l < three.size(); l++) {
				if(onePlaceDiff(toBinary(two.get(j), numinputs), toBinary(three.get(l), numinputs)) == true){
					simpairs.add(combine(toBinary(two.get(j), numinputs), toBinary(three.get(l), numinputs)));
					List<Integer> temp = new ArrayList<Integer>();
					temp.add(two.get(j));
					temp.add(three.get(l));
					simpairnums.add(temp);
				}
			}
		}
		for (int k = 0; k < three.size(); k++) {
			for (int l = 0; l < four.size(); l++) {
				if(onePlaceDiff(toBinary(three.get(k), numinputs), toBinary(four.get(l), numinputs)) == true){
					simpairs.add(combine(toBinary(three.get(k), numinputs), toBinary(four.get(l), numinputs)));
					List<Integer> temp = new ArrayList<Integer>();
					temp.add(three.get(k));
					temp.add(four.get(l));
					simpairnums.add(temp);
				}
			}
		}
		//System.out.println(simpairs);
		for (int i = 0; i < simpairs.size(); i++) {
			if(indices[simpairnums.get(i).get(0)] == 0){
				cero.add(simpairs.get(i));
				boolcero.add(false);
			}
			if(indices[simpairnums.get(i).get(0)] == 1){
				uno.add(simpairs.get(i));
				booluno.add(false);
			}
			if(indices[simpairnums.get(i).get(0)] == 2){
				dos.add(simpairs.get(i));
				booldos.add(false);
			}
			if(indices[simpairnums.get(i).get(0)] == 3){
				tres.add(simpairs.get(i));
				booltres.add(false);
			}
			if(indices[simpairnums.get(i).get(0)] == 4){
				cuatro.add(simpairs.get(i));
				boolcuatro.add(false);
			}
		}
		for (int m = 0; m < cero.size(); m++) {
			for (int p = 0; p < uno.size(); p++) {
				if(onePlaceDiff(cero.get(m), uno.get(p)) == true && noOverlap(simpairnums, simpairs, cero.get(m), uno.get(p)) == true){
					extsimpairs.add(combine(cero.get(m), uno.get(p)));
					boolcero.set(m, true);
					booluno.set(p, true);
				}
			}
		}
		for (int m = 0; m < uno.size(); m++) {
			for (int p = 0; p < dos.size(); p++) {
				if(onePlaceDiff(uno.get(m), dos.get(p)) == true && noOverlap(simpairnums, simpairs, uno.get(m), dos.get(p)) == true){
					extsimpairs.add(combine(uno.get(m), dos.get(p)));
					booluno.set(m, true);
					booldos.set(p, true);
				}
			}
		}
		for (int n = 0; n < dos.size(); n++) {
			for (int p = 0; p < tres.size(); p++) {
				if(onePlaceDiff(dos.get(n), tres.get(p)) == true && noOverlap(simpairnums, simpairs, dos.get(n), tres.get(p)) == true){
					extsimpairs.add(combine(dos.get(n), tres.get(p)));
					booldos.set(n, true);
					booltres.set(p, true);
				}
			}
		}
		for (int o = 0; o < tres.size(); o++) {
			for (int p = 0; p < cuatro.size(); p++) {
				if(onePlaceDiff(tres.get(o), cuatro.get(p)) == true  && noOverlap(simpairnums, simpairs, tres.get(o), cuatro.get(p)) == true){
					extsimpairs.add(combine(tres.get(o), cuatro.get(p)));
					booltres.set(o, true);
					boolcuatro.set(p, true);
				}
			}
		}
		for (int i = 0; i < extsimpairs.size(); i++) {
			for (int k = 0; k < extsimpairs.size(); k++) {
				if(i != k && extsimpairs.get(i).equals(extsimpairs.get(k))){ 
					extsimpairs.remove(k--);
				}
			}
		}
		for (int i = 0; i < cero.size(); i++) {
			if(boolcero.get(i) == false) extsimpairs.add(cero.get(i));
		}
		for (int i = 0; i < uno.size(); i++) {
			if(booluno.get(i) == false) extsimpairs.add(uno.get(i));
		}
		for (int i = 0; i < dos.size(); i++) {
			if(booldos.get(i) == false) extsimpairs.add(dos.get(i));
		}
		for (int i = 0; i < tres.size(); i++) {
			if(booltres.get(i) == false) extsimpairs.add(tres.get(i));
		}
		for (int i = 0; i < cuatro.size(); i++) {
			if(boolcuatro.get(i) == false) extsimpairs.add(cuatro.get(i));
		}
//		System.out.println("New Output: ");
//		System.out.println(simpairs);
//		System.out.println(extsimpairs);
		System.out.println(toLetters(extsimpairs));
//		else{
//			System.out.println(toLetters(simpairs));
//		}
	}
	static String toBinary(int value, int numinputs){
		String bin = Integer.toBinaryString(value);
		if(numinputs < 4){
			while(bin.length() < 3){
				bin = "0" + bin;
			}
		}else{
			while(bin.length() < 4){
				bin = "0" + bin;
			}
		}
		return bin;
	}
	static boolean onePlaceDiff(String a, String b){
		int x = 0;
		for (int i = 0; i < a.length(); i++) {
			if(a.charAt(i) != b.charAt(i)) x++;
			if(a.charAt(i) != b.charAt(i) && (a.charAt(i) == 'x' || b.charAt(i) == 'x')) return false;
			if(x == 2) return false;
		}
		return true;
	}
	static boolean noOverlap(List<List<Integer>> nums, List<String> simpairs, String a, String b){
		int x = 0;
		int y = 0;
		for (int i = 0; i < simpairs.size(); i++) {
			if(simpairs.get(i) == a) x = i;
			if(simpairs.get(i) == b) y = i;
		}
		for (int i = 0; i < nums.get(x).size(); i++) {
			for (int j = 0; j < nums.get(y).size(); j++) {
				if(nums.get(x).get(i) == nums.get(y).get(j)) return false;
			}
		}
		return true;
	}
	static String combine(String a, String b){
		String result = null;
		for (int i = 0; i < a.length(); i++) {
			if(i == 0 && a.charAt(i) == b.charAt(i)) result = Character.toString(a.charAt(i));
			else if(i == 0 && a.charAt(i) != b.charAt(i)) result = "x";
			else if(a.charAt(i) != b.charAt(i)) result = result + "x"; 
			else result = result + Character.toString(a.charAt(i));
		}
		return result;
	}
	static String toLetters(List<String> ext){
		List<String> result = new ArrayList<String>();
		mergeSort(ext);
		for (int i = 0; i < ext.size(); i++) {
			for (int j = 0; j < ext.get(i).length(); j++) {
				if(ext.get(i).charAt(j) == '0'){
					result.add(Character.toString((char) (Character.valueOf(ext.get(i).charAt(j)) + 49 + j)));
				}else if(ext.get(i).charAt(j) == '1'){
					result.add(result + Character.toString((char) (Character.valueOf(ext.get(i).charAt(j)) + 16 + j)));
				}
			}
			if(i != ext.size() -1) result.add(" + ");
		}
		return result.toString();
	}
	static List<String> mergeSort(List<String> input){
		if(input.size() <= 1) return input;
		List<String> first = new ArrayList<String>();
		List<String> second = new ArrayList<String>();
		for (int i = 0; i < input.size()/2; i++) {
			first.add(input.get(i));
		}
		for (int i = 0; i < input.size() - input.size()/2; i++) {
			second.add(input.get(i + input.size()/2));
		}
		mergeSort(first);
		mergeSort(second);
		merge(first, second, input);
		return input;
	}
	static List<String> merge(List<String> first, List<String> second, List<String> input){
		int j = 0;
		int k = 0;
		for (int i = 0; i < input.size(); i++) {
			if(k == second.size() || j != first.size() && first.get(j).compareToIgnoreCase(second.get(k)) < 0){
				input.set(i, first.get(j));
				j++;
			}else if(j == first.size() || second.get(k).compareToIgnoreCase(first.get(j)) <= 0){
				input.set(i, second.get(k));
				k++;
			}
		}
		return input;
	}
	static void testing(){
		Path source = Paths.get("/Users/student/Documents/workspace/ACSLMN/BooleanExpressions");
		try {
			BufferedReader bf = Files.newBufferedReader(source, StandardCharsets.UTF_8);
			int i = 1;
			for(String s = bf.readLine(); s != null; s = bf.readLine()){
				String[] input = s.split(", ");
				analyze(input, i);
				i++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static String[] real(Scanner scan){
		String inputString = scan.nextLine();
		String[] input = inputString.split(", ");
		return input;
	}
}