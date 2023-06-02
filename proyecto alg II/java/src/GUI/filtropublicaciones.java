package GUI;

import javax.swing.*;
import java.awt.event.*;

public class filtropublicaciones extends JDialog {
    private int filtro = 0;
    private JPanel contentPane;
    private JButton sepiaButton;
    private JButton blancoYNegroButton;
    private JButton clarendonButton;
    private JButton sinFiltroButton;

    public filtropublicaciones () {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(sinFiltroButton);

        sinFiltroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSinFiltroButton();
            }
        });
        sepiaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSepiaButton();
            }
        });
        blancoYNegroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onBlancoYNegroButton();
            }
        });
        clarendonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClarendonButton();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onX();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onX();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }
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
        // add your code here
        dispose();
    }
}
