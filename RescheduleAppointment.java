import java.io.IOException;

public class RescheduleAppointment implements UpdateAppointments{
    String id;
    String NewDate;
    String NewTime;
    String Doctor;
    String OldDoctor;

    public RescheduleAppointment(String id, String NewDate, String NewTime, String Doctor, String OldDoctor)
    {
        this.id = id;
        this.NewDate = NewDate;
        this.NewTime = NewTime;
        this.Doctor = Doctor;
        this.OldDoctor = OldDoctor;
    }

    public void updateAppointments() throws IOException
    {
        ScheduleAppointment schedule = new ScheduleAppointment(id, this.NewDate, this.Doctor, this.NewTime);
        schedule.updateAppointments();

    }
    public void CancelPreviousAppointment() throws IOException
    {
        Database db = new Database();
        db.UpdateFile("Appointments.txt", this.id, "Upcoming", "Available");
        db.UpdateFile("Appointments.txt", this.id, "Requested", "Available");
        db.UpdateFile("Appointments.txt", this.id, this.OldDoctor, "None");
        db.UpdateFile("Appointments.txt", this.id, this.id, "None");
    }
}
