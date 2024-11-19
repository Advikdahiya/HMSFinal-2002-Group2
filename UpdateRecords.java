import java.io.IOException;

public class UpdateRecords implements UpdatePatient
{
    String id;
    String old;
    String replace;

    public UpdateRecords(String id, String old, String replace)
    {
        this.id = id;
        this.old = old;
        this.replace = replace;
    }

    public void updatePatients() {
        Database db = new Database();
        try {
            db.UpdateFile("Patient.txt", id, old, replace);
            System.out.println("Update successful for ID: " + id);
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }

}
