package org.dclm.sealApp.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Race {
    BLACK("Black"),
    ASIAN("Asian"),
    INDIAN("Indian"),
    COLOURED("Coloured"),
    WHITE("White");

    @Getter
    private final String displayValue;

    @Override
    public String toString() {
        return getDisplayValue();
    }
}
