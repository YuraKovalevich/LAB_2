package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
class MainFrame extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 420;
    private Double sum = 0.0D;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaID = 1;

    public Double calculate1(Double x, Double y, Double z) {
        return (Math.pow(Math.cos(Math.exp(x) + Math.exp(y * y) + Math.pow(1 / x, 1 / 2)), 1 / 4)) /
                Math.pow(Math.cos(Math.PI * z * z * z) + Math.log((1 + z) * (1 + z)), Math.sin(x));
    }

    public Double calculate2(Double x, Double y, Double z) {
        return (1 + Math.pow(x, z) + Math.log(y * y)) / (Math.pow(x * x * x + 1, 1 / 2)) * (1 - Math.sin(y * z));
    }

    private void addRadioButton(String buttonName, final int formulaID) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.formulaID = formulaID;
            }
        });
        this.radioButtons.add(button);
        this.hboxFormulaType.add(button);
    }

    public MainFrame() {
        super("Вычисление формулы");
        this.setSize(400, 420);
        Toolkit kit = Toolkit.getDefaultToolkit();
        this.setLocation((kit.getScreenSize().width - 400) / 2, (kit.getScreenSize().height - 420) / 2);

        this.hboxFormulaType.add(Box.createHorizontalGlue());
        this.addRadioButton("Формула1", 1);
        this.addRadioButton("Формула2", 2);
        this.radioButtons.setSelected(((AbstractButton) this.radioButtons.getElements().nextElement()).getModel(), true);
        this.hboxFormulaType.add(Box.createHorizontalGlue());
        this.hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        JLabel labelForX = new JLabel("X:");
        this.textFieldX = new JTextField("0", 5);
        this.textFieldX.setMaximumSize(this.textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        this.textFieldY = new JTextField("0", 5);
        this.textFieldY.setMaximumSize(this.textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        this.textFieldZ = new JTextField("0", 5);
        this.textFieldZ.setMaximumSize(this.textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(this.textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(30));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(this.textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(30));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(this.textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());

        JLabel labelForResult = new JLabel("Результат: ");
        this.textFieldResult = new JTextField("0", 15);
        this.textFieldResult.setMaximumSize(this.textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(this.textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));


        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Double x = Double.parseDouble(MainFrame.this.textFieldX.getText());
                    Double y = Double.parseDouble(MainFrame.this.textFieldY.getText());
                    Double z = Double.parseDouble(MainFrame.this.textFieldZ.getText());
                    Double result;
                    if (MainFrame.this.formulaID == 1) {
                        result = MainFrame.this.calculate1(x, y, z);
                    } else {
                        result = MainFrame.this.calculate2(x, y, z);
                    }

                    MainFrame.this.textFieldResult.setText(result.toString());
                } catch (NumberFormatException var6) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате числа с плавающей точкой", "Ошибочный формат числа", 2);
                }

            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.textFieldX.setText("0");
                MainFrame.this.textFieldY.setText("0");
                MainFrame.this.textFieldZ.setText("0");
                MainFrame.this.textFieldResult.setText("0");
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        //hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonReset);
        //hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        JButton buttonMPlus = new JButton("M+");
        buttonMPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Double x = Double.parseDouble(MainFrame.this.textFieldX.getText());
                    Double y = Double.parseDouble(MainFrame.this.textFieldY.getText());
                    Double z = Double.parseDouble(MainFrame.this.textFieldZ.getText());
                    if (MainFrame.this.formulaID == 1) {
                        MainFrame.this.sum = MainFrame.this.sum + MainFrame.this.calculate1(x, y, z);
                    } else {
                        MainFrame.this.sum = MainFrame.this.sum + MainFrame.this.calculate2(x, y, z);
                    }

                    MainFrame.this.textFieldResult.setText(MainFrame.this.sum.toString());
                } catch (NumberFormatException var5) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате числа с плавающей точкой", "Ошибочный формат числа", 2);
                }

            }
        });

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.sum = 0.0D;
                MainFrame.this.textFieldResult.setText(MainFrame.this.sum.toString());
            }
        });

        Box hboxButtons2 = Box.createHorizontalBox();
        hboxButtons2.add(Box.createHorizontalGlue());
        hboxButtons2.add(buttonMPlus);
        hboxButtons2.add(Box.createHorizontalStrut(10));
        hboxButtons2.add(buttonMC);
        hboxButtons2.add(Box.createHorizontalGlue());
        hboxButtons2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(this.hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxButtons);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons2);
        contentBox.add(Box.createVerticalGlue());
        this.getContentPane().add(contentBox, "Center");
    }

}