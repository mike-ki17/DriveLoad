
package Modelo;


public class Vehiculo {
    
    String placa, tipo;
    double capacidad;
        
    public Vehiculo() {}
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }
    
    public String mostrarIfo (String placa, String tipo, double capacidad){
        String mensaje = "Placa: " + placa + " Tipo: " + tipo + " Capacidad: " + capacidad;
        System.out.println(mensaje);
        return mensaje;
    }
    
}
