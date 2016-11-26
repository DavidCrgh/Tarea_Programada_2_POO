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
    public ArrayList<Platillo> platillos;//Array con todos los platillos disponibles, se carga cuando se inicializa el servidor.
    public ArrayList<Usuario> usuarios;//ArrayList con los usuarios y contraseñas
    public ListaPedidos pedidos;//array list con los pedidos hechos al servidor
    private int costoExpress;
    private int costoEmpaque;

    public Servidor(int _puerto){
        pedidos = new ListaPedidos();
        this.puerto=_puerto;
        costoExpress=0;
        costoEmpaque=0;
        hiloPeticiones= new ThreadPeticiones(this);
        iniciarServidor();
        try {
            platillos = Utilitarias.cargarMenu();//carga los platillos definidos en el xml
            usuarios = Utilitarias.cargarCuentas();//carga las cuentas definidas en el xml
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void iniciarServidor(){
        hiloPeticiones.start();//pone a correr el hilo que aceta nuevos usuarios al servidor
    }

    public void setCostoExpress(int nuevoCosto){

        costoExpress=nuevoCosto;
    }

    public void setCostoEmpaque(int nuevoCostoEmpaque){
        costoEmpaque=nuevoCostoEmpaque;
    }

    public int getCostoEmpaque(){return costoEmpaque;}

    public int getCostoExpress(){return costoExpress;}
}
