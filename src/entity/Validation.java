/*
 **********************************************************************************************
 * Course: CMPP 264 Java Programming for OOSD
 * Purpose: Day 6 Assignment
 * Date: May 16, 2019.
 * Author: Tim Leslie
 * Description: This Validation class contains a static method which establishes a connection
 * to the TravelExperts database.
 **********************************************************************************************/
package entity;

import java.time.LocalDate;

public class Validation {

    private String lineEnd;

    public Validation() {
        this.lineEnd = "\n";
    }

    public Validation(String lineEnd) {
        this.lineEnd = lineEnd;
    }


    public String isInteger(String value, String name) {
        String msg = "";
        try {
            Integer.parseInt(value);
        }
        catch (NumberFormatException e)
        {
            msg = name + " must be an integer." + lineEnd;
        }
        return msg;


    }
    public String isProvided(String value, String name) {
        String msg = "";
        if (value.isEmpty()) {
            msg = name + " is required." + lineEnd;
        }
        return msg;
    }

    public String isDateGreater(LocalDate date1, LocalDate date2, String name) {
        String msg = "";
        if (date2.compareTo(date1) < 0) {
            msg = name + " must be later than Start Date." + lineEnd;
        }
        return msg;
    }
    
}

