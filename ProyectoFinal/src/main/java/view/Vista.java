package view;

import controller.Controlador;
import models.Producto;
import models.ProgressObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vista extends JFrame implements ProgressObserver {
    private Controlador controlador;
    private JComboBox<String> tipoProductoComboBox;
    private JComboBox<String> nombreProductoComboBox;
    private JProgressBar progressBar;
    private JLabel progressLabel;
    private JPanel mainPanel;
    private JTextArea carritoTextArea;
    private JLabel stockLabel;
    private JTextField nombreClienteField;

    public Vista(Controlador controlador) {
        this.controlador = controlador;
        this.setTitle("Tienda");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
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
        clientePanel.setLayout(new BorderLayout());

        JPanel productoPanel = new JPanel();
        tipoProductoComboBox = new JComboBox<>(new String[]{"madera", "hierro"});
        nombreProductoComboBox = new JComboBox<>();
        stockLabel = new JLabel();

        tipoProductoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProductos();
            }
        });

        actualizarProductos();

        JButton agregarCarritoButton = new JButton("Agregar al Carrito");

        agregarCarritoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoProducto = (String) tipoProductoComboBox.getSelectedItem();
                String nombreProducto = (String) nombreProductoComboBox.getSelectedItem();
                controlador.agregarAlCarrito(tipoProducto, nombreProducto);
                actualizarCarrito();
                actualizarStock();
            }
        });

        productoPanel.add(new JLabel("Tipo de Producto:"));
        productoPanel.add(tipoProductoComboBox);
        productoPanel.add(new JLabel("Nombre del Producto:"));
        productoPanel.add(nombreProductoComboBox);
        productoPanel.add(stockLabel);
        productoPanel.add(agregarCarritoButton);

        clientePanel.add(productoPanel, BorderLayout.NORTH);

        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new BorderLayout());
        progressLabel = new JLabel("Progreso:");
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressPanel.add(progressLabel, BorderLayout.NORTH);
        progressPanel.add(progressBar, BorderLayout.CENTER);

        clientePanel.add(progressPanel, BorderLayout.CENTER);

        carritoTextArea = new JTextArea(5, 30);
        carritoTextArea.setEditable(false);
        JScrollPane carritoScrollPane = new JScrollPane(carritoTextArea);
        clientePanel.add(carritoScrollPane, BorderLayout.SOUTH);

        JButton mostrarCarritoButton = new JButton("Mostrar Carrito");
        mostrarCarritoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCarrito();
            }
        });

        JButton comprarButton = new JButton("Comprar");
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.realizarPedido(Vista.this);
            }
        });

        JPanel clienteFormPanel = new JPanel();
        nombreClienteField = new JTextField(20);
        JButton agregarClienteButton = new JButton("Agregar Cliente");

        agregarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreCliente = nombreClienteField.getText();
                controlador.agregarCliente(nombreCliente);
            }
        });

        clienteFormPanel.add(new JLabel("Nombre del Cliente:"));
        clienteFormPanel.add(nombreClienteField);
        clienteFormPanel.add(agregarClienteButton);

        clientePanel.add(clienteFormPanel, BorderLayout.SOUTH);

        clientePanel.add(mostrarCarritoButton, BorderLayout.EAST);
        clientePanel.add(comprarButton, BorderLayout.WEST);

        mainPanel.add(clientePanel, "cliente");

        administradorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Vista.this, "Administración de pedidos en curso...");
            }
        });

        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "cliente");
            }
        });

        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void actualizarProductos() {
        String tipoProducto = (String) tipoProductoComboBox.getSelectedItem();
        nombreProductoComboBox.removeAllItems();
        if (tipoProducto.equals("madera")) {
            nombreProductoComboBox.addItem("Silla de Madera");
            nombreProductoComboBox.addItem("Mesa de Madera");
            nombreProductoComboBox.addItem("Estantería de Madera");
            nombreProductoComboBox.addItem("Armario Madera");
        } else if (tipoProducto.equals("hierro")) {
            nombreProductoComboBox.addItem("Silla de Hierro");
            nombreProductoComboBox.addItem("Mesa de Hierro");
            nombreProductoComboBox.addItem("Estantería de Hierro");
        }
        actualizarStock();
    }

    private void actualizarStock() {
        String tipoProducto = (String) tipoProductoComboBox.getSelectedItem();
        String nombreProducto = (String) nombreProductoComboBox.getSelectedItem();
        if (nombreProducto != null) {
            int stock = controlador.obtenerStock(tipoProducto, nombreProducto);
            stockLabel.setText("Stock: " + stock);
        } else {
            stockLabel.setText("");
        }
    }

    private void actualizarCarrito() {
        StringBuilder carritoBuilder = new StringBuilder();
        for (Producto producto : controlador.getCarrito()) {
            carritoBuilder.append(producto.getNombre()).append("\n");
        }
        carritoTextArea.setText(carritoBuilder.toString());
    }

    @Override
    public void updateProgress(String step, int progress) {
        SwingUtilities.invokeLater(() -> {
            progressLabel.setText(step);
            progressBar.setValue(progress);
        });
    }
}