import java.io.IOException;
import java.util.Scanner;

public class RecordAppointmentOutcome implements UpdateAppointments {

    private String doctor_name;

    public RecordAppointmentOutcome(String doctor_name) {
        this.doctor_name = doctor_name;
    }
    public void updateAppointments() {
        Database db = new Database();
        Scanner sc = new Scanner(System.in);

        try {
            String appointments = db.ReadFile("Appointments.txt", doctor_name, 4);

            String[] appointmentLines = appointments.split(System.lineSeparator());
            boolean hasUpcomingAppointments = false;

            for (String appointment : appointmentLines) {
                String[] details = appointment.split("\\|");
                String doctor_name = details[4];
                String status = details[3];
                if (doctor_name.equals(this.doctor_name) && status.equals("Upcoming")) {
                    System.out.println(appointment);
                    hasUpcomingAppointments = true;
                }
            }

            if (!hasUpcomingAppointments) {
                System.out.println("No appointments to record.");
                return;
            }

            for (int i = 0; i < appointmentLines.length; i++) {
                appointmentLines[i] = appointmentLines[i].trim();
            }

            System.out.print("Which appointment (id) would you like to record outcome for: ");
            String appointment_id = sc.nextLine();
            Boolean appointment_available = false;

            for (String appointment : appointmentLines) {
                String[] details = appointment.split("\\|");
                String id = details[0];
                if (id.equals(appointment_id)) {
                    appointment_available = true;
                    System.out.println("Enter the following details");
                    System.out.print("Type of service provided: ");
                    String service = sc.nextLine();
                    System.out.print("Enter the Diagnosis of the Patient: ");
                    String diagnosis = sc.nextLine();
                    System.out.print("Medicine Prescribed: ");
                    String medicine = sc.nextLine();
                    String status = "Pending";
                    System.out.print("Amount of Medicine: ");
                    String med_amnt = sc.nextLine();
                    System.out.print("Consultation Notes: ");
                    String notes = sc.nextLine();
                    String[] outcome = {service, diagnosis, medicine, status, med_amnt, notes};
                    db.UpdateFile("Appointments.txt", id, "Upcoming", "Completed");
                    db.appendToLine("Appointments.txt", id, outcome);
                    System.out.println("Outcome Record Updated");
                }
            }

            if (!appointment_available) {
                System.out.println("Appointment does not exist");
            }
        } catch (IOException e) {
            System.err.println("An error occurred while updating appointments: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
