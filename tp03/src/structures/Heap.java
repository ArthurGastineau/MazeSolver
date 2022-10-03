package structures;

public class Heap {
	// attributes
	private final String[] heap;

	public Heap(String[] args) {

		heap = new String[args.length];

		for (int i = 0; i < args.length; i++) {
			heap[i] = args[i];
		}
	}
}
