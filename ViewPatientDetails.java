import java.io.IOException;


public class ViewPatientDetails implements ViewPatient{

    private String patient_id;

    public ViewPatientDetails(String patient_id){
        this.patient_id = patient_id;
    }

    public void viewPatients() {
        Database db = new Database();
        
        try {
            String record = db.ReadFile("Patient.txt", patient_id, 0);
            
            if (record.isEmpty()) {
                System.out.println("Patient does not exist");
            } else {
                System.out.println(record);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while retrieving the patient record: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}