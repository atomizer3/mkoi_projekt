package com.mkoi.prime;

import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import javax.swing.*;

/**
 * Created by Tomek on 2014-03-31.
 */
public class MainApi{
    public JTextArea textArea;
    public JButton cancelButton;
    public JButton nextButton;
    public JTextField textField;
    public JRadioButton radioButton1;
    public JRadioButton radioButton2;

    protected static Fermat fermat;


    JSpinner spinner;

    private void initButtons(){
        cancelButton = new JButton("Clear");
        nextButton = new JButton("Start");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainButtonAction();

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                textField.setText("0");
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
        spinner = new JSpinner();
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner);
        editor.getFormat().setGroupingUsed(false);
        spinner.setEditor(editor);
        textField = ((JSpinner.DefaultEditor)spinner.getEditor()).getTextField();
//        editor.setEditor(new JSpinner.NumberEditor(spinner,"#"));
        radioButton1 = new JRadioButton("Fermat");
        radioButton2 = new JRadioButton("Solovay-Strassen");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);

    }

    protected void initView(){
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
//        buttonPane.add(spinner);
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
        fermat = new Fermat();
        fermat.initView();
    }
    public BigInteger readValue(){
        return new BigInteger(textField.getText().replace(" ",""));
    }
    public void mainButtonAction(){
        if (!textField.getText().equals(null) && !textField.getText().equals("")){
            if (radioButton1.isSelected()){
                textArea.setText("");
                fermat.check_prime(readValue(),10);
            } else if (radioButton2.isSelected()){
                textArea.setText("");
                //solovay-strassen.check_prime();
            } else {
                textArea.setText("Select algorithm to validate prime number\n");
            }
        } else {
            textArea.setText("Provide number to validate\n");
        }
    }

}
