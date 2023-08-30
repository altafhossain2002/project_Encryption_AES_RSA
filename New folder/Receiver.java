import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver{

    public void receive() {
        int port = 1234;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Receiver is on...");

            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                long fileSize = dis.readLong();
                String savePath = "D:\\altaf\\m.mp4";
                FileOutputStream fos = new FileOutputStream(savePath);
                byte[] buffer = new byte[1024];
                int bytesRead;
                long totalBytesRead = 0;
                while (totalBytesRead < fileSize && (bytesRead = dis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;
                }
                fos.close();
                dis.close();
                socket.close();

                System.out.println("File received successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}