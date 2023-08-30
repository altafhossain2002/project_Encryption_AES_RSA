

import java.io.*;
import java.util.Scanner;

public class RSA {
    Scanner sc = new Scanner(System.in);
    void keyPair(){
        long p,q;
                System.out.println("Enter the value for p");
                p=sc.nextLong();
                System.out.println("Enter the value for q");
                q=sc.nextLong();

        
        long n = p * q;
        long phi = (p - 1) * (q - 1);
    
        long e ;
        System.out.println("Enter the value for p");
         e=sc.nextLong();
        long d = calculatePrivateKey(e, phi);
        System.out.println("Public Key:"+e+" "+n);
        System.out.println("Public Key:"+d+" "+n);
    }
    

    void encrypt(){
        System.out.print("Enter the text to be encrypted: ");
                String plaintext = sc.nextLine();
                long e,n;
                System.out.println("Enter the value for e");
                e=sc.nextLong();
                System.out.println("Enter the value for n");
                n=sc.nextLong();
                long[] encrypted = encryptString(plaintext, e, n);
                byte[] encryptedBytes = convertLongArrayToBytes(encrypted);
                System.out.print("Enter the file path to write encrypted data: ");
                String filePath = sc.nextLine();
        
                try {
                     try (OutputStream out = new FileOutputStream(filePath)) {
            out.write(encryptedBytes);
        }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

    }
    void decrypt(){
        System.out.print("Enter the file path to read encrypted data: ");
               String filePath = sc.nextLine();
                try {
                    byte[] readBytes = readFromFile(filePath);
                    long[] readEncrypted = convertBytesToLongArray(readBytes);
                    long d,n;
                System.out.println("Enter the value for d");
                d=sc.nextLong();
                System.out.println("Enter the value for n");
                n=sc.nextLong();
                    String decryptedFromRead = decryptString(readEncrypted, d, n);
                    System.out.println("Decrypted Message from File: " + decryptedFromRead);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
    }

public  void rsaEncryption() {

        System.out.print("For RSA keypair enter 1\nFor RSA encryption enter 2\nFor RSA decryption enter 3\nEnter your choice: ");
        int x = sc.nextInt();
        switch (x) {
            case 1:
                 {  keyPair();     
                  break;
                   }   
             case 2: {
                encrypt();
                break;
            }
            case 3: {
                decrypt();
                break;
            }
            default: {
                System.out.println("Invalid choice");
            }
                break;
        }

    }

    private static long[] encryptString(String input, long exponent, long modulus) {
        long[] encrypted = new long[input.length()];
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            encrypted[i] = modularExponentiation(c, exponent, modulus);
        }
        return encrypted;
    }

    private static String decryptString(long[] encrypted, long exponent, long modulus) {
        StringBuilder decrypted = new StringBuilder();
        for (long num : encrypted) {
            char c = (char) modularExponentiation(num, exponent, modulus);
            decrypted.append(c);
        }
        return decrypted.toString();
    }

    private static long modularExponentiation(long base, long exponent, long modulus) {
        long result = 1;
        base = base % modulus;

        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }
            exponent >>= 1;
            base = (base * base) % modulus;
        }

        return result;
    }

    private static long calculatePrivateKey(long e, long phi) {
        long d = 1;
        while ((e * d) % phi != 1) {
            d++;
        }
        return d;
    }

    private static byte[] convertLongArrayToBytes(long[] data) {
        byte[] bytes = new byte[data.length * 8];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < 8; j++) {
                bytes[i * 8 + j] = (byte) ((data[i] >> (8 * (7 - j))) & 0xFF);
            }
        }
        return bytes;
    }

    private static long[] convertBytesToLongArray(byte[] bytes) {
        int longCount = bytes.length / 8;
        long[] data = new long[longCount];
        for (int i = 0; i < longCount; i++) {
            data[i] = 0;
            for (int j = 0; j < 8; j++) {
                data[i] |= ((long) (bytes[i * 8 + j] & 0xFF)) << (8 * (7 - j));
            }
        }
        return data;
    }

    private static byte[] readFromFile(String filePath) throws Exception {
        File file = new File(filePath);
        byte[] data = new byte[(int) file.length()];
        try (InputStream in = new FileInputStream(file)) {
            int bytesRead = in.read(data);
            if (bytesRead != data.length) {
                throw new IOException("Failed to read all data from file");
            }
        }
        return data;
    }
}
