import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class PasswordManager {

    private static SecretKey secretKey;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        // Generate a key for encryption/decryption
        generateKey();

        System.out.println("Welcome to Password Manager!");
        System.out.println("Available commands: generate, encrypt, decrypt, exit");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("generate")) {
                System.out.print("Enter the length of the password: ");
                int length = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                String password = generatePassword(length);
                System.out.println("Generated Password: " + password);
            } else if (command.equalsIgnoreCase("encrypt")) {
                System.out.print("Enter the password to encrypt: ");
                String password = scanner.nextLine();
                String encryptedPassword = encryptPassword(password);
                System.out.println("Encrypted Password: " + encryptedPassword);
            } else if (command.equalsIgnoreCase("decrypt")) {
                System.out.print("Enter the encrypted password to decrypt: ");
                String encryptedPassword = scanner.nextLine();
                String decryptedPassword = decryptPassword(encryptedPassword);
                System.out.println("Decrypted Password: " + decryptedPassword);
            } else if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the application. Goodbye!");
                break;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }

        scanner.close();
    }

    private static void generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // for example, 128 or 256 bits key size
        secretKey = keyGen.generateKey();
    }

    private static String generatePassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+<>?";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }

        return password.toString();
    }

    private static String encryptPassword(String password) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private static String decryptPassword(String encryptedPassword) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }
}
