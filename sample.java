import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class sample {
    public static void main(String[] args) throws IOException {
        String command = "runner.py";
        Process p = Runtime.getRuntime().exec(command);
        try {
            p.waitFor();
        }
        catch (Exception e){
            System.out.println(e);
        }
        BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String line;
        while ((line = bri.readLine()) != null) {
            System.out.println(line);
        }
        bri.close();
        while ((line = bre.readLine()) != null) {
            System.out.println(line);
        }
        bre.close();

        System.out.println("Done.");

        p.destroy();
    }
}
