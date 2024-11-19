import java.io.IOException;
import java.util.Scanner;

public class RemoveStaff implements UpdateStaff{
    Database db = new Database();
    String filePath = "Staff.txt";
    private Scanner sc = new Scanner(System.in);

    public void updateStaff() throws IOException{
        System.out.println("Remove by which field? ");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Role");
        System.out.println("4. Gender");
        System.out.println("5. Age");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                System.out.print("ID: ");
                String id = sc.nextLine();
                db.deleteRecord(filePath, id, 0);
                break;
            case 2:
                System.out.print("Name: ");
                String name = sc.nextLine();
                db.deleteRecord(filePath, name, 1);
                break;
            case 3:
                System.out.print("Role: ");
                String role = sc.nextLine();
                db.deleteRecord(filePath, role, 2);
                break;
            case 4:
                System.out.print("Gender: ");
                String gender = sc.nextLine();
                db.deleteRecord(filePath, gender, 3);
                break;
            case 5:
                System.out.print("Age: ");
                String age = sc.nextLine();
                db.deleteRecord(filePath, age, 4);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    }
}
