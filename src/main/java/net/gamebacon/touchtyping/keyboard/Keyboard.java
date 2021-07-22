package net.gamebacon.touchtyping.keyboard;

import net.gamebacon.touchtyping.util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Keyboard extends JPanel {

    HashMap<Key, JButton> buttons = new HashMap<>();

    public Keyboard() {
        setLayout(new GridBagLayout());

        Insets zeroInset = new Insets(0, 0, 0, 0);
        Font monospace = new Font(Font.MONOSPACED, Font.PLAIN, 12);

        JPanel pRow;
        JButton button;

        GridBagConstraints cRow = new GridBagConstraints(), cButton = new GridBagConstraints();
        cRow.anchor = GridBagConstraints.WEST;
        cButton.ipady = 21;

        Key[][] keys = Util.keyboards.get(KeyboardType.AMERICAN);

        // first dimension of the key array
        // representing a row on the keyboard
        for (int row = 0, i = 0; row < keys.length; ++row) {
            pRow = new JPanel(new GridBagLayout());

            cRow.gridy = row;

            // second dimension representing each key
            for (int col = 0; col < keys[row].length; ++col, ++i) {
                final Key key = keys[row][col];
                // specify padding and insets for the buttons
                switch (key) {
                    case BACKSPACE:   cButton.ipadx = 0; break;
                    case TAB:         cButton.ipadx = 17; break;
                    case CAPS_LOCK:        cButton.ipadx = 10; break;
                    case ENTER:       cButton.ipadx = 27; break;
                    case LEFT_SHIFT:       cButton.ipadx = 27; break;
                    case FORWARD_DASH:
                        //cButton.insets = new Insets(0, 0, 0, 24);
                        break;
                    case SPACE:
                        cButton.ipadx = 247;
                        cButton.insets = new Insets(0, 192 + 50, 0, 72 + 50);
                        break;
                    default:
                        cButton.ipadx = 7;
                        cButton.insets = zeroInset;
                }

                button = new JButton(key.toString());
                button.setFont(monospace);
                button.setFocusable(false);
                buttons.put(key, button);
                pRow.add(button, cButton);
            }

            add(pRow, cRow);
        }
    }

    public void push(Key key) {
        buttons.get(key).doClick(100);
    }


}
