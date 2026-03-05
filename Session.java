import java.security.PublicKey;

//attributes of the session class
public class Session {
    private static int NextSessionID = 1;
    private int SessionID; //auto generated
    private String SessionName;
    private String FitnessLevel; //beginner, intermediate, advanced
    private String DayOfWeek; //format MON, TUE
    private String StartTime; //in hh:mm format
    private int Duration; //in minutes
    private int AvailableSpaces;

//default constructor, creates a default session with empty values
    public Session(){
        this.SessionID = NextSessionID++;
        this.SessionName = " ";
        this.FitnessLevel = " ";
        this.DayOfWeek = " ";
        this.StartTime = " ";
        this.Duration = 0;
        this.AvailableSpaces = 0;
    }

//constructor with given values
    public Session (String SessionName,
                   String FitnessLevel,
                   String DayOfWeek,
                   String StartTime,
                   int Duration,
                   int AvailableSpaces){
        this.SessionID = NextSessionID++;
        this.SessionName = SessionName;
        this.FitnessLevel = FitnessLevel;
        this.DayOfWeek = DayOfWeek;
        this.StartTime = StartTime;
        this.Duration = Duration;
        this.AvailableSpaces = AvailableSpaces;
    }

    //getters and setters - access to class variables
    public int getSessionID() {
        return SessionID;
    }
    public String getSessionName() {
        return SessionName;
    }
    public void setSessionName(String SessionName) {
        this.SessionName = SessionName;
    }
    public String getFitnessLevel() {
        return FitnessLevel;
    }
    public void setFitnessLevel(String FitnessLevel) {
        this.FitnessLevel = FitnessLevel;
    }
    public String getDayOfWeek() {
        return DayOfWeek;
    }
    public void setDayOfWeek(String DayOfWeek) {
        this.DayOfWeek = DayOfWeek;
    }
    public String getStartTime() {
        return StartTime;
    }
    public void setStartTime(String StartTime) {
        this.StartTime = StartTime;
    }
    public int getDuration() {
        return Duration;
    }
    public void setDuration(int Duration) {
        this.Duration = Duration;
    }
    public int getAvailableSpaces() {
        return AvailableSpaces;
    }
    public void setAvailableSpaces(int AvailableSpaces) {
        this.AvailableSpaces = AvailableSpaces;
    }

//a customer registers updating value of session, if there are available spaces
    public boolean Registration() {
        if (AvailableSpaces > 0) {
            AvailableSpaces--;
            return true;
        }
        return false;
    }
        public void Cancellation() {
            AvailableSpaces++;
        }

        public void PrintTable () {
        System.out.printf("| %-10d | %-12s | %-15s | %-9s | %-10s | %-15d | %-16d |%n",
                SessionID, SessionName, FitnessLevel, DayOfWeek, StartTime, Duration, AvailableSpaces);
        }

    public boolean getSessionId() {
        return false;
    }
}

