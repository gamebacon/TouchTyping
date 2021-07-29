package net.gamebacon.touchtyping;

import net.gamebacon.touchtyping.io.IOManager;
import net.gamebacon.touchtyping.keyboard.Key;
import net.gamebacon.touchtyping.keyboard.Keyboard;
import net.gamebacon.touchtyping.util.Data;
import net.gamebacon.touchtyping.util.sound.Sound;
import net.gamebacon.touchtyping.util.sound.SoundUtil;
import net.gamebacon.touchtyping.util.Util;

import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class TouchTyping extends JFrame implements KeyListener, FocusListener, ActionListener {

    private final char SPACE_CHAR = '␣';
    private final int WORDS_PER_TEXT_PORTION = 20;
    private final int LINE_WIDTH = 50;

    private final Color CORRECT_COLOR = new Color(37, 167, 37);
    private final Color WRONG_COLOR = new Color(189, 26, 26);
    private final Color HIGHLIGHT_COLOR = new Color(0, 41, 255);
    private final Color DEFAULT_COLOR = Color.GRAY;

    private final DefaultStyledDocument doc = new DefaultStyledDocument();
    private final SimpleAttributeSet attributeSet = new SimpleAttributeSet();
    private final StyleContext context = new StyleContext();
    private final JTextPane textPane = new JTextPane(doc);



    private final Data data = new Data();

    private final Font font = new Font(Font.MONOSPACED, Font.PLAIN, 40);

    private final Dimension FRAME_SIZE = new Dimension(1200,550);
    private final Dimension TEXTAREA_SIZE = new Dimension(450, 200);


    private final JLabel scoreText = new JLabel( "100%");
    private final JButton focusButton = new JButton("Start");
    private final JCheckBox useSoundCheckBox = new JCheckBox("Sounds", true);
    private final JCheckBox mustCompleteBox = new JCheckBox("Must complete", false);
    private final JCheckBox debugCheck = new JCheckBox("debug", false);

    private final Keyboard keyboard = new Keyboard();

    private int fullIndex = 0;
    private int currentIndex = 0;

    private int wordIndex = 0;

    private final String fullText = "Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. It is a general-purpose programming language intended to let application developers write once, run anywhere (WORA),[16] meaning that compiled Java code can run on all platforms that support Java without the need for recompilation.[17] Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of the underlying computer architecture. The syntax of Java is similar to C and C++, but has fewer low-level facilities than either of them. The Java runtime provides dynamic capabilities (such as reflection and runtime code modification) that are typically not available in traditional compiled languages. As of 2019, Java was one of the most popular programming languages in use according to GitHub,[18][19] particularly for client-server web applications, with a reported 9 million developers.[20]\n" +
            "\n" +
            "Java was originally developed by James Gosling at Sun Microsystems (which has since been acquired by Oracle) and released in 1995 as a core component of Sun Microsystems' Java platform. The original and reference implementation Java compilers, virtual machines, and class libraries were originally released by Sun under proprietary licenses. As of May 2007, in compliance with the specifications of the Java Community Process, Sun had relicensed most of its Java technologies under the GPL-2.0-only license. Oracle offers its own HotSpot Java Virtual Machine, however the official reference implementation is the OpenJDK JVM which is free open source software and used by most developers and is the default JVM for almost all Linux distributions.\n" +
            "\n" +
            "As of March 2021, the latest version is Java 16, with Java 11, a currently supported long-term support (LTS) version, released on September 25, 2018. Oracle released the last zero-cost public update for the legacy version Java 8 LTS in January 2019 for commercial use, although it will otherwise still support Java 8 with public updates for personal use indefinitely. Other vendors have begun to offer zero-cost builds of OpenJDK 8 and 11 that are still receiving security and other upgrades.\n" +
            "\n" +
            "Oracle (and others) highly recommend uninstalling outdated versions of Java because of serious risks due to unresolved security issues.[21] Since Java 9, 10, 12, 13, 14, and 15 are no longer supported, Oracle advises its users to immediately transition to the latest version (currently Java 16) or an LTS release.";
    private String currentTextPortion = "";

    private String[] fullTextWords;
    private int score = 0;

    private boolean init = false;

    private final Clip themeSong;
    private Timer timer = new Timer(1000, this);
    private final JLabel WPMText = new JLabel("WPM: 0");
    private int WPM = 0;
    private int sec = 0;

    public TouchTyping() {
        themeSong = SoundUtil.getClip("/sounds/song.wav");
        init();
    }

    private void init() {

        fullTextWords = fullText.split(" ");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, 1));
        mainPanel.setFocusable(true);
        mainPanel.addKeyListener(this);


        focusButton.addActionListener(e -> {
            this.requestFocus();
            System.out.println(getSize());
        });


        JPanel topPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(topPanel, 2);
        topPanel.setLayout(boxLayout);
        topPanel.add(WPMText);
        topPanel.add(scoreText);
        topPanel.add(focusButton);
        topPanel.add(useSoundCheckBox);
        topPanel.add(mustCompleteBox);
        topPanel.add(debugCheck);
        mainPanel.add(topPanel);



        textPane.setFont(font);
        textPane.setEditable(false);
        textPane.setSize(TEXTAREA_SIZE);
        textPane.setForeground(Color.GRAY);
        textPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, 2));
        textPanel.add(textPane);
        JScrollPane jScrollPane = new JScrollPane(textPanel, 20,31);
        mainPanel.add(jScrollPane);


        mainPanel.add(keyboard);


        getContentPane().add(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(FRAME_SIZE);
        setLocationRelativeTo(null);
        addKeyListener(this);
        addFocusListener(this);
        setFocusable(true);
        requestFocus();

        prepareText();

        if(true)
            try{
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            }catch(Exception e){
                e.printStackTrace();
            }

        setVisible(true);
    }

    @Override
    public void keyTyped(final KeyEvent e) {
        Key typed_key = Util.keys.get(e.getExtendedKeyCode());
        Key org_key = Util.keys.get(KeyEvent.getExtendedKeyCodeForChar(getCurrentChar()));


        if (!init) {
            init = true;
            timer.start();
        }

        if (org_key == Key.ENTER) {
            org_key = Util.keys.get(KeyEvent.getExtendedKeyCodeForChar(currentTextPortion.charAt(++currentIndex)));
        }

        if(typed_key == Key.SPACE)
            typed_key = Key.SPACE_REPLACEMENT;


        final boolean correct = typed_key == org_key;

        System.out.println((int) 'a');
        System.out.println((int) 'A');

        System.out.println(String.format("actual: %d, typed: %d, correct: %b", ((int) getCurrentChar()), e.getKeyCode(), correct));
        System.out.println(String.format("actual: %d, typed: %d, correct: %b", KeyEvent.getExtendedKeyCodeForChar(getCurrentChar()), e.getExtendedKeyCode(), correct));
        //System.out.println(String.format("actual: %s, typed: %s", org_key.toString(), typed_key.toString()));

        if (org_key == Key.SPACE) {
            wordIndex++;
            if(correct)
                WPM++;
        }

        if(!debug())
            setCurrentChar(correct ? CORRECT_COLOR : WRONG_COLOR);

        keyboard.click(typed_key);

        if (correct || !mustCompleteBox.isSelected()) {
            currentIndex++;
            fullIndex++;
        }

        if (currentIndex >= currentTextPortion.length())
            prepareText();

        visualiseCursor();
    }

    private char getCurrentChar() {
        return currentTextPortion.charAt(currentIndex);
    }

    private void setCurrentChar(Color color) {
        Style style = context.addStyle("Gello", null);
        StyleConstants.setForeground(style, color);
        try {
            doc.replace(currentIndex, 1, String.valueOf(getCurrentChar()), style);
        } catch (BadLocationException badLocationException) {
            badLocationException.printStackTrace();
        }

    }

    private void updateErrorCount(char typed) {
        data.getWrongTyped().put(typed, data.getWrongTyped().getOrDefault(typed, 0) + 1);
    }

    private void updateScore() {
        float percent =  ((float) score/fullIndex) * 100;
        scoreText.setText(String.format("Success rate: %.1f%s", percent, "%"));
        //System.out.println(String.format("percent: %.1f, score: %d, index: %d", percent, score, fullIndex));
    }

    private void updateWPM() {
        WPMText.setText(String.format("WPM: %.1f", ((float) WPM/((float) sec/60))));
    }



    private void prepareText() {
        //fullIndex += currentIndex;

        currentTextPortion = getTextPortion();//fullText.substring(fullIndex, fullIndex + MAX_TEXT_CHAR_PORTION).replace(' ', SPACE_CHAR);
        textPane.setText(currentTextPortion);

        initDoc();

        currentIndex = 0;
        visualiseCursor();
    }

    private String getWord() {
        if(currentTextPortion.charAt(currentIndex) == ' ')
            return "";
        return fullTextWords[wordIndex + 1];
    }


    private void initDoc() {
        attributeSet.addAttribute(StyleConstants.ColorConstants.Foreground, DEFAULT_COLOR);
        StyleConstants.setAlignment(attributeSet, StyleConstants.ALIGN_CENTER);
        //StyleConstants.setLineSpacing(attributeSet, 1.5f);


        doc.setCharacterAttributes(0, currentTextPortion.length(), attributeSet, true);
        doc.setParagraphAttributes(0, doc.getLength(), attributeSet,false);
    }

    private String getTextPortion() {
        final StringBuffer stringBuffer = new StringBuffer();
        int lineWidth = 0;

        for(int i = 0; i < WORDS_PER_TEXT_PORTION; i++) {
            String word = fullTextWords[wordIndex + i] + SPACE_CHAR;
            //System.out.println(word);

            if(lineWidth + word.length() > LINE_WIDTH) {
                stringBuffer.append("\n");
                lineWidth = 0;
            }

            stringBuffer.append(word);
            lineWidth += word.length();
        }

        //wordIndex += WORDS_PER_TEXT_PORTION;

        return stringBuffer.substring(0, stringBuffer.length()-1).toString();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(false && useSound())
            SoundUtil.playSound(Sound.KEY_PUSH);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(false && useSound())
            SoundUtil.playSound(Sound.KEY_RELEASE);
    }

    private boolean useSound() {
        return useSoundCheckBox.isSelected();
    }
    private boolean debug() {
        return debugCheck.isSelected();
    }

    private void visualiseCursor() {

        int extra = currentTextPortion.charAt(currentIndex) == '\n' ? 1 : 0;


        Style style = context.addStyle("Gello", null);
        StyleConstants.setForeground(style, HIGHLIGHT_COLOR);
        StyleConstants.setUnderline(style, true);
        doc.setCharacterAttributes(currentIndex + extra, 1, style, true);
    }



    @Override
    public void focusGained(FocusEvent e) {
        //System.err.println("focus gained");
    }

    @Override
    public void focusLost(FocusEvent e) {
        //System.err.println("focus lost");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sec++;
        updateWPM();
        //System.out.println(dateFormat.format(System.currentTimeMillis()));
    }
}
