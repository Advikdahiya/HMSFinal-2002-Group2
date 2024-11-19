import java.io.IOException;
import java.util.Scanner;

public class NurseMenu implements Menu{
    Scanner sc = new Scanner(System.in);
    ViewStaff view;
    UpdateStaff update;
    public void displayMenu() throws IOException {
        chooseOption(); 
    }
    public void chooseOption() throws IOException {
        while (true) {
            System.out.println("\n--- Doctor Menu for Hospital Management System ---");
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. View Doctors");
            System.out.println("3. Update Patient Medical Records");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
    
            int choice = sc.nextInt();
            sc.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.println("1. View Patients by ID");
                    System.out.println("2. View All");
                    System.out.print("Enter your choice: ");
                    int choice2 = sc.nextInt();
                    sc.nextLine(); 
                    switch (choice2) {
                        case 1:
                            System.out.print("Enter the ID of the patient you want to view: ");
                            String id = sc.nextLine();
                            NurseViewPatient view = new NurseViewPatient(id);
                            view.viewPatients();
                            break;
                        case 2:
                            NurseViewPatient viewAll = new NurseViewPatient(null);
                            viewAll.viewAll();
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;
                case 2:
                    NurseViewDoctor doctors = new NurseViewDoctor();
                    doctors.viewStaff();
                    break;
                case 3:
                    System.out.print("Enter the ID of the patient you want to update: ");
                    String id = sc.nextLine();
                    NurseUpdatePatient update = new NurseUpdatePatient(id);
                    update.updatePatients();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }    
}