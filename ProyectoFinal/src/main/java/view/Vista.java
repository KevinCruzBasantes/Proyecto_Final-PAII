package view;
import controller.Controlador;
import models.ProgressObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Vista extends JFrame implements ProgressObserver {
    private Controlador controlador;
    private JComboBox<String> productoComboBox;
    private JProgressBar progressBar;
    private JLabel progressLabel;

    public Vista(Controlador controlador) {
        this.controlador = controlador;
        this.setTitle("Tienda");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        JButton clienteButton = new JButton("Cliente");
        JButton administradorButton = new JButton("Administrador");

        buttonPanel.add(clienteButton);
        buttonPanel.add(administradorButton);

        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new BorderLayout());
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressLabel = new JLabel("Progreso:");

        progressPanel.add(progressLabel, BorderLayout.NORTH);
        progressPanel.add(progressBar, BorderLayout.CENTER);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(progressPanel, BorderLayout.CENTER);

        this.add(mainPanel);

        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarMenuCliente();
            }
        });

        administradorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarMenuAdministrador();
            }
        });

        this.setVisible(true);
    }

    private void mostrarMenuCliente() {
        JDialog clienteDialog = new JDialog(this, "Cliente", true);
        clienteDialog.setSize(300, 150);
        clienteDialog.setLayout(new FlowLayout());

        productoComboBox = new JComboBox<>(new String[]{"madera", "hierro"});
        JButton comprarButton = new JButton("Comprar");

        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoProducto = (String) productoComboBox.getSelectedItem();
                controlador.realizarPedido(tipoProducto, Vista.this);
                clienteDialog.dispose();
            }
        });

        clienteDialog.add(new JLabel("Seleccione producto:"));
        clienteDialog.add(productoComboBox);
        clienteDialog.add(comprarButton);

        clienteDialog.setVisible(true);
    }

    private void mostrarMenuAdministrador() {
        JOptionPane.showMessageDialog(this, "Administración de pedidos en curso...");
    }

    @Override
    public void updateProgress(String step, int progress) {
        SwingUtilities.invokeLater(() -> {
            progressLabel.setText("Progreso: " + step);
            progressBar.setValue(progress);
        });
    }
}