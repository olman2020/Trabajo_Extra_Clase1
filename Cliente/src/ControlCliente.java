import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Cliente que atiende el socket y las peticines de usuario.
 * Lo que llega por el socket lo muestra en el textArea del panel, lo que
 * escribe el ususario en el panel lo envia por el socket
 */

public class ControlCliente implements ActionListener, Runnable {
    /** Para la lectura de datos del socket*/
    private DataInputStream dataInput;

    /** Para escritura de datos en el socket*/
    private DataOutputStream dataOutput;

    /** Panel con los controles para el usuario*/
    private PanelCliente panel;

    private String Name;

    /**
     * Contruye una instancia de esta clase, lanzando un hilo para atender al
     * socket.
     */

    public ControlCliente(Socket socket, PanelCliente panel, String name){
        this.panel = panel;
        this.Name = name;
        try{
            dataInput = new DataInputStream(socket.getInputStream());
            dataOutput = new DataOutputStream(socket.getOutputStream());
            panel.addActionListener(this);
            Thread hilo = new Thread(this);
            hilo.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Recoge el texto del panel y lo envia por el socket.
     * El panel llamara a este metodo cuando el usuario escriba algo y
     * pulse el boton de "enviar" o pulse "enter" sobre el textfield.
     */

    public void actionPerformed(ActionEvent evento){
        try{
            dataOutput.writeUTF(this.Name+" : "+panel.getTexto());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo run para antender al socket. Todo lo que llega por el socket se
     * escribe en el panel.
     */


    public void run() {
        try {
            while (true){
                String texto = dataInput.readUTF();
                panel.addTexto(texto);
                panel.addTexto("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

