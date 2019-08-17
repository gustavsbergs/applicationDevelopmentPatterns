package main.java.com.accenture.application.development.patterns;

import main.java.com.accenture.application.development.patterns.controller.EmployeeManagementController;

public class Application {

    public static void main(String[] args) {
        final EmployeeManagementController controller = new EmployeeManagementController();
        controller.loadUserInterface();
    }
}
