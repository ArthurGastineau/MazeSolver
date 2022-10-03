package structures;

import java.util.Arrays;

public class Heap {
	// attributes
	private final String[] heap;

	public Heap(String[] args) {
		heap = args.clone();
	}
	
	public void swap (int i, int j) {
		String temp  = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	private void pullUp (int k) {
		int son = k - 1;
		int father = (k - 1) / 2;
		
		while ( (son > 0) && (heap[father].compareTo(heap[son]) < 0) ) {		
				swap(son, father);
				son = father;
				father = (son - 1) / 2;		
		}
	}
	
	private void buildHeap() {
		int sizeof = heap.length;
		for (int i = 2; i < sizeof; i++) {
			pullUp(i);
		}
	}
	
}
