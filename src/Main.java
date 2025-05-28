
import Controlador.VehiculoController;
import Modelo.VehiculoDAO;
import Vista.VistaIniciar;
import Vista.listarVehiculos;


//import Modelo.VistaVehiculos;




public class Main {
      public static void main(String[] args) {
        // Solo para probar la conexión y llamar al modelo
        Modelo.VehiculoDAO dao = new  Modelo.VehiculoDAO();
        VistaIniciar init = new VistaIniciar();
        init.setVisible(true);
        
//        dao.obtenerUsuarios();
        
//        VistaVehiculos listaVehiculos new VistaVehiculos();
//        
//        listaVehiculos.
//       listarVehiculos view = new listarVehiculos();
//       VehiculoDAO dao = new VehiculoDAO();
//       VehiculoController controller = new VehiculoController(view, dao);
//
//       controller.cargarVehiculos();
//       view.setVisible(true);
    }
}
