package Controlador;

import Modelo.Conductor;
import Modelo.ConductorDAO;
import Modelo.VehiculoDAO;
import Vista.MessageError;
import Vista.MessageSucefull;
import Vista.RegisterConductores;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JTextField;

public class ConductorController implements ActionListener, MouseListener {

    RegisterConductores registerConductor;
    Conductor conductor;
    private final List<CampoConTexto> camposTexto = new ArrayList<>();
    
    private ConductorDAO conductorDAO;
    private static final Logger logger = Logger.getLogger(VehiculoController.class.getName());
    
    public ConductorController() {
        this.conductorDAO = new ConductorDAO();
    }

    public ConductorController(RegisterConductores registerConductor, Conductor conductor) {
        this.registerConductor = registerConductor;
        this.conductor = conductor;

        this.registerConductor.getBtnRegistrarConductor().addActionListener(this);

        camposTexto.add(new CampoConTexto(registerConductor.getNombreText(), "Ingrese el nombre del conductor", "nombre"));
        camposTexto.add(new CampoConTexto(registerConductor.getLicenciaText(), "Ingrese tipo de licencia", "licencia"));
        camposTexto.add(new CampoConTexto(registerConductor.getExperienciaText(), "Ingrese los años de experiencia", "experiencia"));

        for (CampoConTexto campo : camposTexto) {
            campo.textField.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent e) {
                    if (campo.textField.getText().equals(campo.textoPorDefecto)) {
                        campo.textField.setText("");
                        campo.textField.setForeground(Color.BLACK);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("ENVIAR".equals(e.getActionCommand())) {
            for (CampoConTexto campo : camposTexto) {
                switch (campo.id) {
                    case "nombre":
                        String nombre = campo.textField.getText().trim();
                        if (nombre.isEmpty() || nombre.equals(campo.textoPorDefecto)) {
                            registerConductor.getNombreErrorLabel().setText("Campo requerido");
                            return;
                        } else {
                            registerConductor.getNombreErrorLabel().setText("");
                            conductor.setNombre(nombre);
                        }
                        break;

                    case "licencia":
                        String licencia = campo.textField.getText().trim();
                        if (licencia.isEmpty() || licencia.equals(campo.textoPorDefecto)) {
                            registerConductor.getLicenciaErrorLabel().setText("Campo requerido");
                            return;
                        } else {
                            registerConductor.getLicenciaErrorLabel().setText("");
                            conductor.setLicencia(licencia);
                        }
                        break;

                    case "experiencia":
                        String experienciaStr = campo.textField.getText().trim();
                        if (experienciaStr.isEmpty() || experienciaStr.equals(campo.textoPorDefecto)) {
                            registerConductor.getExperienciaErrorLabel().setText("Campo requerido");
                            return;
                        }
                        try {
                            int experiencia = Integer.parseInt(experienciaStr);
                            if (experiencia < 0) {
                                registerConductor.getExperienciaErrorLabel().setText("Debe ser un número positivo.");
                                return;
                            } else {
                                registerConductor.getExperienciaErrorLabel().setText("");
                                conductor.setExperiencia(experiencia);
                            }
                        } catch (NumberFormatException ex) {
                            registerConductor.getExperienciaErrorLabel().setText("Ingrese un número válido.");
                            return;
                        }
                        break;
                }
            }

            ConductorDAO dao = new ConductorDAO();
            boolean response = dao.registrarConductor(conductor);

            if (response) {
                MessageSucefull message = new MessageSucefull();
                message.getRegisterExitosoPlacaLabel().setText("Nombre: " + conductor.getNombre());
                message.getRegisterExitosoTipoLabel().setText("Licencia: " + conductor.getLicencia());
                message.getRegisterExitosoCapacidadLabel().setText("Experiencia: " + conductor.getExperiencia() + " años");
                message.getMessageLabel().setText("Conductor registrado exitosamente");

                message.setVisible(true);
            } else {
                MessageError error = new MessageError();
                error.setVisible(true);
            }
        }
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        RegisterConductores registerConductor = new RegisterConductores();
        Conductor conductor = new Conductor();
        ConductorController controller = new ConductorController(registerConductor, conductor);
        registerConductor.setVisible(true);
    }
}
