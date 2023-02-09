package sg.edu.nus.iss.app;

@FunctionalInterface
public interface OperationI<T> {
    T process(T a, T b);        
}

// T is a generic type
// process is the method name
