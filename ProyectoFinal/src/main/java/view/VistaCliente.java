package view;

import controller.Controlador;
import models.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class VistaCliente extends JFrame {
    private Controlador controlador;
    private DefaultListModel<Producto> listModel;
    private JList<Producto> pedidosList;
    private JPanel statusPanel;
    private Map<Producto, JLabel> statusLabels;

    public VistaCliente(Controlador controlador) {
        this.controlador = controlador;
        this.setTitle("Tienda - Cliente");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        pedidosList = new JList<>(listModel);
        pedidosList.setVisibleRowCount(4); // Hacer la JList más pequeña
        JScrollPane scrollPane = new JScrollPane(pedidosList);

        JPanel inputPanel = new JPanel();
        JComboBox<String> productoComboBox = new JComboBox<>(new String[]{"silla", "mesa"});
        JComboBox<String> materialComboBox = new JComboBox<>(new String[]{"madera", "hierro"});
        JSpinner cantidadSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JButton comprarButton = new JButton("Comprar");

        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoProducto = (String) productoComboBox.getSelectedItem();
                String material = (String) materialComboBox.getSelectedItem();
                int cantidad = (int) cantidadSpinner.getValue();
                controlador.realizarPedido(tipoProducto, material, cantidad);
            }
        });

        inputPanel.add(new JLabel("Producto:"));
        inputPanel.add(productoComboBox);
        inputPanel.add(new JLabel("Material:"));
        inputPanel.add(materialComboBox);
        inputPanel.add(new JLabel("Cantidad:"));
        inputPanel.add(cantidadSpinner);
        inputPanel.add(comprarButton);

        statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusLabels = new HashMap<>();

        this.add(inputPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(statusPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public void agregarPedido(Producto producto) {
        listModel.addElement(producto);
        JLabel statusLabel = new JLabel("Pedido de " + producto.getNombre() + " en espera...");
        statusPanel.add(statusLabel);
        statusLabels.put(producto, statusLabel);
        this.revalidate();
    }

    public void notificarPedido(Producto producto, boolean exito) {
        String mensaje = exito ? "Producto creado con éxito: " : "No se pudo crear el producto: ";
        JLabel statusLabel = statusLabels.get(producto);
        if (statusLabel != null) {
            statusLabel.setText(mensaje + producto.getNombre());
        }
    }
}
