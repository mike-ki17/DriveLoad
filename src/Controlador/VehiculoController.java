
package Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Modelo.Vehiculo;
import Vista.RegisterVehiculo;


public class VehiculoController implements ActionListener, MouseListener {
    
    RegisterVehiculo registerVehiculo;
    Vehiculo vehiculo;
    
    public VehiculoController (RegisterVehiculo registerVehiculo, Vehiculo vehiculo){
        this.registerVehiculo = registerVehiculo; // Vista
        this.vehiculo = vehiculo;  // Modelo
        this.registerVehiculo.getBtnRegistrar().addActionListener(this);
        this.registerVehiculo.getPlacaText().addMouseListener(this);
        
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
        Object source = e.getSource();
        System.out.println(source);
        if (source == registerVehiculo.getPlacaText() &&
            "Ingrese la placa del vehiculo".equals(registerVehiculo.getPlacaText().getText()) ) {
            registerVehiculo.getPlacaText().setText("");
            System.out.println("Se hizo clic en el label de Placa");
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {
    Object source = e.getSource();
        System.out.println(source);
        if (registerVehiculo.getPlacaText().getText().isEmpty()) {
            registerVehiculo.getPlacaText().setText("Ingrese la placa del vehiculo");
        }}
    
    public static void main(String[] arg){
         RegisterVehiculo registerVehiculo = new RegisterVehiculo();
         Vehiculo vehiculo = new Vehiculo();

         VehiculoController controlador = new VehiculoController(registerVehiculo, vehiculo);

         registerVehiculo.setVisible(true);
        
    }

    
    
    
    
    
}
