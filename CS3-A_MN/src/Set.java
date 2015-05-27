import java.util.HashSet;


public class Set {
    public  HashSet<Integer> s;

    public void Set() {
      s = new HashSet<Integer>();
    }

    public void add(int i) {
      s.add(i);
    }

    public void  remove(int i) {
      s.remove(i);
    }

    boolean contains(int i) {
      return s.contains(i);
    }
    
    /*
    all the subsets that can be generated of subSetSize size
    jmpmulter
    Advice on how to find inital [] size from http://mathworld.wolfram.com/Permutation.html
 	Sort the set
    find # permutations, using formula from wolfram
    create [^.length]
    go through the sorted list, filling each spot in the [] with a set
    return the []
    */
    Set[] subSets(int subSetSize, int totalnumber) {
    	Set[] blank = null;
    	return blank;	
    }
}