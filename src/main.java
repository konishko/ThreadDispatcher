import Dispatcher.ThreadDispatcher;
import Monitor.MonitorWindow;

public class main {
    public static void main(String[] args){
        ThreadDispatcher td = ThreadDispatcher.getInstance();
        MonitorWindow mw = new MonitorWindow(td.getMonitor());
        td.add(mw, "GUI");
    }
}
