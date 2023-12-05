package com.felipe.uniball.view;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import com.felipe.uniball.Constants;
import com.felipe.uniball.controller.Auth;
import net.miginfocom.swing.MigLayout;

import static com.felipe.uniball.Constants.GREEN;

public class Form extends JFrame {
    public Form() {
        super(Constants.LOGIN);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new MigLayout("fill, insets 10", "[grow]", "[grow]"));
        getContentPane().setBackground(GREEN);

        JPanel mainPanel = new JPanel(new MigLayout("fill, insets 20", "[grow]", "[align center]"));
        mainPanel.setBackground(Color.WHITE);

        JLabel titlePage = new JLabel("UNIBALL");
        titlePage.setFont(new Font("Sans", Font.BOLD, 80));
        titlePage.setForeground(Color.WHITE);
        add(titlePage, "align center");

        JLabel titleLabel = new JLabel(Constants.LOGIN);
        titleLabel.setFont(new Font("Sans", Font.BOLD, 40));
        titleLabel.setForeground(Color.BLACK);
        mainPanel.add(titleLabel, "align center, wrap");

        JPanel inputPanel = new JPanel(new MigLayout("fillx, insets 20", "[grow]", "[]10[]"));
        JLabel userLabel = new JLabel(Constants.USER);
        userLabel.setFont(new Font("Sans", Font.BOLD, 20));

        JLabel passwordLabel = new JLabel(Constants.PASSWORD);
        passwordLabel.setFont(new Font("Sans", Font.BOLD, 20));

        JTextField userField = new JTextField(20);
        userField.setPreferredSize(new Dimension(300, 30));
        userField.setFont(new Font("Sans", Font.PLAIN, 20));

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(300, 30));
        passwordField.setFont(new Font("Sans", Font.PLAIN, 20));

        inputPanel.add(userLabel);
        inputPanel.add(userField, "wrap");
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        mainPanel.add(inputPanel, "wrap, grow");

        JButton loginButton = new JButton(Constants.LOGIN);
        loginButton.setFont(new Font("Sans", Font.BOLD, 20));
        loginButton.setPreferredSize(new Dimension(300, 40));
        loginButton.setBackground(GREEN);
        loginButton.setForeground(Color.lightGray);
        loginButton.addActionListener(e -> {
            try {
                if (!Auth.login(userField.getText(), Arrays.toString(passwordField.getPassword()))) {
                    System.out.println(userField.getText() + Arrays.toString(passwordField.getPassword()));
                    JOptionPane.showMessageDialog(this, Constants.FAILED_LOGIN);
                    return;
                }
                new Menu();
                Thread.sleep(50);
                dispose();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        JButton registerButton = new JButton(Constants.REGISTER);
        registerButton.setFont(new Font("Sans", Font.BOLD, 20));
        registerButton.setPreferredSize(new Dimension(300, 40));
        registerButton.setBackground(GREEN);
        registerButton.setForeground(Color.lightGray);
        registerButton.addActionListener(e -> {
            try {
                new Components.RegistrationDialog(this).setVisible(true);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        JButton forgetPasswordButton = new JButton(Constants.FORGOT);
        forgetPasswordButton.setFont(new Font("Sans", Font.BOLD, 20));
        forgetPasswordButton.setPreferredSize(new Dimension(300, 40));
        forgetPasswordButton.setBackground(GREEN);
        forgetPasswordButton.setForeground(Color.lightGray);
        forgetPasswordButton.addActionListener(e -> {
            try {
                new Components.GetUserForgetPasswordDialog(this).setVisible(true);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        mainPanel.add(registerButton, "align center, split 2");
        mainPanel.add(loginButton, "align center, wrap");
        mainPanel.add(forgetPasswordButton, "grow");
        add(mainPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
