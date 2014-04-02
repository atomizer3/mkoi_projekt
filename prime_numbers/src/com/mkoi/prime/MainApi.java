package com.mkoi.prime;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import javax.swing.*;

/**
 * Created by Tomek on 2014-03-31.
 */
public class MainApi {
    private JTextArea textArea;
    private JButton cancelButton;
    private JButton nextButton;
    private JTextField textField;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;

    JSpinner spinner;

    private void initButtons(){
        cancelButton = new JButton("Cancel");
        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ala ma kota na next");
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(textArea.getText()+"ala nie ma kota na cancel\n");
                System.out.println("ala nie ma kota na cancel");
            }
        });
    }

    private void initTextareas(){
        textArea = new JTextArea();
        textArea.setMinimumSize(new Dimension(200,100));
        textArea.setEnabled(false);
        textArea.setToolTipText("Insert number-value here");
    }

    private void initApi(){
        textField = new JTextField();
        radioButton1 = new JRadioButton("Fermat");
        radioButton2 = new JRadioButton("Solovay-Strassen");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);

    }

    private void initView(){
        JFrame frame = new JFrame("Prime numbers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initButtons();
        initTextareas();
        initApi();

        JPanel buttonPane = new JPanel();
        JPanel radioPane = new JPanel();
        JScrollPane scrollPane = new JScrollPane(textArea);

        radioPane.add(radioButton1);
        radioPane.add(radioButton2);
        radioPane.setLayout(new BoxLayout(radioPane,BoxLayout.Y_AXIS));

        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(textField);
        buttonPane.add(radioPane);
        buttonPane.add(cancelButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(nextButton);

        scrollPane.setPreferredSize(new Dimension(400,150));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);

        Container contentPane = frame.getContentPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.PAGE_START);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }

    private void initBackground(){
        //initliaize jspinner model here and orher radio buttons and whatever else
        //https://community.oracle.com/message/9339792?#9337792
        //http://stackoverflow.com/questions/1313390/is-there-any-way-to-accept-only-numeric-values-in-a-jtextfield

    }

    public static void main(String[] args) {
        MainApi mainapi = new MainApi();
        mainapi.initView();
    }
    public int readValue() {
        return 1;
    }
//    MainCards = new JPanel(new CardLayout());

}
