package ca.mcgill.ecse223.block.controller;

import java.io.IOException;

public class InvalidInputException extends IOException {
    // I do not get the need for this
    private static final long serialVersionUID = -5633915762703837868L;

    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}