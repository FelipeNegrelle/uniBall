package com.felipe.uniball.view;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

import com.felipe.uniball.Constants;
import com.felipe.uniball.controller.Auth;
import com.felipe.uniball.controller.Util;
import com.felipe.uniball.models.Player;
import net.miginfocom.swing.MigLayout;

import static com.felipe.uniball.Constants.*;
import static com.felipe.uniball.controller.Util.deletePlayer;

public class Components {
    public static class GenericLabel extends JLabel {
        public GenericLabel(String text, int weight) {
            super(text);
            setFont(new Font("Sans", weight, 20));
        }
    }

    public static class RegistrationDialog extends JDialog {
        public RegistrationDialog(JFrame parent, boolean hasPrivacyPolicy) {
            super(parent, REGISTER, true);
            setSize(800, 600);
            setLocationRelativeTo(parent);

            JPanel panel = new JPanel(new MigLayout("filly", "[align right][grow]", "[]10[]10[]10[]10[]10[]10[]"));

            final GenericLabel nameLabel = new GenericLabel(NAME, Font.BOLD);
            JTextField nameField = new JTextField(30);
            nameField.setFont(new Font("Sans", Font.PLAIN, 20));
            nameField.setPreferredSize(new Dimension(300, 10));
            panel.add(nameLabel, "alignx left");
            panel.add(nameField, "growx, wrap");

            final GenericLabel numberLabel = new GenericLabel(NUMBER, Font.BOLD);
            JTextField numberField = new JTextField(30);
            numberField.setFont(new Font("Sans", Font.PLAIN, 20));
            numberField.setPreferredSize(new Dimension(300, 10));
            panel.add(numberLabel, "alignx left");
            panel.add(numberField, "growx, wrap");

            final GenericLabel positionLabel = new GenericLabel(POSITION, Font.BOLD);
            final JComboBox<String> positionField = new JComboBox<>();
            positionField.addItem("Atacante");
            positionField.addItem("Meio-campo");
            positionField.addItem("Lateral");
            positionField.addItem("Zagueiro");
            positionField.addItem("Goleiro");
            positionField.setPreferredSize(new Dimension(300, 10));
            positionField.setFont(new Font("Sans", Font.PLAIN, 20));
            panel.add(positionLabel, "alignx left");
            panel.add(positionField, "growx, wrap");

            final GenericLabel usernameLabel = new GenericLabel(USER, Font.BOLD);
            JTextField usernameField = new JTextField(30);
            usernameField.setFont(new Font("Sans", Font.PLAIN, 20));
            usernameField.setPreferredSize(new Dimension(300, 10));
            panel.add(usernameLabel, "alignx left");
            panel.add(usernameField, "growx, wrap");

            final GenericLabel passwordLabel = new GenericLabel(PASSWORD, Font.BOLD);
            JPasswordField passwordField = new JPasswordField(30);
            passwordField.setFont(new Font("Sans", Font.PLAIN, 20));
            passwordField.setPreferredSize(new Dimension(300, 10));
            panel.add(passwordLabel, "alignx left");
            panel.add(passwordField, "growx, wrap");

            final GenericLabel questionLabel = new GenericLabel(SECRET_PHRASE, Font.BOLD);
            final JComboBox<String> questionField = new JComboBox<>();
            questionField.addItem("Qual seu jogador de futebol favorito?");
            questionField.addItem("Qual seu time de futebol favorito?");
            questionField.addItem("Qual posição você joga?");
            questionField.setPreferredSize(new Dimension(300, 10));
            questionField.setFont(new Font("Sans", Font.PLAIN, 20));
            panel.add(questionLabel, "alignx left");
            panel.add(questionField, "growx, wrap");

            final GenericLabel answerLabel = new GenericLabel(SECRET_ANSWER, Font.BOLD);
            JTextField answerField = new JTextField(30);
            answerField.setFont(new Font("Sans", Font.PLAIN, 20));
            answerField.setPreferredSize(new Dimension(300, 10));
            panel.add(answerLabel, "alignx left");
            panel.add(answerField, "growx, wrap");

            JButton registerButton = new JButton(REGISTER);
            registerButton.setFont(new Font("Sans", Font.BOLD, 20));
            registerButton.setPreferredSize(new Dimension(300, 40));
            registerButton.setBackground(new Color(0x096B06));
            registerButton.setForeground(Color.white);
            panel.add(registerButton, "span 2, align center");

            registerButton.addActionListener(e -> {
                if (hasPrivacyPolicy) {
                    if (!privacyPolicy()) {
                        System.out.println("Não aceitou");
                        return;
                    }
                }

                if (Util.isNumber(numberField.getText())
                        && Objects.nonNull(positionField.getSelectedItem())
                        && Objects.nonNull(usernameField.getText())
                        && Objects.nonNull(passwordField.getPassword())
                        && Objects.nonNull(questionField.getSelectedItem())
                        && Objects.nonNull(answerField.getText())) {
                    boolean registrationSuccess = Auth.register(
                            nameField.getText(),
                            numberField.getText(),
                            positionField.getSelectedItem().toString(),
                            usernameField.getText(),
                            Arrays.toString(passwordField.getPassword()),
                            questionField.getSelectedItem().toString(),
                            answerField.getText()
                    );

                    if (registrationSuccess) {
                        JOptionPane.showMessageDialog(RegistrationDialog.this, SUCCESSFUL_REGISTER);
                    } else {
                        JOptionPane.showMessageDialog(RegistrationDialog.this, FAILED_REGISTER, Constants.ERROR, JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    System.out.println(numberField.getText());
                    JOptionPane.showMessageDialog(RegistrationDialog.this, FAILED_REGISTER, Constants.ERROR, JOptionPane.ERROR_MESSAGE);
                }

                dispose();
            });

            add(panel);
        }
    }

    public static class EditPlayerDialog extends JDialog {
        private static JTextField nameField;
        private static JTextField numberField;
        private static JComboBox<String> positionField;

        public EditPlayerDialog(Frame parent, Player player) {
            super(parent, "Editar Jogador", true);
            setSize(800, 600);
            setLocationRelativeTo(parent);

            GenericLabel nameLabel = new GenericLabel(NAME, Font.BOLD);
            nameField = new JTextField(player.getName(), 30);
            nameField.setFont(new Font("Sans", Font.PLAIN, 20));
            nameField.setPreferredSize(new Dimension(300, 10));

            GenericLabel numberLabel = new GenericLabel(NUMBER, Font.BOLD);
            numberField = new JTextField(String.valueOf(player.getNumber()), 30);
            numberField.setFont(new Font("Sans", Font.PLAIN, 20));

            JLabel positionLabel = new JLabel(POSITION);
            positionLabel.setFont(new Font("Sans", Font.BOLD, 20));
            positionField = new JComboBox<>();
            positionField.addItem("Atacante");
            positionField.addItem("Meio-campo");
            positionField.addItem("Lateral");
            positionField.addItem("Zagueiro");
            positionField.addItem("Goleiro");
            positionField.setPreferredSize(new Dimension(300, 10));
            positionField.setFont(new Font("Sans", Font.PLAIN, 20));
            positionField.setSelectedItem(player.getPosition());

            JButton editButton = new JButton(EDIT);
            editButton.setFont(new Font("Sans", Font.BOLD, 20));
            editButton.setPreferredSize(new Dimension(300, 40));
            editButton.setBackground(new Color(0x096B06));
            editButton.setForeground(Color.WHITE);

            JPanel panel = new JPanel(new MigLayout("filly", "[align right][grow]", "[]10[]10[]10[]10[]10[]10[]"));
            panel.add(nameLabel, "alignx left");
            panel.add(nameField, "growx, wrap");

            panel.add(numberLabel, "alignx left");
            panel.add(numberField, "growx, wrap");

            panel.add(positionLabel, "alignx left");
            panel.add(positionField, "growx, wrap");

            panel.add(editButton, "span 2, align center");

            editButton.addActionListener(e -> {
                int id = player.getId();

                boolean registrationSuccess = Auth.edit(
                        id,
                        nameField.getText(),
                        numberField.getText(),
                        positionField.getSelectedItem().toString()
                );

                if (registrationSuccess) {
                    JOptionPane.showMessageDialog(EditPlayerDialog.this, "Editado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(EditPlayerDialog.this, "Editar falhou, tente novamente", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            });

            add(panel);
        }
    }


    public static class DeleteDialog extends JDialog {
        public DeleteDialog(JFrame parent, Player player) {
            super(parent, DELETE, true);
            setSize(600, 300);
            setLocationRelativeTo(parent);

            final GenericLabel confirmLabel = new GenericLabel("Você tem certeza que deseja apagar o jogador?", Font.BOLD);

            JButton deleteButton = new JButton(DELETE);
            deleteButton.setFont(new Font("Sans", Font.BOLD, 20));
            deleteButton.setPreferredSize(new Dimension(300, 40));
            deleteButton.setBackground(new Color(0xBE0606));
            deleteButton.setForeground(Color.white);

            JPanel panel = new JPanel(new MigLayout("fill", "[grow][fill]", "[]10[]"));
            panel.add(confirmLabel, "align center, wrap");
            panel.add(deleteButton, "span 2, align center");

            deleteButton.addActionListener(e -> {
                deletePlayer(player.getId());
                dispose();
            });


            add(panel);
        }
    }

    private static boolean privacyPolicy() {
        JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        int result = JOptionPane.showConfirmDialog(dialog, "Ao utilizar o Uniball, você concorda com os termos de uso e a política de privacidade.", "Política de privacidade", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    public static class GetUserForgetPasswordDialog extends JDialog {
        public GetUserForgetPasswordDialog(JFrame parent) {
            super(parent, FORGOT, true);
            setSize(600, 300);
            setLocationRelativeTo(parent);

            final JLabel userLabel = new JLabel("Digite seu usuário");
            userLabel.setFont(new Font("Sans", Font.BOLD, 20));

            final JTextField userField = new JTextField(30);
            userField.setFont(new Font("Sans", Font.PLAIN, 20));

            JButton sendButton = new JButton(SEND);
            sendButton.setFont(new Font("Sans", Font.BOLD, 20));
            sendButton.setPreferredSize(new Dimension(300, 40));
            sendButton.setBackground(new Color(0x096B06));
            sendButton.setForeground(Color.white);

            JPanel panel = new JPanel(new MigLayout("fill", "[grow][fill]", "[]10[]"));
            panel.add(userLabel, "alignx left, wrap");
            panel.add(userField, "alignx left, growx, wrap");
            panel.add(sendButton, "span 2, align center");

            sendButton.addActionListener(e -> {
                if (Util.checkPlayerExistence(userField.getText())) {
                    dispose();
                    new ForgetPasswordDialog(parent, userField.getText()).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(GetUserForgetPasswordDialog.this, "Usuário não existe", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            });

            add(panel);
        }
    }

    public static class ForgetPasswordDialog extends JDialog {
        public ForgetPasswordDialog(JFrame parent, String user) {
            super(parent, FORGOT, true);
            setSize(750, 500);
            setLocationRelativeTo(parent);

            final JLabel nameLabel = new JLabel( USER+ ": " + user);
            nameLabel.setFont(new Font("Sans", Font.BOLD, 20));

            final GenericLabel questionLabel = new GenericLabel(SECRET_PHRASE + ": " + Util.getSecretPhrase(user), Font.BOLD);

            final GenericLabel answerLabel = new GenericLabel(SECRET_ANSWER, Font.BOLD);
            JTextField answerField = new JTextField(30);
            answerField.setFont(new Font("Sans", Font.PLAIN, 20));
            answerField.setPreferredSize(new Dimension(300, 10));

            JButton sendButton = new JButton(SEND);
            sendButton.setFont(new Font("Sans", Font.BOLD, 20));
            sendButton.setPreferredSize(new Dimension(300, 40));
            sendButton.setBackground(new Color(0x096B06));
            sendButton.setForeground(Color.white);

            JPanel panel = new JPanel(new MigLayout("fill", "[grow][fill]", "[]10[]"));
            panel.add(nameLabel, "alignx left, wrap");
            panel.add(questionLabel, "alignx left, wrap");
            panel.add(answerLabel, "alignx left, wrap");
            panel.add(answerField, "growx, wrap");
            panel.add(sendButton, "span 2, align center");

            sendButton.addActionListener(e -> {
//                    Auth.forgetPassword(nameField.getText());
                if (Auth.checkSecretAnswer(user, answerField.getText().replaceAll(" ", "").toLowerCase())) {
                    dispose();
                    new ResetPasswordDialog(parent, user).setVisible(true);
                    System.out.println(user);
                } else {
                    JOptionPane.showMessageDialog(ForgetPasswordDialog.this, "Resposta incorreta", "Erro", JOptionPane.ERROR_MESSAGE);
                    System.out.println(answerField.getText().trim().toLowerCase());
                }
            });

            add(panel);
        }
    }

    public static class ResetPasswordDialog extends JDialog {
        public ResetPasswordDialog(JFrame parent, String user) {
            super(parent, FORGOT, true);
            setSize(750, 500);
            setLocationRelativeTo(parent);

            final GenericLabel nameLabel = new GenericLabel("Usuário " + user, Font.BOLD);

            final GenericLabel passwordLabel = new GenericLabel(PASSWORD, Font.BOLD);
            final JPasswordField passwordField = new JPasswordField(30);
            passwordField.setFont(new Font("Sans", Font.PLAIN, 20));
            passwordField.setPreferredSize(new Dimension(300, 10));

            final GenericLabel confirmPasswordLabel = new GenericLabel("Confirme sua senha", Font.BOLD);
            final JPasswordField confirmPasswordField = new JPasswordField(30);
            confirmPasswordField.setFont(new Font("Sans", Font.PLAIN, 20));
            confirmPasswordField.setPreferredSize(new Dimension(300, 10));

            JButton sendButton = new JButton("Redefinir senha");
            sendButton.setFont(new Font("Sans", Font.BOLD, 20));
            sendButton.setPreferredSize(new Dimension(300, 40));
            sendButton.setBackground(new Color(0x096B06));
            sendButton.setForeground(Color.white);

            JPanel panel = new JPanel(new MigLayout("fill", "[grow][fill]", "[]10[]"));
            panel.add(nameLabel, "alignx left, wrap");
            panel.add(passwordLabel, "alignx left, wrap");
            panel.add(passwordField, "alignx left, growx, wrap");
            panel.add(confirmPasswordLabel, "alignx left, wrap");
            panel.add(confirmPasswordField, "alignx left, growx, wrap");
            panel.add(sendButton, "span 2, align center");

            sendButton.addActionListener(e -> {
                if (
                        !Arrays.toString(passwordField.getPassword()).equals(Arrays.toString(confirmPasswordField.getPassword()))
                        && Objects.nonNull(passwordField.getPassword())
                        && Objects.nonNull(confirmPasswordField.getPassword())
                ) {
                    JOptionPane.showMessageDialog(ResetPasswordDialog.this, "As senhas não coincidem", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    Auth.forgotPassword(user, Arrays.toString(confirmPasswordField.getPassword()));
                    dispose();
                }
            });

            add(panel);
        }
    }

    public static class newGameDialog extends JDialog {
        public newGameDialog(JFrame parent) {
            super(parent, NEW_GAME, true);
            setSize(750, 500);
            setLocationRelativeTo(parent);
        }
    }
}