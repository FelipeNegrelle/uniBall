package com.felipe.uniball.view;

import com.felipe.uniball.Constants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.felipe.uniball.controller.Util;
import com.felipe.uniball.models.Player;

import java.awt.*;
import java.util.List;

import static com.felipe.uniball.Constants.PLAYERS;

public class NewGame extends JFrame {
    private static final DefaultTableModel leftModel = new DefaultTableModel(new Object[]{"Nome", "Número", "Posição"}, 0);
    JTable leftTable = new JTable(leftModel);
    List<Player> leftTablePlayers = null;

    private static final DefaultTableModel middleModel = new DefaultTableModel(new Object[]{"Nome", "Número", "Posição"}, 0);
    JTable middleTable = new JTable(middleModel);

    private static final DefaultTableModel rightModel = new DefaultTableModel(new Object[]{"Nome", "Número", "Posição"}, 0);
    JTable rightTable = new JTable(rightModel);
    List<Player> rightTablePlayers = null;

    public NewGame() {
        super(Constants.NEW_GAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new MigLayout("fill, insets 0, align center", "[fill]", "[fill]"));

        JPanel mainPanel = new JPanel(new MigLayout("align center, wrap 3", "[grow]", "[grow]"));
        mainPanel.setBackground(new Color(0x096B06));

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

        JScrollPane leftScrollPane = new JScrollPane(leftTable);
        leftScrollPane.setMinimumSize(new Dimension(100, 100));

        JButton leftAddButton = new JButton("Adicionar Jogador");
        leftAddButton.addActionListener(e -> {
            final int selectedRow = middleTable.getSelectedRow();

            if (selectedRow != -1) {
                final String name = (String) middleTable.getValueAt(selectedRow, 0);
                final int number = (int) middleTable.getValueAt(selectedRow, 1);
                final String position = (String) middleTable.getValueAt(selectedRow, 2);

                leftModel.addRow(new Object[]{name, number, position});

                Player p = new Player();
                p.setName(name);
                p.setNumber(number);
                p.setPosition(position);
                leftTablePlayers.add(p);

                middleModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um jogador para adicionar ao time A", Constants.ERROR, JOptionPane.ERROR_MESSAGE);
            }
        });
        leftAddButton.setBackground(Color.white);
        leftAddButton.setForeground(Color.BLACK);
        leftAddButton.setFont(new Font("Tahoma", Font.BOLD, 20));

        JButton leftRemoveButton = new JButton("Remover Jogador");
        leftRemoveButton.addActionListener(e -> {
            final int selectedRow = leftTable.getSelectedRow();

            if (selectedRow != -1) {
                final String name = (String) leftTable.getValueAt(selectedRow, 0);
                final int number = (int) leftTable.getValueAt(selectedRow, 1);
                final String position = (String) leftTable.getValueAt(selectedRow, 2);

                middleModel.addRow(new Object[]{name, number, position});

                leftModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um jogador para remover do time A", Constants.ERROR, JOptionPane.ERROR_MESSAGE);
            }
        });
        leftRemoveButton.setBackground(Color.white);
        leftRemoveButton.setForeground(Color.BLACK);
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

        JScrollPane rightScrollPane = new JScrollPane(rightTable);
        rightScrollPane.setMinimumSize(new Dimension(100, 100));

        JButton rightAddButton = new JButton("Adicionar Jogador");
        rightAddButton.addActionListener(e -> {
            final int selectedRow = middleTable.getSelectedRow();

            if (selectedRow != -1) {
                final String name = (String) middleTable.getValueAt(selectedRow, 0);
                final int number = (int) middleTable.getValueAt(selectedRow, 1);
                final String position = (String) middleTable.getValueAt(selectedRow, 2);

                rightModel.addRow(new Object[]{name, number, position});

                Player p = new Player();

                p.setName(name);
                p.setNumber(number);
                p.setPosition(position);
                rightTablePlayers.add(p);

                middleModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um jogador para adicionar ao time B", Constants.ERROR, JOptionPane.ERROR_MESSAGE);
            }
        });
        rightAddButton.setBackground(Color.white);
        rightAddButton.setForeground(Color.BLACK);
        rightAddButton.setFont(new Font("Tahoma", Font.BOLD, 20));

        JButton rightRemoveButton = new JButton("Remover Jogador");
        rightRemoveButton.addActionListener(e -> {
            final int selectedRow = rightTable.getSelectedRow();

            if (selectedRow != -1) {
                final String name = (String) rightTable.getValueAt(selectedRow, 0);
                final int number = (int) rightTable.getValueAt(selectedRow, 1);
                final String position = (String) rightTable.getValueAt(selectedRow, 2);

                middleModel.addRow(new Object[]{name, number, position});

                rightModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um jogador para remover do time B", Constants.ERROR, JOptionPane.ERROR_MESSAGE);
            }
        });
        rightRemoveButton.setBackground(Color.white);
        rightRemoveButton.setForeground(Color.BLACK);
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
        returnButton.setBackground(new Color(0xBE0606));
        returnButton.setForeground(Color.WHITE);
        returnButton.setFont(new Font("Tahoma", Font.BOLD, 50));

        JButton startButton = new JButton(Constants.NEW_GAME);
        startButton.addActionListener(e -> {
            new Components.newGameDialog(this).setVisible(true);
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

        updatePlayers(middleModel, Util.getPlayers());

        add(mainPanel);
        setLocationRelativeTo(this);
        pack();
        setVisible(true);
    }

    private static void updatePlayers(DefaultTableModel model, List<Player> playerList) {
        model.setRowCount(0);

        for (Player player : playerList) {
            model.addRow(new Object[]{player.getName(), player.getNumber(), player.getPosition()});
        }
    }
}