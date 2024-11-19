import java.io.IOException;
import java.util.Scanner;

public class AdminViewAppointment implements ViewAppointments{
    Database db = new Database();
    String filePath = "Appointments.txt";
    private Scanner sc = new Scanner(System.in);

    public void viewAppointments() throws IOException{
        System.out.println("Search Appointment Details by which field? ");
        System.out.println("1. PatientID");
        System.out.println("2. Date");
        System.out.println("3. Timeslot");
        System.out.println("4. Appointment Status");
        System.out.println("5. Doctor Name");
        System.out.println("6. Outcome");
        System.out.println("7. View All");
        int choice = sc.nextInt();
        sc.nextLine();
        String search;
        switch (choice) {
            case 1:
                System.out.print("PatientID: ");
                String id = sc.nextLine();
                search = db.ReadFile(filePath, id, 0);
                System.out.println(search);
                break;
            case 2:
                System.out.print("Data: ");
                String date = sc.nextLine();
                search = db.ReadFile(filePath, date, 1);
                System.out.println(search);
                break;
            case 3:
                System.out.print("Timeslot: ");
                String time = sc.nextLine();
                search = db.ReadFile(filePath, time, 2);
                System.out.println(search);
                break;
            case 4:
                System.out.print("Appointment Status: ");
                String status = sc.nextLine();
                search = db.ReadFile(filePath, status, 3);
                System.out.println(search);
                break;
            case 5:
                System.out.print("Doctor Name: ");
                String doctor = sc.nextLine();
                search = db.ReadFile(filePath, doctor, 4);
                System.out.println(search);
                break;
            case 6:
                System.out.print("Appointment Outcome: ");
                String outcome = sc.nextLine();
                search = db.ReadFile(filePath, outcome, 5);
                System.out.println(search);
                break;
            case 7:
                search = db.ReadAll(filePath);
                System.out.println(search);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    }
}
