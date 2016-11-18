package sockets.client;

import interfaz.ControladorInicio;
import interfaz.ControladorPrincipalAdministrador;
import interfaz.ControladorPrincipalCliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Creado por David Valverde Garro - 2016034774
 * Fecha: 17-Nov-16 Tiempo: 3:11 PM
 */
public class ThreadCliente extends Thread {
    private ControladorPrincipalAdministrador controladorAdministrador;
    private ControladorPrincipalCliente controladorCliente;
    private DataInputStream entrada;
    private ObjectInputStream entradaObjeto;

    public ThreadCliente(ControladorPrincipalAdministrador ventana, DataInputStream _entrada, ObjectInputStream _entradaObjeto){
        controladorAdministrador = ventana;
        entrada = _entrada;
        entradaObjeto = _entradaObjeto;
    }

    public ThreadCliente(ControladorPrincipalCliente ventana, DataInputStream _entrada, ObjectInputStream _entradaObjeto){
        controladorCliente = ventana;
        entrada = _entrada;
        entradaObjeto = _entradaObjeto;
    }

    public void run(){




    }
}

