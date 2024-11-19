import java.io.IOException;
import java.util.Scanner;

public class UpdatePatientDetails implements UpdatePatient{

    private String patient_id;

    public UpdatePatientDetails(String patient_id) {
        this.patient_id = patient_id;
    }

    public void updatePatients() {
        Database db = new Database();
        Scanner sc = new Scanner(System.in);
        try {
            String old_record = db.ReadFile("Patient.txt", patient_id, 0);
            
            if (old_record.isEmpty()) {
                System.out.println("Patient does not exist");
            } else {
                System.out.print("Enter the new diagnoses: ");
                String new_diagnoses = sc.nextLine();
                
                System.out.print("Enter the prescriptions: ");
                String prescriptions = sc.nextLine();
                
                System.out.print("Enter the treatment plans: ");
                String treatment_plans = sc.nextLine();
                
                String[] update_info = {new_diagnoses, prescriptions, treatment_plans};
                db.appendToLine("Patient.txt", patient_id, update_info);
                
                System.out.println("Patient Updated Successfully");
            }
        } catch (IOException e) {
            System.err.println("An error occurred while updating the patient record: " + e.getMessage());
            e.printStackTrace();
        }
    }
}