import java.io.IOException;
import java.util.Scanner;

public class CheckLowStock implements ViewInventory{
    private Database db;
    private String filePath;
    private Scanner scanner;
    private String medicineToCheck;
    private int threshold;

    public CheckLowStock(Database db, String filePath, Scanner scanner, String medicineToCheck, int threshold) {
        this.db = db;
        this.filePath = filePath;
        this.scanner = scanner;
        this.medicineToCheck = medicineToCheck;
        this.threshold = threshold;
    }

    public void viewInventory() throws IOException {
        String medicineRecord = db.ReadFile(filePath, medicineToCheck, 0);

        if (medicineRecord != null && !medicineRecord.isEmpty()) {
            String[] fields = medicineRecord.split("\\|");

            for (int i = 0; i < fields.length; i++) {
                fields[i] = fields[i].trim();
            }

            int stockLevel = Integer.parseInt(fields[1]);
            String replenishStatus = fields[2];
            

            System.out.println("Current Stock Level for " + medicineToCheck + " is " + stockLevel);

            if (stockLevel < threshold) {
                if (replenishStatus.equalsIgnoreCase("Requested")) {
                    System.out.println("Request has already been sent for " + medicineToCheck + ".");
                    return;
                }

                System.out.println("The Stock Level for " + medicineToCheck + " is low. Replenishment Needed");
                System.out.print("Send Request for Replenishment (y/n): ");
                String answer = scanner.nextLine();

                if (answer.equalsIgnoreCase("y")) {
                    db.UpdateFile(filePath, medicineToCheck, replenishStatus, "Requested");
                    String result = String.valueOf(threshold - stockLevel);
                    System.out.println(result);
                    db.UpdateFile(filePath, medicineToCheck, "None", result);
                    System.out.println("Replenishment request sent.");
                } else {
                    System.out.println("Replenishment request not sent.");
                }
            } else {
                System.out.println("No Replenishment needed now.");
                if (!replenishStatus.equalsIgnoreCase("NA")) {
                    db.UpdateFile(filePath, medicineToCheck, replenishStatus, "NA");
                }
            }
        } else {
            System.out.println("No record found for this medicine.");
        }
    }
}