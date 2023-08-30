import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print(
                "Enter 1 for sending file\nEnter 2 for receiving file\nEnter 3 for AES Encryption\nEnter 4 for RSA Encryption\nEnter your choice ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Sender s = new Sender();
                s.sent();

                break;
            case 2:
                Receiver r = new Receiver();
                r.receive();

                break;
            case 3:
                AES aes = new AES();
                aes.aesEncryption();

                break;

            case 4:
                RSA rsa = new RSA();
                rsa.rsaEncryption();

                break;

            default:
                System.out.println("Invalid choice");

                break;
        }

    }
}
