import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by dimal on 27.03.2017.
 */
public class ChatWindow extends Frame {

    private int charsLeft;
    private JTextField textField;
    private JTextArea textArea;
    private JButton sendButton;
    private JLabel charCount;
    private JScrollPane jScrollPane;
    private JScrollPane jScrollPaneList;
    private JList<String> usersList;
    private DefaultListModel<String> listModel;
    private Client client;

    public ChatWindow(Client clnt) {
        super("WSIU Chat");

        charsLeft = 1000;
        textField = new JTextField(55);
        textArea = new JTextArea(35, 69);
        jScrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sendButton = new JButton("Send");
        charCount = new JLabel("Chars left: " + (charsLeft - textField.getText().length()));
        listModel = new DefaultListModel<String>();
        usersList = new JList<String>(listModel);

        usersList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        usersList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        jScrollPaneList = new JScrollPane(usersList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.setFont(new Font("TimesRoman", Font.BOLD, 14));
        textArea.setForeground(Color.BLACK);
        textArea.setFocusable(true);
        jScrollPane.setFocusable(true);

        jScrollPaneList.setBounds(0, 0, 200, this.getHeight() - 30);
        jScrollPane.setBounds(201, 0, 790, this.getHeight() - 65);
        textField.setBounds(201, 636, 590, 30);
        sendButton.setBounds(792, 636, 90, 30);
        charCount.setBounds(883, 636, 110, 30);

        this.add(jScrollPaneList);
        this.add(jScrollPane);
        this.add(textField);
        this.add(sendButton);
        this.add(charCount);

        this.setVisible(true);

        this.client = clnt;
        this.client.setWindow(this);
        this.client.start();

        textArea.setEnabled(false);
        textField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null) return;
                if ((getLength() + str.length()) <= charsLeft) {
                    super.insertString(offset, str, attr);
                }
            }
        });

        this.textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                charCount.setText("Chars left: " + (charsLeft - textField.getText().length()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                charCount.setText("Chars left: " + (charsLeft - textField.getText().length()));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !textField.getText().isEmpty()) {
                    client.sendMessage(new Message(client.getUsername(), textField.getText(), Message.MESSAGE));
                    textField.setText("");
                }
                charCount.setText("Chars left: " + (charsLeft - textField.getText().length()));
            }
        });

        this.sendButton.addActionListener((ActionEvent e) -> {
            if (!textField.getText().isEmpty()) {
                for(String user: usersList.getSelectedValuesList().toArray(new String[0]))
                    //System.out.println(user);
                client.sendMessage(new Message(client.getUsername(), textField.getText(), Message.MESSAGE,
                        usersList.getSelectedValuesList().toArray(new String[0])));
                textField.setText("");
                charCount.setText("Chars left: " + (charsLeft - textField.getText().length()));
            }
        });

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                client.disconnect();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

    }

    public void addText(String str){
        this.textArea.append(str + "\r\n");
    }

    public void addUserToList(String message){
        if(!message.equals(client.getUsername()))listModel.addElement(message);
    }

    public void removeUserFromList(String message){
        listModel.removeElement(message);
    }

}
