package Dispatcher;

import Monitor.ThreadMonitor;
import Threaded.Threaded;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadDispatcher {
    private static volatile ThreadDispatcher instance;
    private ConcurrentLinkedQueue<Thread> threads = new ConcurrentLinkedQueue<>();
    private ThreadMonitor monitor;

    public static ThreadDispatcher getInstance(){
        if(instance == null){
            synchronized (ThreadDispatcher.class) {
                if(instance == null)
                    instance = new ThreadDispatcher();
            }
        }
        return instance;
    }

    private ThreadDispatcher(){
        ThreadMonitor monitor = new ThreadMonitor(this);
        this.monitor = monitor;
        add(monitor, "Monitor");
    }

    public boolean isAlive(){
        return instance != null;
    }

    public void add(Threaded task, String name){
        Thread thread = new Thread(task, name);
        thread.start();
        threads.add(thread);
        monitor.addThread(thread);
    }

    public void add(Threaded task){
        Thread thread = new Thread(task);
        thread.start();
        threads.add(thread);
        monitor.addThread(thread);
    }

    public void kill(){
        instance = null;
    }

    public ThreadMonitor getMonitor(){
        return this.monitor;
    }
}
