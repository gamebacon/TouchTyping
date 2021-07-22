package net.gamebacon.touchtyping;

import net.gamebacon.touchtyping.keyboard.Keyboard;

import javax.swing.*;

public class Driver {
    public static void main(String[] args) {
        TouchTyping touchTyping = new TouchTyping();
        if(true)
            return;
        JFrame test = new JFrame();
        test.add(new Keyboard());
        test.pack();
        test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        test.setVisible(true);

    }
}
