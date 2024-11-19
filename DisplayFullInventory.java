import java.io.IOException;

public class DisplayFullInventory implements ViewInventory{
    private Database db;
    private String filePath;

    // Constructor
    public DisplayFullInventory(Database db, String filePath) {
        this.db = db;
        this.filePath = filePath;
    }

    @Override
    public void viewInventory() throws IOException {
        String details = db.ReadAll(filePath);
        if (details == null || details.isEmpty()) {
            System.out.println("Inventory is empty or could not be read.");
            return;
        }

        String[] lines = details.split(System.lineSeparator());

        System.out.println("Inventory Details");
        System.out.println("------------------");
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length >= 2) {
                String itemName = parts[0].trim();
                String quantity = parts[1].trim();
                System.out.println("Item: " + itemName + " | Quantity: " + quantity);
            } else {
                System.out.println("Invalid record format: " + line);
            }
        }
    }
}