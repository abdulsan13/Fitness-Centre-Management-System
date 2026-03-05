package com.fitall;

public class Session {
    private static int nextSessionId = 1;
    private int sessionId;
    private String sessionName;
    private String fitnessLevel;
    private String dayOfWeek;
    private String startTime;
    private int duration;
    private int availableSpaces;

    public Session() {
    }

    public Session(String sessionName, String fitnessLevel, String dayOfWeek, 
                  String startTime, int duration, int availableSpaces) {
        this.sessionId = nextSessionId++;
        this.sessionName = sessionName;
        this.fitnessLevel = fitnessLevel;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.duration = duration;
        this.availableSpaces = availableSpaces;
    }

    public int getSessionId() {
        return sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getFitnessLevel() {
        return fitnessLevel;
    }

    public void setFitnessLevel(String fitnessLevel) {
        this.fitnessLevel = fitnessLevel;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAvailableSpaces() {
        return availableSpaces;
    }

    public void setAvailableSpaces(int availableSpaces) {
        this.availableSpaces = availableSpaces;
    }

    public boolean registration() {
        if (availableSpaces > 0) {
            availableSpaces--;
            return true;
        }
        return false;
    }

    public void cancellation() {
        availableSpaces++;
    }
}
