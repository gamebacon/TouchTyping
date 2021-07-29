package net.gamebacon.touchtyping.keyboard;

import java.util.Locale;

public enum Key {

    LEFT_PARENTHESES(519, "("),
    RIGHT_PARENTHESES(522, ")"),

    LEFT_BRACKET(91, "["),
    RIGHT_BRACKET(93, "]"),
    DASH(45, "-"),
    EQUALS(61, "="),
    BACKSPACE(8, "Backspace"),

    SEMI_COLLON(59, "Backspace"),

    FORWARD_DASH(47, "/"),
    BACKWARDS_DASH(92, "\\"),
    COMMA(44, ","),
    DOT(46, "."),
    ENTER(10, "Enter"),

    THING(192, "`"),
    THING2(222, "'"),

    SPACE(32, " "),
    SPACE_REPLACEMENT(0, "␣"),

    RIGHT_SHIFT(-1, "Shift"),
    LEFT_SHIFT(-1, "Shift"),

    TAB(-1, "TAB"),
    CAPS_LOCK(-1, "Caps"),

    UP(-1, "\u2191"),
    DOWN(-1, "\u2193"),
    LEFT(-1, "\u2190"),
    RIGHT(-1, "\u2192"),

    ONE(49, "1"),
    TWO(50, "2"),
    THREE(51, "3"),
    FOUR(52, "4"),
    FIVE(53, "5"),
    SIX(54, "6"),
    SEVEN(55, "7"),
    EIGHT(56, "8"),
    NINE(57, "9"),
    ZERO(58, "0"),
    A(65, "A"),
    B(66, "B"),
    C(67, "C"),
    D(68, "D"),
    E(69, "E"),
    F(70, "F"),
    G(71, "G"),
    H(72, "H"),
    I(73, "I"),
    J(74, "J"),
    K(75, "K"),
    L(76, "L"),
    M(77, "M"),
    N(78, "N"),
    O(79, "O"),
    P(80, "P"),
    Q(81, "Q"),
    R(82, "R"),
    S(83, "S"),
    T(84, "T"),
    U(85, "U"),
    V(86, "V"),
    W(87, "W"),
    X(88, "X"),
    Y(89, "Y"),
    Z(90, "Z"),
    Ä(16777412, "X"),
    Å(16777413, "Y"),
    Ö(16777430, "Z");





    private Key(int keycode, String display) {
        this.keycode = keycode;
        this.display = display;
    }


    private final int keycode;
    private final String display;

    public int getKeycode() {
        return keycode;
    }

    @Override
    public String toString() {
        return display;
    }
}
