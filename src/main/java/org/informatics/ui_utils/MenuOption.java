package org.informatics.ui;

import lombok.Getter;

@Getter
public class MenuOption {

    private final int code;
    private final String label;

    public MenuOption(int code, String label) {
        this.code = code;
        this.label = label;
    }

}
