package org.dclm.sealApp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MaritalStatus {

    MARRIED("Married"),
    SINGLE("Single"),
    DIVORCED("Divorced"),
    SEPARATED("Separated"),
    WIDOWED("Widowed");

    @Getter
    private final String displayValue;
}