import java.io.IOException;
import java.util.Scanner;

public class ViewScheduledAppointments implements ViewAppointments{
    private String id;

    public ViewScheduledAppointments(String id)
    {
        this.id = id;
    }
    public void viewAppointments() throws IOException
    {
        Database db = new Database();
        Scanner sc = new Scanner(System.in);
        System.out.println("Search Appointment Details by which field? ");
        System.out.println("1. PatientID");
        System.out.println("2. Date");
        System.out.println("3. TimeSlot");
        System.out.println("4. Appointment Status");
        System.out.println("5. Doctor Name");
        int choice = sc.nextInt();
        sc.nextLine();
        String search;
        switch (choice) {
            case 1:
                search = db.ReadFile("Appointments.txt", id, 0);
                if (search.isEmpty()) {
                    System.out.println("No appointments found for ID: " + id);
                    return;
                }
            
                System.out.println("Upcoming appointments for ID: " + id);
                String[] lines = search.split(System.lineSeparator());
                for (String line : lines) {
                    String[] columns = line.split("\\|");
                    if (columns.length > 3 && columns[3].trim().equals("Upcoming")) {
                        System.out.println(line); 
                    }
                }
                break;

            case 2:
                search = db.ReadFile("Appointments.txt", id, 0);
                if (search.isEmpty()) {
                    System.out.println("No appointments found for ID: " + id);
                    return;
                }
                System.out.println("Date: ");
                String date = sc.next();
                System.out.println("Upcoming appointments for ID: " + id);
                lines = search.split(System.lineSeparator());
                for (String line : lines) {
                    String[] columns = line.split("\\|");
                    if (columns.length > 1 && columns[1].trim().equals(date)) {
                        System.out.println(line); 
                    }
                    else
                    {
                        System.out.println("No appointments found for ID: " + id);
                    }
                }
                break;

            case 3:
                search = db.ReadFile("Appointments.txt", id, 0);
                if (search.isEmpty()) {
                    System.out.println("No appointments found for ID: " + id);
                    return;
                }
                System.out.println("Time: ");
                String time = sc.next();
                System.out.println("Upcoming appointments for ID: " + id);
                lines = search.split(System.lineSeparator());
                for (String line : lines) {
                    String[] columns = line.split("\\|");
                    if (columns.length > 2 && columns[2].trim().equals(time)) {
                        System.out.println(line); 
                    }
                    else
                    {
                        System.out.println("No appointments found for ID: " + id);
                    }
                }
                break;
    
            case 4:
                search = db.ReadFile("Appointments.txt", id, 0);
                if (search.isEmpty()) {
                    System.out.println("No appointments found for ID: " + id);
                    return;
                }
                System.out.println("Status: ");
                String status = sc.next();
                System.out.println("Upcoming appointments for ID: " + id);
                lines = search.split(System.lineSeparator());
                for (String line : lines) {
                    String[] columns = line.split("\\|");
                    if (columns.length > 3 && columns[3].trim().equals(status)) {
                        System.out.println(line); 
                    }
                    else
                    {
                        System.out.println("No appointments found for ID: " + id);
                    }
                }
                break;

            case 5:
               search = db.ReadFile("Appointments.txt", id, 0);
                if (search.isEmpty()) {
                    System.out.println("No appointments found for ID: " + id);
                    return;
                }
                System.out.println("Doctor: ");
                String doctor = sc.next();
                System.out.println("Upcoming appointments for ID: " + id);
                lines = search.split(System.lineSeparator());
                for (String line : lines) {
                    String[] columns = line.split("\\|");
                    if (columns.length > 4 && columns[4].trim().equals(doctor)) {
                        System.out.println(line); 
                    }
                    else
                    {
                        System.out.println("No appointments found for ID: " + id);
                    }
                }
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    
    }   
}