package com.felipe.uniball.view;

import com.felipe.uniball.Constants;
import com.felipe.uniball.controller.Util;
import com.felipe.uniball.models.Player;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Players extends JFrame {
    private static DefaultTableModel model;

    public Players() {
        super(Constants.PLAYERS);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new MigLayout("fill, insets 0", "[grow]", "[grow]"));

        JPanel panel = new JPanel(new MigLayout("align center, wrap 4", "[grow]", "[][grow][]"));
//        panel.setPreferredSize(new Dimension(1080, 920));
        panel.setBackground(new Color(0x096B06));

        JLabel titleLabel = new JLabel(Constants.PLAYERS);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel, "span, center, gapbottom 15");

        model = new DefaultTableModel(new Object[]{"Código", "Nome", "Número", "Posição", "Usuário"}, 0);

        JTable table = new JTable(model);
        table.setFont(new Font("Sans", Font.PLAIN, 20));
        table.setSelectionBackground(new Color(0xE5096B06, true));
        table.setSelectionForeground(Color.WHITE);

        JButton returnButton = new JButton(Constants.BACK);
        returnButton.addActionListener(e -> {
            dispose();
            new Menu();
        });
//        returnButton.setPreferredSize(new Dimension(400, 150));
        returnButton.setBackground(new Color(0xBE0606));
        returnButton.setForeground(Color.WHITE);
        returnButton.setFont(new Font("Tahoma", Font.BOLD, 50));

        JButton newPlayer = new JButton("Novo jogador");
//        newPlayer.setPreferredSize(new Dimension(400, 150));
        newPlayer.setBackground(Color.white);
        newPlayer.setForeground(Color.black);
        newPlayer.setFont(new Font("Tahoma", Font.BOLD, 50));
        newPlayer.addActionListener(e -> {
            new Components.RegistrationDialog(this, false).setVisible(true);
            updatePlayerTable();
        });

        JButton editPlayer = new JButton("Editar jogador");
//        editPlayer.setPreferredSize(new Dimension(400, 150));
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
//        deletePlayer.setPreferredSize(new Dimension(400, 150));
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
        table.setRowHeight(30);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setFont(new Font("Sans", Font.BOLD, 20));

        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setPreferredSize(new Dimension(1080, 920));
        panel.add(scrollPane, "span, align center, grow");
        panel.add(returnButton, "split 4, align left");
        panel.add(newPlayer, "align right");
        panel.add(editPlayer, "align center");
        panel.add(deletePlayer, "align right");

        add(panel, "align center, grow");
//        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private static void updatePlayerTable() {
        model.setRowCount(0);

        List<Player> playerList = Util.getPlayers();
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