package sockets.client;

import interfaz.ControladorPrincipalAdministrador;
import interfaz.ControladorPrincipalCliente;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import model.ListaPedidos;
import model.Platillo;

import java.util.ArrayList;

/**
 * Creado por David Valverde Garro - 2016034774
 * Fecha: 17-Nov-16 Tiempo: 3:11 PM
 */
public class ThreadCliente extends Thread {
    private ControladorPrincipalAdministrador controladorAdministrador;
    private ControladorPrincipalCliente controladorCliente;
    private Usuario usuario;


    public ThreadCliente(ControladorPrincipalAdministrador ventana, Usuario _usuario){
        controladorAdministrador = ventana;
        usuario = _usuario;
    }

    public ThreadCliente(ControladorPrincipalCliente ventana, Usuario _usuario){
        controladorCliente = ventana;
        usuario = _usuario;
    }

    public void run(){
        int opcion;

        while(true){
            try{
                sleep(100);
                opcion = usuario.getEntradaDatos().readInt();
                switch(opcion){
                    case 1:
                        ArrayList<Platillo> platillos = (ArrayList<Platillo>) usuario.getEntradaObjetos().readObject();

                        if(controladorCliente != null){
                            Platform.runLater(() -> {
                                controladorCliente.construirTabla(platillos);
                            });
                        }
                        if(controladorAdministrador != null){
                            Platform.runLater(() -> {
                                controladorAdministrador.platillos=platillos;
                                controladorAdministrador.construirTabla(platillos);

                            });
                        }
                        break;
                    case 2:
                        try {
                            ListaPedidos pedidosClientes = (ListaPedidos) usuario.getEntradaObjetos().readObject();
                            Platform.runLater(()->{
                                controladorAdministrador.construirTablaPedidos(pedidosClientes);
                            });
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        controladorAdministrador.platillos= (ArrayList<Platillo>) usuario.getEntradaObjetos().readUnshared();
                        Platform.runLater(()->{
                            controladorAdministrador.controladorConsultaNoPedidos.construirTabla(controladorAdministrador.platillos);

                    });
                        break;
                    case 4:
                        int costoEmpaque = usuario.getEntradaDatos().readInt();
                        int costoExpress = usuario.getEntradaDatos().readInt();
                        controladorCliente.costoEmpaque = costoEmpaque;
                        controladorCliente.costoExpress = costoExpress;
                        break;
                    case 5:
                        controladorAdministrador.platillos= (ArrayList<Platillo>) usuario.getEntradaObjetos().readUnshared();
                        Platform.runLater(()->{
                            controladorAdministrador.controladorTop10.construirTabla(controladorAdministrador.platillos);

                        });
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

