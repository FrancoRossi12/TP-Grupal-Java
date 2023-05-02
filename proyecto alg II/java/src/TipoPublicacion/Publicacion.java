package TipoPublicacion;

import java.util.ArrayList;

public class Publicacion {
    private int cantMG;
    private String nombre, descPost;
    private ArrayList<String> listaComentario;
    private ArrayList<String> listaHashtag;

    public Publicacion(){
        cantMG=0;
        descPost=" ";
        listaComentario = new ArrayList<String>();
        listaHashtag = new ArrayList<String>();
    }
    //posible(realizar) set_nombrePost
    public void modificaDescPost(String desc){descPost=desc;}
}
