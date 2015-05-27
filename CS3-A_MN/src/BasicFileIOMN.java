import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BasicFileIOMN {
	public static void main(String[] args) {
		String input = "moo";
		Scanner scan = new Scanner(System.in);
		List<Student> students = new ArrayList<Student>();
		Path source2 = Paths.get("/Users/student/Documents/workspace/CS3-A_MN/all_students_jan.csv");
		File output = new File("/Users/student/Documents/workspace/CS3-A_MN/Output");
		try {
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader bf = Files.newBufferedReader(source2, StandardCharsets.ISO_8859_1);
			bf.readLine();
			int i = 0;
			for(String s = bf.readLine(); s != null; s = bf.readLine()){
				Student temp = new Student(s, i++);
				students.add(temp);
			}
			//input handling
			while(!input.equalsIgnoreCase("exit")){
				//file.flush();
				System.out.println("Type sort by: ____ to sort all students by your preference.\nThe options are first name, middle name, last name, gender,"
						+ "\ngrade entered, class year, current grade, birthday, and house advisor\nAll sorts are written to the file 'Output'\nType twins to see a list of twins\n"
						+ "type grade entered count to see totals for how many students entered in each grade\n"
						+ "Type top __ first/middle/last name to see the __ most common first/middle/last name.\nFor example: type top 12 last names to see the 12 most common last names\n"
						+ "Type gender count to see the split between males and females\nType students born in ____ to see the students born in a given year\n"
						+ "Type first name: ____ to see all students with that first name\nType last name: _____ to see students with that last name\n"
						+ "Type exit to stop the program");
				input = scan.nextLine();
				output.delete();
				output.createNewFile();
				//handling firstname
				if(input.toLowerCase().contains("sort by: ")){
					input = input.split(": ")[1];
					switch(input){
					case "first name":
						mergeSort(students, "firstname", 0);
						break;
					case "last name":
						mergeSort(students, "lastname", 0);
						break;
					case "middle name":
						mergeSort(students, "middlename", 0);
						break;
					case "gender":
						mergeSort(students, "sex", 0);
						break;
					case "grade entered":
						mergeSort(invert(mergeSort(kToZero(students), "firstname", 1)), "gradeentered", 0);
						break;
					case "current grade":
						mergeSort(invert(mergeSort(kToZero(students), "firstname", 1)), "currentgrade", 0);
						break;
					case "class year":
						mergeSort(invert(mergeSort(students, "firstname", 1)), "classyear", 0);
						break;
					case "birthday":
						mergeSort(invert(mergeSort(students, "firstname", 1)), "birthday", 0);
						break;
					case "house advisor":
						mergeSort(invert(mergeSort(students, "firstname", 1)), "houseadvisor", 0);
						break;
					default:
						break;
					}
					/*
					if(input.equalsIgnoreCase("first name")){
						//placing temp declaration within for loop results in data being overwriten b/c sending students through mergeSort modifies students itself
						mergeSort(students, "firstname", 0);
					}else if(input.equalsIgnoreCase("middle name")){
						List<Student> temp = mergeSort(students, "middlename");
						for (int j = 0; j < students.size(); j++) {
							if(temp.get(j).middlename.equals(" "));
							else{
								System.out.println(temp.get(j).middlename + " " + temp.get(j).firstname + " " + temp.get(j).lastname + " Class of " + temp.get(j).classyear);
								//file.write(temp.get(j).middlename + " " + temp.get(j).firstname + " " + temp.get(j).lastname + " Class of " + temp.get(j).classyear + "\n");
							}
						}
					}else if(input.equalsIgnoreCase("last name")){
						List<Student> temp = mergeSort(students, "lastname");
						for (int j = 0; j < students.size(); j++) {
							if(temp.get(j).lastname.equals(" "));
							else{
								System.out.println(temp.get(j).lastname + ", " + temp.get(j).firstname + " Class of " + temp.get(j).classyear);
								//file.write(temp.get(j).lastname + ", " + temp.get(j).firstname + " Class of " + temp.get(j).classyear + "\n");
							}
						}
					}
					else if(input.equalsIgnoreCase("gender")){
						List<Student> temp = mergeSort(invert(mergeSort(students, "firstname")), "sex");
						for (int j = 0; j < students.size(); j++) {
							System.out.println(temp.get(j).firstname + " " + temp.get(j).lastname + " " + temp.get(j).sex + " Class of " + temp.get(j).classyear);
						}
					}else if(input.equalsIgnoreCase("grade entered")){
						List<Student> temp = GEcorrection(mergeSort(invert(mergeSort(students, "firstname")), "gradeentered"));
						for (int j = 0; j < students.size(); j++) {
							System.out.println(temp.get(j).firstname + " " + temp.get(j).lastname + " " + temp.get(j).gradeentered + " Class of " + temp.get(j).classyear);
						}
					}
					else if(input.equalsIgnoreCase("current grade")){
						List<Student> temp = GEcorrection(mergeSort(invert(mergeSort(students, "firstname")), "currentgrade"));
						for (int j = 0; j < students.size(); j++) {
							System.out.println(temp.get(j).firstname + " " + temp.get(j).lastname + " " + temp.get(j).currentgrade + " Class of " + temp.get(j).classyear);
						}
					}else if(input.equalsIgnoreCase("class year")){
						List<Student> temp = mergeSort(invert(mergeSort(students, "firstname")), "classyear");
						for (int j = 0; j < students.size(); j++) {
							System.out.println(temp.get(j).firstname + " " + temp.get(j).lastname + " Class of " + temp.get(j).classyear);
						}
					}else if(input.equalsIgnoreCase("birthday")){
						List<Student> temp = mergeSort(invert(mergeSort(students, "firstname")), "birthday");
						for (int j = 0; j < students.size(); j++) {
							System.out.println(temp.get(j).firstname + " " + temp.get(j).lastname + " " + monthHasher(temp.get(j).birthmonth) + " " + temp.get(j).birthday + ", " + temp.get(j).birthyear + " Class of " + temp.get(j).classyear);
						}
					}else if(input.equalsIgnoreCase("house advisor")){
						List<Student> temp = mergeSort(invert(mergeSort(students, "firstname")), "houseadvisor");
						for (int j = 0; j < students.size(); j++) {
							System.out.println(temp.get(j).firstname + " " + temp.get(j).lastname + " " + temp.get(j).houseadvisor + " Class of " + temp.get(j).classyear);
						}
					}
					*/
				}
				else if(input.toLowerCase().contains("top")){
					int num = Integer.parseInt(input.split(" ")[1]);
					input = input.split(" ")[2];
					if(input.equalsIgnoreCase("first")){
						List<List<Student>> temp = nameFreqSorter(nameFreq(students, "first"));
						List<Student> temp2 = new ArrayList<Student>();
						int k = 1;
						for (int j = temp.size()-1; j > temp.size()-num-1; j--) {
							System.out.print(k++ + ". ");
							temp2 = temp.get(j);
							mergeSort(temp2, "firstname", 1);	
							for (int j2 = 0; j2 < temp2.size(); j2++) {
								System.out.print(temp2.get(j2).firstname);
								if(j2 != temp2.size()-1){
									System.out.print(", ");
								}
							}
							System.out.println(" (" + temp.get(j).get(0).freq + " instances)");
						}
					}else if(input.equalsIgnoreCase("middle")){
						List<List<Student>> temp = nameFreqSorter(nameFreq(students, "middle"));
						List<Student> temp2 = new ArrayList<Student>();
						int k = 1;
						for (int j = temp.size()-1; j > temp.size()-num-1; j--) {
							System.out.print(k++ + ". ");
							temp2 = temp.get(j);
							mergeSort(temp2, "middlename", 1);
							for (int j2 = 0; j2 < temp2.size(); j2++) {
								System.out.print(temp2.get(j2).middlename);
								if(j2 != temp2.size()-1){
									System.out.print(", ");
								}
							}
							System.out.println(" (" + temp.get(j).get(0).freq + " instances)");
						}
					}else if(input.equalsIgnoreCase("last")){
						List<List<Student>> temp = nameFreqSorter(nameFreq(students, "last"));
						List<Student> temp2 = new ArrayList<Student>();
						int k = 1;
						for (int j = temp.size()-1; j > temp.size()-num-1; j--) {
							System.out.print(k++ + ". ");
							temp2 = temp.get(j);
							mergeSort(temp2, "lastname", 1);
							for (int j2 = 0; j2 < temp2.size(); j2++) {
								System.out.print(temp2.get(j2).lastname);
								if(j2 != temp2.size()-1){
									System.out.print(", ");
								}
							}
							System.out.println(" (" + temp.get(j).get(0).freq + " instances)");
						}
					}
				}
				else if(input.equalsIgnoreCase("twins")){
					List<Student> temp = twinChecker(mergeSort(invert(mergeSort(students, "firstname", 1)), "birthday", 1));
					for (int j = 0; j < temp.size(); j++) {
						System.out.println(temp.get(j).firstname + " " + temp.get(j).lastname);
					}
				}else if(input.equalsIgnoreCase("gender count")){
					int[] temp = genderCount(students);
					System.out.println("Females: " + temp[0] + "\nMales: " + temp[1]);
				}else if(input.equalsIgnoreCase("grade entered count")){
					int[] temp = gradeEnteredCount(students);
					int total = 0;
					for (int j = 0; j < temp.length; j++) {
						total = total + temp[j];
					}
					System.out.println("Kindergarten: " + temp[0] + "\nFirst Grade: " + temp[1] +
							"\nSecond Grade: " + temp[2] + "\nThird Grade: " + temp[3] + "\nFourth Grade: " + 
							temp[4]+ "\nFifth Grade: " + temp[5] + "\nSixth Grade: " + temp[6] + "\nSeventh Grade: " 
							+ temp[7] + "\nEighth Grade: " + temp[8] + "\nNinth Grade: " + temp[9] + "\nTenth Grade: " 
							+ temp[10] + "\nEleventh Grade: "+ temp[11] + "\nTwelfth Grade: " + temp[12] + "\nTotal: " + total);
				}else if(input.toLowerCase().contains("students born in")){
					String [] temp = input.split(" ");
					List<Student> temp2 = yearSplit(mergeSort(invert(mergeSort(students, "firstname", 1)), "birthday", 1), Integer.parseInt(temp[3]));
					for (int j = 0; j < temp2.size(); j++) {
						System.out.println(temp2.get(j).firstname + " " + temp2.get(j).lastname + " " + monthHasher(temp2.get(j).birthmonth) + " " + temp2.get(j).birthday + ", " + temp2.get(j).birthyear);
					}
				}else if(input.toLowerCase().contains("first name: ")){
					String [] temp = input.split(" ");
					List<Student> temp2 = nameFinder(mergeSort(students, "lastname", 1), true, temp[2]);
					for (int j = 0; j < temp2.size(); j++) {
						System.out.println(temp2.get(j).firstname + " " + temp2.get(j).lastname);
					}
				}else if(input.toLowerCase().contains("last name: ")){
					String [] temp = input.split(" ");
					List<Student> temp2 = nameFinder(mergeSort(students, "lastname", 1), false, temp[2]);
					for (int j = 0; j < temp2.size(); j++) {
						System.out.println(temp2.get(j).lastname + ", " + temp2.get(j).firstname);
					}
				}
			}
			//file.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static List<Student> nameFinder(List<Student> students, boolean first, String name){
		List<Student> clone = new ArrayList<Student>();
		for (int i = 0; i < students.size(); i++) {
			if(first == true && students.get(i).firstname.equalsIgnoreCase(name)){
				clone.add(students.get(i));
			}
			if(first == false && students.get(i).lastname.equalsIgnoreCase(name)){
				clone.add(students.get(i));
			}
		}
		return clone;
	}
	static List<Student> yearSplit(List<Student> students, int year){
		List<Student> temp = new ArrayList<Student>();
		for (int i = 0; i < students.size(); i++) {
			if(students.get(i).birthyear == year) temp.add(students.get(i));
		}
		return temp;
	}
	static int[] gradeEnteredCount(List<Student> students){
		int[] ge = new int[13];
		for (int i = 0; i < students.size(); i++) {
			if(students.get(i).gradeentered.equals("K")) ge[0]++;
			else if(students.get(i).gradeentered.equals(" "));
			else ge[Integer.parseInt(students.get(i).gradeentered)]++;
		}
		return ge;
	}
	static int[] genderCount(List<Student> students){
		int[] females_males = new int[2];
		for (int i = 0; i < students.size(); i++) {
			if(students.get(i).sex == 'F') females_males[0]++;
			else females_males[1]++;
		}
		return females_males;
	}
	static List<Student> twinChecker(List<Student> students){
		List<Student> clone = students;
		List<Student> twins = new ArrayList<Student>();
		for (int i = 0; i < clone.size(); i++) {
			if(i == clone.size() - 1) break;
			if(clone.get(i).birthday == clone.get(i+1).birthday && clone.get(i).lastname.equalsIgnoreCase(clone.get(i+1).lastname)){
				twins.add(clone.get(i++));
				twins.add(clone.get(i));
			}
		} 
		return twins;
	}
	static List<List<Student>> nameFreqSorter(List<List<Student>> students){
		List<List<Student>> sortedstudents = new ArrayList<List<Student>>();
		for (int i = 0; i < students.size(); i++) {
			if(i != 0 && i != students.size() - 1 && students.get(i).size() == students.get(i+1).size() && students.get(i).size() == students.get(i-1).size()){
				students.get(i).get(0).setFreq(students.get(i).size());
				sortedstudents.get(sortedstudents.size()-1).add(students.get(i).get(0));
			}else if(i != 0 && students.get(i).size() == students.get(i-1).size()){
				students.get(i).get(0).setFreq(students.get(i).size());
				sortedstudents.get(sortedstudents.size()-1).add(students.get(i).get(0));
				if(i != students.size() - 1){
					List<Student> temp = new ArrayList<Student>();
					temp.add(students.get(++i).get(0));
					students.get(i).get(0).setFreq(students.get(i).size());
					sortedstudents.add(temp);
				}
			}else{
				List<Student> temp = new ArrayList<Student>();
				temp.add(students.get(i).get(0));
				students.get(i).get(0).setFreq(students.get(i).size());
				sortedstudents.add(temp);
			}
		}
		return sortedstudents;
	}
	static List<List<Student>> nameFreq(List<Student> students, String first){
		List<List<Student>> freqs = new ArrayList<List<Student>>();
		List<Student> temp = new ArrayList<Student>();
		temp.add(students.get(0));
		freqs.add(temp);
		for (int i = 1; i < students.size(); i++) {
			for (int j = 0; j < freqs.size(); j++) {
				if(first.equalsIgnoreCase("first") && freqs.get(j).get(0).firstname.equalsIgnoreCase(students.get(i).firstname)){
					freqs.get(j).add(students.get(i));
					break;
				}else if(first.equalsIgnoreCase("middle") && freqs.get(j).get(0).middlename.equalsIgnoreCase(students.get(i).middlename)){
					freqs.get(j).add(students.get(i));
					break;
				}else if(first.equalsIgnoreCase("last") && freqs.get(j).get(0).lastname.equalsIgnoreCase(students.get(i).lastname)){
					freqs.get(j).add(students.get(i));
					break;
				}else if(first.equalsIgnoreCase("middle") && freqs.get(j).get(0).middlename.equals(" ")){
				}else if(j == freqs.size() - 1){
					List<Student> temp2 = new ArrayList<Student>();
					temp2.add(students.get(i));
					freqs.add(temp2);
					break;
				}
			}
		}
		freqs = sizeMergeSort(freqs);
		return freqs;
	}
	static List<List<Student>> sizeMergeSort(List<List<Student>> input){
		if(input.size() <= 1) return input;
		List<List<Student>> first = new ArrayList<List<Student>>();
		List<List<Student>> second = new ArrayList<List<Student>>();
		for (int i = 0; i < input.size()/2; i++) {
			first.add(input.get(i));
		}
		for (int i = 0; i < input.size() - input.size()/2; i++) {
			second.add(input.get(i + input.size()/2));
		}
		sizeMergeSort(first);
		sizeMergeSort(second);
		intMerge(first, second, input);
		return input;
	}
	static List<List<Student>> intMerge(List<List<Student>> first, List<List<Student>> second, List<List<Student>> input){
		int j = 0;
		int k = 0;
		for (int i = 0; i < input.size(); i++) {
			if(k == second.size() || j != first.size() && first.get(j).size() < second.get(k).size()){
				input.set(i, first.get(j));
				j++;
			}else if(j == first.size() || second.get(k).size() <= first.get(j).size()){
				input.set(i, second.get(k));
				k++;
			}
		}
		return input;
	}
	static List<Student> mergeSort(List<Student> input, String var, int depth){
		if(input.size() <= 1) return input;
		List<Student> first = new ArrayList<Student>();
		List<Student> second = new ArrayList<Student>();
		for (int i = 0; i < input.size()/2; i++) {
			first.add(input.get(i));
		}
		for (int i = first.size(); i < input.size(); i++) {
			second.add(input.get(i));
		}
		mergeSort(first, var, depth+1);
		mergeSort(second, var, depth+1);
		merge(first, second, input, var);
		if(depth != 0) return input;
		else{
			switch(var){
			case "firstname":
				writeToFile(input, var);
				for (int i = 0; i < input.size(); i++) {
					System.out.println(input.get(i).firstname + " " + input.get(i).lastname);
				}
				break;
			case "middlename": 
				writeToFile(input, var);
				for (int i = 0; i < input.size(); i++) {
					if(input.get(i).middlename.equals(" "));
					else System.out.println(input.get(i).middlename + ", " + input.get(i).firstname + " " + input.get(i).lastname);
				}
				break;
			case "lastname": 
				writeToFile(input, var);
				for (int i = 0; i < input.size(); i++) {
					System.out.println(input.get(i).lastname + ", " + input.get(i).firstname);
				}
				break;
			case "sex": 
				writeToFile(input, var);
				for (int i = 0; i < input.size(); i++) {
					System.out.println(input.get(i).firstname + " " + input.get(i).lastname + " " + input.get(i).sex);
				}
				break;
			case "gradeentered": 
				input = zeroToK(input);
				writeToFile(input, var);
				for (int i = 0; i < input.size(); i++) {
					System.out.println(input.get(i).firstname + " " + input.get(i).lastname + " " + input.get(i).gradeentered);
				}
				break;
			case "currentgrade":
				input = zeroToK(input);
				writeToFile(input, var);
				for (int i = 0; i < input.size(); i++) {
					System.out.println(input.get(i).firstname + " " + input.get(i).lastname + " " + input.get(i).currentgrade);
				}
				break;
			case "classyear": 
				writeToFile(input, var);
				for (int i = 0; i < input.size(); i++) {
					System.out.println(input.get(i).firstname + " " + input.get(i).lastname + " Class of " + input.get(i).classyear);
					if(i!= input.size()-1 && input.get(i).classyear != input.get(i+1).classyear) System.out.println("================================================================================================");
				}
				break;
			case "birthday": 
				writeToFile(input, var);
				for (int i = 0; i < input.size(); i++) {
					System.out.println(input.get(i).firstname + " " + input.get(i).lastname + " " + monthHasher(input.get(i).birthmonth) + " " + input.get(i).birthday + ", " + input.get(i).birthyear + " Class of " + input.get(i).classyear);			
					if(i!= input.size()-1 && input.get(i).birthday != input.get(i+1).birthday) System.out.println("================================================================================================");
				}
				break;
			case "houseadvisor": 
				writeToFile(input, var);
				for (int i = 0; i < input.size(); i++) {
					System.out.println(input.get(i).firstname + " " + input.get(i).lastname + " " + input.get(i).houseadvisor + " House");
					if(i!= input.size()-1 && !input.get(i).houseadvisor.equals(input.get(i+1).houseadvisor)) System.out.println("================================================================================================");
				}
				break;
			default: 
				break;
			}
			return input;
		}
	}
	static List<Student> merge(List<Student> first, List<Student> second, List<Student> input, String var){
		int j = 0;
		int k = 0;
		for (int i = 0; i < input.size(); i++) {
			if(var.equalsIgnoreCase("firstname")){
				if(k == second.size() || j != first.size() && first.get(j).firstname.compareToIgnoreCase(second.get(k).firstname) < 0){
					input.set(i, first.get(j));
					j++;
				}else if(j == first.size() || second.get(k).firstname.compareToIgnoreCase(first.get(j).firstname) <= 0){
					input.set(i, second.get(k));
					k++;
				}
			}else if(var.equalsIgnoreCase("middlename")){
				if(k == second.size() || j != first.size() && first.get(j).middlename.compareToIgnoreCase(second.get(k).middlename) < 0){
					input.set(i, first.get(j));
					j++;
				}else if(j == first.size() || second.get(k).middlename.compareToIgnoreCase(first.get(j).middlename) <= 0){
					input.set(i, second.get(k));
					k++;
				}
			}else if(var.equalsIgnoreCase("lastname")){
				if(k == second.size() || j != first.size() && first.get(j).lastname.compareToIgnoreCase(second.get(k).lastname) < 0){
					input.set(i, first.get(j));
					j++;
				}else if(j == first.size() || second.get(k).lastname.compareToIgnoreCase(first.get(j).lastname) <= 0){
					input.set(i, second.get(k));
					k++;
				}
			}else if(var.equalsIgnoreCase("sex")){
				if(k == second.size() || j != first.size() && first.get(j).sex < second.get(k).sex){
					input.set(i, first.get(j));
					j++;
				}else if(j == first.size() || second.get(k).sex <= first.get(j).sex){
					input.set(i, second.get(k));
					k++;
				}
			}else if(var.equalsIgnoreCase("gradeentered")){
				if(k == second.size() || j != first.size() && Integer.parseInt(first.get(j).gradeentered) < Integer.parseInt(second.get(k).gradeentered)){
					input.set(i, first.get(j));
					j++;
				}else if(j == first.size() || Integer.parseInt(second.get(k).gradeentered) <= Integer.parseInt(first.get(j).gradeentered)){
					input.set(i, second.get(k));
					k++;
				}
			}else if(var.equalsIgnoreCase("currentgrade")){
				if(k == second.size() || j != first.size() && Integer.parseInt(first.get(j).currentgrade) < Integer.parseInt(second.get(k).currentgrade)){
					input.set(i, first.get(j));
					j++;
				}else if(j == first.size() || Integer.parseInt(second.get(k).currentgrade) <= Integer.parseInt(first.get(j).currentgrade)){
					input.set(i, second.get(k));
					k++;
				}
			}else if(var.equalsIgnoreCase("classyear")){
				if(k == second.size() || j != first.size() && first.get(j).classyear < second.get(k).classyear){
					input.set(i, first.get(j));
					j++;
				}else if(j == first.size() || second.get(k).classyear <= first.get(j).classyear){
					input.set(i, second.get(k));
					k++;
				}
			}else if(var.equalsIgnoreCase("birthday")){
				if(k == second.size() || j != first.size() && birthdayChecker(first.get(j).birthday, first.get(j).birthmonth, first.get(j).birthyear, second.get(k).birthday, second.get(k).birthmonth, second.get(k).birthyear) == true){
					input.set(i, first.get(j));
					j++;
				}else if(j == first.size() || birthdayChecker(first.get(j).birthday, first.get(j).birthmonth, first.get(j).birthyear, second.get(k).birthday, second.get(k).birthmonth, second.get(k).birthyear) == false){
					input.set(i, second.get(k));
					k++;
				}
			}else if(var.equalsIgnoreCase("houseadvisor")){
				if(k == second.size() || j != first.size() && first.get(j).houseadvisor.compareToIgnoreCase(second.get(k).houseadvisor) < 0){
					input.set(i, first.get(j));
					j++;
				}else if(j == first.size() || second.get(k).houseadvisor.compareToIgnoreCase(first.get(j).houseadvisor) <= 0){
					input.set(i, second.get(k));
					k++;
				}
			}
		}
		return input;
	}
	static List<Student> invert(List<Student> input){
		List<Student> temp = new ArrayList<Student>();
		for (int i = 0; i < input.size(); i++) {
			temp.add(input.get(i));
		}
		for (int i = 0; i < input.size(); i++) {
			input.set(i, temp.get(input.size() - 1 - i));
		}
		return input;
	}
	static List<Student> GEcorrection(List<Student> input){
		int i = input.size() - 1;
		for (int j = input.size() - 1; j  >= 0; j--) {
			if(input.get(i).gradeentered.equals("K")){
				input.add(0, input.get(i));
				input.remove(i+1);
			}
		}
		for (int j = 0; j < input.size(); j++) {
			Student temp = input.get(j);
			if(input.get(j).gradeentered.equals("9") && input.get(j+1).gradeentered.equals("10")){
				break;
			}else if(input.get(j).gradeentered.equals("10") || input.get(j).gradeentered.equals("11") || input.get(j).gradeentered.equals("12")){
				//System.out.println("moo");
				input.add(temp);
				input.remove(j--);
			}
		}
		return input;
	}
	static boolean birthdayChecker(int fbirthday, int fbirthmonth, int fbirthyear, int sbirthday, int sbirthmonth, int sbirthyear){
		if(fbirthyear < sbirthyear) return true;
		else if(fbirthyear == sbirthyear && fbirthmonth < sbirthmonth) return true;
		else if(fbirthyear == sbirthyear && fbirthmonth == sbirthmonth && fbirthday < sbirthday) return true;
		else return false;
	}
	static boolean sameBirthday(int fbirthday, int fbirthmonth, int fbirthyear, int sbirthday, int sbirthmonth, int sbirthyear){
		if(fbirthday == sbirthday && fbirthmonth == sbirthmonth && fbirthyear == sbirthyear) return true;
		else return false;
	}
	static String monthHasher(int month){
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		return months[month-1];
	}
	static void writeToFile(List<Student> students, String var){
		File output = new File("/Users/student/Documents/workspace/CS3-A_MN/Output");
		FileWriter fw;
		try {
			fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			switch(var){
			case "firstname":
				for (int i = 0; i < students.size(); i++) {
					try {
						bw.write(students.get(i).firstname + " " + students.get(i).lastname + "\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case "lastname":
				for (int i = 0; i < students.size(); i++) {
					try {
						bw.write(students.get(i).lastname + ", " + students.get(i).firstname + "\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case "middlename":
				for (int i = 0; i < students.size(); i++) {
					try {
						bw.write(students.get(i).middlename + ", " + students.get(i).firstname + " " + students.get(i).lastname + "\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case "sex":
				for (int i = 0; i < students.size(); i++) {
					try {
						bw.write(students.get(i).firstname + " " + students.get(i).lastname + " " + students.get(i).sex + "\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case "gradeentered":
				for (int i = 0; i < students.size(); i++) {
					try {
						bw.write(students.get(i).firstname + " " + students.get(i).lastname + " " + students.get(i).gradeentered + "\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case "currentgrade":
				for (int i = 0; i < students.size(); i++) {
					try {
						bw.write(students.get(i).firstname + " " + students.get(i).lastname + " " + students.get(i).currentgrade + "\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case "classyear":
				for (int i = 0; i < students.size(); i++) {
					try {
						bw.write(students.get(i).firstname + " " + students.get(i).lastname + " Class of: " + students.get(i).classyear + "\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case "birthday":
				for (int i = 0; i < students.size(); i++) {
					try {
						bw.write(students.get(i).firstname + " " + students.get(i).lastname + monthHasher(students.get(i).birthmonth) + " " + students.get(i).birthday + ", " + students.get(i).birthyear + "\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case "houseadvisor":
				for (int i = 0; i < students.size(); i++) {
					try {
						bw.write(students.get(i).firstname + " " + students.get(i).lastname + " " + students.get(i).houseadvisor + " House" + "\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			default:
				break;
			}
			bw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	static List<Student> kToZero(List<Student> students){
		for (int i = 0; i < students.size(); i++) {
			if(students.get(i).currentgrade.equals("K")){
				students.get(i).currentgrade = "0";
			}if(students.get(i).gradeentered.equals("K")){
				students.get(i).gradeentered = "0";
			}else if(students.get(i).gradeentered.equals(" ") || students.get(i).gradeentered.equals("no data")){
				students.get(i).gradeentered = "13";
			}
		}
		return students;
	}
	static List<Student> zeroToK(List<Student> students){
		for (int i = 0; i < students.size(); i++) {
			if(students.get(i).currentgrade.equals("0")){
				students.get(i).currentgrade = "K";
			}if(students.get(i).gradeentered.equals("0")){
				students.get(i).gradeentered = "K";
			}else if(students.get(i).gradeentered.equals("13")){
				students.get(i).gradeentered = "no data";
			}
		}
		return students;
	}
}
class Student{
	String firstname;
	String middlename;
	String lastname;
	char sex;
	String gradeentered;
	String currentgrade;
	int classyear;
	int birthday;
	int birthmonth;
	int birthyear;
	String houseadvisor;
	int position;
	int freq;
	public void setFreq(int f){
		this.freq = f;
	}
	public Student(String info, int pos){
		String[] splitinfo = info.split(",");
		if(splitinfo.length < 11) return;
		firstname = splitinfo[0];
		middlename = splitinfo[1];
		lastname = splitinfo[2];
		sex = splitinfo[3].charAt(0);
		gradeentered = splitinfo[4];
		currentgrade = splitinfo[5];
		classyear = Integer.parseInt(splitinfo[6]);
		birthday = Integer.parseInt(splitinfo[7]);
		birthmonth = Integer.parseInt(splitinfo[8]);
		birthyear = Integer.parseInt(splitinfo[9]);
		houseadvisor = splitinfo[10];
		position = pos;
	}
}