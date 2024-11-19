import java.io.IOException;
import java.util.Scanner;

public class ManageStaff implements UpdateStaff{
    Database db = new Database();
    String filePath = "Staff.txt";
    private Scanner sc = new Scanner(System.in);

    public void updateStaff() {
        System.out.println("Enter Staff ID to Update: ");
        String id = sc.nextLine();
        System.out.println("Enter the field to update (Name, Role, Gender, Age): ");
        String field = sc.nextLine();
        System.out.println("Enter the old value: ");
        String oldValue = sc.nextLine();
        System.out.println("Enter the new value: ");
        String newValue = sc.nextLine();
    
        try {
            db.UpdateFile(filePath, id, oldValue, newValue);
            System.out.println("Staff updated successfully.");
        } catch (IOException e) {
            System.out.println("Error updating staff: " + e.getMessage());
        }
    }
}
