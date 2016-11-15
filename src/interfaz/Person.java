package interfaz;

import javafx.beans.property.SimpleStringProperty;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 10-Nov-16.
 */
public class Person {

    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;
    private final SimpleStringProperty clave;
    private final SimpleStringProperty numeroCelualar;
    private boolean admin;

    public Person(String fName, String lName, String email, String pClave, String pNumeroCelular) {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(email);
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

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String fName) {
        firstName.set(fName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String fName) {
        lastName.set(fName);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String fName) {
        email.set(fName);
    }
}
