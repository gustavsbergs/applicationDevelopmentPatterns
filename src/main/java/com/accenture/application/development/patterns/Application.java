package main.java.com.accenture.application.development.patterns;

import main.java.com.accenture.application.development.patterns.controller.EmployeeManagementController;

/**
 * Behaviour of the application can be controlled in EmployeeManagementController class by setting the fullyRandomMode boolean value.
 * */
public class Application {

    public static void main(String[] args) {
        final EmployeeManagementController controller = new EmployeeManagementController();
        controller.loadUserInterface();
    }
}
