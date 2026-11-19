// VTU OOPs Concept: Inheritance + Polymorphism (method overriding)
// Student IS-A Person, so it extends Person and reuses name, id, password.

public class Student extends Person {
    private String department;

    public Student(String name, String id, String password, String department) {
        super(name, id, password); // calls Person constructor
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Polymorphism: same method name as Person, different behaviour
    @Override
    public void displayRole() {
        System.out.println("Role: Student");
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Department: " + department);
    }
}
