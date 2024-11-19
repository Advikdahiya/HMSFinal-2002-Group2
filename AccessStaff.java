import java.io.IOException;
import java.util.Scanner;

public class AccessStaff implements ViewStaff{
    Database db = new Database();
    String filePath = "Staff.txt";
    private Scanner sc = new Scanner(System.in);

    public void viewStaff() throws IOException{
        System.out.println("Search by which field? ");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Role");
        System.out.println("4. Gender");
        System.out.println("5. Age");
        System.out.println("6. View All");
        
        int choice = sc.nextInt();
        sc.nextLine();
        String search;
        switch (choice) {
            case 1:
                System.out.print("ID: ");
                String id = sc.nextLine();
                search = db.ReadFile(filePath, id, 0);
                System.out.println(search);
                break;
            case 2:
                System.out.print("Name: ");
                String name = sc.nextLine();
                search = db.ReadFile(filePath, name, 1);
                System.out.println(search);
                break;
            case 3:
                System.out.print("Role: ");
                String role = sc.nextLine();
                search = db.ReadFile(filePath, role, 2);
                System.out.println(search);
                break;
            case 4:
                System.out.print("Gender: ");
                String gender = sc.nextLine();
                search = db.ReadFile(filePath, gender, 3);
                System.out.println(search);
                break;
            case 5:
                System.out.print("Age: ");
                String age = sc.nextLine();
                search = db.ReadFile(filePath, age, 4);
                System.out.println(search);
                break;
            case 6:
                search = db.ReadAll(filePath);
                System.out.println(search);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    }
}
