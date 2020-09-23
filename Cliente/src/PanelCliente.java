import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel para mostrar la conversaci�n y pedir al usuario el texto que quiere
 * enviar.
 */

public class PanelCliente {

    /**Scroll*/
    private JScrollPane scroll;

    /** Area para mostrar la conversacion*/
    private JTextArea textArea;

    /**Para pedir texto al usuario*/
    private JTextField textField;

    /** Boton para enviar el texto*/
    private JButton boton;

    /**
     * Crea el panel con todos sus componentes. Un Area de texto para ver la
     * conversacion, un textField para escribir el texto que queremos enviar y
     * un boton de enviar.
     *
     *            Contenedor en el que añadir todos los componentes
     */

    public PanelCliente(Container contenedor){
        contenedor.setLayout(new BorderLayout());
        textArea = new JTextArea();
        scroll = new JScrollPane(textArea);
        JPanel panel = new JPanel(new FlowLayout());
        textField = new JTextField(50);
        boton = new JButton("Enviar");
        panel.add(textField);
        panel.add(boton);

        contenedor.add(scroll, BorderLayout.CENTER);
        scroll.setPreferredSize(new Dimension(500,500));
        contenedor.add(panel, BorderLayout.SOUTH);
    }

    /** Añade el actionListener que se le pasa tanto a pulsar <intro> en el
     * textField como al boton.
     */

    public void addActionListener(ActionListener accion){
        textField.addActionListener(accion);
        boton.addActionListener(accion);
    }

    /**
     * Añade el texto que se le pasa al textArea.
     */

    public void addTexto(String texto){
        textArea.append(texto);
    }

    /**
     * Devuelve el texto que hay en el textfield y lo borra.
     */

    public String getTexto(){
        String texto = textField.getText();
        textField.setText("");
        return texto;
    }


}

