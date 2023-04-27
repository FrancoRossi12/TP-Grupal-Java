package TipoPublicacion;

import java.util.ArrayList;

public class Publicacion {

    private int Id_publicacion,cantMG;
    private String nombre, fechasubida;

    private  ArrayList<String> listaComentario;
    private ArrayList<String> listaHashtag;

    public Publicacion(){

        listaComentario = new ArrayList<String>();
        listaHashtag = new ArrayList<String>();
    }

}
