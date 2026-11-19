// VTU OOPs Concept: Inheritance + Polymorphism (method overriding)
// Admin IS-A Person, just like Student, but with a different role.

public class Admin extends Person {

    public Admin(String name, String id, String password) {
        super(name, id, password);
    }

    // Polymorphism: same method name as Person, different behaviour
    @Override
    public void displayRole() {
        System.out.println("Role: Admin");
    }
}
