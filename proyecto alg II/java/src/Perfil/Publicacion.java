package Perfil;

import java.util.ArrayList;

public class Publicacion {

    private int Id_publicacion,cantMG;
    String nombre, fechasubida;

    ArrayList<String> listaComentario;
    ArrayList<String> listaHashtag;

    Publicacion(){

        listaComentario = new ArrayList<String>();
        listaHashtag = new ArrayList<String>();
    }

}
