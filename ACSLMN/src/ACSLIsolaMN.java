import java.util.Scanner;


public class ACSLIsolaMN {
	public static void main(String[] args) {
		//declarations
		Scanner scan = new Scanner(System.in);
		while(true){
			int x = 0;
			String input = scan.nextLine();
			//System.out.println("NONE");
			String[] values = input.split(", ");
			int pluspos = Integer.parseInt(values[0]);
			int xpos = Integer.parseInt(values[1]);
			int[] remtiles = new int[values.length - 2];
			remtiles[remtiles.length-1] = Integer.parseInt(values[0]);
			for (int i = 0; i < remtiles.length - 1; i++) {
				remtiles[i] = Integer.parseInt(values[i+2]);
			}
			//right, left, diagonal up-left, diagonal down-right, up, down, diagonal up-right, diagonal down-left
			int[] tilesneeded = {
					pluspos+1, pluspos-1, pluspos+6, pluspos-6, pluspos+7, pluspos-7, pluspos+8, pluspos-8
			};
			boolean[] blocked = new boolean[8];
			//conditionals to catch the border
			if(pluspos % 7 == 0){
				blocked[0] = true;
				blocked[3] = true;
				blocked[6] = true;
			}else if(pluspos % 7 == 1){
				blocked[1] = true;
				blocked[2] = true;
				blocked[7] = true;
			}if(pluspos >= 43){
				blocked[2] = true;
				blocked[4] = true;
				blocked[6] = true;
			}else if(pluspos <= 7){
				blocked[3] = true;
				blocked[5] = true;
				blocked[7] = true;
			}
			//removing all tiles adjacent to the plus piece
			//TODO: handle removing all tiles
			for (int i = 0; i < remtiles.length; i++) {
				for (int j = 0; j < tilesneeded.length; j++) {
					if(remtiles[i] == tilesneeded[j]){
						blocked[j] = true;
					}
				}
			}
			/* debugging
			for (int i = 0; i < tilesneeded.length; i++) {
				if(blocked[i] == false){
					System.out.print(tilesneeded[i] + ", ");
				}
			}
			 */
			int[][] side = new int[2][7];
			//setting the right path
			for (int i = 0; i < side[0].length; i++) {
				if((xpos + i) % 7 == 1 && i > 0) break;
				else side[0][i] = xpos + i + 1;
			}
			//setting the left path
			for (int i = 0; i < side[1].length; i++) {
				if((xpos - i) % 7 == 0 && i > 0) break;
				else side[1][i] = xpos - i - 1;
			}
//			printing the side arrays
//			for (int i = 0; i < side[0].length; i++) {
//				System.out.print(side[0][i] + ", ");
//			}
//			System.out.println();
//			for (int i = 0; i < side[1].length; i++) {
//				System.out.print(side[1][i] + ", ");
//			}
//			System.out.println();
			//setting the up-left, up, and up-right path
			int[][] up = new int[3][7];
			for (int i = 0; i < up.length; i++) {
				for (int j = 0; j < up[i].length; j++) {
					//checking if it hits a removed tile
					for (int k = 0; k < remtiles.length; k++) {
						if(remtiles[k] == xpos + (7-1+i)*(j)){
							j = 100;
							break;
						}
					}
					if(xpos + (7-1+i)*(j) > 49 || ((xpos + (7-1+i)*(j)) % 7 == 1 && j != 0 && i == 2) || ((xpos + (7-1+i)*(j)) % 7 == 0 && j != 0 && i == 0)) break;
					else up[i][j] = xpos + (7-1+i)*(j);
				}
			}
//			printing the up paths
//			for (int i = 0; i < up[0].length; i++) {
//				System.out.print(up[0][i] + ", ");
//			}
//			System.out.println();
//			for (int i = 0; i < up[1].length; i++) {
//				System.out.print(up[1][i] + ", ");
//			}
//			System.out.println();
//			for (int i = 0; i < up[2].length; i++) {
//				System.out.print(up[2][i] + ", ");
//			}
//			System.out.println();
			//setting the down-left, down, and down-right path
			int[][] down = new int[3][7];
			for (int i = 0; i < down.length; i++) {
				for (int j = 0; j < down[i].length; j++) {
					//checking if it hits a removed tile
					for (int k = 0; k < remtiles.length; k++) {
						if(remtiles[k] == xpos - (7+1-i)*(j+1)){
							j = 100;
							break;
						}
					}
					if(xpos - (7+1-i)*(j) < 0 || ((xpos - (7+1-i)*(j))%7 == 1 && j != 0 && i == 2) || ((xpos - (7+1-i)*(j))%7 == 0 && j != 0 && i == 0)) break;
					else down[i][j] = xpos - (7-1+i)*(j);
				}
			}
			//printing the down paths
//			for (int i = 0; i < down[0].length; i++) {
//				System.out.print(down[0][i]);
//			}
//			System.out.println();
//			for (int i = 0; i < down[1].length; i++) {
//				System.out.print(down[1][i]);
//			}
//			System.out.println();
//			for (int i = 0; i < down[2].length; i++) {
//				System.out.print(down[2][i]);
//			}
//			System.out.println();
			//checking to see if any of the paths cover the remaining tiles
			boolean[] clone = new boolean[blocked.length];
			//checking side paths
			for (int i = 0; i < side.length; i++) {
				//resetting the array
				System.arraycopy(blocked, 0, clone, 0, blocked.length);
				//checking subarrays of side (aka right and left)
				for (int j = 0; j < side[i].length; j++) {
					//checking tiles needed against tiles in path
					for (int k = 0; k < tilesneeded.length; k++) {
						if(side[i][j] == tilesneeded[k]) clone[k] = true;
					}
				}
				for (int j = 0; j < clone.length; j++) {
					if(clone[j] == false) break;
					else if(clone[clone.length - 1] == true && j == clone.length - 1){
						//System.out.println("side: ");
						x++;
						for (int j2 = 1; j2 < side[i].length; j2++) {
							if(side[i][j2] == 0) break;
							if(j2 != 1) System.out.print(", ");
							System.out.print(side[i][j2]);
						}
						//System.exit(1);
					}
				}
			}
			//checking down paths
			for (int i = 0; i < down.length; i++) {
				//resetting the array
				System.arraycopy(blocked, 0, clone, 0, blocked.length);
				//checking subarrays of down (aka down-right and down and down-left)
				for (int j = 0; j < down[i].length; j++) {
					//checking tiles needed against tiles in path
					for (int k = 0; k < tilesneeded.length; k++) {
						if(down[i][j] == tilesneeded[k]) clone[k] = true;
					}
				}
				for (int j = 0; j < clone.length; j++) {
					if(clone[j] == false) break;
					else if(clone[clone.length - 1] == true && j == clone.length - 1){
						//System.out.println("down: ");
						x++;
						for (int j2 = 1; j2 < down[i].length; j2++) {
							if(down[i][j2] == 0) break;
							if(j2 != 1) System.out.print(", ");
							System.out.print(down[i][j2]);
						}
						//System.exit(1);
					}
				}
			}
			//checking up paths
			for (int i = 0; i < up.length; i++) {
				//resetting the array
				System.arraycopy(blocked, 0, clone, 0, blocked.length);
				//checking subarrays of up (aka up-right and up and up-left)
				for (int j = 0; j < up[i].length; j++) {
					//checking tiles needed against tiles in path
					for (int k = 0; k < tilesneeded.length; k++) {
						if(up[i][j] == tilesneeded[k]) clone[k] = true;
					}
				}
				for (int j = 0; j < clone.length; j++) {
					if(clone[j] == false) break;
					else if(clone[clone.length - 1] == true && j == clone.length - 1){
						//System.out.println("up: ");
						x++;
						for (int j2 = 1; j2 < up[i].length; j2++) {
							if(up[i][j2] == 0) break;
							if(j2 != 1) System.out.print(", ");
							System.out.print(up[i][j2]);
						}
						//System.exit(1);
					}
				}
			}
		if(x == 0) System.out.println("NONE");
		else System.out.println("");
		}
	}//end of main
}
