
package Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Modelo.Vehiculo;
import Modelo.VehiculoDAO;
import Vista.MessageError;
import Vista.MessageSucefull;
import Vista.RegisterVehiculo;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;





public class VehiculoController implements ActionListener, MouseListener {
    
    RegisterVehiculo registerVehiculo;
    Vehiculo vehiculo;
    private final List<CampoConTexto> camposTexto = new ArrayList<>();
    private VehiculoDAO vehiculoDAO;
    
    public VehiculoController() {
        this.vehiculoDAO = new VehiculoDAO();
    }
    
    
    public VehiculoController (RegisterVehiculo registerVehiculo, Vehiculo vehiculo){
        this.registerVehiculo = registerVehiculo; // Vista
        this.vehiculo = vehiculo;  // Modelo
        this.registerVehiculo.getBtnRegistrar().addActionListener(this);
        
        camposTexto.add(new CampoConTexto(registerVehiculo.getPlacaText(), "Ingrese la placa del vehiculo", "placa"));
        camposTexto.add(new CampoConTexto(registerVehiculo.getTipoText(), "Ingrese el tipo de vehiculo", "tipo"));
        camposTexto.add(new CampoConTexto(registerVehiculo.getCapacidadText(), "Ingrese la capacidad del vehiculo", "capacidad"));
        
        

    
        for (CampoConTexto campo : camposTexto) {
            campo.textField.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent e) {
                   
                    if (campo.textField.getText().equals(campo.textoPorDefecto)) {
                        campo.textField.setText("");
                        campo.textField.setForeground(Color.black);
                    }
                }
                @Override
                public void focusLost(java.awt.event.FocusEvent e) {
                    if (campo.textField.getText().isEmpty()) {
                        campo.textField.setText(campo.textoPorDefecto);
                        campo.textField.setForeground(Color.GRAY);
                    }
                }
            });
            campo.textField.addMouseListener(this);
        }
    }
    
    
    private static class CampoConTexto {
        JTextField textField;
        String textoPorDefecto;
        String id;

        CampoConTexto(JTextField textField, String textoPorDefecto, String id) {
            this.textField = textField;
            this.textoPorDefecto = textoPorDefecto;
            this.id = id;
            
        }
    }
    
    
    public void actionPerformed(ActionEvent e) {
         if ("ENVIAR".equals(e.getActionCommand())){
            
             
            for (CampoConTexto campo : camposTexto) {
                
                switch (campo.id) {
                    case "placa":
                        String placa = campo.textField.getText().trim();
                        if (placa.length() == 0 || placa.equals(campo.textoPorDefecto)) {
                            registerVehiculo.getPlacaErrorLabel().setText("Campo requerido");
                            return;
                        }
                        if (placa.length() != 6) {
                            registerVehiculo.getPlacaErrorLabel().setText("La placa debe tener exactamente 6 caracteres.");
                            return;
                        } else {
                            registerVehiculo.getPlacaErrorLabel().setText("");
                            vehiculo.setPlaca(placa);
                        }
                        break;

                    case "tipo":
                        String tipo = campo.textField.getText().trim();
                        System.out.println(tipo);
                        if (tipo.length() == 0 || tipo.equals(campo.textoPorDefecto)) {
                            registerVehiculo.getTipoErrorLabel().setText("Campo requerido");
                            return;
                        } else {
                            registerVehiculo.getTipoErrorLabel().setText("");
                            vehiculo.setTipo(tipo);
                        }
                        break;

                    case "capacidad":
                        String capacidadStr = campo.textField.getText().trim();
                        if (capacidadStr.length() == 0 || capacidadStr.equals(campo.textoPorDefecto)) {
                            registerVehiculo.getCapacidadErrorLabel().setText("Campo requerido");
                            return;
                        }
                        try {
                            double capacidad = Double.parseDouble(capacidadStr);
                            if (capacidad <= 0) {
                                registerVehiculo.getCapacidadErrorLabel().setText("La capacidad debe ser mayor a cero.");
                                return;
                            } else {
                                registerVehiculo.getCapacidadErrorLabel().setText("");
                                vehiculo.setCapacidad(capacidad);
                            }
                        } catch (NumberFormatException ex) {
                            registerVehiculo.getCapacidadErrorLabel().setText("Ingrese un número válido.");
                            return;
                        }
                        break;
                }
            }
             System.out.println("Placa registrada: " + vehiculo.getPlaca());
             System.out.println("Tipo resgistrado: " + vehiculo.getTipo());
             System.out.println("Capacidad registrada: " + vehiculo.getCapacidad());
             
            VehiculoDAO v = new VehiculoDAO();
            boolean response = v.registrarVehiculo(vehiculo);
            
            if (response){
                MessageSucefull message = new MessageSucefull();
                message.getRegisterExitosoPlacaLabel().setText("Placa: " + vehiculo.getPlaca());
                message.getRegisterExitosoTipoLabel().setText("Tipo: " + vehiculo.getTipo());
                message.getRegisterExitosoCapacidadLabel().setText("Capacidad: " + String.valueOf(vehiculo.getCapacidad()));
                
                message.getMessageLabel().setText("Vehiculo registrado");
                message.setVisible(true);
            }
            else{
                MessageError error = new MessageError();
                error.setVisible(true);
            }
            
        }
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    
    
    
    public void cargarVehiculosEnTabla(DefaultTableModel modelo) {
        // Limpiar la tabla antes de cargar nuevos datos
        modelo.setRowCount(0);
        
        // Llamar al método del DAO para obtener los vehículos
        vehiculoDAO.obtenerVehiculosTabla(modelo);
    }
    
    public void eliminarVehiculo (String placa) {
        System.out.println("Ohhhh vas a eliminar: " + placa);
    }
    
    
    
    public static void main(String[] arg){
         RegisterVehiculo registerVehiculo = new RegisterVehiculo();
         Vehiculo vehiculo = new Vehiculo();

         VehiculoController controlador = new VehiculoController(registerVehiculo, vehiculo);

         registerVehiculo.setVisible(true);
        
    }

    
    
    
    
    
}
