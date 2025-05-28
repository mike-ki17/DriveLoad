package Modelo;

public class Conductor {
    
    private String nombre;
    private String licencia;
    private int experiencia;

    public Conductor() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public String mostrarInfo() {
        String mensaje = "Nombre: " + nombre + " | Licencia: " + licencia + " | Años de experiencia: " + experiencia;
        System.out.println(mensaje);
        return mensaje;
    }
}
