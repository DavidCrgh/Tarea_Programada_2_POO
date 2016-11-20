package sockets.server;

import java.io.IOException;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 15-Nov-16.
 */
public class MainServidor {
    public static void main(String[] args) throws IOException {
        Servidor server = new Servidor(6564);
        while(true){}
    }
}
