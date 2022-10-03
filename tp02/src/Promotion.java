import java.io.PrintStream;
import java.util.ArrayList;

public class Promotion {
	//attributes
	private ArrayList<Student> studentList;

	public Promotion() {
		studentList = new ArrayList<Student>();
	}
	
	public int add (String firstName, String lastName) {
		int id = newId();
		studentList.add(new Student (id, firstName, lastName));
		return id;
	}
	
	public int newId() {
		int studentAmmount = studentList.size();
		if (studentAmmount == 0) {
			return 0;
		}
		int max = studentList.get(0).getId();
		for (int i=1; i < studentList.size(); i++) {
			if (studentList.get(i).getId() > max) {
				max = studentList.get(i).getId();
			}
		}
		return max + 1;
	}
	
	public void printToConsole() {
		PrintStream out = System.out;
		for (Student student : studentList) {
			out.println(student);
		}
	}
	
	public void selectionSort() {
		for (int i=0; i < studentList.size() - 1; i++) {
			int smallerIndex = i;
			Student student1 = studentList.get(i);
			for (int j=i+1; j < studentList.size(); j++) {
				Student student2 = studentList.get(j);
				if (student2.compareTo(student1) < 0) {
					smallerIndex = j;
					student1 = studentList.get(j);
				}
			}
			swap(i, smallerIndex);
		}
	}
	
	private int partition(int g, int d) {
		Student key = studentList.get(g);
		int i = g + 1;
		int j = d;
		while( i <= j) {
			while((i <= j) && (studentList.get(i).compareTo(key) <= 0)) {
				i++;
			}
			while((j >= i) && (studentList.get(j).compareTo(key) > 0))
			{
				j--;
			}
			if (i < j) {
				swap(i, j);
				i++;
				j--;
			}
		}
		swap(g,j);
		return j;
	}
	
	private void quickSort(int g, int d) {
		if (g < d) {
			int j = partition(g ,d);
			quickSort(g, j-1);
			quickSort(j+1, d);
		}
	}
	
	public void quickSort() {
		quickSort(0, studentList.size() - 1);
	}
	
	public void swap (int index1, int index2) {
		Student temp = studentList.get(index1);
		studentList.set(index1, studentList.get(index2));
		studentList.set(index2, temp);
	}
}
