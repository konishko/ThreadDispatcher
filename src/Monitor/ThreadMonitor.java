package Monitor;

import Dispatcher.ThreadDispatcher;
import Threaded.Threaded;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadMonitor extends Threaded {
    private ThreadDispatcher dispatcher;
    private ConcurrentLinkedQueue<Thread> threads = new ConcurrentLinkedQueue<>();

    public ThreadMonitor(ThreadDispatcher disp){
        this.dispatcher = disp;
    }
    @Override
    public void run() {
        while(dispatcher.isAlive()) {
            Iterator<Thread> threadsIter = threads.iterator();

            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e){
                System.out.println("Interrupted");
            }

            while (threadsIter.hasNext()) {
                Thread thread = threadsIter.next();

                if (!thread.isAlive())
                    deleteThread(thread);
            }
        }
    }

    public void addThread(Thread thread){
        this.threads.add(thread);
    }

    public void deleteThread(Thread thread){
        this.threads.remove(thread);
    }

    public ThreadDispatcher getDispatcher(){
        return dispatcher;
    }

    public ConcurrentLinkedQueue<Thread> getThreads(){
        return this.threads;
    }
}
