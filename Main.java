import java.io.*;
import java.util.Scanner;

public class Main {

    // File where passwords are stored
    static String FILE_NAME = "passwords.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // 🔐 Master Password Check
            System.out.print("Enter Master Password: ");
            String input = sc.nextLine();

            if (!input.equals("admin123")) {
                System.out.println("Access Denied!");
                return;
            }

            System.out.println("Access Granted!");

            // 📋 Menu Loop
            while (true) {
                System.out.println("\n==== Password Manager ====");
                System.out.println("1. Add Password");
                System.out.println("2. View Passwords");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");

                int choice = sc.nextInt();
                sc.nextLine(); // clear buffer

                if (choice == 1) {
                    addPassword(sc);
                } else if (choice == 2) {
                    viewPasswords();
                } else if (choice == 3) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Invalid choice!");
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    // ➕ Add Password
    public static void addPassword(Scanner sc) throws IOException {
        System.out.print("Enter Site: ");
        String site = sc.nextLine();

        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        FileWriter fw = new FileWriter(FILE_NAME, true);
        fw.write(site + "," + username + "," + password + "\n");
        fw.close();

        System.out.println("✅ Password Saved Successfully!");
    }

    // 📖 View Passwords
    public static void viewPasswords() throws IOException {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No passwords stored yet!");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        String line;

        System.out.println("\n--- Saved Passwords ---");

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");

            System.out.println("Site: " + data[0]);
            System.out.println("Username: " + data[1]);
            System.out.println("Password: " + data[2]);
            System.out.println("----------------------");
        }

        br.close();
    }
}