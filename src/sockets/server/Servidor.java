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
    private ObjectInputStream entradaObjeto;
    private ObjectOutputStream salidaObjeto;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private ThreadPeticiones hiloPeticiones;

    ArrayList<Platillo> platillos;
    ListaPedidos pedidos;

    public Servidor(int _puerto){
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
        pedidos = new ListaPedidos();
    }

    public void iniciarServidor(){
        hiloPeticiones.start();
    }

    public void esucharPeticiones() throws IOException{
        cliente1 = servidor.accept();
        new ThreadServidor(this,cliente1).start();
        System.out.println("Cliente Aceptado");

        cliente2 = servidor.accept();
        new ThreadServidor(this,cliente2).start();
        System.out.println("Cliente Aceptado");
    }
}
