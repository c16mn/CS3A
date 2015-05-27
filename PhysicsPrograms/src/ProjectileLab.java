import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;


public class ProjectileLab {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		double x1 = 1.018;
		double v1 = 6.552;
		double angle1 = 60;
		double x2 = 1.65;
		double v2 = 4.85;
		double angle2 = 25;
		double x3 = .445;
		double v3 = 3.035;
		double angle3 = 45;
		String input;
		double max = 0;
		double maxangle = 0;
		double angle = 0;
		double x;
		double v;
		while(true){
			System.out.println("Would you like to calculate your fixed shot or the max");
			input = scan.nextLine();
			if(input.equalsIgnoreCase("fixed")){
				System.out.println("Shot 1: " + fixedAngle(x1, v1, angle1) + " meters");
				System.out.println("Shot 2: " + fixedAngle(x2, v2, angle2) + " meters");
				System.out.println("Shot 3: " + fixedAngle(x3, v3, angle3) + " meters");
			}else if(input.equalsIgnoreCase("max")){
				System.out.println("Which shot would you like to calculate?");
				input = scan.nextLine();
				if(input.equalsIgnoreCase("shot 1")){
					maxAngle(x1, v1);
				}else if(input.equalsIgnoreCase("shot 2")){
					maxAngle(x2, v2);
				}else if(input.equalsIgnoreCase("shot 3")){
					maxAngle(x3, v3);
				}else{
					System.out.println("Please give xnaught");
					x = scan.nextDouble();
					System.out.println("Please give vnaught");
					v = scan.nextDouble();
					maxAngle(x, v);
				}		
			}
		}	
	}

	static double toRadian(double angle){
		return angle*(Math.PI/180);
	}
	
	static void maxAngle(double x, double v){
		double angle = 0;
		double max = 0;
		double maxangle = 0;
		double ans;
		for (int i = 0; i < 90; i++) {
			ans = fixedAngle(x, v, angle);
			if(ans > max){
				max = ans;
				maxangle = angle;
			}
			//System.out.println(ans);
			angle = angle + 1;
			//System.out.println(angle);
		}
		System.out.println("The max range is: " + max + " meters and the max angle is: " + maxangle);
	}
	
	static double fixedAngle(double x, double v, double angle){
		double ans;
		ans = v*Math.cos(toRadian(angle));
		ans = ans*(v*Math.sin(toRadian(angle))+Math.sqrt((v*Math.sin(toRadian(angle)))*(v*Math.sin(toRadian(angle)))+20*x));
		ans = ans/9.8;
		return ans;
	}

}
