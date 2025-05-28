
package Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Modelo.Vehiculo;
import Vista.RegisterVehiculo;
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
        
        camposTexto.add(new CampoConTexto(registerVehiculo.getTipoText(), "Ingrese el tipo de vehiculo"));
        camposTexto.add(new CampoConTexto(registerVehiculo.getPlacaText(), "Ingrese la placa del vehiculo"));
        camposTexto.add(new CampoConTexto(registerVehiculo.getCapacidadText(), "Ingrese la capacidad del vehiculo"));



//        for (CampoConTexto campo : camposTexto) {
//            System.out.println(campo);
//            System.out.println(campo.textoPorDefecto);
//            campo.textField.addMouseListener(this);
//        }
    
    for (CampoConTexto campo : camposTexto) {
            campo.textField.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent e) {
                    if (campo.textField.getText().equals(campo.textoPorDefecto)) {
                        campo.textField.setText("");
                    }
                }
                @Override
                public void focusLost(java.awt.event.FocusEvent e) {
                    if (campo.textField.getText().isEmpty()) {
                        campo.textField.setText(campo.textoPorDefecto);
                    }
                }
            });
        }
    }
    
    
    private static class CampoConTexto {
        JTextField textField;
        String textoPorDefecto;

        CampoConTexto(JTextField textField, String textoPorDefecto) {
            this.textField = textField;
            this.textoPorDefecto = textoPorDefecto;
        }
    }
    
    
    public void actionPerformed(ActionEvent e) {
         if ("ENVIAR".equals(e.getActionCommand())){
//             String placa = registerVehiculo.getPlacaText().getText();
//             vehiculo.setPlaca(placa);
            
             System.out.println("Placa registrada: " + vehiculo.getPlaca());
         }
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
//        Object source = e.getSource();
//        System.out.println(source);
//        if (source == registerVehiculo.getPlacaText() &&
//            "Ingrese la placa del vehiculo".equals(registerVehiculo.getPlacaText().getText()) ) {
//            registerVehiculo.getPlacaText().setText("");
//            System.out.println("Se hizo clic en el label de Placa");
//     
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {
//        for (CampoConTexto campo : camposTexto) {
//                if (e.getSource() == campo.textField && campo.textField.getText().equals(campo.textoPorDefecto)) {
//                    campo.textField.setText("");
//                }
//            }
    }
    @Override
    public void mouseExited(MouseEvent e) {
//        Object source = e.getSource();
//        System.out.println(source);
//        if (registerVehiculo.getPlacaText().getText().isEmpty()) {
//            registerVehiculo.getPlacaText().setText("Ingrese la placa del vehiculo");
//        }
//    for (CampoConTexto campo : camposTexto) {
//            if (source == campo.textField && campo.textField.getText().isEmpty()) {
//                campo.textField.setText(campo.textoPorDefecto);
//            }
//        }
 }
    
    public static void main(String[] arg){
         RegisterVehiculo registerVehiculo = new RegisterVehiculo();
         Vehiculo vehiculo = new Vehiculo();

         VehiculoController controlador = new VehiculoController(registerVehiculo, vehiculo);

         registerVehiculo.setVisible(true);
        
    }

    
    
    
    
    
}
