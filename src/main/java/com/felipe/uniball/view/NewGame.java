package com.felipe.uniball.view;

import com.felipe.uniball.Constants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.felipe.uniball.controller.Util;
import com.felipe.uniball.models.Player;

import java.awt.*;
import java.util.*;
import java.util.List;

import static com.felipe.uniball.Constants.*;

public class NewGame extends JFrame {
    private static final String[] requiredPositions = {"Goleiro", "Atacante", "Meio-campo", "Zagueiro", "Lateral"};
    private static final DefaultTableModel leftModel = new DefaultTableModel(new Object[]{"Código", "Nome", "Número da camisa", "Posição"}, 0);
    private final JTable leftTable = new JTable(leftModel);
    private final List<Player> leftTablePlayers = new ArrayList<>();
    private static final DefaultTableModel middleModel = new DefaultTableModel(new Object[]{"Código", "Nome", "Número da camisa", "Posição"}, 0);
    private final JTable middleTable = new JTable(middleModel);
    private final List<Player> middleTablePlayers = new ArrayList<>();
    private static final DefaultTableModel rightModel = new DefaultTableModel(new Object[]{"Código", "Nome", "Número da camisa", "Posição"}, 0);
    private final JTable rightTable = new JTable(rightModel);
    private final List<Player> rightTablePlayers = new ArrayList<>();

