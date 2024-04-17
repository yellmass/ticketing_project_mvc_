package com.cydeo.enums;

import lombok.Getter;

@Getter
public enum ProjectStatus {
    OPEN("Open"), IN_PROGRESS("In Progress"), COMPLETED("Completed");

    private String value;

    ProjectStatus(String value) {
        this.value = value;
    }
}
