package sockets.server;

import model.Pedido;
import model.Platillo;
import model.Utilitarias;
import sockets.client.Usuario;

import java.io.*;
import java.lang.Thread;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Bryan on 11/16/2016.
 */
public class ThreadServidor extends Thread {
    private Servidor servidor;
    private Socket usuarioSocket;

    private DataOutputStream salidaDato;
    private DataInputStream entradaDato;
    private ObjectInputStream entradaObjeto;
    private ObjectOutputStream salidaObjeto;

    public ThreadServidor(Servidor _servidor, Socket _usuarioSocket) {
        this.servidor = _servidor;
        this.usuarioSocket = _usuarioSocket;
    }

    public void escribirArchivo(String comando) {
        File file = new File("recursos\\Bitacora.txt");
        String buffer;
        buffer=comando+"\n";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(buffer);
            writer.close();
        }
        catch(Exception e) {
            System.out.println("Error al escribir en archivo");
        }
    }

    public void run(){
        try{
            salidaDato = new DataOutputStream(usuarioSocket.getOutputStream());
            entradaDato = new DataInputStream(usuarioSocket.getInputStream());
            entradaObjeto = new ObjectInputStream(usuarioSocket.getInputStream());
            salidaObjeto = new ObjectOutputStream(usuarioSocket.getOutputStream());
        }catch(Exception e){
            e.printStackTrace();
        }
        int opcion;
        while(true){
            try{
                sleep(100);
                opcion= entradaDato.readInt();
                switch(opcion){
                    case 1:
                        salidaDato.writeInt(1);
                        salidaObjeto.writeObject(servidor.platillos);
                        Date fecha = new Date();
                        escribirArchivo("Servidor envia lista de platillos"+" "+fecha.toString());
                        break;
                    case 2:
                        Platillo platilloLeido= (Platillo)entradaObjeto.readObject();
                        servidor.platillos.add(platilloLeido);
                        Utilitarias.agregarProducto(platilloLeido);
                        fecha = new Date();
                        escribirArchivo("Servidor annade nuevo platillo"+" "+fecha.toString());
                       // salidaDato.writeInt(1);
                        //salidaObjeto.writeObject(servidor.platillos);
                        break;
                    case 3:
                        servidor.platillos = (ArrayList<Platillo>) entradaObjeto.readObject();
                        Utilitarias.reconstruirMenuXML(servidor.platillos);
                        fecha = new Date();
                        escribirArchivo("Servidor reconstruye XML"+" "+fecha.toString());
                        break;
                    case 4:
                        sleep(10000);
                        Pedido newPedido = (Pedido) entradaObjeto.readUnshared();
                        servidor.pedidos.agregarPedido(newPedido);
                        fecha = new Date();
                        escribirArchivo("Servidor recibe nuevo Pedido"+" "+fecha.toString());
                        break;
                    case 5:
                        salidaObjeto.writeObject(servidor.usuarios);
                        fecha = new Date();
                        escribirArchivo("Servidor envia lista de usuarios"+" "+fecha.toString());
                        break;
                    case 6:
                        servidor.usuarios = (ArrayList<Usuario>) entradaObjeto.readObject();
                        Utilitarias.reconstruirCuentasXML(servidor.usuarios);
                        fecha=new Date();
                        escribirArchivo("Servidor annade nuevo usuario"+" "+fecha.toString());
                        break;
                    case 7:
                        salidaDato.writeInt(2);
                        salidaObjeto.writeObject(servidor.pedidos);
                        fecha = new Date();
                        escribirArchivo("Servidor envia lista de pedidos actuales"+" "+fecha.toString());
                        break;
                    case 8:
                        ArrayList<Platillo>pedidos = (ArrayList<Platillo>) entradaObjeto.readUnshared();
                        for (Platillo pedido : pedidos) {
                            for (Platillo platillo : servidor.platillos) {
                                if(pedido.getCodigo().equals(platillo.getCodigo())){
                                    platillo.yaPedido=true;
                                }
                            }
                        }
                        break;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

