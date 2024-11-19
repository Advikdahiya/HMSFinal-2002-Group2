import java.io.IOException;
import java.util.Scanner;

public class NurseViewDoctor implements ViewStaff {
    Scanner sc = new Scanner(System.in);
    Database db = new Database();
    String search;
    public void viewStaff(){
        try{
        search = db.ReadFile("Staff.txt", "Doctor", 2);
        System.out.println(search);
        } catch (IOException e) {
            System.err.println("An error occurred while retrieving the patient record: " + e.getMessage());
            e.printStackTrace();
        }
    }
}