    public NewGame() {
        super(Constants.NEW_GAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new MigLayout("fill, insets 0, align center", "[fill]", "[fill]"));

        JPanel mainPanel = new JPanel(new MigLayout("align center, wrap 3", "[grow]", "[grow]"));
        mainPanel.setBackground(GREEN);

        JLabel titleLabel = new JLabel(Constants.NEW_GAME, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Sans", Font.BOLD, 40));
        titleLabel.setForeground(Color.white);

        JPanel contentPanel = new JPanel(new MigLayout("fill, insets 20", "[grow][grow][grow]", "[grow]"));

        JPanel leftPanel = new JPanel(new MigLayout("fill, aligny bottom", "[fill]", "[grow][]"));
        leftPanel.setBackground(Color.WHITE);

        leftTable.setRowHeight(25);
        leftTable.setDefaultEditor(Object.class, null);
        leftTable.setFont(new Font("Sans", Font.PLAIN, 18));
        leftTable.getTableHeader().setReorderingAllowed(false);
        leftTable.getTableHeader().setResizingAllowed(false);
        leftTable.getTableHeader().setFont(new Font("Sans", Font.BOLD, 15));
        leftTable.setFont(new Font("Sans", Font.PLAIN, 20));
        leftTable.setSelectionBackground(GREEN);
        leftTable.setSelectionForeground(Color.WHITE);

        JScrollPane leftScrollPane = new JScrollPane(leftTable);
        leftScrollPane.setMinimumSize(new Dimension(100, 100));

        JButton leftAddButton = new JButton("Adicionar Jogador");
        leftAddButton.addActionListener(e -> {
            final int selectedRow = middleTable.getSelectedRow();

            if (selectedRow != -1) {
                final int id = (int) middleTable.getValueAt(selectedRow, 0);
                final String name = (String) middleTable.getValueAt(selectedRow, 1);
                final int number = (int) middleTable.getValueAt(selectedRow, 2);
                final String position = (String) middleTable.getValueAt(selectedRow, 3);

                leftModel.addRow(new Object[]{id, name, number, position});

                Player p = new Player();
                p.setId(id);
                p.setName(name);
                p.setNumber(number);
                p.setPosition(position);
                leftTablePlayers.add(p);

                middleModel.removeRow(selectedRow);

                middleTablePlayers.remove(p);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um jogador para adicionar ao time A", Constants.ERROR, JOptionPane.ERROR_MESSAGE);
            }
        });
        leftAddButton.setBackground(GREEN);
        leftAddButton.setForeground(Color.WHITE);
        leftAddButton.setFont(new Font("Tahoma", Font.BOLD, 20));

        JButton leftRemoveButton = new JButton("Remover Jogador");
        leftRemoveButton.addActionListener(e -> {
            final int selectedRow = leftTable.getSelectedRow();

            if (selectedRow != -1) {
                final int id = (int) leftTable.getValueAt(selectedRow, 0);
                final String name = (String) leftTable.getValueAt(selectedRow, 1);
                final int number = (int) leftTable.getValueAt(selectedRow, 2);
                final String position = (String) leftTable.getValueAt(selectedRow, 3);

                Player p = new Player();
                p.setId(id);
                p.setName(name);
                p.setNumber(number);
                p.setPosition(position);
                middleTablePlayers.add(p);

                middleModel.addRow(new Object[]{id, name, number, position});

                leftModel.removeRow(selectedRow);

                leftTablePlayers.remove(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um jogador para remover do time A", Constants.ERROR, JOptionPane.ERROR_MESSAGE);
            }
        });
        leftRemoveButton.setBackground(RED);
        leftRemoveButton.setForeground(Color.WHITE);
        leftRemoveButton.setFont(new Font("Tahoma", Font.BOLD, 20));

        leftPanel.setBorder(BorderFactory.createTitledBorder("Time A"));
        leftPanel.add(leftScrollPane, "grow, wrap");
        leftPanel.add(leftAddButton, "wrap");
        leftPanel.add(leftRemoveButton, "");

        JPanel middlePanel = new JPanel(new MigLayout("fill, align center", "[fill]", "[grow]"));
        middlePanel.setBackground(Color.WHITE);

        middleTable.setRowHeight(25);
        middleTable.setDefaultEditor(Object.class, null);
        middleTable.setFont(new Font("Sans", Font.PLAIN, 18));
        middleTable.getTableHeader().setReorderingAllowed(false);
        middleTable.getTableHeader().setResizingAllowed(false);
        middleTable.getTableHeader().setFont(new Font("Sans", Font.BOLD, 15));
        middleTable.setFont(new Font("Sans", Font.PLAIN, 20));
        middleTable.setSelectionBackground(GREEN);
        middleTable.setSelectionForeground(Color.WHITE);

        JScrollPane middleScrollPane = new JScrollPane(middleTable);
        middleScrollPane.setMinimumSize(new Dimension(100, 100));

        middlePanel.setBorder(BorderFactory.createTitledBorder(PLAYERS));
        middlePanel.add(middleScrollPane, "grow");

        JPanel rightPanel = new JPanel(new MigLayout("fill, aligny bottom", "[fill]", "[grow][]"));
        rightPanel.setBackground(Color.WHITE);

        rightTable.setRowHeight(25);
        rightTable.setDefaultEditor(Object.class, null);
        rightTable.setFont(new Font("Sans", Font.PLAIN, 18));
        rightTable.getTableHeader().setReorderingAllowed(false);
        rightTable.getTableHeader().setResizingAllowed(false);
        rightTable.getTableHeader().setFont(new Font("Sans", Font.BOLD, 15));
        rightTable.setFont(new Font("Sans", Font.PLAIN, 20));
        rightTable.setSelectionBackground(GREEN);
        rightTable.setSelectionForeground(Color.WHITE);

        JScrollPane rightScrollPane = new JScrollPane(rightTable);
        rightScrollPane.setMinimumSize(new Dimension(100, 100));

        JButton rightAddButton = new JButton("Adicionar Jogador");
        rightAddButton.addActionListener(e -> {
            final int selectedRow = middleTable.getSelectedRow();

            if (selectedRow != -1) {
                final int id = (int) middleTable.getValueAt(selectedRow, 0);
                final String name = (String) middleTable.getValueAt(selectedRow, 1);
                final int number = (int) middleTable.getValueAt(selectedRow, 2);
                final String position = (String) middleTable.getValueAt(selectedRow, 3);

                rightModel.addRow(new Object[]{id, name, number, position});

                Player p = new Player();
                p.setId(id);
                p.setName(name);
                p.setNumber(number);
                p.setPosition(position);

                rightTablePlayers.add(p);

                middleModel.removeRow(selectedRow);

                middleTablePlayers.remove(p);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um jogador para adicionar ao time B", Constants.ERROR, JOptionPane.ERROR_MESSAGE);
            }
        });
        rightAddButton.setBackground(GREEN);
        rightAddButton.setForeground(Color.WHITE);
        rightAddButton.setFont(new Font("Tahoma", Font.BOLD, 20));

        JButton rightRemoveButton = new JButton("Remover Jogador");
        rightRemoveButton.addActionListener(e -> {
            final int selectedRow = rightTable.getSelectedRow();

            if (selectedRow != -1) {
                final int id = (int) rightTable.getValueAt(selectedRow, 0);
                final String name = (String) rightTable.getValueAt(selectedRow, 1);
                final int number = (int) rightTable.getValueAt(selectedRow, 2);
                final String position = (String) rightTable.getValueAt(selectedRow, 3);

                Player p = new Player();
                p.setId(id);
                p.setName(name);
                p.setNumber(number);
                p.setPosition(position);
                middleTablePlayers.add(p);

                middleModel.addRow(new Object[]{id, name, number, position});

                rightModel.removeRow(selectedRow);

                rightTablePlayers.remove(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um jogador para remover do time B", Constants.ERROR, JOptionPane.ERROR_MESSAGE);
            }
        });
        rightRemoveButton.setBackground(RED);
        rightRemoveButton.setForeground(Color.WHITE);
        rightRemoveButton.setFont(new Font("Tahoma", Font.BOLD, 20));

        rightPanel.setBorder(BorderFactory.createTitledBorder("Time B"));
        rightPanel.add(rightScrollPane, "grow, wrap");
        rightPanel.add(rightAddButton, "wrap");
        rightPanel.add(rightRemoveButton, "");

        JButton returnButton = new JButton(Constants.BACK);
        returnButton.addActionListener(e -> {
            leftModel.setRowCount(0);

            middleModel.setRowCount(0);

            rightModel.setRowCount(0);

            dispose();

            new Menu();
        });
        returnButton.setBackground(RED);
        returnButton.setForeground(Color.WHITE);
        returnButton.setFont(new Font("Tahoma", Font.BOLD, 50));

        JButton startButton = new JButton(Constants.NEW_GAME);
        startButton.addActionListener(e -> {
            if ((leftTablePlayers.size() >= 7 && leftTablePlayers.size() <= 10) && (rightTablePlayers.size() >= 7 && rightTablePlayers.size() <= 10)) {
                if (hasAllPositions(leftTablePlayers) && hasAllPositions(rightTablePlayers)) {
                    setPlayersTeams(leftTablePlayers, 'A');
                    setPlayersTeams(rightTablePlayers, 'B');
                    new Components.NewGameDialog(this, leftTablePlayers, rightTablePlayers).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Cada time deve ter pelo menos um jogador de cada posição", Constants.ERROR, JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cada time deve ter entre 7 e 10 jogadores", Constants.ERROR, JOptionPane.ERROR_MESSAGE);
            }
        });
        startButton.setBackground(Color.white);
        startButton.setForeground(Color.BLACK);
        startButton.setFont(new Font("Tahoma", Font.BOLD, 50));

        contentPanel.add(leftPanel, "grow");
        contentPanel.add(middlePanel, "grow");
        contentPanel.add(rightPanel, "grow");

        mainPanel.add(titleLabel, "grow, span, wrap");
        mainPanel.add(contentPanel, "wrap, grow, span");
        mainPanel.add(returnButton, "split 2, align left");
        mainPanel.add(startButton, "align right");

        updatePlayerTable();

        add(mainPanel);
        pack();
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public boolean hasAllPositions(List<Player> players) {
        Set<String> positions = new HashSet<>();
        for (Player player : players) {
            positions.add(player.getPosition());
        }

        return positions.containsAll(Arrays.asList(requiredPositions));
    }

    private static void setPlayersTeams(List<Player> players, char team) {
        for (Player player : players) {
            player.setTeam(team);
        }
    }

    private static void updatePlayerTable() {
        middleModel.setRowCount(0);

        List<Player> playerList = Util.getPlayers("id_player", "ASC");
        for (Player player : playerList) {
            middleModel.addRow(new Object[]{
                    player.getId(),
                    player.getName(),
                    player.getNumber(),
                    player.getPosition(),
            });
        }
    }
}