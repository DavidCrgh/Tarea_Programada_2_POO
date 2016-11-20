package sockets.server;

import model.ListaPedidos;
import model.Platillo;
import model.Utilitarias;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Created by Bryan on 11/15/2016.
 */
public class Servidor {
    private ServerSocket servidor;
    private int puerto;
    private Socket cliente1;
    private Socket cliente2;
    private ThreadPeticiones hiloPeticiones;
    ArrayList<Platillo> platillos;
    ListaPedidos pedidos;

    public Servidor(int _puerto){
        pedidos = new ListaPedidos();
        this.puerto=_puerto;
        hiloPeticiones= new ThreadPeticiones(this);
        iniciarServidor();
        try {
            platillos = Utilitarias.cargarMenu();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void iniciarServidor(){
        hiloPeticiones.start();
    }
}
