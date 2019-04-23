package Worker;

import Threaded.Threaded;

import java.util.Random;

public class Sleeper extends Threaded {
    @Override
    public void run() {
        try {
            Random rnd  = new Random();
            Thread.sleep(500 * rnd.nextInt(60));
        }

        catch(InterruptedException ex){
            return;
        }
    }
}
