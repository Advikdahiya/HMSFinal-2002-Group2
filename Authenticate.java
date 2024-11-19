public class Authenticate {
    private String password;
    private String id;
    private String storedPassword; 

    public Authenticate(String password, String id) {
        this.password = password;
        this.id = id;
    }

    public boolean verifyPassword() {
        Database db = new Database();
        try {
            String storedLine = db.ReadFile("Allusers.txt", id, 0); 

            if (storedLine == null || storedLine.equals("None") || storedLine.isEmpty()) {
                return false;
            }

            String[] parts = storedLine.split("\\|");
            if (parts.length <= 1) {
                return false;
            }

            storedPassword = parts[1].trim();

            boolean isMatch;
            if (storedPassword.equals(password)) {

                isMatch = true;
                String hashedPassword = Hash.hashPassword(password);
                db.UpdateFile("Allusers.txt", id, password, hashedPassword);
            } else {
                isMatch = Hash.verifyPassword(password, storedPassword);
            }

            return isMatch;
        } catch (Exception e) {
            System.out.println("An error occurred while reading from the database. Please try again.");
            return false;
        }
    }

    public String getStoredPassword() {
        return storedPassword;
    }
}