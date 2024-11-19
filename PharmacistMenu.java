import java.io.IOException;
import java.util.Scanner;

public class PharmacistMenu implements Menu{
    public void displayMenu() throws IOException {
        chooseOption(); 
    }
    public void chooseOption() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Database db = new Database();
        String inventoryFilePath = "Inventory.txt";
        String patientFilePath = "Patient.txt";
        String appointmentFilePath = "Appointments.txt"; // Needed for UpdatePrescriptionStatus and ViewAppOutcome

        while (true) {
            System.out.println("\n--- Pharmacist Menu ---");
            System.out.println("1: View Medication Inventory");
            System.out.println("2: Check if Stock is Low");
            System.out.println("3: Update Prescription Status");
            System.out.println("4: View Appointment Outcome");
            System.out.println("5: Logout");

            System.out.print("Enter your choice: ");
            String choiceInput = scanner.nextLine();

            try {
                int choice = Integer.parseInt(choiceInput);
                switch (choice) {
                    case 1:
                        // Polymorphic call via ViewInventory interface
                        ViewInventory displayInventory = new DisplayFullInventory(db, inventoryFilePath);
                        displayInventory.viewInventory();
                        break;

                    case 2:
                        // Polymorphic call via ViewInventory interface
                        System.out.print("Enter Medicine Name to check stock: ");
                        String medicineToCheck = scanner.nextLine();
                        System.out.print("Enter warning stock point: ");
                        String thresholdInput = scanner.nextLine();
                        int threshold;

                        try {
                            threshold = Integer.parseInt(thresholdInput);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid threshold. Please enter a valid number.");
                            break;
                        }

                        ViewInventory stockChecker = new CheckLowStock(db, inventoryFilePath, scanner, medicineToCheck, threshold);
                        stockChecker.viewInventory();
                        break;

                    case 3:
                        // Polymorphic call via UpdatePatient interface
                        System.out.print("Enter Patient ID to update prescription status: ");
                        String patientID = scanner.nextLine();

                        UpdatePatient prescriptionUpdater = new UpdatePrescriptionStatus(patientFilePath, appointmentFilePath, patientID);
                        prescriptionUpdater.updatePatients();
                        break;

                    case 4:
                        // Polymorphic call via ViewAppointments interface
                        ViewAppointments viewAppointmentOutcome = new ViewAppOutcome(appointmentFilePath, scanner);
                        viewAppointmentOutcome.viewAppointments();
                        break;

                    case 5:
                        System.out.println("Logging out.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid Choice. Please select a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number corresponding to the menu options.");
            } catch (IOException e) {
                System.out.println("An error occurred while performing the action: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
