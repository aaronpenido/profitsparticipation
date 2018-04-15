package models;

import exceptions.InvalidJobTitleException;

public enum JobTitle {

    ANALYST, MANAGER, TRAINEE;

    public static JobTitle jobTitleFromString(String jobTitleString) throws InvalidJobTitleException {

        try {
            return JobTitle.valueOf(jobTitleString.toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new InvalidJobTitleException();
        }
    }
}
