package com.felipe.uniball.view;

import com.felipe.uniball.Constants;
import com.felipe.uniball.controller.Util;
import com.felipe.uniball.models.Player;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static com.felipe.uniball.Constants.GREEN;
import static com.felipe.uniball.Constants.RED;

public class Players extends JFrame {
    private static DefaultTableModel model;

    public Players() {
        super(Constants.PLAYERS);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new MigLayout("fill, insets 0", "[grow]", "[grow]"));

        JPanel panel = new JPanel(new MigLayout("align center, wrap 4", "[grow]", "[][grow][]"));
        panel.setBackground(GREEN);

        JLabel titleLabel = new JLabel(Constants.PLAYERS);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel, "span, center, gapbottom 15");

        model = new DefaultTableModel(new Object[]{"Código", "Nome", "Número da camisa", "Posição", "Usuário"}, 0);

        JTable table = new JTable(model);
        table.setFont(new Font("Sans", Font.PLAIN, 20));
        table.setSelectionBackground(GREEN);
        table.setSelectionForeground(Color.WHITE);

        JButton returnButton = new JButton(Constants.BACK);
        returnButton.addActionListener(e -> {
            dispose();
            new Menu();
        });
        returnButton.setBackground(RED);
        returnButton.setForeground(Color.WHITE);
        returnButton.setFont(new Font("Tahoma", Font.BOLD, 50));

        JButton newPlayer = new JButton("Novo jogador");
        newPlayer.setBackground(Color.white);
        newPlayer.setForeground(Color.black);
        newPlayer.setFont(new Font("Tahoma", Font.BOLD, 50));
        newPlayer.addActionListener(e -> {
            new Components.RegistrationDialog(this).setVisible(true);
            updatePlayerTable();
        });

        JButton editPlayer = new JButton("Editar jogador");
        editPlayer.setBackground(Color.white);
        editPlayer.setForeground(Color.black);
        editPlayer.setFont(new Font("Tahoma", Font.BOLD, 50));
        editPlayer.addActionListener(e -> {
            final int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Player player = new Player();

                player.setId((int) model.getValueAt(selectedRow, 0));
                player.setName((String) model.getValueAt(selectedRow, 1));
                player.setNumber((int) model.getValueAt(selectedRow, 2));
                player.setPosition((String) model.getValueAt(selectedRow, 3));

                new Components.EditPlayerDialog(this, player).setVisible(true);

                updatePlayerTable();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um jogador para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton deletePlayer = new JButton("Excluir jogador");
        deletePlayer.setBackground(Color.white);
        deletePlayer.setForeground(Color.black);
        deletePlayer.setFont(new Font("Tahoma", Font.BOLD, 50));
        deletePlayer.addActionListener(e -> {
            final int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Player player = new Player();
                player.setId((int) model.getValueAt(selectedRow, 0));

                new Components.DeleteDialog(this, player).setVisible(true);

                updatePlayerTable();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um jogador para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        updatePlayerTable();

        table.getSelectionModel().addListSelectionListener(e -> table.repaint());
        table.setAutoCreateRowSorter(true);
        table.setRowHeight(30);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setFont(new Font("Sans", Font.BOLD, 20));

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, "wrap, align center, grow");
        panel.add(returnButton, "split 4, align left, grow");
        panel.add(newPlayer, "align right, grow");
        panel.add(editPlayer, "align center, grow");
        panel.add(deletePlayer, "align right, grow");

        add(panel, "align center, grow");
        pack();
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private static void updatePlayerTable() {
        model.setRowCount(0);

        List<Player> playerList = Util.getPlayers("id_player", "ASC");
        for (Player player : playerList) {
            model.addRow(new Object[]{
                    player.getId(),
                    player.getName(),
                    player.getNumber(),
                    player.getPosition(),
                    player.getUser()
            });
        }
    }
}