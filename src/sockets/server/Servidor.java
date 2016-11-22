package sockets.server;

import model.ListaPedidos;
import model.Platillo;
import model.Utilitarias;
import org.xml.sax.SAXException;
import sockets.client.Usuario;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Created by Bryan on 11/15/2016.
 */
public class Servidor {
    private int puerto;
    private ThreadPeticiones hiloPeticiones;
    ArrayList<Platillo> platillos;
    ArrayList<Usuario> usuarios;
    ListaPedidos pedidos;

    public Servidor(int _puerto){
        pedidos = new ListaPedidos();
        this.puerto=_puerto;
        hiloPeticiones= new ThreadPeticiones(this);
        iniciarServidor();
        try {
            platillos = Utilitarias.cargarMenu();
            usuarios = Utilitarias.cargarCuentas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void iniciarServidor(){
        hiloPeticiones.start();
    }
}
