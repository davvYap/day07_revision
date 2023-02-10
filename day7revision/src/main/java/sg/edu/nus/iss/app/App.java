package sg.edu.nus.iss.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class App 
{
    public static void main( String[] args ) 
    {
        // if (args[0].equalsIgnoreCase("kill")){
        //     System.exit(0);
        // }

        sortArray();

        List<Employee> employees = getEmployees();

        sortEmployees(employees);

        steamFilterSortExample(employees);

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

        // to execute day08 revision classes
        sortArray();
        sortMap();

        threadTask();
        threadTask2();
        threadTaskLambda();
        linkedListExample();
        stackExample();
    }

    // Day 07 revision
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

    public static void sortIntegers(){
        // sorting integers
        List<Integer> integers = Arrays.asList(3,6,5,8,7,9,0,1,2,4);
        integers.sort(Comparator.naturalOrder());
        System.out.println(integers);

        // reverse order
        integers.sort(Comparator.reverseOrder());
        System.out.println(integers);
    }

    public static void sortEmployees(List<Employee> employees){
        // ascending sorting list
        employees.sort(Comparator.comparing(e -> e.getFirstName()));
        System.out.println("Ascending order:");
        System.out.println(employees);

        //descending sorting list
        Comparator<Employee> comparator = Comparator.comparing(e -> e.getFirstName());
        employees.sort(comparator.reversed());
        System.out.println("Descending order:");
        employees.forEach(emp1 -> System.out.println(emp1)); //use forEach to print employee in the list
    }

    public static void steamFilterSortExample(List<Employee> employee){
        // Stream and filter
        List<Employee> filteredEmployees = employee.stream().filter(e -> e.getLastName().equalsIgnoreCase("ng")).collect(Collectors.toList());
        System.out.println("Filtered employees: ");
        System.out.println(filteredEmployees);
        //equalsIgnoreCase() will compare 2 strings without considering capital sensitive

        // normal equals() is capital sensitive, thus we need to call toLowerCase()
        Employee emp = employee.stream().filter(e -> e.getLastName().toLowerCase().equals("ng")).findFirst().get();
        System.out.println("1st employee after filtering:");
        System.out.println(emp);

        // finding index
        int idx = employee.indexOf(emp);
        Employee returnEmp = employee.get(idx);
        System.out.println("Return employee based on index: "+returnEmp);
    }
    
    // Day 08 Revision
    // to sort an array
    public static void sortArray(){
        String[] arr = {"practise","workshop","lecture","revision"};

        Arrays.sort(arr);
        System.out.println("Sorted array: "+Arrays.toString(arr));

        Arrays.sort(arr, Collections.reverseOrder());
        System.out.println("Reversed array: "+Arrays.toString(arr));
    }

    // to sort a Map by converting the data to list
    public static void sortMap(){
        Map<String,Integer> map = new HashMap<>();
        map.put("Sushi", 5);
        map.put("Chocolate", 15);
        map.put("Coffee", 8);
        map.put("Tea", 12);
        map.put("Sandwich", 18);
        map.put("Hashbrown", 10);

        map.forEach((k,v) -> System.out.printf("Key: %s, Value: %d\n",k,v));
        
        // we cannot sort map directly, thus we have to pump the data into list
        Set<Entry<String,Integer>> set = map.entrySet();;
        List<Entry<String,Integer>> mapList = new ArrayList<>(set);
        mapList.sort(Entry.comparingByValue());
        mapList.forEach(e -> System.out.println(e));
    }

    // threading example
    public static void threadTask(){
        System.out.println("Thread task 1:");
        MyRunnable mr1 = new MyRunnable("task 1");
        MyRunnable mr2 = new MyRunnable("task 2");
        MyRunnable mr3 = new MyRunnable("task 3");
        MyRunnable mr4 = new MyRunnable("task 4");
        MyRunnable mr5 = new MyRunnable("task 5");

        ExecutorService es = Executors.newFixedThreadPool(4);   //only allow 2 threads
        es.execute(mr1);    
        es.execute(mr2);
        es.execute(mr3);
        es.execute(mr4);
        es.submit(mr5);       // can either use submit() -> will have return type
        es.shutdown();
    }

    // alternative method to use thread
    public static void threadTask2(){
        System.out.println("Thread task 2:");
        MyRunnable mr1 = new MyRunnable("task 1");
        MyRunnable mr2 = new MyRunnable("task 2");
        MyRunnable mr3 = new MyRunnable("task 3");
        MyRunnable mr4 = new MyRunnable("task 4");
        MyRunnable mr5 = new MyRunnable("task 5");

        // cannot limit the thread number, different thank ExecutorService
        Thread t1 = new Thread(mr1);
        Thread t2 = new Thread(mr2);
        Thread t3 = new Thread(mr3);
        Thread t4 = new Thread(mr4);
        Thread t5 = new Thread(mr5);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    // threading using Lambda expression
    public static void threadTaskLambda(){
        new Thread(
            () -> {
                for(int i = 0; i < 3; i++){
                    System.out.println("Hi Lambda: " + i);
                }
            }
        ).start();
    }

    // linkedList example
    public static void linkedListExample(){
        LinkedList<String> ll = new LinkedList<>();

        ll.add("A");
        ll.add("B");
        ll.add("C");
        ll.add("D");
        ll.add("E");
        ll.add(2, "F"); // add at position 2

        ll.forEach(s -> System.out.println(s));

        ll.remove("C");
        ll.forEach(s -> System.out.println(s));

        ll.forEach(s -> System.out.println(s));
    }

    // stack example
    public static void stackExample(){

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        stack.forEach(i -> System.out.println("All numbers in stack >>>" + i));

        for (int i = 0; i < 5; i++) {   
            int removedNum = stack.pop();
            System.out.println("Removed number >>> " + removedNum);
        }

        System.out.println("Top item >>>" + stack.peek());
        System.out.println("Bottom item >>> " + stack.firstElement());

        Iterator<Integer> iterator = stack.iterator();
        while(iterator.hasNext()){
            int stackItem = iterator.next();
            System.out.println("Iterator through stack >>>" + stackItem);
        }

    }
}


