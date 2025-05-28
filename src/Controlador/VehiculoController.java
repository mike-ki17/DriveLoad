
package Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Modelo.Vehiculo;
import Modelo.VehiculoDAO;
import Vista.MessageSucefull;
import Vista.RegisterVehiculo;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;





public class VehiculoController implements ActionListener, MouseListener {
    
    RegisterVehiculo registerVehiculo;
    Vehiculo vehiculo;
    private final List<CampoConTexto> camposTexto = new ArrayList<>();
    
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
                
                switch (campo.id){
                    case "placa":
                        vehiculo.setPlaca(campo.textField.getText());
                        break;
                    case "tipo":
                        vehiculo.setTipo(campo.textField.getText());
                        break;
                    case "capacidad":
                        vehiculo.setCapacidad(Double.parseDouble(campo.textField.getText()));
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
                
                message.setVisible(true);
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
    
    public static void main(String[] arg){
         RegisterVehiculo registerVehiculo = new RegisterVehiculo();
         Vehiculo vehiculo = new Vehiculo();

         VehiculoController controlador = new VehiculoController(registerVehiculo, vehiculo);

         registerVehiculo.setVisible(true);
        
    }

    
    
    
    
    
}
