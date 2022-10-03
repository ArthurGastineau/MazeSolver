package structures;

public class Heap {
	// attributes
	private final String[] heap;

	// constructor
	public Heap(String[] args) {
		heap = args.clone();
	}

	// methods
	public void swap(int i, int j) {
		String temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}

	private void pullUp(int k) {
		int son = k - 1;
		int father = (k - 1) / 2;

		while ((son > 0) && (heap[father].compareTo(heap[son]) < 0)) {
			swap(son, father);
			son = father;
			father = (son - 1) / 2;
		}
	}

	private void buildHeap() {
		int sizeof = heap.length;
		for (int i = 2; i <= sizeof; i++) {
			pullUp(i);
		}
	}

	private void pullDown(int i, int k) {
		int leftIndex = 2 * i + 1;
		// no left son then he dones'nt have any
		if (leftIndex >= k)
			return;

		int rightIndex = 2 * i + 2;

		// no right son so only a left one
		if (rightIndex >= k) {
			if (heap[i].compareTo(heap[leftIndex]) < 0) {
				swap(i, leftIndex);
				return;
			}
		}
		// right son is the larger one
		if (heap[leftIndex].compareTo(heap[rightIndex]) < 0) {
			if (heap[i].compareTo(heap[rightIndex]) < 0) {
				swap(i, rightIndex);
				pullDown(rightIndex, k);
				return;
			}
		}
		// left one is
		else {
			if (heap[i].compareTo(heap[leftIndex]) < 0) {
				swap(i, leftIndex);
				pullDown(leftIndex, k);
				return;
			}
		}
	}

	private void sortHeap() {
		int sizeOf = heap.length;
		for (int k = sizeOf; k > 1; k--) {
			swap(0, k - 1);
			pullDown(0, k - 1);
		}
	}

	public String[] sort() {
		this.buildHeap();
		this.sortHeap();
		return heap.clone();
	}
}
