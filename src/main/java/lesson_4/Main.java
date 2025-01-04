package lesson_4;

import lesson_4.Employee;
import lesson_4.Park;

public class Main {
    public static void main(String[] args) {
//        Lesson 4
//        Create an array of 5 employees
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
        employees[1] = new Employee("Sarah Connor", "Manager", "sarah.connor@mail.com", "899999123", 40000, 45);
        employees[2] = new Employee("John Doe", "Developer", "john.doe@mail.com", "877712345", 50000, 35);
        employees[3] = new Employee("Petr Petrov", "Tester", "petr.petrov@mail.com", "866623789", 25000, 28);
        employees[4] = new Employee("Samuel Jackson", "Support", "samuel.jackson@mail.com", "855567890", 27000, 40);

//        Print information about employees
        System.out.println("\n--- Employees ---");
        for (Employee employee : employees) {
            employee.printInfo();
        }

//        Create a park with attractions
        Park park = new Park();
        Park.Attraction attraction1 = park.new Attraction("Burning Man", "10:00 - 22:00", 500);
        Park.Attraction attraction2 = park.new Attraction("Astro Wheel", "11:00 - 20:00", 300);
        Park.Attraction attraction3 = park.new Attraction("Haunted House", "12:00 - 23:00", 700);

//        Print information about attractions
        System.out.println("\n--- Attractions ---");
        attraction1.printInfo();
        attraction2.printInfo();
        attraction3.printInfo();
    }
}