package GUI;

import javax.swing.*;
import java.awt.event.*;

/**
 * The type Filtropublicaciones.
 */
public class filtropublicaciones extends JDialog {
    private int filtro = 0;
    private JPanel contentPane;
    private JButton sepiaButton;
    private JButton blancoYNegroButton;
    private JButton clarendonButton;
    private JButton sinFiltroButton;

    /**
     * Instantiates a new Filtropublicaciones.
     */
    public filtropublicaciones () {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(sinFiltroButton);

        sinFiltroButton.addActionListener(e -> onSinFiltroButton());
        sepiaButton.addActionListener(e -> onSepiaButton());
        blancoYNegroButton.addActionListener(e -> onBlancoYNegroButton());
        clarendonButton.addActionListener(e -> onClarendonButton());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onX();
            }
        });

        contentPane.registerKeyboardAction(e -> onX(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    /**
     * Get filtroaplicado int.
     *
     * @return the int
     */
    public int getFiltroaplicado(){
        return filtro;
    }
    private void onSinFiltroButton() {

        filtro = 0;
        dispose();
    }
    private void onSepiaButton() {
        filtro = 3;
        dispose();
    }
    private void onClarendonButton() {
        filtro = 2;
        dispose();
    }
    private void onBlancoYNegroButton() {
        filtro = 1;
        dispose();
    }
    private void onX() {
        dispose();
    }
}
