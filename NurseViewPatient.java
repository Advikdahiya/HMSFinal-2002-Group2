import java.io.IOException;

public class NurseViewPatient extends ViewPatientDetails{
    Database db = new Database();
    public NurseViewPatient(String patient_id){
        super(patient_id);
    }
    public void viewAll(){
        try{
            String view = db.ReadAll("Patient.txt");
            System.out.println(view);
        } catch (IOException e) {
            System.err.println("An error occurred while retrieving the patient record: " + e.getMessage());
            e.printStackTrace();
        }
    }
}