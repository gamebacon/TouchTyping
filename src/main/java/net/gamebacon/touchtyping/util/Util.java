package net.gamebacon.touchtyping.util;

import net.gamebacon.touchtyping.keyboard.Key;
import net.gamebacon.touchtyping.keyboard.KeyboardType;

import java.util.HashMap;


public class Util {
    public final static HashMap<KeyboardType, Key[][]> keyboards = new HashMap<KeyboardType, Key[][]>();

    static {
        keyboards.put(KeyboardType.AMERICAN, new Key[][]{
                {Key.THING, Key.ONE, Key.TWO, Key.THREE, Key.FOUR, Key.FIVE, Key.SIX, Key.SEVEN, Key.EIGHT, Key.NINE, Key.ZERO, Key.DASH, Key.EQUALS, Key.BACKSPACE},
                {Key.TAB, Key.Q, Key.W, Key.E, Key.R, Key.T, Key.Y, Key.U, Key.I, Key.O, Key.P, Key.LEFT_BRACKET, Key.RIGHT_BRACKET, Key.BACKWARDS_DASH},
                {Key.CAPS_LOCK, Key.A, Key.S, Key.D, Key.F, Key.G, Key.H, Key.J, Key.K, Key.L, Key.SEMI_COLLON, Key.THING2, Key.ENTER},
                {Key.LEFT_SHIFT, Key.Z, Key.X, Key.C, Key.V, Key.B, Key.N, Key.M, Key.COMMA, Key.DOT, Key.FORWARD_DASH, Key.RIGHT_SHIFT, Key.UP},
                {Key.SPACE, Key.LEFT, Key.DOWN, Key.RIGHT}
        });

        keyboards.put(KeyboardType.SWEDISH, new Key[][]{
                {Key.THING, Key.ONE, Key.TWO, Key.THREE, Key.FOUR, Key.FIVE, Key.SIX, Key.SEVEN, Key.EIGHT, Key.NINE, Key.ZERO, Key.DASH, Key.EQUALS, Key.BACKSPACE},
                {Key.TAB, Key.Q, Key.W, Key.E, Key.R, Key.T, Key.Y, Key.U, Key.I, Key.O, Key.P, Key.LEFT_BRACKET, Key.RIGHT_BRACKET, Key.BACKWARDS_DASH},
                {Key.CAPS_LOCK, Key.A, Key.S, Key.D, Key.F, Key.G, Key.H, Key.J, Key.K, Key.L, Key.SEMI_COLLON, Key.THING2, Key.ENTER},
                {Key.LEFT_SHIFT, Key.Z, Key.X, Key.C, Key.V, Key.B, Key.N, Key.M, Key.COMMA, Key.DOT, Key.FORWARD_DASH, Key.RIGHT_SHIFT, Key.UP},
                {Key.SPACE, Key.LEFT, Key.DOWN, Key.RIGHT}
        });
    }

    public static Key getKey(int keycode) {
        for(Key key :Key.values())
            if(key.getKeycode() == keycode)
                return key;
            return null;
    }

}
