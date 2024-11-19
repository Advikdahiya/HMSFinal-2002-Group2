public class Login {
    private String storedPassword; 
    private String newPassword;
    private String id;

    public Login(String storedPassword, String newPassword, String id) {
        this.storedPassword = storedPassword;
        this.newPassword = newPassword;
        this.id = id;
    }

    public void changePassword() {
        Database db = new Database();
        try {
            String hashedNewPassword = Hash.hashPassword(newPassword);
            db.UpdateFile("Allusers.txt", id, storedPassword, hashedNewPassword);
            System.out.println("Password updated successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while updating the password. Please try again.");
        }
    }
}