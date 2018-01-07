package clone;

import java.util.concurrent.ScheduledExecutorService;

public class CloningExample {

    public static void main(String[] args) {
        try {
            shallowClone();
            deepClone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private static void shallowClone() throws CloneNotSupportedException {
        Address address1 = new Address("404", "EM", "Mumbai", "India");
        Employee emp1 = new Employee(1, "Jeevan", address1, 1000D);

        Employee emp2 = (Employee) emp1.clone();

        System.out.println("========== Shallow Cloning: Before change ==========");
        System.out.println(emp1);
        System.out.println(emp2);

        emp2.setId(2);
        emp2.setName("Unmesh");
        emp2.getAddress().setAdress1("502");
        emp2.getAddress().setAdress2("SP");
        emp2.getAddress().setCity("Pune");
        emp2.getAddress().setCity("India");
        emp2.setSal(1500D);

        System.out.println("========== Shallow Cloning: After change ==========");
        System.out.println(emp1);
        System.out.println(emp2);
    }

    private static void deepClone() throws CloneNotSupportedException {
        School school = new School(1,"Loyola");
        Student student1 = new Student(1, "Jeevan", school);

        Student student2 = (Student) student1.clone();

        System.out.println("========== Deep Cloning: Before change ==========");
        System.out.println(student1);
        System.out.println(student2);

        student2.setId(2);
        student2.setName("Unmesh");
        student2.getSchool().setId(2);
        student2.getSchool().setName("KG");

        System.out.println("========== Deep Cloning: After change ==========");
        System.out.println(student1);
        System.out.println(student2);
    }
}
