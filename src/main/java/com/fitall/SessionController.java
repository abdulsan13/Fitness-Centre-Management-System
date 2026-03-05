package com.fitall;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SessionController {

    private List<Session> sessions = new ArrayList<>();
    private List<Integer> registeredSessions = new ArrayList<>();

    public SessionController() {
        initializeHardcodedSessions();
    }

    private void initializeHardcodedSessions() {
        sessions.add(new Session("Pilates", "Beginner", "MON", "13:35", 55, 15));
        sessions.add(new Session("Yoga", "Beginner", "FRI", "18:10", 55, 15));
        sessions.add(new Session("Core", "Intermediate", "TUE", "19:40", 55, 20));
        sessions.add(new Session("Pump", "Intermediate", "TUE", "10:25", 55, 15));
        sessions.add(new Session("Yoga", "Intermediate", "WED", "12:15", 55, 15));
        sessions.add(new Session("Core", "Advanced", "THU", "18:45", 45, 20));
        sessions.add(new Session("Cycling", "Advanced", "WED", "9:30", 45, 10));
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/timetable";
    }

    private List<Session> getRegisteredSessionObjects() {
        List<Session> registered = new ArrayList<>();
        for (Integer sessionId : registeredSessions) {
            for (Session session : sessions) {
                if (session.getSessionId() == sessionId) {
                    registered.add(session);
                    break;
                }
            }
        }
        return registered;
    }

    @GetMapping("/timetable")
    public String timetable(Model model) {
        model.addAttribute("sessions", sessions);
        model.addAttribute("registeredSessions", getRegisteredSessionObjects());
        return "timetable";
    }

    @PostMapping("/register")
    public String register(@RequestParam int sessionId, Model model) {
        for (Session session : sessions) {
            if (session.getSessionId() == sessionId) {
                boolean success = session.registration();
                if (success) {
                    registeredSessions.add(sessionId);
                    model.addAttribute("message", "Registration successful!");
                } else {
                    model.addAttribute("error", "No available spaces in this session.");
                }
                break;
            }
        }
        model.addAttribute("sessions", sessions);
        model.addAttribute("registeredSessions", getRegisteredSessionObjects());
        return "timetable";
    }

    @PostMapping("/cancel")
    public String cancel(@RequestParam int sessionId, Model model) {
        if (registeredSessions.contains(sessionId)) {
            for (Session session : sessions) {
                if (session.getSessionId() == sessionId) {
                    session.cancellation();
                    registeredSessions.remove((Integer) sessionId);
                    model.addAttribute("message", "Cancellation successful!");
                    break;
                }
            }
        } else {
            model.addAttribute("error", "You are not registered for this session.");
        }
        model.addAttribute("sessions", sessions);
        model.addAttribute("registeredSessions", getRegisteredSessionObjects());
        return "timetable";
    }

    @GetMapping("/add")
    public String addSessionForm(Model model) {
        model.addAttribute("fitSession", new Session());
        return "add-session";
    }

    @PostMapping("/add")
    public String addSession(@ModelAttribute Session fitSession, Model model) {
        sessions.add(fitSession);
        model.addAttribute("message", "Session added successfully! Session ID: " + fitSession.getSessionId());
        model.addAttribute("sessions", sessions);
        model.addAttribute("registeredSessions", getRegisteredSessionObjects());
        return "timetable";
    }
}
