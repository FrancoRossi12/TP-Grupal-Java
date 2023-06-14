package TipoPublicacion;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Publicacion.
 */
public class Publicacion {
    private int cantMG;
    private String nombre, descPost, fechaSubida;
    private ArrayList<String> listaComentario;
    private ArrayList<String> listaHashtag;


    /**
     * Instantiates a new Publicacion.
     *
     * @param nombre          the nombre
     * @param descripcion     the descripcion
     * @param fechaSubida     the fecha subida
     * @param cantMG          the cant mg
     * @param listaHashtag    the lista hashtag
     * @param listaComentario the lista comentario
     */
    public Publicacion(String nombre, String descripcion, String fechaSubida, int cantMG,ArrayList<String> listaHashtag,ArrayList<String> listaComentario){
        this.nombre = nombre;
        this.descPost = descripcion;
        this.cantMG = cantMG;
        this.listaComentario = listaComentario;
        this.listaHashtag = listaHashtag;
        this.fechaSubida = fechaSubida;
    }

    /**
     * Modifica desc post.
     *
     * @param desc the desc
     */
//posible(realizar) set_nombrePost
    public void modificaDescPost(String desc){descPost=desc;}

    /**
     * Gets nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Gets fecha subida.
     *
     * @return the fecha subida
     */
    public String getFechaSubida() {
        return fechaSubida;
    }

    /**
     * Gets descripcion post.
     *
     * @return the descripcion post
     */
    public String getDescripcionPost() {
        return this.descPost;
    }

    /**
     * Gets cant mg.
     *
     * @return the cant mg
     */
    public int getCantMG() {
        return this.cantMG;
    }

    /**
     * Gets comentarios.
     *
     * @return the comentarios
     */
    public List<String> getComentarios() {
        return listaComentario;
        
    }

    /**
     * Gets hashtags.
     *
     * @return the hashtags
     */
    public List<String> getHashtags() {
        return listaHashtag;
    }

}
