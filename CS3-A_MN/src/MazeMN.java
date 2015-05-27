import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*
 * edges are as follows: 0 = top, 1 = right, 2 = bottom, 3 = left
 */
public class MazeMN {
	public static void main(String[] args) {
		int maxweight = 30;
		int size = 100;
		Cell[][] mazeboard = new Cell[size][size];
		Random gen = new Random();
		for (int i = 0; i < mazeboard.length; i++) {
			for (int j = 0; j < mazeboard.length; j++) {
				List<Integer> temp = new ArrayList<Integer>();
				for (int k = 0; k < 4; k++) {
					temp.add(gen.nextInt(maxweight));
				}
				if(i == 0) temp.set(0, maxweight + 10);
				else if(i == size - 1) temp.set(2, maxweight + 10);
				if(j == 0) temp.set(3, maxweight + 10);
				else if(j == size - 1) temp.set(1, maxweight + 10);
				mazeboard[i][j] = new Cell(temp);
			}
		}
	}
	public void spanningTree(Cell start, Cell finish){
		
	}
}
class Cell{
	List<Cell> neighbors = new ArrayList<Cell>();
	List<Integer> edges = new ArrayList<Integer>();
	public Cell(List<Integer> weights){
		for (int i = 0; i < weights.size(); i++) {
			edges.add(weights.get(i));
		}
	}
}
