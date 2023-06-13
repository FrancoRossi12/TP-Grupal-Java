package TipoPublicacion;

import java.util.ArrayList;
import java.util.List;

public class Publicacion {
    private int cantMG;
    private String nombre, descPost;
    private ArrayList<String> listaComentario;
    private ArrayList<String> listaHashtag;


    public Publicacion(String nombre, String descripcion, int cantMG,ArrayList<String> listaHashtag,ArrayList<String> listaComentario){
        this.nombre = nombre;
        this.descPost = descripcion;
        this.cantMG = cantMG;
        this.listaComentario = listaComentario;
        this.listaHashtag = listaHashtag;
    }
    //posible(realizar) set_nombrePost
    public void modificaDescPost(String desc){descPost=desc;}

    public String getNombre() {
        return nombre;
    }

    public String getDescripcionPost() {
        return this.descPost;
    }

    public int getCantMG() {
        return this.cantMG;
    }

    public List<String> getComentarios() {
        return listaComentario;
        
    }
    public List<String> getHashtags() {
        return listaHashtag;
    }

}
