import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SimpleChatBot extends JFrame implements ActionListener {

    final String TITLE_OF_PROGRAM = "Chatter: simple chatbot ";
    final int START_LOCATION = 200;
    final int WINDOW_WIDTH = 350;
    final int WINDOW_HEIGHT = 450;

    JTextArea dialogue; //area for dialog
    JCheckBox ai;       //enable/disable AI
    JTextField message; //field for entering message
    SimpleBot sbot;     // chat-bot class (in bot package)

    public static void main(String[] args) {
        new SimpleChatBot();
    }

    SimpleChatBot() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);
        // area for dialog
        dialogue = new JTextArea();
        dialogue.setLineWrap(true);
        JScrollPane scrollBar = new JScrollPane(dialogue);
        // panel for checkbox, message field and button
        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
        ai = new JCheckBox("AI");
        //ai.doClick();
        message = new JTextField();
        message.addActionListener(this);
        JButton enter = new JButton("Enter");
        enter.addActionListener(this);
        // adding all elements to the window
        bp.add(ai);
        bp.add(message);
        bp.add(enter);
        add(BorderLayout.CENTER, scrollBar);
        add(BorderLayout.SOUTH, bp);
        add(BorderLayout.CENTER, scrollBar);
        setVisible(true);
        sbot = new SimpleBot(); // creating bot object
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (message.getText().trim().length() > 0) {
            dialogue.append(message.getText() + "\n");
            dialogue.append(TITLE_OF_PROGRAM.substring(0, 9) +
                sbot.sayInReturn(message.getText(), ai.isSelected()) + "\n");
        }
        message.setText("");
        message.requestFocusInWindow();
    }
}