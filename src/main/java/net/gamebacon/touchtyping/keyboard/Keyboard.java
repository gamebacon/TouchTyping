package net.gamebacon.touchtyping.keyboard;

import net.gamebacon.touchtyping.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class Keyboard extends JPanel {

    private final HashMap<Key, JButton> buttons = new HashMap<>();

    public Keyboard() {
        setLayout(new GridBagLayout());

        Insets zeroInset = new Insets(0, 0, 0, 0);
        final Font monospace = new Font(Font.MONOSPACED, Font.BOLD, 15);

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
                Color color = null;
                switch (col) {
                    case 0: color = Util.L1; break;
                    case 1: color = Util.L1; break;
                    case 2: color = Util.L2; break;
                    case 3: color = Util.L3; break;
                    case 4: color = Util.L4; break;
                    case 5: color = Util.L4; break;
                    case 6: color = Util.R4; break;
                    case 7: color = Util.R4; break;
                    case 8: color = Util.R3; break;
                    case 9: color = Util.R2; break;
                    case 10: color = Util.R1; break;
                    default: color = Util.R1; break;
                }
                if(key == Key.SPACE)
                    color = Util.RL5;

                // specify padding and insets for the buttons
                switch (key) {
                    case BACKSPACE:   cButton.ipadx = 0; break;
                    case TAB:         cButton.ipadx = 17 + 10; break;
                    case CAPS_LOCK:        cButton.ipadx = 10 + 20; break;
                    case ENTER:       cButton.ipadx = 27; break;
                    case LEFT_SHIFT:       cButton.ipadx = 27 + 10; break;
                    case FORWARD_DASH:
                        //cButton.insets = new Insets(0, 0, 0, 24);
                        break;
                    case SPACE:
                        cButton.ipadx = 350;
                        cButton.insets = new Insets(0, 192 + 180, 0, 72 + 90);
                        break;
                    default:
                        cButton.ipadx = 7;
                        cButton.insets = zeroInset;
                }

                button = new JButton(key.toString());
                button.setForeground(color);
                button.setBackground(color);
                button.setOpaque(false);

                button.setFont(monospace);
                button.setFocusable(false);
                buttons.put(key, button);
                pRow.add(button, cButton);
            }

            add(pRow, cRow);
        }
    }

    public HashMap<Key, JButton> getButtons() {
        return buttons;
    }

    public void click(Key key) {

        if(buttons.containsKey(key)) {
            buttons.get(key).doClick();
        }
    }

}
