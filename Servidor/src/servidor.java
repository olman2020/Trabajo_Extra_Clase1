import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor del chat
 * Acepta conexiones de clientes, crea un hilo para atenderlos y espera la siguiente conexion.
 *
 */

public class servidor {
    /** Lista en la que se guardara toda la conversacion*/
    private DefaultListModel chat = new DefaultListModel();

    /**
     * Instacia esta clase
     *
     */
    public static void main(String[] args){
        new servidor();
    }

    public servidor(){
        try{
            ServerSocket socketServidor = new ServerSocket(5557);
            System.out.println("Servidor en funcion");
            while (true){
                Socket cliente = socketServidor.accept();
                Runnable Clientenuevo = new HiloDelCliente(chat, cliente);
                Thread hilo = new Thread(Clientenuevo);
                hilo.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

