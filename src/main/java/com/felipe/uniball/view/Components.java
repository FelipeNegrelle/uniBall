package com.felipe.uniball.view;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import com.felipe.uniball.controller.Auth;
import net.miginfocom.swing.MigLayout;

public class Components {
    public static class BorderedPanel extends JPanel {
        public BorderedPanel(String buttonText, String paneText, String label, int width, int height) {
            // Set the preferred size to the provided width and height
            setPreferredSize(new Dimension(width, height));

            // Use MigLayout for the panel
            setLayout(new MigLayout("fill, insets 5", "[grow]", "[align center][][][grow]"));

            setBorder(new BevelBorder(BevelBorder.LOWERED));

            JButton button = new JButton(buttonText);
            button.setFont(new Font("Tahoma", Font.BOLD, 12));
            add(button, "cell 0 0, alignx center, wrap");

            JTextPane textPanel = new JTextPane();
            textPanel.setBackground(new Color(220, 227, 250));
            textPanel.setFont(new Font("Tahoma", Font.PLAIN, 14));
            textPanel.setText(paneText);
            textPanel.setEditable(false);
            textPanel.setCaret(null);
            add(textPanel, "cell 0 1, align center, grow, wrap");

            JLabel panelLabel = new JLabel(label);
            panelLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
            panelLabel.setForeground(new Color(0, 0, 128));
            add(panelLabel, "cell 0 2, align center");
        }
    }

//    public static class RegistrationDialog extends JDialog {
//        public RegistrationDialog(JFrame parent) {
//            super(parent, "Register", true);
//            setSize(300, 150);
//            setLocationRelativeTo(parent);
//
//            final JTextField nameField = new JTextField(20);
//            final JTextField numberField = new JTextField(20);
//            final JTextField positionField = new JTextField(20);
//            final JTextField usernameField = new JTextField(20);
//            final JPasswordField passwordField = new JPasswordField(20);
//            JButton registerButton = new JButton("Register");
//
//            JPanel panel = new JPanel(new GridLayout(6, 2));
//            panel.add(new JLabel("Name:"));
//            panel.add(nameField);
//            panel.add(new JLabel("Number:"));
//            panel.add(numberField);
//            panel.add(new JLabel("Position:"));
//            panel.add(positionField);
//            panel.add(new JLabel("Username:"));
//            panel.add(usernameField);
//            panel.add(new JLabel("Password:"));
//            panel.add(passwordField);
//            panel.add(new JLabel());
//            panel.add(registerButton);
//
//            registerButton.addActionListener(e -> {
//                boolean registrationSuccess = Auth.register(usernameField.getText(), new String(passwordField.getPassword()));
//
//                if (registrationSuccess) {
//                    JOptionPane.showMessageDialog(RegistrationDialog.this, "Registration successful!");
//                } else {
//                    JOptionPane.showMessageDialog(RegistrationDialog.this, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//
//                dispose();
//            });
//
//            add(panel);
//        }
//    }

    public static class RegistrationDialog extends JDialog {
        public RegistrationDialog(JFrame parent) {
            super(parent, "Register", true);
            setSize(800, 600);
            setLocationRelativeTo(parent);

            final JLabel nameLabel = new JLabel("Nome:");
            nameLabel.setFont(new Font("Sans", Font.BOLD, 20));

            final JTextField nameField = new JTextField(30);
            nameField.setFont(new Font("Sans", Font.PLAIN, 20));

            final JLabel numberLabel = new JLabel("Número:");
            numberLabel.setFont(new Font("Sans", Font.BOLD, 20));

            final JTextField numberField = new JTextField(30);
            numberField.setFont(new Font("Sans", Font.PLAIN, 20));

            final JLabel positionLabel = new JLabel("Posição:");
            positionLabel.setFont(new Font("Sans", Font.BOLD, 20));

            final JTextField positionField = new JTextField(30);
            positionField.setPreferredSize(new Dimension(300, 10));
            positionField.setFont(new Font("Sans", Font.PLAIN, 20));

            final JLabel usernameLabel = new JLabel("Usuário:");
            usernameLabel.setFont(new Font("Sans", Font.BOLD, 20));

            final JTextField usernameField = new JTextField(30);
            usernameField.setPreferredSize(new Dimension(300, 10));
            usernameField.setFont(new Font("Sans", Font.PLAIN, 20));

            final JLabel passwordLabel = new JLabel("Senha:");
            passwordLabel.setFont(new Font("Sans", Font.BOLD, 20));

            final JPasswordField passwordField = new JPasswordField(30);
            passwordField.setPreferredSize(new Dimension(300, 10));
            passwordField.setFont(new Font("Sans", Font.PLAIN, 20));

            JButton registerButton = new JButton("Register");
            registerButton.setFont(new Font("Sans", Font.BOLD, 20));
            registerButton.setPreferredSize(new Dimension(300, 40));
            registerButton.setBackground(new Color(0x096B06));
            registerButton.setForeground(Color.white);

            JPanel panel = new JPanel(new MigLayout("filly", "[align right][grow]", "[]10[]10[]10[]10[]10[]10[]"));
            panel.add(nameLabel, "alignx left");
            panel.add(nameField, "growx, wrap");

            panel.add(numberLabel, "alignx left");
            panel.add(numberField, "growx, wrap");

            panel.add(positionLabel, "alignx left");
            panel.add(positionField, "growx, wrap");

            panel.add(usernameLabel, "alignx left");
            panel.add(usernameField, "growx, wrap");

            panel.add(passwordLabel, "alignx left");
            panel.add(passwordField, "growx, wrap");

            panel.add(registerButton, "span 2, align center");

            registerButton.addActionListener(e -> {
                boolean registrationSuccess = Auth.register(
                        nameField.getText(),
                        numberField.getText(),
                        positionField.getText(),
                        usernameField.getText(),
                        Arrays.toString(passwordField.getPassword())
                );

                if (registrationSuccess) {
                    JOptionPane.showMessageDialog(RegistrationDialog.this, "Registration successful!");
                } else {
                    JOptionPane.showMessageDialog(RegistrationDialog.this, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                dispose();
            });

            add(panel);
        }
    }
}