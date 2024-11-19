import java.io.IOException;

public class ViewUpcomingAppointments implements ViewAppointments{
    private String doctor_name;

    public ViewUpcomingAppointments(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void viewAppointments() {
        Database db = new Database();
    
        try {
            String appointments = db.ReadFile("Appointments.txt", doctor_name, 4);
            
            if (appointments == null || appointments.trim().isEmpty()) {
                System.out.println("No appointments found.");
                return;
            }
            
            String[] appointmentLines = appointments.split(System.lineSeparator());
    
            boolean foundUpcoming = false;
    
            for (String appointment : appointmentLines) {
                String[] details = appointment.split("\\|");

                if (details.length < 4) {
                    System.err.println("Invalid appointment record: " + appointment);
                    continue;
                }
    
                String status = details[3];
    
                if ("Upcoming".equalsIgnoreCase(status)) {
                    System.out.println(appointment);
                    foundUpcoming = true;
                }
            }

            if (!foundUpcoming) {
                System.out.println("No upcoming appointments found.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred while retrieving appointments: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
