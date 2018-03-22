package com.github.jankroken.commandline.domain;

public class Switch {
    private final String longSwitch;
    private final String shortSwitch;

    public Switch(String longSwitch, String shortSwitch) {
        this.longSwitch = longSwitch;
        this.shortSwitch = shortSwitch;
    }

    public String getLongSwitch() {
        return longSwitch;
    }

    public String getShortSwitch() {
        return shortSwitch;
    }

    public boolean matches(String _switch) {
        return (_switch.equals(longSwitch) || _switch.equals(shortSwitch));
    }
}
