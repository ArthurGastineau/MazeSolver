public class Test {
	public static void main (String[] args) {
		Promotion promotion1 = new Promotion();
		promotion1.add("Jean","Michel");
		promotion1.add("Jean","Pierre");
		promotion1.add("Jean","Lou");
		promotion1.add("Pierre","Lou");
		promotion1.add("Pierre","Henri");
		promotion1.add("Jean","Baptiste");
		promotion1.printToConsole();
		promotion1.quickSort();
		System.out.println("**********  Sorted one  ************");
		promotion1.printToConsole();
	}
}
