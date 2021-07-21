package net.gamebacon.touchtyping.util;

import java.util.HashMap;

public class Data {

    private final HashMap<Character, Integer> wrongTyped = new HashMap();

    public Data() {

    }

    public HashMap<Character, Integer> getWrongTyped() {
        return wrongTyped;
    }
}
