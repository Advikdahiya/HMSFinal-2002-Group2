import java.io.IOException;

public class ViewAvailableAppointments implements ViewAppointments{
    public void viewAppointments() throws IOException
    {
        Database db = new Database();
        System.out.println(db.ReadFile("Appointments.txt", "Available", 3));
    }
}
