// Online Examination System - Java Mini Project
// Students: Avinash and Shubam, 3rd Semester, AI & DS
// VTU OOPs Concept: Inheritance (base class) + Encapsulation + Abstraction
// This is the base class. Student and Admin reuse it (inheritance).

public class Person {
    // Encapsulation: fields are private, access only through methods
    private String name;
    private String id;
    private String password;

    // Constructor
    public Person(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // No getPassword() on purpose - password is never shown.
    // Only checked, not read. This is data hiding.
    public boolean checkPassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    // Polymorphism: child classes override this method
    public void displayRole() {
        System.out.println("Role: User");
    }

    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
    }
}
