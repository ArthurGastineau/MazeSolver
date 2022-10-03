public class Student {
	// attributes
	private final int 	 id;
	private 	  String firstName;
	private 	  String lastName;
	
	public Student(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}
	
	public int compareTo(Student anotherStudent) {
		int compare = this.lastName.compareTo(anotherStudent.lastName);
		// different last names
		if (compare != 0) {
			return compare;
		}
		//compare their first names
		compare = this.firstName.compareTo(anotherStudent.firstName);
		// different ones
		if (compare != 0) {
			return compare;
		}
		//compare ids 
		if (this.id < anotherStudent.id) {
			return -1;
		}
		else if (this.id > anotherStudent.id) {
			return 1;
		}
		else return 0;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + " (" + id + ")";
	}
}
