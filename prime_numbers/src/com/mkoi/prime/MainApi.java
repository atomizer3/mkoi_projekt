package com.mkoi.prime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Tomek on 2014-03-31.
 */

/**
 * Main class that implements GUI for user
 */
public class MainApi {

    public JTextArea textArea;
    public JButton cancelButton;
    public JButton nextButton;
    public JTextField textField1;
    public JTextField textField2;
    public JRadioButton radioButton1;
    public JRadioButton radioButton2;
    public JCheckBox isEnhancedVerbosity;

    protected final FermatPrimalityTest fermat;
    protected final SolovayStrassenPrimalityTest solovayStrassenPrimalityTest;
    protected final MkoiLogger logger;
    protected final IRandomNumberService randomNumberService;

    JSpinner spinner1;
    JSpinner spinner2;

    /**
     * Initialization of GUI components and  prime test objects
     */
    public MainApi() {
        initView();

        randomNumberService = new RandomNumberService(new Random(System.currentTimeMillis()));
        logger = new MkoiLogger(this.textArea);

        fermat = new FermatPrimalityTest(logger, randomNumberService);
        solovayStrassenPrimalityTest = new SolovayStrassenPrimalityTest(logger, randomNumberService);
    }

    /**
     * Initialization buttons and their actions
     */
    private void initButtons() {
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
                textField1.setText("0");
                textField2.setText("1");
            }
        });
    }

    /**
     * Initialization of TextArea object
     */
    private void initTextareas() {
        textArea = new JTextArea();
        textArea.setMinimumSize(new Dimension(200, 100));
        textArea.setEnabled(false);
    }

    /**
     * Controls initialization (TextFields, Radio controls etc)
     */
    private void initApi() {
        textField1 = new JTextField();
        textField2 = new JTextField();

        textField1.setText("Number");
        textField1.setText("Rounds");
       /* spinner1 = new JSpinner();
        spinner2 = new JSpinner();
        JSpinner.NumberEditor editor1 = new JSpinner.NumberEditor(spinner1);
        JSpinner.NumberEditor editor2 = new JSpinner.NumberEditor(spinner2);
        editor1.getFormat().setGroupingUsed(false);
        editor2.getFormat().setGroupingUsed(false);
        spinner1.setEditor(editor1);
        spinner2.setEditor(editor2);
        textField1 = ((JSpinner.DefaultEditor) spinner1.getEditor()).getTextField();
        textField2 = ((JSpinner.DefaultEditor) spinner2.getEditor()).getTextField();*/
        textField2.setText("1");

        radioButton1 = new JRadioButton("Fermat");
        radioButton2 = new JRadioButton("Solovay-Strassen");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);

        isEnhancedVerbosity = new JCheckBox();
        isEnhancedVerbosity.setText("Enhanced verbosity");
    }

    /**
     * Main GUI initialization
     * adds all created objects into main Frame
     * sets dimensions, shape and sizes of objects
     */
    protected void initView() {
        JFrame frame = new JFrame("Prime numbers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //initializations
        initButtons();
        initTextareas();
        initApi();

        JPanel buttonPane = new JPanel();
        JPanel radioPane = new JPanel();
        JScrollPane scrollPane = new JScrollPane(textArea);

        //adding Panes into Frame and set settings
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(textField1);
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        buttonPane.add(separator);
        buttonPane.add(textField2);

        radioPane.add(radioButton1);
        radioPane.add(radioButton2);
        radioPane.setLayout(new BoxLayout(radioPane, BoxLayout.Y_AXIS));
        buttonPane.add(radioPane);

        buttonPane.add(cancelButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(nextButton);
        buttonPane.add(isEnhancedVerbosity);

        scrollPane.setPreferredSize(new Dimension(400, 150));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);

        Container contentPane = frame.getContentPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.PAGE_START);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Read value from TextField considered as prime candidate
     * @return Biginteger with test value given by user
     */
    public BigInteger readValue() {
        return new BigInteger(textField1.getText().replace(" ", ""));
    }

    /**
     * Read value from TextField considered as repeat indicator
     * @return integer value how many times selected algorithm should be executed
     */
    public int readAttempts() {
        return new Integer(textField2.getText().replace(" ", ""));
    }

    /**
     * Checks if given-by-user values (tested number and repeat value) are correct
     * @return true if values are correct
     * false if values are incorrect
     */
    private boolean validateTextFields(){
        if ( readValue().compareTo(BigInteger.ONE) == 1 && readAttempts() > 0 ){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Main function invoking selected algorithm with given parameters
     * counting time in nanos from algorithm invokation into termination
     * also provides validation of selection and raise proper communicate for user
     */
    public void mainButtonAction() {
        if (!textField1.getText().equals(null) && !textField1.getText().equals("") && !textField2.getText().equals(null) && !textField2.getText().equals("")) {
            boolean result = false;
            long startTime,endTime;
            if ( validateTextFields() == true ) {
                if (radioButton1.isSelected()) {
                    textArea.setText("");
                    startTime = System.nanoTime();
                    result = fermat.probablyPrime(readValue(), readAttempts(), isEnhancedVerbosity.isSelected());
                    endTime = System.nanoTime();
                    textArea.setText(textArea.getText()+"\n"+
                                    "execution time: "+(endTime-startTime)/1000000000.0);
                } else if (radioButton2.isSelected()) {
                    textArea.setText("");
                    startTime = System.nanoTime();
                    result = solovayStrassenPrimalityTest.probablyPrime(readValue(), readAttempts(), isEnhancedVerbosity.isSelected());
                    endTime = System.nanoTime();
                    textArea.setText(textArea.getText()+"\n"+
                            "execution time: "+(endTime-startTime)/1000000000.0);
                } else {
                    textArea.setText("Select algorithm to validate prime number\n");
                }
            } else {
                textArea.setText("Given parameters are incorrect\n");
            }
        } else {
            textArea.setText("Provide number to validate\n");
        }
    }
}
