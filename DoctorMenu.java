import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DoctorMenu implements Menu {
    private Scanner sc = new Scanner(System.in);

    public void displayMenu() throws IOException {
        chooseOption(); 
    }

    public void chooseOption() throws IOException {
        int choice = 0;

        do {
            System.out.println("\n--- Doctor Menu for Hospital Management System ---");
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. Update Patient Medical Records");
            System.out.println("3. View Personal Schedule");
            System.out.println("4. Accept or Decline Appointment Requests");
            System.out.println("5. View Upcoming Appointments");
            System.out.println("6. Record Appointment Outcome");
            System.out.println("7. Logout");
            System.out.print("Enter your choice (1-7): ");

            try {
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine(); 

                    switch (choice) {
                        case 1:
                            System.out.print("Enter the ID of the patient you want to view: ");
                            String id = sc.nextLine();
                            ViewPatientDetails view = new ViewPatientDetails(id);
                            view.viewPatients();
                            break;
                        case 2:
                            System.out.print("Enter the ID of the patient you want to update: ");
                            id = sc.nextLine();
                            UpdatePatientDetails update = new UpdatePatientDetails(id);
                            update.updatePatients();
                            break;
                        case 3:
                            System.out.print("Enter the doctor which you want to view schedule of: ");
                            String doctor_name = sc.nextLine();
                            ViewPersonalSchedule sched = new ViewPersonalSchedule(doctor_name);
                            sched.viewAppointments();
                            break;
                        case 4:
                            System.out.print("Enter the name of doctor which you want to manage appointment requests: ");
                            doctor_name = sc.nextLine();
                            AcceptDeclineAppointment appntment = new AcceptDeclineAppointment(doctor_name);
                            appntment.updateAppointments();
                            break;
                        case 5:
                            System.out.print("Enter the name of doctor which you want to view upcoming appointments of: ");
                            doctor_name = sc.nextLine();
                            ViewUpcomingAppointments upcoming = new ViewUpcomingAppointments(doctor_name);
                            upcoming.viewAppointments();
                            break;
                        case 6:
                            System.out.print("Enter the name of doctor whose appointment outcome must be recorded: ");
                            doctor_name = sc.nextLine();
                            RecordAppointmentOutcome rec = new RecordAppointmentOutcome(doctor_name);
                            rec.updateAppointments();
                            break;
                        case 7:
                            System.out.println("Logging out...");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 7.");
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
                sc.nextLine(); 
            }

        } while (choice != 7);

        System.out.println("Thank you for using the Medical Management System!");
        sc.close();
    }
}
