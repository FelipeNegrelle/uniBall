package com.felipe.uniball.view;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import com.felipe.uniball.controller.Auth;
import net.miginfocom.swing.MigLayout;

public class Form extends JFrame {
    public Form() {
        super("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new MigLayout("fill, insets 10", "[grow]", "[grow]"));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(new Color(0x096B06));

        JPanel mainPanel = new JPanel(new MigLayout("fill, insets 20", "[grow]", "[align center]"));
        mainPanel.setBackground(Color.WHITE);

        JLabel titlePage = new JLabel("UNIBALL");
        titlePage.setFont(new Font("Sans", Font.BOLD, 80));
        titlePage.setForeground(Color.WHITE); // Set label text color
        add(titlePage, "align center"); // Wrap to next row

        JLabel titleLabel = new JLabel("Formulário de Login");
        titleLabel.setFont(new Font("Sans", Font.BOLD, 40));
        titleLabel.setForeground(Color.BLACK); // Set label text color
        mainPanel.add(titleLabel, "align center, wrap"); // Wrap to next row

        JPanel inputPanel = new JPanel(new MigLayout("fillx, insets 20", "[grow]", "[]10[]"));
        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setFont(new Font("Sans", Font.BOLD, 20));

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setFont(new Font("Sans", Font.BOLD, 20));

        JTextField userField = new JTextField(20); // Increase the columns to make the field wider
        userField.setPreferredSize(new Dimension(300, 30)); // Set size of field
        userField.setFont(new Font("Sans", Font.PLAIN, 20));

        JPasswordField passwordField = new JPasswordField(20); // Increase the columns to make the field wider
        passwordField.setPreferredSize(new Dimension(300, 30)); // Set size of field
        passwordField.setFont(new Font("Sans", Font.PLAIN, 20));

        inputPanel.add(userLabel);
        inputPanel.add(userField, "wrap"); // Wrap to next row
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        mainPanel.add(inputPanel, "wrap, grow"); // Wrap to next row

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Sans", Font.BOLD, 20));
        loginButton.setPreferredSize(new Dimension(300, 40));
        loginButton.setBackground(new Color(0x096B06));
        loginButton.setForeground(Color.lightGray);
        loginButton.addActionListener(e -> {
            try {
                if (!Auth.login(userField.getText(), Arrays.toString(passwordField.getPassword()))) {
                    System.out.println(userField.getText() + Arrays.toString(passwordField.getPassword()));
                    JOptionPane.showMessageDialog(this, "Login failed");
                    return;
                }
                new Menu();
                dispose();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Sans", Font.BOLD, 20));
        registerButton.setPreferredSize(new Dimension(300, 40));
        registerButton.setBackground(new Color(0x096B06));
        registerButton.setForeground(Color.lightGray);
        registerButton.addActionListener(e -> {
            try {
                new Components.RegistrationDialog(this).setVisible(true);
//                Auth.register(userField.getText(), Arrays.toString(passwordField.getPassword()));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        mainPanel.add(registerButton, "align center, split 2");
        mainPanel.add(loginButton, "align center");
        add(mainPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
