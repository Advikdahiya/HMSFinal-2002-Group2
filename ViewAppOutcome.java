import java.io.*;
import java.util.Scanner;

public class ViewAppOutcome implements ViewAppointments{
    private String filePath;
    private Scanner scanner;

    // Constructor
    public ViewAppOutcome(String filePath, Scanner scanner) {
        this.filePath = filePath;
        this.scanner = scanner;
    }

    // Method to view appointment outcome
    public void viewAppointments() throws IOException {
        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine().trim();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");

                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].trim();
                }

                String appointmentId = fields[0];

                if (appointmentId.equalsIgnoreCase(id)) {
                    found = true;
                    String date = fields[1];
                    String time = fields[2];
                    String status = fields[3];
                    String doctor = fields[4];
                    String diagnosis = fields[6];
                    String medicine = fields[7];
                    String prescription_status = fields[8];

                    System.out.println("PatientID: " + id);
                    System.out.println("Appointment date: " + date);
                    System.out.println("Appointment time: " + time);
                    System.out.println("Doctor: " + doctor);
                    System.out.println("Status: " + status);
                    System.out.println("Diagnosis: " + diagnosis);
                    System.out.println("Medicine: " + medicine);
                    System.out.println("Prescription Status: " + prescription_status);
                }
            }

            if (!found) {
                System.out.println("No appointment found for Patient ID: " + id);
            }
        }
    }

}
