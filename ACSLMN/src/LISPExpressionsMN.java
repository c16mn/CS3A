import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//(ADD (EXP -3 2) (SQR 5) (SUB 6 2) (MULT 6 7 -2 3) (DIV 15 5))
//

public class LISPExpressionsMN {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int count = 0;
		String input = scan.nextLine();
		List<List<Integer>> listolists = new ArrayList<List<Integer>>();
		List<String> exps = new ArrayList<String>();
		input = input.replaceFirst("[(]", "");
		input = input.replace(")", "");
		String[] inputarr = input.split(" ");
		//System.out.println(input);
		//handling numbering
		int m = -1;
		for (int i = 0; i < inputarr.length; i++) {
			if(inputarr[i].charAt(0) == '('){
				m++;
				listolists.add(new ArrayList<Integer>());
				inputarr[i] = inputarr[i].replace("(", "");
				exps.add(inputarr[i]);
			}else if(i != 0 && m != -1){
				listolists.get(m).add(Integer.valueOf(inputarr[i]));
			}
		}
		if(m == -1){
			listolists.add(new ArrayList<Integer>());
			for (int i = 1; i < inputarr.length; i++) {
				listolists.get(0).add(Integer.valueOf(inputarr[i]));
			}
			for (int i = 0; i < listolists.get(0).size(); i++) {
				System.out.print(listolists.get(0).get(i) + " ");
			}
			System.out.println();
		}
		//		for (int i = 0; i < exps.size(); i++) {
		//			System.out.print(exps.get(i));
		//			for (int n = 0; n < listolists.get(i).size(); n++) {
		//				System.out.print(" " + listolists.get(i).get(n));
		//			}
		//			System.out.println();
		//		}
		List<String> holder = new ArrayList<String>();
		for (int i = 0; i < exps.size(); i++) {
			holder.add(exps.get(i));
		}
		List<List<Integer>> intholder = new ArrayList<List<Integer>>();
		for (int i = 0; i < exps.size(); i++) {
			intholder.add(listolists.get(i));
		}
		while(true){
			//System.out.println(exps);
			int j = 0;
			int k = 0;
			String command = scan.nextLine();
			String[] param = command.split(" ");
			if(param.length == 3){
				j = Integer.valueOf(param[1]) - 1;
				k = Integer.valueOf(param[2]) - 1;
			}
			//handling count
			if(param[0].equalsIgnoreCase("COUNT")){
				for (int i = 0; i < input.length(); i++) {
					if(input.charAt(i) == '(') count ++;
				}
				if(count == 0){
					for (int i = 0; i < input.length(); i++) {
						if(Character.valueOf(input.charAt(i)) > 47 && Character.valueOf(input.charAt(i)) < 58) count++;
					}			
				}
				System.out.println(count);
			}
			if(param[0].equalsIgnoreCase("MAXIMUM")){
				//handling maximum
				if(listolists.size() == 1){
					int x = 0;
					for (int i = 0; i < listolists.get(0).size(); i++) {
						if(listolists.get(0).get(i) > x) x = listolists.get(0).get(i);
					}
					System.out.println(x);
				}else{
					int x = 0;
					int y = 0;
					for (int i = 0; i < exps.size(); i++) {
						if(listolists.get(i).size() > x){
							x = listolists.get(i).size();
							y = i;
						}
					}
					System.out.print("(" + exps.get(y));
					for (int i = 0; i < listolists.get(y).size(); i++) {
						System.out.print(" " + listolists.get(y).get(i));
					}
					System.out.println(")");
				}
			}
			//handling sort
			if(param[0].equalsIgnoreCase("SORT")){
				List<String> temp = new ArrayList<String>();
				List<String> temp2 = new ArrayList<String>();
				List<List<Integer>> tempints = new ArrayList<List<Integer>>();
				for (int i = j; i <= k; i++) {
					temp.add(exps.get(i));
					temp2.add(exps.get(i));
					tempints.add(listolists.get(i));
				}
				mergeSort(temp);
				//handling moving numbers
				int l = 0;
				for (int i = j; i <= k; i++) {
					for (int n = 0; n < temp2.size(); n++) {
						if(temp.get(l).equals(temp2.get(n))){
							listolists.set(i, tempints.get(n));
							temp2.set(n, "MOO");
							break;
						}
					}
					exps.set(i, temp.get(l));
					l++;
				}
				print(inputarr, exps, listolists);
			}
			//handling reverse
			if(param[0].equals("REVERSE")){
				List<String> temp = new ArrayList<String>();
				List<List<Integer>> tempints = new ArrayList<List<Integer>>();
				for (int i = j; i <= k; i++) {
					temp.add(exps.get(i));
					tempints.add(listolists.get(i));
				}
				int p = 0;
				for (int i = k; i >= j; i--) {
					exps.set(i, temp.get(p));
					listolists.set(i, tempints.get(p));
					int q = 0;
					for (int l = listolists.get(i).size() - 1; l >= 0; l--) {
						listolists.get(i).set(l, tempints.get(p).get(q));
						q++;
					}
					p++;
				}
				//reversing the lists of listolists
				print(inputarr, exps, listolists);
			}
			//handling remove
			if(param[0].equalsIgnoreCase("REMOVE")){
				for (int i = k; i >= j; i--) {
					exps.remove(i);
					listolists.remove(i);
				}
				print(inputarr, exps, listolists);
			}
			//reseting exps
			exps.clear();
			listolists.clear();
			for (int i = 0; i < holder.size(); i++) {
				exps.add(holder.get(i));
			}
			//reseting listolists
			for (int i = 0; i < intholder.size(); i++) {
				listolists.add(intholder.get(i));
			}
			//System.out.println(holder);
		}//close while true
	}//close main

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
	static void print(String[] inputarr, List<String> exps, List<List<Integer>> listolists){
		System.out.print("(" + inputarr[0]);
		for (int i = 0; i < exps.size(); i++) {
			System.out.print(" (" + exps.get(i));
			for (int n = 0; n < listolists.get(i).size(); n++) {
				System.out.print(" " + listolists.get(i).get(n));
			}
			System.out.print(")");
		}
		System.out.println(")");
	}
}
