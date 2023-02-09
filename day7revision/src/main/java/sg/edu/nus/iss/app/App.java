package sg.edu.nus.iss.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App 
{
    public static void main( String[] args ) 
    {
        if (args[0].equalsIgnoreCase("kill")){
            System.exit(0);
        }

        List<Employee> employees = getEmployees();

        // ascending sorting
        employees.sort(Comparator.comparing(e -> e.getFirstName()));
        System.out.println("Ascending order:");
        System.out.println(employees);

        //descending
        Comparator<Employee> comparator = Comparator.comparing(e -> e.getFirstName());
        employees.sort(comparator.reversed());
        System.out.println("Descending order:");
        employees.forEach(emp1 -> System.out.println(emp1)); //use forEach to print employee in the list

        // sorting integers
        List<Integer> integers = Arrays.asList(3,6,5,8,7,9,0,1,2,4);
        integers.sort(Comparator.naturalOrder());
        System.out.println(integers);

        // reverse order
        integers.sort(Comparator.reverseOrder());
        System.out.println(integers);

        // Functional Interface
        OperationI<Integer> addOperations = (a,b) -> {
            return a+b;
        };

        OperationI<Integer> minusOperations = (a,b) -> {
            return a-b;
        };

        OperationI<String> concatOperations = (a,b) -> {
            return a.concat(b);
        };

        System.out.println("Add operation: "+addOperations.process(5,2));
        System.out.println("Minus operation: "+minusOperations.process(5,2));
        System.out.println("Concat operation: "+concatOperations.process("Hello"," World!"));

        // Stream and filter
        List<Employee> filteredEmployees = employees.stream().filter(e -> e.getLastName().equalsIgnoreCase("ng")).collect(Collectors.toList());
        System.out.println("Filtered employees: ");
        System.out.println(filteredEmployees);
        //equalsIgnoreCase() will compare 2 strings without considering capital sensitive

        // normal equals() is capital sensitive, thus we need to call toLowerCase()
        Employee emp = employees.stream().filter(e -> e.getLastName().toLowerCase().equals("ng")).findFirst().get();
        System.out.println("1st employee after filtering:");
        System.out.println(emp);

        // finding index
        int idx = employees.indexOf(emp);
        Employee returnEmp = employees.get(idx);
        System.out.println("Return employee based on index: "+returnEmp);

    }

    public static List<Employee> getEmployees(){
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1, "Hsien Loong", "Lee", 70));
        employees.add(new Employee(2, "Chee Hean", "Tan", 71));
        employees.add(new Employee(3, "Swee Kiat", "Ng", 65));
        employees.add(new Employee(4, "Pritam", "Singh", 55));
        employees.add(new Employee(5, "Eng Heng", "Ng", 70));
        employees.add(new Employee(6, "Lawrence", "Wong", 50));
        employees.add(new Employee(7, "Eng Kwan", "Ong", 55));
        employees.add(new Employee(1, "David", "Yap", 28));

        return employees;
    }
}
