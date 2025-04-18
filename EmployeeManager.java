import java.io.*;
import java.util.*;

class Employee implements Serializable {
    int id;
    String name;
    String department;
    double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Dept: " + department + " | Salary: " + salary;
    }
}

public class EmployeeManager {
    private static final String FILE_NAME = "employees.dat";
    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        loadEmployees();
        addEmployee(new Employee(1, "Alice", "IT", 75000));
        addEmployee(new Employee(2, "Bob", "HR", 65000));
        saveEmployees();
        displayEmployees();
    }

    static void addEmployee(Employee emp) {
        employees.add(emp);
        System.out.println("Added: " + emp);
    }

    static void displayEmployees() {
        System.out.println("\nAll Employees:");
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    static void saveEmployees() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadEmployees() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            employees = (List<Employee>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            employees = new ArrayList<>();
        }
    }
}
