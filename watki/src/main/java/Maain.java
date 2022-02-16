import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static jdk.internal.misc.VM.shutdown;

public class Maain {
    public static void main(String[] args) throws IOException, InterruptedException {

        File file=new File("Liczby.txt");
        Scanner in = new Scanner(file);
        File output=new File("Dzielniki.txt");
        FileWriter fw = new FileWriter(output);
        List<Thread> threads = new ArrayList<>();

        String arguments=args[0];
        Integer intArguments=parseInt(args[0]);

         int tab_liczb[]=new int[intArguments];
        Resource resource=new Resource();
        for(int i = 0; i<intArguments; i++){
            String text;
            text = in.nextLine();
            long num=parseLong(text);
            System.out.print(text+"\n");
             resource.put(num);
            threads.add(new Thread(new Dividers(fw, resource)));
        }

        for (Thread thread : threads)
            thread.start();

        Scanner scanner;
        scanner = new Scanner(System.in);
        String input = "";
        boolean flag=false;
        while (true) {
            input = scanner.next();
            if (input.equals("exit"))
                break;
                resource.put(Long.parseLong(input));
        }
        resource.getFullList().add(444);

        for (Thread thread : threads) {
            thread.interrupt();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        fw.flush();
        fw.close();
        System.exit(0);
    }
}
