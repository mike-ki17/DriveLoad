
package Vista;


import Controlador.VehiculoController;
import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Controlador.VehiculoController;
import java.awt.Frame;
import javax.swing.JTextField;

public class EditarVehiculoDialog extends JDialog {
    private JTextField txtPlaca;
    private JTextField txtTipo;
    private JTextField txtCapacidad;
    private VehiculoController controller;
    
    public EditarVehiculoDialog(Frame parent, boolean modal, String placa, 
                              String tipo, double capacidad, VehiculoController controller) {
        super(parent, modal);
        this.controller = controller;
        initComponents(placa, tipo, capacidad);
    }
    
    private void initComponents(String placa, String tipo, double capacidad) {
        // Configuración básica del diálogo
        setTitle("Editar Vehículo");
        setSize(400, 300);
        setLayout(new GridLayout(4, 2));
        
        // Componentes
        add(new JLabel("Placa:"));
        txtPlaca = new JTextField(placa);
        txtPlaca.setEditable(false); // La placa no se puede modificar (podría ser la PK)
        add(txtPlaca);
        
        add(new JLabel("Tipo:"));
        txtTipo = new JTextField(tipo);
        add(txtTipo);
        
        add(new JLabel("Capacidad:"));
        txtCapacidad = new JTextField(String.valueOf(capacidad));
        add(txtCapacidad);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarCambios());
        add(btnGuardar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }
    
    private void guardarCambios() {
        try {
            String placa = txtPlaca.getText();
            String tipo = txtTipo.getText();
            double capacidad = Double.parseDouble(txtCapacidad.getText());
            
            if (controller.actualizarVehiculo(placa, tipo, capacidad)) {
                JOptionPane.showMessageDialog(this, "Vehículo actualizado correctamente");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el vehículo", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La capacidad debe ser un número válido", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}