import java.io.IOException;
import java.util.Scanner;

public class PatientMenu implements Menu{
    private LoginMenu loginMenu;

    public PatientMenu(LoginMenu loginMenu) {
        this.loginMenu = loginMenu;
    }
    public void displayMenu() throws IOException {
        chooseOption(); 
    }
    public void chooseOption() throws IOException
    {
        boolean loggedIn = true;
        while (loggedIn)
        {
            System.out.println("1: View Medical Record");
            System.out.println("2: Update Personal Information");
            System.out.println("3: View Available Appointment Slots");
            System.out.println("4: Schedule an Appointment");
            System.out.println("5: Reschedule an Appointment");
            System.out.println("6: Cancel an Appointment");
            System.out.println("7: View Scheduled Appointments");
            System.out.println("8: View Past Appointments Records");
            System.out.println("9: Logout");

            int choice;
            Scanner sc = new Scanner(System.in);
            System.out.println("Select an operation:");
            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    ViewMedicalRecords view = new ViewMedicalRecords(loginMenu.getId());
                    view.viewPatients();
                    break;
                
                case 2:
                System.out.println("Enter field to change: ");
                System.out.println("1: Phone ");
                System.out.println("2: Email ");
                System.out.println("3: DOB ");
                System.out.println("4: Gender ");
                System.out.println("5: Blood Type ");
                int field = sc.nextInt();
                
                // Declare variables in one place
                String first, old = "None", replace;
                
                if (field == 1) {
                    System.out.print("First time entering Phone Number (Y/N): ");
                    first = sc.next(); 
                    if (first.equalsIgnoreCase("N")) {
                        System.out.print("Enter old Phone Number: ");
                        old = sc.next();
                    }
                    else
                    {
                        old = "None3";
                    }
                    System.out.print("Enter New Phone Number: ");
                    replace = sc.next();
                    UpdateRecords update = new UpdateRecords(loginMenu.getId(), old, replace);
                    update.updatePatients();
                } else if (field == 2) {
                    System.out.print("First time entering Email (Y/N): ");
                    first = sc.next(); 
                    if (first.equalsIgnoreCase("N")) {
                        System.out.print("Enter old Email: ");
                        old = sc.next();
                    }
                    else
                    {
                        old = "None4";
                    }
                    System.out.print("Enter new Email: ");
                    replace = sc.next();
                    UpdateRecords update = new UpdateRecords(loginMenu.getId(), old, replace);
                    update.updatePatients();
                } else if (field == 3) {
                    System.out.print("First time entering DOB (Y/N): ");
                    first = sc.next(); 
                    if (first.equalsIgnoreCase("N")) {
                        System.out.print("Enter old DOB (yyyy-mm-dd): ");
                        old = sc.next();
                    }
                    else
                    {
                        old = "None1";
                    }
                    System.out.print("Enter new DOB (yyyy-mm-dd): ");
                    replace = sc.next();
                    UpdateRecords update = new UpdateRecords(loginMenu.getId(), old, replace);
                    update.updatePatients();
                } else if (field == 4) {
                    System.out.print("First time entering Gender (Y/N): ");
                    first = sc.next(); 
                    if (first.equalsIgnoreCase("N")) {
                        System.out.print("Enter old Gender: ");
                        old = sc.next();
                    }
                    else
                    {
                        old = "None2";
                    }
                    System.out.print("Enter new Gender: ");
                    replace = sc.next();
                    UpdateRecords update = new UpdateRecords(loginMenu.getId(), old, replace);
                    update.updatePatients();
                } else if (field == 5) {
                    System.out.print("First time entering Blood Type (Y/N): ");
                    first = sc.next(); 
                    if (first.equalsIgnoreCase("N")) {
                        System.out.print("Enter old Blood Type: ");
                        old = sc.next();
                    }
                    else
                    {
                        old = "None5";
                    }
                    System.out.print("Enter new Blood Type: ");
                    replace = sc.next();
                    UpdateRecords update = new UpdateRecords(loginMenu.getId(), old, replace);
                    update.updatePatients();
                } else {
                    System.out.println("Invalid selection. Please try again.");
                }
                
                        break;
                    
                case 3:
                    System.out.println("Available Appointments: ");
                    ViewAvailableAppointments app = new ViewAvailableAppointments();
                    app.viewAppointments();
                    break;
                
                case 4:
                    System.out.println("Available Appointments: ");
                    ViewAvailableAppointments appointments = new ViewAvailableAppointments();
                    appointments.viewAppointments();
                    System.out.print("Enter the Date for appointment: ");
                    String date = sc.next();
                    System.out.print("Enter the Time for appointment: ");
                    String time = sc.next();
                    System.out.print("Enter Doctor Name: ");
                    String doctor = sc.next();
                    ScheduleAppointment schedule = new ScheduleAppointment(loginMenu.getId(), date, doctor, time);
                    schedule.updateAppointments();
                    break;
                
                case 5:
                    System.out.println("Available Appointments: ");
                    ViewAvailableAppointments reappointments = new ViewAvailableAppointments();
                    reappointments.viewAppointments();
                    System.out.print("Enter the New Date for appointment: ");
                    date = sc.next();
                    System.out.print("Enter the New Time for appointment: ");
                    time = sc.next();
                    System.out.print("Enter the New Doctor Name: ");
                    String new_doctor = sc.next();
                    System.out.print("Enter the Old Doctor Name: ");
                    String old_doctor = sc.next();
                    RescheduleAppointment reschedule = new RescheduleAppointment(loginMenu.getId(), date, time, new_doctor, old_doctor);
                    reschedule.CancelPreviousAppointment();
                    reschedule.updateAppointments();
                    break;
                
                case 6:
                    System.out.print("Enter doctor name: ");
                    doctor = sc.next();
                    CancelAppointments cancel = new CancelAppointments(loginMenu.getId(), doctor);
                    cancel.updateAppointments();
                    break;
                case 7:
                    ViewScheduledAppointments apps = new ViewScheduledAppointments(loginMenu.getId());
                    apps.viewAppointments();
                    break;
                
                case 8:
                    ViewPastAppointments past = new ViewPastAppointments(loginMenu.getId());
                    past.viewAppointments();
                    break;
                case 9:
                    System.out.println("Logging out...");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
   
}
