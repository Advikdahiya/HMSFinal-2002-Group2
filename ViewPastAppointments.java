import java.io.IOException;

public class ViewPastAppointments implements ViewAppointments{
    private String id;
    public ViewPastAppointments(String id)
    {
        this.id = id;
    }
    public void viewAppointments() throws IOException
    {
        String search;
        Database db = new Database();
        search = db.ReadFile("Appointments.txt", id, 0);
        if (search.isEmpty()) {
            System.out.println("No appointments found for ID: " + id);
            return;
        }
    
        System.out.println("Completed appointments for ID: " + id);
        String[] lines = search.split(System.lineSeparator());
        for (String line : lines) {
            String[] columns = line.split("\\|");
            if (columns.length > 3 && columns[3].trim().equals("Completed")) {
                System.out.println(line); 
            }
        }       
    }
}