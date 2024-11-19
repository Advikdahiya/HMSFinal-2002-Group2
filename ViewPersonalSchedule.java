import java.io.IOException;

public class ViewPersonalSchedule implements ViewAppointments{
    private String doctor_name;

    public ViewPersonalSchedule(String doctor_name){
        this.doctor_name = doctor_name;
    }

    public void viewAppointments() {
        Database db = new Database();
        
        try {
            String appointments = db.ReadFile("Appointments.txt", doctor_name, 4);
            
            if (appointments == null || appointments.trim().isEmpty()) {
                System.out.println("No appointments found for today.");
            } else {
                System.out.println("This is your schedule for today:");
                System.out.println(appointments);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while retrieving appointments: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
