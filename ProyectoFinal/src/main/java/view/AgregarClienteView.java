package view;

import models.Cliente;
import util.ClienteAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarClienteView extends JFrame {

    private ClienteAPI clienteAPI;
    private JTextField nombreField;
    private Runnable onClienteAdded;

    public AgregarClienteView(ClienteAPI clienteAPI) {
        this.clienteAPI = clienteAPI;
        setTitle("Agregar Cliente");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField(20);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCliente();
            }
        });

        panel.add(nombreLabel);
        panel.add(nombreField);
        panel.add(agregarButton);

        add(panel);
    }

    private void agregarCliente() {
        String nombre = nombreField.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente nuevoCliente = new Cliente(nombre);
        Cliente clienteCreado = clienteAPI.crearCliente(nuevoCliente);

        if (clienteCreado != null) {
            JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Cierra la ventana
            if (onClienteAdded != null) {
                onClienteAdded.run(); // Ejecuta el callback
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setOnClienteAdded(Runnable onClienteAdded) {
        this.onClienteAdded = onClienteAdded;
    }
}
