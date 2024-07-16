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
<<<<<<< HEAD
    private JPanel mainPanel;
=======
>>>>>>> efbea1abb96d44a26c0c2397f33c2eab3ee04358

    public Vista(Controlador controlador) {
        this.controlador = controlador;
        this.setTitle("Tienda");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
<<<<<<< HEAD
        this.setSize(400, 300);
        this.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());

        JPanel homePanel = new JPanel();
        JButton clienteButton = new JButton("Cliente");
        JButton administradorButton = new JButton("Administrador");

        homePanel.add(clienteButton);
        homePanel.add(administradorButton);

        mainPanel.add(homePanel, "home");

        JPanel clientePanel = new JPanel();
        clientePanel.setLayout(new FlowLayout());
        productoComboBox = new JComboBox<>(new String[]{"madera", "hierro"});
        JButton comprarButton = new JButton("Comprar");

        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoProducto = (String) productoComboBox.getSelectedItem();
                controlador.realizarPedido(tipoProducto, Vista.this);
            }
        });

        clientePanel.add(new JLabel("Seleccione producto:"));
        clientePanel.add(productoComboBox);
        clientePanel.add(comprarButton);

        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new BorderLayout());
        progressLabel = new JLabel("Progreso:");
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressPanel.add(progressLabel, BorderLayout.NORTH);
        progressPanel.add(progressBar, BorderLayout.CENTER);

        clientePanel.add(progressPanel);

        mainPanel.add(clientePanel, "cliente");

        administradorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Vista.this, "Administración de pedidos en curso...");
            }
        });
=======
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
>>>>>>> efbea1abb96d44a26c0c2397f33c2eab3ee04358

        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "cliente");
            }
        });

        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

=======
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

>>>>>>> efbea1abb96d44a26c0c2397f33c2eab3ee04358
    @Override
    public void updateProgress(String step, int progress) {
        SwingUtilities.invokeLater(() -> {
            progressLabel.setText("Progreso: " + step);
            progressBar.setValue(progress);
        });
    }
}