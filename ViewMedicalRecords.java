import java.io.IOException;

public class ViewMedicalRecords implements ViewPatient {
    private String id;

    public ViewMedicalRecords(String id) {
        this.id = id; 
    }

    public void viewPatients() throws IOException {
        Database db = new Database();
        System.out.println(db.ReadFile("Patient.txt", this.id, 0));
    }
}
