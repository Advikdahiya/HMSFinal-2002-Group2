import java.io.IOException;
import java.util.Scanner;

public class LoginMenu {
    public String id; 
    private static final String USERS_FILE = "Allusers.txt";
    private static final String STAFF_FILE = "Staff.txt";
    private static final String PATIENT_FILE = "Patient.txt";
    private static final String DEFAULT_PASSWORD = "password";

    public boolean loginMenu(Scanner sc) throws IOException {
        System.out.println("Login");
        System.out.print("First Time Login (Y/N): ");
        if (!sc.hasNext()) {
            System.out.println("No input received.");
            return false;
        }
        char firstTimeLogin = Character.toUpperCase(sc.next().charAt(0));
        String oldPassword;
        String newPassword;
        String name;
        String role;

        Database db = new Database();

        switch (firstTimeLogin) {
            case 'Y':
                System.out.print("Enter your Login Hospital ID: ");
                if (!sc.hasNext()) {
                    System.out.println("No input received.");
                    return false;
                }
                id = sc.next();

                String userLine = db.ReadFile(USERS_FILE, id, 0);

                if (userLine == null || userLine.isEmpty() || userLine.equalsIgnoreCase("None")) {

                    System.out.print("Enter your name: ");
                    if (!sc.hasNext()) {
                        System.out.println("No input received.");
                        return false;
                    }
                    name = sc.next();

                    char roleChar = Character.toUpperCase(id.charAt(0));
                    switch (roleChar) {
                        case 'P':
                            role = "Patient";
                            break;
                        case 'A':
                            role = "Administrator";
                            break;
                        case 'M':
                            role = "Pharmacist";
                            break;
                        case 'D':
                            role = "Doctor";
                            break;
                        case 'N':
                            role = "Nurse";
                            break;
                        default:
                            System.out.println("Invalid ID format. Cannot determine role.");
                            return false;
                    }

                    String hashedDefaultPassword = Hash.hashPassword(DEFAULT_PASSWORD);
                    String newUserEntry = id + "|" + hashedDefaultPassword;
                    db.addnew(USERS_FILE, newUserEntry);

                    if (role.equalsIgnoreCase("Patient")) {
                        String patientEntry = id + "|" + name + "|None1|None2|None3|None4|None5|";
                        db.addnew(PATIENT_FILE, patientEntry);
                    } else {
                        String staffEntry = id + "|" + name + "|" + role + "|None|None";
                        db.addnew(STAFF_FILE, staffEntry);
                    }

                    System.out.println("New user registered with default password 'password'.");
                    System.out.println("Please change your password.");

                    System.out.print("Enter new password: ");
                    if (!sc.hasNext()) {
                        System.out.println("No input received.");
                        return false;
                    }
                    newPassword = sc.next();
                    Login login = new Login(hashedDefaultPassword, newPassword, id);
                    login.changePassword();
                    System.out.println("Password changed successfully. Please login again.");
                    return true;
                } else {
                    System.out.println("User ID already exists. Please login instead.");
                    return false;
                }

            case 'N':
                System.out.print("Enter your Login Hospital ID: ");
                if (!sc.hasNext()) {
                    System.out.println("No input received.");
                    return false;
                }
                id = sc.next();
                System.out.print("Enter password: ");
                if (!sc.hasNext()) {
                    System.out.println("No input received.");
                    return false;
                }
                oldPassword = sc.next();
                Authenticate authenticator = new Authenticate(oldPassword, id);

                if (authenticator.verifyPassword()) {
                    System.out.println("Login successful!");
                    return true;
                } else {
                    System.out.println("Incorrect Password!");
                    return false;
                }

            default:
                System.out.println("Invalid choice. Please enter 'Y' or 'N'.");
                return false;
        }
    }

    public String getId() {
        return id; 
    }
}