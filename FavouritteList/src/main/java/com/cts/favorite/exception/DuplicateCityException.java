package com.cts.favorite.exception;

// DuplicateCityException.java
public class DuplicateCityException extends RuntimeException {
    public DuplicateCityException(String cityName) {
        super("City '" + cityName + "' is already in the favorite list.");
    }
}
