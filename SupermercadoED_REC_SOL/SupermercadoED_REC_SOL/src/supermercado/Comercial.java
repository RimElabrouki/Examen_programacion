package supermercado;

import java.util.ArrayList;

/**
 *
 * @author luis
 */
public class Comercial extends Empleado {

    private ArrayList<String> provicias = new ArrayList<String>();
    private int aniosExperiencia;
    private String telefonoContacto;
    private String disponibilidad;
    private Empleado jefe;

    public Comercial() {
        super();
    }

    public Comercial(Comercial c) {
        this.identificador = c.getIdentificador();
        this.nombre = c.getNombre();
        this.direccion = c.getDireccion();
        this.telefono = c.getTelefono();
        this.email = c.getEmail();
        this.aniosExperiencia = c.getAniosExperiencia();
        this.telefonoContacto = c.getTelefonoContacto();
        this.disponibilidad = c.getDisponibilidad();
        this.jefe = c.getJefe();
    }

    public Comercial(Empleado e, int aniosExperiencia, String telefonoContacto, String disponibilidad, Empleado jefe) {
        super(e);
        this.aniosExperiencia = aniosExperiencia;
        this.telefonoContacto = telefonoContacto;
        this.disponibilidad = disponibilidad;
        this.jefe = jefe;
    }
    
     public Comercial(int identificador, String nombre, String direccion, String telefono, String email, int aniosExperiencia, String telefonoContacto, String disponibilidad, Empleado jefe) {
        super(identificador, nombre, direccion, telefono, email);
        this.aniosExperiencia = aniosExperiencia;
        this.telefonoContacto = telefonoContacto;
        this.disponibilidad = disponibilidad;
        this.jefe = jefe;
    }

    public ArrayList<String> getProvicias() {
        return provicias;
    }

    public void setProvicias(ArrayList<String> provicias) {
        this.provicias = provicias;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Empleado getJefe() {
        return jefe;
    }

    public void setJefe(Empleado jefe) {
        this.jefe = jefe;
    }

}
