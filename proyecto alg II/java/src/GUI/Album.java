package GUI;

import javax.swing.*;
import java.awt.event.*;

public class Album extends JDialog {
    private JPanel contentPane;
    private JButton agregar;
    private JButton eliminar;

    public Album() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(agregar);

        agregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onPublicaciones();
            }
        });

        eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAlbum();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onX();
            }
        });

        // call on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onX();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onX(){
        dispose();
    }
    private void onPublicaciones() {
        // add your code here

    }

    private void onAlbum() {
        // add your code here if necessary
    }
}
