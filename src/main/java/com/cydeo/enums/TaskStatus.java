package com.cydeo.enums;

import lombok.Getter;

@Getter
public enum TaskStatus {
    OPEN("Open"),
    IN_PROGRESS("In Progress"),
    UAT_TESTING("UAT Testing"),
    COMPLETED("Completed");

    private String value;

    TaskStatus(String value) {
        this.value = value;
    }
}
