import java.io.IOException;
import java.util.Scanner;

public class AdminStockApprove implements UpdateInventory{
    Database db = new Database();
    String filePath = "Inventory.txt";
    Scanner sc = new Scanner(System.in);
    public void updateInventory() {
        try {
            String search = db.ReadFile(filePath, "Requested", 2);   
            if (search == null || search.isEmpty()) {
                System.out.println("No replenishment requests found.");
                return;
            }
            System.out.println("Replenishment Requests Found:");
            System.out.println(search);
    
            System.out.println("1. Approve above replenishment requests");
            System.out.println("2. Decline replenishment requests");

            int choice = sc.nextInt();
            sc.nextLine();     
            switch (choice) {
                case 1: 
                    approveReplenishmentRequests(search);
                    break;    
                case 2: 
                    System.out.println("Replenishment requests have been declined.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
    
        } catch (IOException e) {
            System.err.println("An error occurred while processing the request: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void approveReplenishmentRequests(String search) throws IOException {
        String[] lines = search.split(System.lineSeparator());
                for (String line : lines) {
                    String[] columns = line.split("\\|");
                    String updated = columns[0].trim() + "|" + String.valueOf(Integer.parseInt(columns[3].trim())+Integer.parseInt(columns[1].trim())) + "|NA|None";
                    db.replaceLine(filePath, line, updated);
        }
    }   
}