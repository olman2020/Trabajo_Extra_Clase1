import javax.swing.*;
import java.awt.*;
import java.net.Socket;

public class ChatDelCliente {
    /** Socket con el servidor del chat */
    private Socket socket;

    /** Panel con la ventana del cliente */
    private PanelCliente panel;

    /**
     * Arranca el Cliente de chat
     */

    JFrame w = new JFrame();

    private String name;

    public static void main(String[] args){
        new ChatDelCliente();
    }
    /**
     * Crea la ventana, establece la conexion e instancia al controlador
     */
    public ChatDelCliente(){
        try{
            creacionVisualizacionVentana();


            String t = (String)JOptionPane.showInputDialog(
                    w,
                    "Ingrese los datos de conexion:",
                    "IP Destinatario",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    null);


            if ((t == null)){
                w.dispose();
                return;
            }else{
                socket = new Socket(t, 5557);
                w.setEnabled(true);
            }


            /**socket = new Socket(t, 5557);*/


            String s = (String)JOptionPane.showInputDialog(
                    w,
                    "Por favor ingrese su nombre",
                    "Chat Cliente",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    null);
            if ((s == null)) {
                w.dispose();
                return;
            }else{
                this. name = s;
                w.setEnabled(true);
            }

            ControlCliente control = new ControlCliente(socket, panel,name);


        }catch (Exception e){
            JOptionPane.showMessageDialog(w,
                    "No se pudo conectar con el servidor.",
                    "Conexion error",
                    JOptionPane.ERROR_MESSAGE);
            w.dispose();
            e.printStackTrace();
        }
    }

    /**
     * Crea una ventana, introduce el panel para el cliente y la visualizacion
     */

    private void creacionVisualizacionVentana(){

        w.setTitle("Cliente");
        panel = new PanelCliente(w.getContentPane());
        w.pack();
        w.setVisible(true);
        w.setEnabled(false);
        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}

