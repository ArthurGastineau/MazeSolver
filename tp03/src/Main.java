import java.io.PrintStream;

import structures.Heap;

public class Main {
	
	public static void main(String[] args) {
		
		PrintStream out = System.out;
		
		String[] sortedArray = new Heap(args).sort();
		
		for(String string : sortedArray) {
			out.println(string);
		}
	}
}
