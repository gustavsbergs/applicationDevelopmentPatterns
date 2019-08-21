package main.java.com.accenture.application.development.patterns.handlers;

import main.java.com.accenture.application.development.patterns.constants.RequestTypes;

/**
 * Behavioral pattern - Chain of responsibility pattern. Each implementation of this interface verifies if it can handle
 * the specific request. If not, delegates it to the next handler in the chain.
 * */
public interface RequestHandler {

    void handleRequest(RequestTypes types) throws InterruptedException;
}
