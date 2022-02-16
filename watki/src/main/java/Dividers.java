import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dividers implements Runnable {
    private  FileWriter fw;
    private  Resource resource;

    public Dividers(FileWriter fw, Resource resource) {
        this.fw = fw;
        this.resource = resource;
    }

    @Override
    public void run() {
            Long num = null;
        while (!Thread.interrupted())
        {
                try {
                    num = resource.take();
                } catch (InterruptedException ex) {
                    break;

                }

                List<Long> dzielniki = new ArrayList<>();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                }

                for (long i = 1; i <= num ; i++) {
                    if (num % i == 0)
                        dzielniki.add(i);
                }

                synchronized (this.fw) {
                    try {
                        fw.write(num + ": ");
                        for (Long number : dzielniki) {
                            fw.write(number + ", ");
                        }
                        fw.write( "\n");
                    } catch (IOException e) {
                        return;
                    }
                }
            }

    }
}
