import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class HiloDelCliente implements Runnable, ListDataListener {

    /** Lista en la que se guarda toda la charla */
    private DefaultListModel chat;

    /** Socket al que esta conectado el cliente */
    private Socket socket;

    /** Para lectura de datos en el socket */
    private DataInputStream dataInput;

    /** Para escritura de datos en el socket */
    private DataOutputStream dataOutput;

    public HiloDelCliente(DefaultListModel chat, Socket socket){
        System.out.println("Nuevo cliente conectado");
        this.chat = chat;
        this.socket = socket;
        try{
            dataInput = new DataInputStream(socket.getInputStream());
            dataOutput = new DataOutputStream(socket.getOutputStream());
            chat.addListDataListener(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Atiende el socket. Todo lo que llega lo introduce en el chat
     */
    public void run(){
        try{
            while(true){
                String texto = dataInput.readUTF();
                synchronized (chat){
                    chat.addElement(texto);
                    System.out.println(texto);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Envia el ultimo texto de la charla por el socket.
     * Se avisa a este metodo cada vez que se mete algo en charla, incluido
     * cuando lo mete este mismo hilo. De esta manera, lo que un cliente escriba,
     * se le reenviara para que se muestre en el Area del texto
     */

    @Override
    public void intervalAdded(ListDataEvent e) {
        String texto = (String) chat.getElementAt(e.getIndex0());
        try{
            dataOutput.writeUTF(texto);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void intervalRemoved(ListDataEvent e) {

    }

    @Override
    public void contentsChanged(ListDataEvent e) {

    }
}
