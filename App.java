import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        try {
            boolean loggedIn = false;
            LoginMenu loginMenu = new LoginMenu();

            while (!loggedIn) {
                boolean result = loginMenu.loginMenu(sc);

                if (result) {
                    System.out.println("Operation completed successfully.");
                    loggedIn = true;

                    char userType = Character.toUpperCase(loginMenu.getId().charAt(0)); 
                    Menu menu = null;

                    switch (userType) {
                        case 'P': 
                            menu = new PatientMenu(loginMenu);
                            break;
                        case 'A': 
                            menu = new AdministratorMenu();
                            break;
                        case 'M': 
                            menu = new PharmacistMenu();
                            break;
                        case 'D': 
                            menu = new DoctorMenu();
                            break;
                        case 'N':
                            menu = new NurseMenu();
                            break;
                        default:
                            System.out.println("Invalid user type. Please contact support.");
                            sc.close();
                            return;
                    }

                    if (menu != null) {
                        menu.displayMenu(); 
                    }
                } else {
                    // Login or registration failed
                    System.out.println("Operation failed. Please try again.");
                    
                    // Prompt the user to retry or exit
                    System.out.print("Do you want to try again? (Y/N): ");
                    if (sc.hasNext()) {
                        char retry = Character.toUpperCase(sc.next().charAt(0));
                        if (retry != 'Y') {
                            System.out.println("Exiting the application.");
                            break;
                        }
                    } else {
                        System.out.println("No input received. Exiting the application.");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        finally {
            sc.close(); 
        }
    }
}