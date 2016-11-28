package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 15-Nov-16.
 * Lista de todos los pedidos realizados, lo utiliza el servidor para
 * mantener la informacion de los pedidos
 */
public class ListaPedidos implements Serializable{
    private int cantidad;
    private ArrayList<Pedido> pedidos;
    public float arregloRelaciones[];
    public String[] arregloTipos={"En Sitio","Recoger","Express"};
    public ArrayList<String> arregloPorcentajes;

    public float[] getArregloRelaciones() {
        return arregloRelaciones;
    }

    public String[] getArregloTipos() {
        return arregloTipos;
    }

    public ListaPedidos() {
        pedidos = new ArrayList<>();
        arregloPorcentajes= new ArrayList<>();

    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void agregarPedido(Pedido pedido){
        pedidos.add(pedido);
        cantidad++;
        System.out.println("true");
    }

    public ListaPedidos obtenerTop10(){
        return new ListaPedidos();
    }

    public ListaPedidos obtenerNuncaPedidos(){
        return new ListaPedidos();
    }

    public void calcularRelacionRES(){
        float[] arreglo = new float[3];
        int enSitio = totalPedidos(tipoPedido.ENSITIO);
        System.out.println("En sitio es: "+enSitio);
        int recoger = totalPedidos(tipoPedido.RECOGER);
        System.out.println("Recoger es: "+recoger);
        int express = totalPedidos(tipoPedido.EXPRESS);
        System.out.println("Express es: "+express);

        if(pedidos.size()!=0) {
            arreglo[0] = ((float)enSitio / (float)pedidos.size()) * 100;
            arreglo[1] = ((float)recoger / (float)pedidos.size()) * 100;
            arreglo[2] = ((float)express / (float)pedidos.size()) * 100;
        }

        arregloRelaciones=arreglo;
    }

    public ArrayList<Pedido> getPedidos(){
        return pedidos;
    }

    public int totalPedidos(tipoPedido pedido){

        int sumatoria=0;
        System.out.println(pedido);
        for(int i=0;i<pedidos.size();i++){
            System.out.println(pedidos.get(i).tipo);
            if(pedidos.get(i).tipo.equals(pedido))
                sumatoria++;
        }
        return sumatoria;

    }

    public void imprimirInfo(){

        for(int i=0;i<3;i++){

            System.out.println(arregloTipos[i]+"  "+arregloRelaciones[i]);

        }

    }

    public void popularArrayList() {

        for (int i = 0; i < arregloRelaciones.length; i++) {
            String temporal = arregloTipos[i]+"\t\t\t\t"+ arregloRelaciones[i]+"%";
            arregloPorcentajes.add(temporal);
        }
    }

}
