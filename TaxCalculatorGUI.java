import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TaxCalculatorGUI extends JFrame implements ActionListener {

    private JTextField incomeField, taxField, recommendField, expenditureField;
    private JButton calculateButton;
    private JLabel incomeLabel, taxLabel, expenditureLabel;
    private JRadioButton oldRadio, newRadio;
    private ButtonGroup group;
    private JLabel recommendLabel;

    public TaxCalculatorGUI() {
        // Set up the frame
        setTitle("Income Tax Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridLayout(6, 3));

        // Create components
        incomeLabel = new JLabel("Enter your annual income (RS) :");
        incomeField = new JTextField();
        incomeField.setPreferredSize(new Dimension(30, 20));
        
	  expenditureLabel = new JLabel("Enter your Expenditure (RS) :");
        expenditureField = new JTextField();
        expenditureField.setPreferredSize(new Dimension(30, 20));
	  
	  recommendLabel = new JLabel("Suggestions :");
	  
        taxLabel = new JLabel("Tax to be paid (RS) :");
        taxField = new JTextField();
        taxField.setEditable(false);
        oldRadio = new JRadioButton("Old parameters", true);
        newRadio = new JRadioButton("New parameters");
        group = new ButtonGroup();
        group.add(oldRadio);
        group.add(newRadio);
        calculateButton = new JButton("Calculate");
	  calculateButton.setPreferredSize(new Dimension(30, 20));
        calculateButton.addActionListener(this);

	  recommendField = new JTextField();
        recommendField.setEditable(false);

        // Add components to frame
        add(incomeLabel);
        add(incomeField);
        add(oldRadio);
        add(newRadio);
	  add(expenditureLabel);
	  add(expenditureField);
        add(taxLabel);
        add(taxField);
	  
	  add(recommendLabel);
	  add(recommendField);

        add(new JLabel());
        add(calculateButton);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Get inputs from user
        double income = Double.parseDouble(incomeField.getText());
        boolean useOldParameters = oldRadio.isSelected();
	
	  double expenditure = Double.parseDouble(expenditureField.getText());
        double amount = income - expenditure ;
	  double r = (amount / 10000)-0.5;
	  double roundedValue = Math.round(r);
	  double newIncome = roundedValue*10000;

	  double rtax = 0.0;
        if (useOldParameters) {
            if (newIncome <= 250000) {
                rtax = 0;
            } else if (newIncome <= 500000) {
                rtax = (newIncome - 250000) * 0.05;
            } else if (newIncome <= 1000000) {
                rtax = 12500 + (newIncome - 500000) * 0.2;
            } else {
                rtax = 112500 + (newIncome - 1000000) * 0.3;
            }
        } else {
            if (newIncome <= 300000) {
                rtax = 0;
            } else if (newIncome <= 600000) {
                rtax = (newIncome - 300000) * 0.05;
            } else if (newIncome <= 900000) {
                rtax = 15000 + (newIncome - 600000) * 0.1;
            } else if (newIncome <= 1200000) {
                rtax = 45000 + (newIncome - 900000) * 0.15;
            } else if (newIncome <= 1500000) {
                rtax = 82500 + (newIncome - 1200000) * 0.2;
            } else {
                rtax = 142500 + (newIncome - 1500000) * 0.3;
            }
        }
        


        // Calculate tax
        double tax = 0.0;
        if (useOldParameters) {
            if (amount <= 250000) {
                tax = 0;
            } else if (amount <= 500000) {
                tax = (amount - 250000) * 0.05;
            } else if (amount <= 1000000) {
                tax = 12500 + (amount - 500000) * 0.2;
            } else {
                tax = 112500 + (amount - 1000000) * 0.3;
            }
        } else {
            if (amount <= 300000) {
                tax = 0;
            } else if (amount <= 600000) {
                tax = (amount - 300000) * 0.05;
            } else if (amount <= 900000) {
                tax = 15000 + (amount - 600000) * 0.1;
            } else if (amount <= 1200000) {
                tax = 45000 + (amount - 900000) * 0.15;
            } else if (amount <= 1500000) {
                tax = 82500 + (amount - 1200000) * 0.2;
            } else {
                tax = 142500 + (amount - 1500000) * 0.3;
            }
        }

        // Update tax field
        taxField.setText(String.format("%.2f", tax));
	  recommendField.setText(String.format("If your next income is RS. %.2f then your tax is RS. %.2f ", newIncome ,rtax));

    }

    public static void main(String[] args) {
        new TaxCalculatorGUI();
    }
}