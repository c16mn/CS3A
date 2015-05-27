import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
public class NavigatingASCLlandMN {
	public static void main(String[] args) {
		String[] cities = { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K" };
		int[] distances = { 0, 450, 590, 715, 1080, 1330, 1490, 1870, 2105, 2425 };
		Scanner scan = new Scanner(System.in);
		while(true){
			String input = scan.nextLine();
			String[] values = input.split(", ");
			String start1 = values[0];
			String start2 = values[1];
			if(start1.equals(start2)) System.out.println("0:00");
			int intstart1 = 0;
			int intstart2 = 0;
			int distance = 0;
			for (int i = 0; i < cities.length; i++) { 
				if(start1.equals(cities[i])) intstart1 = distances[i];
				if(start2.equals(cities[i])) intstart2 = distances[i];
			}
			distance = Math.abs(intstart1 - intstart2);
			//System.out.println(distance);
			double time1 = Integer.parseInt(values[2]);
			String AMPM1 = values[3];
			if(AMPM1.equals("PM")){
				if(time1 != 12) time1 = time1+12;
			}else if(AMPM1.equals("AM") && time1 == 12) time1 = 0;
			double time2 = Integer.parseInt(values[4]);
			String AMPM2 = values[5];
			if(AMPM2.equals("PM")){
				if(time2 != 12) time2 = time2+12;
			}else if(AMPM2.equals("AM") && time2 == 12) time2 = 0;
			double timediff = time2 - time1;
			if(time2 - time1 > 12) timediff = timediff - 24;
			else if(time2 - time1 < -12) timediff = timediff + 24;	
			//System.out.println(timediff);
			double speed1 = Double.parseDouble(values[6]);
			double speed2 = Double.parseDouble(values[7]);
			double timetot = 0;
			if(timediff*speed1 >= distance)timetot = distance/speed1;	
			else if(Math.abs(timediff)*speed2 >= distance) timetot = distance/speed2;
			else timetot = (distance + timediff*speed2)/(speed1 + speed2);
			int timehours = (int) timetot;
			double timesecs = (timetot*60)%60;
			NumberFormat df = new DecimalFormat("00");
			df.setRoundingMode(RoundingMode.HALF_EVEN);
			System.out.println(timehours + ":" + df.format(timesecs));
			//System.out.println(timetot);
		}
	}
}
