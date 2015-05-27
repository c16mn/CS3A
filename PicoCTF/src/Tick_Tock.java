import java.math.BigInteger;

public class Tick_Tock {
	public static void main(String[] args) {
		int x = 0;
		int stillalive = 0;
		BigInteger interval = new BigInteger("200009");
		BigInteger start = new BigInteger("358054");
		BigInteger maxval = new BigInteger("10000000000000000000000000000000000000000000000000000000");
		BigInteger modvals[] = {new BigInteger("2"), new BigInteger("3"), new BigInteger("13"), 
		new BigInteger("29"), new BigInteger("191"), new BigInteger("397"),  new BigInteger("691"), 
		new BigInteger("1009"), new BigInteger("2039"), new BigInteger("4099"), new BigInteger("7001"), 
		new BigInteger("10009"), new BigInteger("19997"), new BigInteger("30013"), new BigInteger("70009"),  
		new BigInteger("160009"), new BigInteger("200009")};
		BigInteger modans[] = {new BigInteger("1"), new BigInteger("2"), new BigInteger("8"), 
		new BigInteger("4"), new BigInteger("130"), new BigInteger("343"), new BigInteger("652"),
		new BigInteger("858"), new BigInteger("689"), new BigInteger("1184"), new BigInteger("2027"),
		new BigInteger("5119"), new BigInteger("15165"), new	 BigInteger("15340"), new BigInteger("29303"),
		new BigInteger("42873"), new BigInteger("158045")};
		while(start.compareTo(maxval) == -1){
			for (int i = 0; i < modvals.length; i++) {
				if(start.mod(modvals[i]) == modans[i]){
					x++;
				}
			}
			if(x == 17){
				System.out.println("THE ANSWER IS" + start + "!!!!!");
				break;
			}else if(stillalive == 10000000){
				System.out.println("Still Alive");
				System.out.println(start);
				stillalive = 0;
			}
			stillalive ++;
			start = start.add(interval);
		}
		
		
	}
}
