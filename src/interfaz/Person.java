package interfaz;

import javafx.beans.property.SimpleStringProperty;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 10-Nov-16.
 */
public class Person {
    private SimpleStringProperty usuario;
    private final SimpleStringProperty clave;
    private final SimpleStringProperty numeroCelualar;
    private boolean admin;

    public Person(String fName, String pClave, String pNumeroCelular) {
        this.usuario = new SimpleStringProperty(fName);
        this.clave = new SimpleStringProperty(pClave);
        this.numeroCelualar = new SimpleStringProperty(pNumeroCelular);
        this.admin = false;
    }

    public void setAdmin(boolean _admin){
        this.admin = _admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getClave() {
        return clave.get();
    }

    public void setClave(String clave) {
        this.clave.set(clave);
    }

    public String getNumeroCelualar() {
        return numeroCelualar.get();
    }

    public void setNumeroCelualar(String numeroCelualar) {
        this.numeroCelualar.set(numeroCelualar);
    }

    public String getUsuario() {
        return usuario.get();
    }
}
