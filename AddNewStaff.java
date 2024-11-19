import java.io.IOException;
import java.util.Scanner;

public class AddNewStaff implements UpdateStaff{
    Database db = new Database();
    String filePath = "Staff.txt";
    private Scanner sc = new Scanner(System.in);

    public void updateStaff() {
        System.out.println("Enter Staff Details:");
        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Role: ");
        String role = sc.nextLine();
        System.out.print("Gender: ");
        String gender = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine(); 
    
        String newStaff = id + "|" + name + "|" + role + "|" + gender + "|" + age + "\n";
    
        try {
            db.addnew(filePath, newStaff);
            System.out.println("New staff added successfully.");
        } catch (IOException e) {
            System.out.println("Error adding staff: " + e.getMessage());
        }
    }
}
