import java.util.Scanner;
import java.lang.Thread;
import java.util.Arrays;

public class healthSystemManagement {
    int MAX_PATIENTS = 20;

    int patientCount = 0;
    int[] patientIds = new int[MAX_PATIENTS];
    String[] patientNames = new String[MAX_PATIENTS];
    int[] patientAge = new int[MAX_PATIENTS];
    String[] patientDiseases = new String[MAX_PATIENTS];

    int[] doctorIds = new int[MAX_PATIENTS];
    String[] doctorNames = new String[MAX_PATIENTS];
    int[] doctorSpecializations = new int[MAX_PATIENTS];
    int doctorCount = 0;

    int[] roomNumbers = new int[MAX_PATIENTS];
    String[] roomTypes = new String[MAX_PATIENTS];
    boolean[] roomAvailability = new boolean[MAX_PATIENTS];
    int roomCount = 0;

    // Main method to run the health system management
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        healthSystemManagement system = new healthSystemManagement();
        while (true){
            System.out.println("Welcome to the Health System Management System");
            System.out.println("1. Register New Patient");
            System.out.println("2. Assign Doctor to Patient");
            System.out.println("3. Check-in Patient to Room");
            System.out.println("4. Generate Bill for Patient");
            System.out.println("5. Discharge Patient");
            System.out.println("6. View All Patients");
            System.out.println("7. Exit");
            System.out.println("Please enter your choice:");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    system.registerPatient(scanner);
                    break;
                case 2:
                    system.assignDoctorToPatient(scanner);
                    break;
                case 3:
                    system.checkInPatient(scanner);
                    break;
                case 4:
                    system.generateBill(scanner);
                    break;
                case 5:
                    system.dischargePatient(scanner);
                    break;
                case 6:
                    system.viewAllPatients();
                    break;
                case 7:
                    System.out.println("Exiting the system. Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }

    // Method to register a new patient
    public void registerPatient(Scanner scanner) {
        if (patientCount < MAX_PATIENTS) {
            System.out.println("Enter Patient ID:");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Check if patient ID already exists
            for (int i = 0; i < patientCount; i++) {
                if (patientIds[i] == id) {
                    System.out.println("Patient ID already exists. Please use a different ID.");
                    return;
                }
            }

            System.out.println("Enter Patient Name:");
            String name = scanner.nextLine();
            System.out.println("Enter Patient Age:");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println("Enter Patient Disease:");
            String disease = scanner.nextLine();

            patientIds[patientCount] = id;
            patientNames[patientCount] = name;
            patientAge[patientCount] = age;
            patientDiseases[patientCount] = disease;
            patientCount++;

            System.out.println("Patient registered successfully!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted: " + e.getMessage());
            }
        } else {
            System.out.println("Maximum patient limit reached. Cannot register more patients.");
        }
    }

    private static final int SPECIALIZATION_HEART = 1;
    private static final int SPECIALIZATION_LUNG = 2;
    private static final int SPECIALIZATION_CANCER = 3;
    private static final int SPECIALIZATION_GENERAL = 4;

    private boolean isPatientExists(int patientId) {
        for (int i = 0; i < patientCount; i++) {
            if (patientIds[i] == patientId) {
                return true;
            }
        }
        return false;
    }

    private int findAvailableDoctor(int specialization) {
        for (int i = 0; i < doctorCount; i++) {
            if (doctorSpecializations[i] == specialization) {
                return i;
            }
        }
        return -1;
    }

    // Method to assign a doctor to a patient
    public static void assignDoctorToPatient(Scanner scanner) {
        healthSystemManagement system = new healthSystemManagement();

        System.out.println("Enter Patient ID:");
        int patientId = scanner.nextInt();

        if (!system.isPatientExists(patientId)) {
            System.out.println("Patient does not exist.");
            return;
        }

        System.out.println("Enter Doctor Specialization:");
        System.out.println("1. Heart");
        System.out.println("2. Lung");
        System.out.println("3. Cancer");
        System.out.println("4. General");
        int specialization = scanner.nextInt();

        int doctorIndex = system.findAvailableDoctor(specialization);
        if (doctorIndex == -1) {
            System.out.println("Doctor not available");
        } else {
            System.out.println("Assigned to doctor: " + system.doctorNames[doctorIndex]);
        }
    }
    // Method to check in a patient to a room
    public static void checkInPatient(Scanner scanner) {
        healthSystemManagement system = new healthSystemManagement();
        if (system.roomCount < system.MAX_PATIENTS) {
            System.out.println("Enter Room Number:");
            int roomNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println("Enter Room Type:");
            String roomType = scanner.nextLine();
            System.out.println("Is the room available? (true/false):");
            boolean availability = scanner.nextBoolean();

            system.roomNumbers[system.roomCount] = roomNumber;
            system.roomTypes[system.roomCount] = roomType;
            system.roomAvailability[system.roomCount] = availability;
            system.roomCount++;

            System.out.println("Patient checked into room successfully!");
        } else {
            System.out.println("Maximum room limit reached. Cannot check in more patients.");
        }
    }
    // Method to generate a bill for a patient
    public static void generateBill(Scanner scanner) {
        healthSystemManagement system = new healthSystemManagement();
        System.out.println("Enter Patient ID to generate bill:");
        int patientId = scanner.nextInt();

        boolean patientFound = false;
        for (int i = 0; i < system.patientCount; i++) {
            if (system.patientIds[i] == patientId) {
                patientFound = true;
                System.out.println("Generating bill for " + system.patientNames[i]);
                // Here you can add logic to calculate the bill based on treatment, room charges, etc.
                System.out.println("Bill generated successfully!");
                break;
            }
        }
        if (!patientFound) {
            System.out.println("Patient not found.");
        }
    }
    // Method to discharge a patient
    public static void dischargePatient(Scanner scanner) {
        healthSystemManagement system = new healthSystemManagement();
        System.out.println("Enter Patient ID to discharge:");
        int patientId = scanner.nextInt();

        boolean patientFound = false;
        for (int i = 0; i < system.patientCount; i++) {
            if (system.patientIds[i] == patientId) {
                patientFound = true;
                // Logic to remove patient from records
                System.out.println("Discharging patient: " + system.patientNames[i]);
                // Here you can add logic to free up the room and doctor if assigned
                System.out.println("Patient discharged successfully!");
                break;
            }
        }
        if (!patientFound) {
            System.out.println("Patient not found.");
        }
    }
    // Method to view all patients
    public static void viewAllPatients() {
        healthSystemManagement system = new healthSystemManagement();
        if (system.patientCount == 0) {
            System.out.println("No patients registered.");
            return;
        }
        System.out.println("List of all patients:");
        for (int i = 0; i < system.patientCount; i++) {
            System.out.println("ID: " + system.patientIds[i] + ", Name: " + system.patientNames[i] +
                               ", Age: " + system.patientAge[i] + ", Disease: " + system.patientDiseases[i]);
        }
    }
    // Method to add a new doctor
    public static void addDoctor(Scanner scanner) {
        healthSystemManagement system = new healthSystemManagement();
        if (system.doctorCount < system.MAX_PATIENTS) {
            System.out.println("Enter Doctor ID:");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println("Enter Doctor Name:");
            String name = scanner.nextLine();
            System.out.println("Enter Doctor Specialization (1-Heart, 2-Lung, 3-Cancer, 4-General):");
            int specialization = scanner.nextInt();

            system.doctorIds[system.doctorCount] = id;
            system.doctorNames[system.doctorCount] = name;
            system.doctorSpecializations[system.doctorCount] = specialization;
            system.doctorCount++;

            System.out.println("Doctor added successfully!");
        } else {
            System.out.println("Maximum doctor limit reached. Cannot add more doctors.");
        }
    }


}




