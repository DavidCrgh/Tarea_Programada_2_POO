package model;

import sockets.client.Usuario;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Bryan on 11/15/2016.
 */
public class InicioSesion {
    public ArrayList<Usuario> cuentas;
    private Socket cliente;

    Utilitarias objetoUtilitario = new Utilitarias();

    public InicioSesion(){
        cuentas = new ArrayList<>();

        try {
            cuentas =objetoUtilitario.cargarCuentas();
            }
         catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Usuario buscarCuenta(String usuario, String contrasenna){
        int iteraciones = cuentas.size()-1;
        while(iteraciones>=0) {
            Usuario actual = cuentas.get(iteraciones);
            if (actual.getClave().equals(contrasenna) && actual.getUsuario().equals(usuario))
                return actual;
            iteraciones--;
        }
        return null;
    }
    public Socket abrirConexion() {
        try {
            cliente = new Socket("localhost", 8080);

        }
        catch (IOException e) {System.out.println(e);}

        return cliente;
    }


}
