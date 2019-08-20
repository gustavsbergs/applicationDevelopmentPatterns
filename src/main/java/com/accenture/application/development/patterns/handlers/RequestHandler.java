package main.java.com.accenture.application.development.patterns.handlers;

import main.java.com.accenture.application.development.patterns.constants.RequestTypes;

public interface RequestHandler {

    void handleRequest(RequestTypes types) throws InterruptedException;
}
