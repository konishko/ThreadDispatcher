package Monitor;

import Threaded.Threaded;
import Worker.Sleeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MonitorWindow extends Threaded implements ActionListener {
    private ThreadMonitor monitor;

    private final JFrame frame;
    private JPanel threads;

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("add")) {
            monitor.getDispatcher().add(new Sleeper());
        }
    }

    public MonitorWindow(ThreadMonitor monitor){
        JFrame frame = new JFrame("Dispatcher monitor");

        JPanel threads = new JPanel();
        threads.setLayout(new BoxLayout(threads, BoxLayout.Y_AXIS));

        JButton addThread = new JButton("Add sleeper");
        addThread.setActionCommand("add");
        addThread.addActionListener(this);

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        frame.add(threads, BorderLayout.CENTER);

        buttonsPanel.add(addThread);

        frame.add(buttonsPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.frame = frame;
        this.threads = threads;
        this.monitor = monitor;
    }

    private void update(){
        this.threads.removeAll();

        ConcurrentLinkedQueue<Thread> monitorThreads = monitor.getThreads();
        Thread[] threads = monitorThreads.toArray(new Thread[monitorThreads.size()]);

        for(Thread thread: threads)
            this.threads.add(new JLabel(thread.getName()));
        this.threads.updateUI();
    }

    @Override
    public void run() {
        frame.pack();
        frame.setSize(200, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        while(true){
            try {
                Thread.sleep(50);
            }
            catch(InterruptedException e){}
            update();
        }
    }
}
