import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.Scanner;

public class Sender {

    Scanner sc = new Scanner(System.in);
    public  void sent(){
        System.out.print("Enter the receiver's IP address: ");
        String receiverIp = sc.nextLine();
        System.out.print("Enter the path of the file to send: ");
        String filePath = sc.nextLine();
        int port = 900;
        try {

            Socket socket = new Socket(receiverIp, port);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            File file = new File(filePath);
            long fileSize = file.length();
            dos.writeLong(fileSize);
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesRead);
            }
            fis.close();
            dos.close();
            socket.close();
            System.out.println("File sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}