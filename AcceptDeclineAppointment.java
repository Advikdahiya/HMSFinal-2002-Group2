import java.io.IOException;
import java.util.Scanner;

public class AcceptDeclineAppointment implements UpdateAppointments {

    private String doctor_name;

    public AcceptDeclineAppointment(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void updateAppointments() {
        Database db = new Database();
        Scanner sc = new Scanner(System.in);

        try {
            String appointments = db.ReadFile("Appointments.txt", doctor_name, 4);
            if (appointments == null || appointments.trim().isEmpty()) {
                System.out.println("No appointments found.");
                return;
            }

            String[] appointmentLines = appointments.split(System.lineSeparator());

            for (String appointment : appointmentLines) {
                String[] details = appointment.split("\\|");
                String id = details[0];
                String status = details[3];

                appointment = appointment.trim();

                for (int i = 0; i < details.length; i++) {
                    details[i] = details[i].trim();
                }

                if (status.equalsIgnoreCase("Requested")) {
                    System.out.println("Appointment Details: " + appointment);
                    System.out.print("Do you want to Accept or Decline this Appointment {A/D}: ");
                    String choice = sc.nextLine().trim().toUpperCase();

                    switch (choice) {
                        case "A":
                            db.UpdateFile("Appointments.txt", id, status, "Upcoming");
                            System.out.println("Appointment Status set to Upcoming.");
                            break;
                        case "D":
                            db.UpdateFile("Appointments.txt", id, status, "Available");
                            db.UpdateFile("Appointments.txt", id, details[1], "None");
                            db.UpdateFile("Appointments.txt", id, this.doctor_name, "None");
                            System.out.println("Appointment Declined.");
                            break;
                        default:
                            System.out.println("Invalid Input. Please enter 'A' to Accept or 'D' to Decline.");
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while updating appointments: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
