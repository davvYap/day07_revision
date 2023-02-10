package sg.edu.nus.iss.app;

public class MyRunnable implements Runnable{
    
    private String taskName;

    public MyRunnable(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run(){
        // TODO Auto-generated method stub

        for (int i = 0; i < 5; i++) {
            System.out.println(this.taskName + ": " + i);
        }
    }
}
