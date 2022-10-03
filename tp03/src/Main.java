import java.io.PrintStream;

import structures.Heap;

public class Main {
	
	public static void main(String[] args) {
		int count = args.length;
		
		PrintStream out = System.out;
		
		out.println("Argument count: " + count);
		
		for(int i=0; i < count; i++) {
			out.println(args[i]);
		}
		
		Heap h = new Heap(args);
	}
}
