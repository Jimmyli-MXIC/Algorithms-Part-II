package ThreadPoolExecutor;

public abstract class PriorityRunnable implements Runnable, Comparable<PriorityRunnable>{
    private int priority;

    public PriorityRunnable(int priority){
        if (priority < 0)
            throw new IllegalArgumentException();
        this.priority = priority;
    }

    @Override
    public int compareTo(PriorityRunnable that) {
        if (this.priority < that.priority) return 1;
        else return this.priority > that.priority ? -1 : 0;
    }

    @Override
    public void run() {
        doSth();
    }

    public abstract void doSth();
}