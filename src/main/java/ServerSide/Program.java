package ServerSide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Program {

    public static void main(String[] args) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("request.dat"))) {


            File file = new File("Counter-Strike 1.6 All-Servers.7z");
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[fis.available()];

            fis.read(buffer, 0, fis.available());
            Request request = new Request();
            request.setId("1");
            request.setFileName(file.getName());
            request.setFileSize(file.length());
            request.setContent(buffer);

            ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("newObj"));

            oos2.writeObject(request);

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }
}



