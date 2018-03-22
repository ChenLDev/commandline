package com.github.jankroken.commandline.domain;

public class ArgumentToken implements Token {
    private final String value;

    public ArgumentToken(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getArgumentValue() {
        return value;
    }

    @Override
    public String toString() {
        return "<ArgumentToken:" + value + ">";
    }
}
