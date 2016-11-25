package sockets.server;

import model.*;
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

    /**
     * Escribe las lineas de comandos realizads por el servidor en la bitacora de funcionamiento
     * @param comando accion realizada por el servidor
     */
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

    /**
     * Recibe peticiones de los clientes y las procsa enviandoles la informacion que solicitan o almacenado
     * los objetos que recibe
     */
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
                        sleep(1000);
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
                        salidaObjeto.reset();
                        salidaObjeto.writeUnshared(servidor.pedidos);
                        fecha = new Date();
                        escribirArchivo("Servidor envia lista de pedidos actuales"+" "+fecha.toString());
                        break;
                    case 8:
                        Cliente clienteEnviado = (Cliente)entradaObjeto.readObject();
                        Pedido pedidoCliente = clienteEnviado.pedidoEnviado;

                        ArrayList<LineaPedido> productosCliente = clienteEnviado.pedidoEnviado.getLineasPedido();
                        ArrayList<Pedido> pedidosTemporal= servidor.pedidos.getPedidos();

                        for(int i=0;i<pedidosTemporal.size();i++) {
                            if (pedidoCliente.getCliente().getNombre().equals(pedidosTemporal.get(i).getCliente().getNombre())) {
                                ArrayList<LineaPedido> lineaTemporal = pedidosTemporal.get(i).getLineasPedido();
                                int verificador =0;
                                if(lineaTemporal.size()==productosCliente.size()){

                                    for(int j=0;j<lineaTemporal.size();j++){

                                        if(productosCliente.get(j).getCantidadPiezas()==lineaTemporal.get(j).getCantidadPiezas() &&
                                                productosCliente.get(j).getPlatillo().getNombre().equals(lineaTemporal.get(j).getPlatillo().getNombre())){

                                            verificador++;

                                        }
                                        else{
                                            verificador=0;
                                        }
                                    }
                                    if(verificador==lineaTemporal.size()){

                                        servidor.pedidos.getPedidos().remove(i);

                                    }
                                }
                            }
                        }
                        break;
                    case 9:
                        ArrayList<Platillo>pedidos = (ArrayList<Platillo>) entradaObjeto.readUnshared();
                        for (Platillo pedido : pedidos) {
                            for (Platillo platillo : servidor.platillos) {
                                if(pedido.getCodigo().equals(platillo.getCodigo())){
                                    platillo.yaPedido=true;
                                }
                            }
                        }
                        break;
                    case 10:
                        salidaDato.writeInt(3);
                        salidaObjeto.reset();
                        salidaObjeto.writeUnshared(servidor.platillos);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

