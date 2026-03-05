import java.util.ArrayList; //creates dynamic lists
import java.util.Scanner; //reads from keyboard
import java.util.InputMismatchException; //handles cases where user input match data type

/**
 * Main class for the FitAll Fitness Center Management System.
 * Handles user interactions and manages group sessions.
 */
public class FitAll {
    private static ArrayList<Session> sessions = new ArrayList<>();
    private static ArrayList<Integer> registeredSessions = new ArrayList<>();

    public static void main(String[] args) {
        initializeHardcodedSessions();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

//prints a menu which get user choice until they choose exit
        while (running) {
            printMenu();
            int ClassChoice = getIntInput(scanner, "Choose an option: ");

            switch (ClassChoice) {
                case 1:
                    printTimetable();
                    break;
                case 2:
                    registerCustomer(scanner);
                    break;
                case 3:
                    cancelRegistration(scanner);
                    break;
                case 4:
                    addSession(scanner);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
        scanner.close();
        System.out.println("Goodbye from FitAll!");
    }
// creates predefined fitness sessions that are printed
    private static void initializeHardcodedSessions() {
        sessions.add(new Session("Pilates", "Beginner", "MON", "13:35", 55, 15));
        sessions.add(new Session("Yoga", "Beginner", "FRI", "18:10", 55, 15));
        sessions.add(new Session("Core", "Intermediate", "TUE", "19:40", 55, 20));
        sessions.add(new Session("Pump", "Intermediate", "TUE", "10:25", 55, 15));
        sessions.add(new Session("Yoga", "Intermediate", "WED", "12:15", 55, 15));
        sessions.add(new Session("Core", "Advanced", "THU", "18:45", 45, 20));
        sessions.add(new Session("Cycling", "Advances", "WED", "9:30", 45, 10));
    }
//options that user interactions
    private static void printMenu() {
        System.out.println("\nWelcome to FitAll club!!!!!!!!!!!!!!!!!!!");
        System.out.println("1. Class options");
        System.out.println("2. Class registration");
        System.out.println("3. Class cancellation :(");
        System.out.println("4. Register new session");
        System.out.println("5. Exit from FitAll");
    }

//table format
    private static void printTimetable() {
        System.out.println("\nGroup classes timetable");
        System.out.println("| SessionID | SessionName | Level of fitness | Day    | Start Time | Duration (mins) | Available places |");
        System.out.println("|-----------|-------------|------------------|--------|------------|-----------------|------------------|");
        for (Session session : sessions) {
            session.PrintTable();
        }
        System.out.println();
    }

//table of already registered sessions
    private static void printRegisteredRecords() {
        System.out.println("\nRegistered records: " + registeredSessions.size());
        System.out.println("| SessionID | SessionName | Level of fitness | Day    | Start Time | Duration (mins) |");
        System.out.println("|-----------|-------------|------------------|--------|------------|-----------------|");
        for (Integer sessionId : registeredSessions) {
            for (Session session : sessions) {
                if (session.getSessionID() == sessionId) {
                    System.out.printf("| %-10d | %-12s | %-15s | %-9s | %-10s | %-15d |%n",
                            session.getSessionID(),
                            session.getSessionName(),
                            session.getFitnessLevel(),
                            session.getDayOfWeek(),
                            session.getStartTime(),
                            session.getDuration());
                    break;
                }
            }
        }
        System.out.println();
    }

//customer registration, asks for ID, register and updates records
    private static void registerCustomer(Scanner scanner) {
        int sessionId = getIntInput(scanner, "Enter Session ID to register: ");

        for (Session session : sessions) {
            if (session.getSessionID() == sessionId) {
                boolean success = session.Registration();
                if (success) {
                    registeredSessions.add(sessionId);
                    System.out.println("Registration successful.");
                    printTimetable();
                    printRegisteredRecords();
                } else {
                    System.out.println("No available spaces in this session.");
                }
                return;
            }
        }
        System.out.println("Session ID " + sessionId + " not found.");
    }

//customer cancellation, updates and displays records
    private static void cancelRegistration(Scanner scanner) {
        int sessionId = getIntInput(scanner, "Enter Session ID to cancel: ");

        if (registeredSessions.contains(sessionId)) {
            for (Session session : sessions) {
                if (session.getSessionID() == sessionId) {
                    session.Cancellation();
                    registeredSessions.remove((Integer) sessionId);
                    System.out.println("Cancellation successful.");
                    printTimetable();
                    printRegisteredRecords();
                    return;
                }
            }
            System.out.println("Session ID " + sessionId + " not found.");
        } else {
            System.out.println("You are not registered for session " + sessionId + ".");
        }
    }

//adds info for a new session, updating list available sessions
    private static void addSession(Scanner scanner) {
        System.out.println("\nAdd new session:");
        String name = getStringInput(scanner, "Enter session name: ");
        String level = getFitnessLevelInput(scanner);
        String day = getStringInput(scanner, "Enter day of the week: ");
        String startTime = getStartTimeInput(scanner);
        int duration = getPositiveIntInput(scanner, "Enter duration in minutes: ");
        int spaces = getPositiveIntInput(scanner, "Enter available spaces: ");

        Session newSession = new Session(name, level, day, startTime, duration, spaces);
        sessions.add(newSession);
        System.out.println("Session added successfully. Session ID: " + newSession.getSessionId());
    }

//gets fitness level of user
    private static String getFitnessLevelInput(Scanner scanner) {
        String level;
        do {
            level = getStringInput(scanner, "Enter fitness level (Beginner/Intermediate/Advanced): ").trim();
            if (!level.equalsIgnoreCase("Beginner") && !level.equalsIgnoreCase("Intermediate") && !level.equalsIgnoreCase("Advanced")) {
                System.out.println("Invalid fitness level. Please enter Beginner, Intermediate, or Advanced.");
            }
        } while (!level.equalsIgnoreCase("Beginner") && !level.equalsIgnoreCase("Intermediate") && !level.equalsIgnoreCase("Advanced"));
        return level.substring(0,1).toUpperCase() + level.substring(1).toLowerCase();
    }

//gets start time of user session
    private static String getStartTimeInput(Scanner scanner) {
        String startTime;
        do {
            startTime = getStringInput(scanner, "Enter start time (HH:mm): ");
            if (!startTime.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
                System.out.println("Invalid time format. Please use HH:mm.");
            }
        } while (!startTime.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]"));
        return startTime;
    }

//checks user int input is greater than 0
    private static int getPositiveIntInput(Scanner scanner, String prompt) {
        int input;
        do {
            input = getIntInput(scanner, prompt);
            if (input < 0) {
                System.out.println("Please enter a non-negative number.");
            }
        } while (input < 0);
        return input;
    }

//prompts user for int input and ensuring it is valid
    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // consume newline
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }

//prompts user for str input
    private static String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}