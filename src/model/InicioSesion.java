package model;

import interfaz.Person;

import java.util.ArrayList;

/**
 * Created by Bryan on 11/15/2016.
 */
public class InicioSesion {
    private ArrayList<Person> cuentas;

    public InicioSesion(){

    }

    public Person buscarCuenta(String usuario, String contrasenna){
        int iteraciones = cuentas.size();
        while(iteraciones>=0) {
            Person actual = cuentas.get(iteraciones);
            if (actual.getClave().equals(contrasenna) && actual.getUsuario().equals(usuario))
                return actual;
            iteraciones--;
        }
        return null;
    }
}
