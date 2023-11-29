package com.felipe.uniball.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

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
        public RegistrationDialog(JFrame parent) {
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
            registerButton.setBackground(GREEN);
            registerButton.setForeground(Color.white);
            panel.add(registerButton, "span 2, align center");

            registerButton.addActionListener(e -> {
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
            editButton.setBackground(GREEN);
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
            deleteButton.setBackground(RED);
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
            sendButton.setBackground(GREEN);
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

            final JLabel nameLabel = new JLabel(USER + ": " + user);
            nameLabel.setFont(new Font("Sans", Font.BOLD, 20));

            final GenericLabel questionLabel = new GenericLabel(SECRET_PHRASE + ": " + Util.getSecretPhrase(user), Font.BOLD);

            final GenericLabel answerLabel = new GenericLabel(SECRET_ANSWER, Font.BOLD);
            JTextField answerField = new JTextField(30);
            answerField.setFont(new Font("Sans", Font.PLAIN, 20));
            answerField.setPreferredSize(new Dimension(300, 10));

            JButton sendButton = new JButton(SEND);
            sendButton.setFont(new Font("Sans", Font.BOLD, 20));
            sendButton.setPreferredSize(new Dimension(300, 40));
            sendButton.setBackground(GREEN);
            sendButton.setForeground(Color.white);

            JPanel panel = new JPanel(new MigLayout("fill", "[grow][fill]", "[]10[]"));
            panel.add(nameLabel, "alignx left, wrap");
            panel.add(questionLabel, "alignx left, wrap");
            panel.add(answerLabel, "alignx left, wrap");
            panel.add(answerField, "growx, wrap");
            panel.add(sendButton, "span 2, align center");

            sendButton.addActionListener(e -> {
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
            sendButton.setBackground(GREEN);
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

    public static class NewGameDialog extends JDialog {
        public NewGameDialog(JFrame parent, List<Player> teamAPlayers, List<Player> teamBPlayers) {
            super(parent, NEW_GAME, true);
            setSize(1080, 920);
            setLocationRelativeTo(parent);
            setLayout(new MigLayout("fill, insets 20", "[grow][grow]", ""));

            Font font = new Font("Sans", Font.PLAIN, 20);

            JTextField teamAScoreField = new JTextField("0", 5);
            JTextField teamBScoreField = new JTextField("0", 5);

            final List<Player> players = new ArrayList<>();
            players.addAll(teamAPlayers);
            players.addAll(teamBPlayers);

            JPanel leftPanel = new JPanel(new MigLayout("insets 0, fill", "[grow]", "[grow]"));

            DefaultTableModel playersTableModel = new DefaultTableModel(new Object[]{"Nome", "Pontuação", "Time"}, 0);

            JTable playersTable = new JTable(playersTableModel);
            playersTable.getSelectionModel().addListSelectionListener(e -> playersTable.repaint());
            playersTable.setAutoCreateRowSorter(true);
            playersTable.setRowHeight(30);
            playersTable.setDefaultEditor(Object.class, null);
            playersTable.getTableHeader().setReorderingAllowed(false);
            playersTable.getTableHeader().setFont(font);
            playersTable.setSelectionBackground(GREEN);
            playersTable.setSelectionForeground(Color.WHITE);
            playersTable.setFont(font);

            JScrollPane tableScrollPane = new JScrollPane(playersTable);
            tableScrollPane.setPreferredSize(new Dimension(700, 600));

            Util.updatePlayers(playersTableModel, players);

            leftPanel.add(tableScrollPane, "grow");

            add(leftPanel, "grow");

            JPanel rightPanel = new JPanel(new MigLayout("insets 10, fill", "[grow]", "[][]"));

            JPanel scorePanel = new JPanel(new MigLayout("fill", "[grow]10[][grow]", "[grow]"));

            JTextField scoreField = new JTextField("0", 5);
            scoreField.setFont(font);
            scoreField.setHorizontalAlignment(JTextField.CENTER);
            scoreField.setEditable(false);

            JButton decreaseButton = new JButton("-");
            decreaseButton.setFont(font);
            decreaseButton.addActionListener(e -> {
                int currentScore = Integer.parseInt(scoreField.getText());
                if (currentScore > 0) {
                    scoreField.setText(String.valueOf(currentScore - 1));
                }
            });
            decreaseButton.setBackground(RED);
            decreaseButton.setForeground(Color.WHITE);
            decreaseButton.setFont(new Font("Tahoma", Font.BOLD, 20));

            JButton increaseButton = new JButton("+");
            increaseButton.addActionListener(e -> {
                int currentScore = Integer.parseInt(scoreField.getText());
                scoreField.setText(String.valueOf(currentScore + 1));
            });
            increaseButton.setBackground(GREEN);
            increaseButton.setForeground(Color.WHITE);
            increaseButton.setFont(new Font("Tahoma", Font.BOLD, 20));

            JButton saveButton = new JButton("Salvar");
            saveButton.addActionListener(e -> {
                final int selectedRow = playersTable.getSelectedRow();

                if (selectedRow != -1) {
                    Player player = players.get(selectedRow);
                    player.setScore(Integer.parseInt(scoreField.getText()));

                    playersTableModel.setValueAt(player.getName(), selectedRow, 0);
                    playersTableModel.setValueAt(player.getScore(), selectedRow, 1);
                    playersTableModel.setValueAt(player.getTeam(), selectedRow, 2);

                    int teamAScore = 0;
                    int teamBScore = 0;

                    for (Player p : players) {
                        if (p.getTeam() == 'A') {
                            teamAScore += p.getScore();
                        } else {
                            teamBScore += p.getScore();
                        }
                    }

                    teamAScoreField.setText(String.valueOf(teamAScore));
                    teamBScoreField.setText(String.valueOf(teamBScore));
                } else {
                    JOptionPane.showMessageDialog(this, "Selecione um jogador para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            });

            saveButton.setBackground(Color.WHITE);
            saveButton.setForeground(Color.BLACK);
            saveButton.setFont(new Font("Tahoma", Font.BOLD, 20));

            JLabel scoreLabel = new JLabel("Gols");
            scoreLabel.setFont(font);

            scorePanel.add(scoreLabel);

            scorePanel.add(decreaseButton, "growy");

            scorePanel.add(scoreField, "grow");

            scorePanel.add(increaseButton, "growy");

            scorePanel.add(saveButton, "span, grow");

            rightPanel.add(scorePanel, "span 2, align center, wrap, gapy 10");

            JPanel resultPanel = new JPanel(new MigLayout("fill", "[grow]5[][grow]", "[]"));

            JLabel teamALabel = new JLabel("Time A");
            teamALabel.setFont(font);
            teamALabel.setHorizontalAlignment(JLabel.CENTER);

            teamAScoreField.setFont(font);
            teamAScoreField.setHorizontalAlignment(JTextField.CENTER);
            teamAScoreField.setEditable(false);

            JLabel xLabel = new JLabel("X");
            xLabel.setFont(font);
            xLabel.setHorizontalAlignment(JLabel.CENTER);

            teamBScoreField.setFont(font);
            teamBScoreField.setHorizontalAlignment(JTextField.CENTER);
            teamBScoreField.setEditable(false);

            JLabel teamBLabel = new JLabel("Time B");
            teamBLabel.setFont(font);
            teamBLabel.setHorizontalAlignment(JLabel.CENTER);

            resultPanel.add(teamALabel, "split 3");

            resultPanel.add(teamAScoreField);

            resultPanel.add(xLabel);

            resultPanel.add(teamBScoreField);

            resultPanel.add(teamBLabel, "wrap");

            rightPanel.add(resultPanel, "span 2, wrap");

            JLabel bestPlayerLabel = new JLabel("Melhor jogador:");
            bestPlayerLabel.setFont(font);

            JComboBox<String> bestPlayerComboBox = new JComboBox<>(Util.getPlayersWithScore(players));
            bestPlayerComboBox.setFont(font);
            bestPlayerComboBox.setSelectedItem(null);
            bestPlayerComboBox.setPreferredSize(new Dimension(300, 10));
            bestPlayerComboBox.setFont(new Font("Sans", Font.PLAIN, 20));

            rightPanel.add(bestPlayerLabel);

            rightPanel.add(bestPlayerComboBox, "span 2, wrap");

            JLabel beautifulScoreLabel = new JLabel("Gol mais bonito:");
            beautifulScoreLabel.setFont(font);

            JComboBox<String> beautifulScoreComboBox = new JComboBox<>(Util.getPlayersWithScore(players));
            beautifulScoreComboBox.setFont(font);
            beautifulScoreComboBox.setSelectedItem(null);
            beautifulScoreComboBox.setPreferredSize(new Dimension(300, 10));
            beautifulScoreComboBox.setFont(new Font("Sans", Font.PLAIN, 20));

            rightPanel.add(beautifulScoreLabel);

            rightPanel.add(beautifulScoreComboBox, "span 2, wrap");

            JPanel dateTimePanel = new JPanel(new MigLayout("fill", "[grow]", "[]"));

            JLabel dateTimeLabel = new JLabel("Data/Hora:");
            dateTimeLabel.setFont(font);

            JTextField dateTimeField = new JTextField();
            dateTimeField.setFont(font);
            dateTimeField.setEditable(false);
            dateTimeField.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
            dateTimeField.setPreferredSize(new Dimension(220, 10));

            dateTimePanel.add(dateTimeLabel);

            dateTimePanel.add(dateTimeField);

            rightPanel.add(dateTimePanel, "span 2, wrap, align left");

            JButton newMatchButton = new JButton(NEW_GAME);
            newMatchButton.addActionListener(e -> {
                try {
                    Integer.parseInt(teamAScoreField.getText());

                    Integer.parseInt(teamBScoreField.getText());

                    if (Objects.nonNull(bestPlayerComboBox.getSelectedItem())) {
                        String selectedBestPlayer = (String) bestPlayerComboBox.getSelectedItem();

                        String selectedBeautifulScorePlayer = (String) beautifulScoreComboBox.getSelectedItem();

                        Player bestPlayer = players.stream().filter(player -> player.getName().equals(selectedBestPlayer)).findFirst().orElse(null);

                        Player beautifulScorePlayer = players.stream().filter(player -> player.getName().equals(selectedBeautifulScorePlayer)).findFirst().orElse(null);

                        if (bestPlayer != null) {
                            bestPlayer.setBestPlayer();
                        } else {
                            JOptionPane.showMessageDialog(this, "O melhor jogador selecionado não foi encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        int totalScore = 0;

                        for (Player player : players) {
                            totalScore += player.getScore();
                        }

                        if (beautifulScorePlayer != null && totalScore > 0 && beautifulScorePlayer.getScore() > 0) {
                            beautifulScorePlayer.setBeautifulScore();
                        } else if (totalScore > 0) {
                            JOptionPane.showMessageDialog(this, "Um jogador ou uma partida sem gols não pode ter o gol mais bonito.", "Aviso", JOptionPane.WARNING_MESSAGE);
                            beautifulScoreComboBox.setSelectedItem(null);
                            return;
                        } else {
                            JOptionPane.showMessageDialog(this, "Um jogo ou jogador sem gols não pode ter o gol mais bonito", "Aviso", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        if (Integer.parseInt(teamAScoreField.getText()) + Integer.parseInt(teamBScoreField.getText()) == totalScore) {
                            ResultSet rs = Util.saveMatch(dateTimeField.getText(), teamAScoreField.getText(), teamBScoreField.getText(), beautifulScorePlayer != null ? beautifulScorePlayer.getId() : 0, bestPlayer.getId());

                            if (rs.next()) {
                                int idMatch = rs.getInt("id_match");

                                for (Player player : players) {
                                    int id = player.getId();

                                    if (id != 0) {
                                        try {
                                            Util.relateMatchPlayer(idMatch, id, player.getScore());

                                            Util.updateScores(id, idMatch);
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                            System.out.println("Erro com a partida.");
                                            return;
                                        }
                                    } else {
                                        System.out.println("Não foi possível relacionar o jogador " + player.getName() + " com a partida.");
                                        return;
                                    }
                                }

                                Util.updateBeautifulScoreBestPlayer(idMatch);
                            } else {
                                JOptionPane.showMessageDialog(this, "A soma dos gols dos times não é igual ao total de gols.", "Aviso", JOptionPane.WARNING_MESSAGE);
                                return;
                            }

                            JOptionPane.showMessageDialog(this, "Partida salva com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(this, "Erro ao salvar partida.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Selecione o melhor jogador ou o gol mais bonito.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Insira um número válido para o resultado da partida.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            });
            newMatchButton.setBackground(GREEN);
            newMatchButton.setForeground(Color.WHITE);
            newMatchButton.setFont(new Font("Tahoma", Font.BOLD, 20));

            rightPanel.add(newMatchButton, "span, align center");

            add(rightPanel, "grow");
        }
    }
}