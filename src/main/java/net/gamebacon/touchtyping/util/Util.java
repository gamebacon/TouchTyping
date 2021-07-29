package net.gamebacon.touchtyping.util;

import net.gamebacon.touchtyping.keyboard.Key;
import net.gamebacon.touchtyping.keyboard.KeyboardType;

import java.awt.*;
import java.util.HashMap;


public class Util {

    public static final Color L1 = new Color(246, 107, 107);
    public static final Color L3 = new Color(108, 115, 194);
    public static final Color L4 = new Color(59, 160, 160);
    public static final Color L2 = new Color(169, 97, 215);

    public static final Color R1 = new Color(191, 84, 34);
    public static final Color R2 = new Color(210, 193, 90);
    public static final Color R3 = new Color(127, 172, 105);
    public static final Color R4 = new Color(199, 88, 88);

    public static final Color RL5 = new Color(145, 4, 175);


    public final static HashMap<Integer, Key> keys = new HashMap<>();
    public final static HashMap<KeyboardType, Key[][]> keyboards = new HashMap<>();

    static {

        for(Key key : Key.values())
            keys.put(key.getKeycode(), key);

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
}
