package com.github.jankroken.commandline.domain;

import com.github.jankroken.commandline.util.PeekIterator;

public class SimpleTokenizer implements Tokenizer {

    private final PeekIterator<String> stringIterator;
    private boolean argumentEscapeEncountered;
    private String argumentTerminator;

    public SimpleTokenizer(PeekIterator<String> stringIterator) {
        this.stringIterator = stringIterator;
    }

    public void setArgumentTerminator(String argumentTerminator) {
        this.argumentTerminator = argumentTerminator;
    }

    public boolean hasNext() {
        return stringIterator.hasNext();
    }

    public Token peek() {
        var value = stringIterator.peek();
        return makeToken(value);
    }

    public Token next() {
        var value = stringIterator.next();
        return makeToken(value);
    }

    private Token makeToken(String value) {
        if (value.equals(argumentTerminator)) {
            argumentTerminator = null;
            return new ArgumentToken(value);
        }
        if (argumentTerminator != null) {
            return new ArgumentToken(value);
        }
        if (isArgumentEscape(value)) {
            argumentEscapeEncountered = true;
            return next();
        }
        if (!argumentEscapeEncountered && isSwitch(value)) {
            return new SwitchToken(value.substring(1), value);
        } else {
            return new ArgumentToken(value);
        }
    }

    public void remove() {
        stringIterator.remove();
    }


    private static boolean isSwitch(String argument) {
        return argument.matches("-.*");
    }

    private boolean isArgumentEscape(String value) {
        return ("--".equals(value) && !argumentEscapeEncountered);
    }

}