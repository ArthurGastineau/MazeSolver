import java.util.Arrays;

import structures.Heap;

public class HeapSortTest {

	public static void main(String[] args) {
		String[] tab = { "2", "1", "4", "3" };
		Heap heapTab = new Heap(tab);
		String[] tabSorted = heapTab.sort();
		String[] ref = { "1", "2", "3", "4" };
		if (Arrays.equals(tabSorted, ref)) {
			System.out.println("Test is successful");
		} else {
			System.out.println("Test failed");
		}
	}
}
