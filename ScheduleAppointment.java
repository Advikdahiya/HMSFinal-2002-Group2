import java.io.IOException;

public class ScheduleAppointment implements UpdateAppointments
{
    String Doctor;
    String Date;
    String TimeSlot;
    String id;

    public ScheduleAppointment(String id, String Date, String Doctor, String TimeSlot)
    {
        this.Date = Date;
        this.TimeSlot = TimeSlot;
        this.id = id;
        this.Doctor = Doctor;
    }

    public void updateAppointments() throws IOException {
        Database db = new Database();
    
        String tocheck = "None|" + this.Date + "|" + this.TimeSlot + "|Available|None";
        String line = db.ReadFile("Appointments.txt", tocheck, -1);
    
        if (line.startsWith("An error occurred")) {
            System.out.println("Error fetching appointments: " + line);
            return;
        }
    
        boolean Available = !line.isEmpty() && line.contains("Available");
    
        if (Available) {
            String toAdd = this.id + "|" + this.Date + "|" + this.TimeSlot + "|Requested|" + this.Doctor;
            
            db.replaceLine("Appointments.txt", line.trim(), toAdd);
            System.out.println("Appointment requested");
        } else {
            System.out.println("Appointment not available at this time");
        }
    }
    
    
    
}
