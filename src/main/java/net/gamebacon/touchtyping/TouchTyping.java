package net.gamebacon.touchtyping;

import net.gamebacon.touchtyping.util.Sound;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TouchTyping extends JFrame implements KeyListener, FocusListener {

    String text = "Flatulence is defined in the medical literature as \"flatus expelled through the anus\" or the \"quality or state of being flatulent\",[1] which is defined in turn as \"marked by or affected with gases generated in the intestine or stomach; likely to cause digestive flatulence\".[2] The root of these words is from the Latin flatus - \"a blowing, a breaking wind\".[3] Flatus is also the medical word for gas generated in the stomach or bowels.[4] Despite these standard definitions, a proportion of intestinal gas may be swallowed environmental air, and hence flatus is not totally generated in the stomach or bowels. The scientific study of this area of medicine is termed flatology.[5]";

    private final Color correctColor = new Color(37, 167, 37);
    private final Color wrongColor = new Color(189, 26, 26);
    private final Color highlightColor = Color.BLUE;//new Color(58, 16, 206);
    private final Color defaultColor = Color.GRAY;


    private final DefaultStyledDocument doc = new DefaultStyledDocument();
    private final JTextPane textPane = new JTextPane(doc);
    private final StyleContext context = new StyleContext();

    private final Font font = new Font("VCR OSD Mono", Font.BOLD, 15);

    private final Dimension frameSize = new Dimension(500,500);
    private final Dimension textAreaSize = new Dimension(450, 200);


    private final JLabel scoreText = new JLabel( "100%");
    private final JCheckBox useSoundCheckBox = new JCheckBox("Sounds", true);
    private final JCheckBox forgiveErrorsBox = new JCheckBox("Forgive errors", false);

    private final JButton focusButton = new JButton("Continue");

    private boolean init = false;

    private int currentIndex = 0;

    private int score = 0;

    public TouchTyping() {
        init();
    }

    private void init() {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        attributeSet.addAttribute(StyleConstants.ColorConstants.Foreground, defaultColor);
        doc.setCharacterAttributes(0, text.length(), attributeSet, true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, 1));
        mainPanel.setFocusable(true);
        mainPanel.addKeyListener(this);


        focusButton.addActionListener(e -> {
            this.requestFocus();
        });


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, 2));
        topPanel.add(scoreText);
        topPanel.add(focusButton);
        topPanel.add(useSoundCheckBox);
        topPanel.add(forgiveErrorsBox);
        mainPanel.add(topPanel);



        textPane.setFont(font);
        textPane.setEditable(false);
        textPane.setText(text);
        textPane.setSize(textAreaSize);
        textPane.setForeground(Color.GRAY);
        textPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, 2));
        textPanel.add(textPane);
        mainPanel.add(textPanel);




        setVisible(true);
        getContentPane().add(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(frameSize);
        setLocationRelativeTo(null);
        addKeyListener(this);
        addFocusListener(this);
        setFocusable(true);
        requestFocus();
        visualiseCursor();
    }


    @Override
    public void keyTyped(KeyEvent e) {
        final char c = e.getKeyChar();
        final char org = text.charAt(currentIndex);

        boolean correct = org == c;


        Style style = context.addStyle("Gello", null);
        StyleConstants.setForeground(style, correct ? correctColor : wrongColor);
        try {
            doc.replace(currentIndex, 1, String.valueOf(org), style);
        } catch (BadLocationException badLocationException) {
            badLocationException.printStackTrace();
        }

        if(correct)
            score++;

		if(useSoundCheckBox.isSelected()) {
			if(!correct && !forgiveErrorsBox.isSelected())
	            Sound.playSound("error", 1);
        	Sound.playSound("key_typed", 3);
		}

        if (correct || !forgiveErrorsBox.isSelected())
            currentIndex++;

        updateScore();
        visualiseCursor();
    }

    private void updateScore() {
        float percent =  ((float) score/currentIndex) * 100;
        scoreText.setText(String.format("Success rate: %.1f%s", percent, "%"));
    }


    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void visualiseCursor() {
        Style style = context.addStyle("Gello", null);
        StyleConstants.setForeground(style, highlightColor);
        StyleConstants.setUnderline(style, true);
        StyleConstants.setBold(style, true);
        doc.setCharacterAttributes(currentIndex, 1, style, true);
    }



    @Override
    public void focusGained(FocusEvent e) {
        System.out.println("gainedsadfasd");
        this.setForeground(null);
    }

    @Override
    public void focusLost(FocusEvent e) {
        System.out.println("lost");
        this.setForeground(Color.GRAY);
        focusButton.setVisible(true);
    }
}
