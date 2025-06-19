import java.util.Arrays;
import java.util.Scanner;

public class myMiniHealthSystem {
    static final int MAX_PATIENTS = 20;
    static int patientCount = 0;

    static String[] patientIDs = new String[MAX_PATIENTS];
    static String[] patientNames = new String[MAX_PATIENTS];
    static int[] patientAges = new int[MAX_PATIENTS];
    static String[] doctorSpecialties = new String[MAX_PATIENTS];
    static int[] roomNumbers = new int[MAX_PATIENTS];
    static boolean[] isAdmitted = new boolean[MAX_PATIENTS];

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Hospital Management System =====");
            System.out.println("1. Register New Patient");
            System.out.println("2. Assign Doctor to Patient");
            System.out.println("3. Check-In Patient to Room");
            System.out.println("4. Generate Bill for Patient");
            System.out.println("5. Discharge Patient");
            System.out.println("6. View All Patients");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> assignDoctor();
                case 3 -> checkInPatient();
                case 4 -> generateBill();
                case 5 -> dischargePatient();
                case 6 -> viewAllPatients();
                case 7 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 7);
    }

    static void registerPatient() {
        if (patientCount >= MAX_PATIENTS) {
            System.out.println("Hospital capacity full.");
            return;
        }

        System.out.print("Enter Patient ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine(); // consume newline

        if (age <= 0) {
            System.out.println("Invalid age.");
            return;
        }

        patientIDs[patientCount] = id;
        patientNames[patientCount] = name;
        patientAges[patientCount] = age;
        patientCount++;
        System.out.println("Patient registered successfully.");
    }

    static void assignDoctor() {
        System.out.print("Enter Patient ID: ");
        String id = sc.nextLine();
        int index = findPatientIndex(id);
        if (index == -1) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Enter Doctor Specialty (Heart/Lungs/Cancer/General): ");
        String specialty = sc.nextLine();

        if (Arrays.asList("Heart", "Lungs", "Cancer", "General").contains(specialty)) {
            doctorSpecialties[index] = specialty;
            System.out.println("Doctor assigned successfully.");
        } else {
            System.out.println("Specialist not available. Assigned to 'General'.");
            doctorSpecialties[index] = "General";
        }
    }

    static void checkInPatient() {
        System.out.print("Enter Patient ID: ");
        String id = sc.nextLine();
        int index = findPatientIndex(id);
        if (index == -1) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Enter Room Number (101-110): ");
        int room = sc.nextInt();
        sc.nextLine();

        if (room < 101 || room > 110) {
            System.out.println("Invalid room number.");
            return;
        }

        roomNumbers[index] = room;
        isAdmitted[index] = true;
        System.out.println("Patient checked in to room " + room);
    }

    static void generateBill() {
        System.out.print("Enter Patient ID: ");
        String id = sc.nextLine();
        int index = findPatientIndex(id);
        if (index == -1) {
            System.out.println("Patient not found.");
            return;
        }

        int roomCost = 100;
        int medicine = 50;
        int test = 25;
        int total = roomCost + medicine + test;

        System.out.println("Room: $" + roomCost);
        System.out.println("Medicine: $" + medicine);
        System.out.println("Test: $" + test);
        System.out.println("Total Bill: $" + total);
    }

    static void dischargePatient() {
        System.out.print("Enter Patient ID: ");
        String id = sc.nextLine();
        int index = findPatientIndex(id);
        if (index == -1) {
            System.out.println("Patient not found.");
            return;
        }

        // Remove by resetting data
        patientIDs[index] = null;
        patientNames[index] = null;
        patientAges[index] = 0;
        doctorSpecialties[index] = null;
        roomNumbers[index] = 0;
        isAdmitted[index] = false;

        System.out.println("Patient discharged successfully.");
    }

    static void viewAllPatients() {
        System.out.println("\n--- All Patients ---");
        for (int i = 0; i < patientCount; i++) {
            if (patientIDs[i] != null) {
                System.out.printf("ID: %s | Name: %s | Age: %d | Doctor: %s | Room: %d | Admitted: %b\n",
                        patientIDs[i], patientNames[i], patientAges[i],
                        doctorSpecialties[i] != null ? doctorSpecialties[i] : "N/A",
                        roomNumbers[i], isAdmitted[i]);
            }
        }
    }

    static int findPatientIndex(String id) {
        for (int i = 0; i < patientCount; i++) {
            if (id.equals(patientIDs[i])) {
                return i;
            }
        }
        return -1;
    }
}
