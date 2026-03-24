package com.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class SwingCalculator extends JFrame implements ActionListener {
    private JTextField display;

    private StringBuilder currentInput = new StringBuilder();
    private BigDecimal firstOperand = null;
    private String operator = "";

    public SwingCalculator() {
        setTitle("Precision GUI Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+",
                "Sqrt", "Pow", ".", "Del",
                "C->F", "F->C", "USD->INR", "INR->USD"
        };
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 15));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if ("0123456789.".contains(command)) {
                if (command.equals(".") && currentInput.toString().contains("."))
                    return;

                currentInput.append(command);
                display.setText(currentInput.toString());

            } else if ("C".equals(command)) {
                currentInput.setLength(0);
                firstOperand = null;
                operator = "";
                display.setText("");

            } else if ("Del".equals(command)) {
                if (currentInput.length() > 0) {
                    currentInput.deleteCharAt(currentInput.length() - 1);
                    display.setText(currentInput.toString());
                }

            } else if ("+-*/".contains(command) || "Pow".equals(command)) {
                if (currentInput.length() > 0) {
                    firstOperand = new BigDecimal(currentInput.toString());
                    operator = command;
                    currentInput.setLength(0);
                }

            } else if ("Sqrt".equals(command)) {
                if (currentInput.length() > 0) {
                    BigDecimal num = new BigDecimal(currentInput.toString());
                    if (num.signum() < 0) {
                        display.setText("Negative SQRT Error");
                    } else {
                        double sqrt = Math.sqrt(num.doubleValue());
                        displayResult(BigDecimal.valueOf(sqrt));
                    }
                }
            } else if ("C->F".equals(command)) {
                if (currentInput.length() > 0) {
                    BigDecimal num = new BigDecimal(currentInput.toString());
                    BigDecimal f = num.multiply(new BigDecimal("1.8")).add(new BigDecimal("32"));
                    displayResult(f);
                }
            } else if ("F->C".equals(command)) {
                if (currentInput.length() > 0) {
                    BigDecimal num = new BigDecimal(currentInput.toString());
                    BigDecimal c = num.subtract(new BigDecimal("32")).divide(new BigDecimal("1.8"),
                            MathContext.DECIMAL64);
                    displayResult(c);
                }
            } else if ("USD->INR".equals(command)) {
                if (currentInput.length() > 0) {
                    BigDecimal num = new BigDecimal(currentInput.toString());
                    BigDecimal inr = num.multiply(new BigDecimal("83.50")).setScale(2, RoundingMode.HALF_UP);
                    displayResult(inr);
                }
            } else if ("INR->USD".equals(command)) {
                if (currentInput.length() > 0) {
                    BigDecimal num = new BigDecimal(currentInput.toString());
                    BigDecimal usd = num.divide(new BigDecimal("83.50"), 2, RoundingMode.HALF_UP);
                    displayResult(usd);
                }

            } else if ("=".equals(command)) {
                if (firstOperand != null && currentInput.length() > 0) {
                    BigDecimal secondOperand = new BigDecimal(currentInput.toString());
                    BigDecimal result = BigDecimal.ZERO;

                    switch (operator) {
                        case "+":
                            result = firstOperand.add(secondOperand);
                            break;
                        case "-":
                            result = firstOperand.subtract(secondOperand);
                            break;
                        case "*":
                            result = firstOperand.multiply(secondOperand);
                            break;
                        case "/":
                            if (secondOperand.compareTo(BigDecimal.ZERO) == 0) {
                                display.setText("Divide by 0 Error");
                                currentInput.setLength(0);
                                firstOperand = null;
                                return;
                            }
                            result = firstOperand.divide(secondOperand, MathContext.DECIMAL64);
                            break;
                        case "Pow":
                            try {
                                result = firstOperand.pow(secondOperand.intValueExact(), MathContext.DECIMAL64);
                            } catch (Exception ex) {
                                display.setText("Exponent Error");
                                return;
                            }
                            break;
                    }

                    displayResult(result);
                    firstOperand = null;
                    operator = "";
                }
            }
        } catch (Exception ex) {
            display.setText("Error");
            currentInput.setLength(0);
        }
    }

    private void displayResult(BigDecimal result) {
        String formattedResult = result.stripTrailingZeros().toPlainString();
        display.setText(formattedResult);
        currentInput.setLength(0);
        currentInput.append(formattedResult);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingCalculator());
    }
}