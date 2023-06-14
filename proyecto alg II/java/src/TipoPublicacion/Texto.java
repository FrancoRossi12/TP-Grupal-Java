package TipoPublicacion;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Texto.
 */
public class Texto extends Publicacion{
    /**
     * The Fuente.
     */
    String fuente;
    /**
     * The Cant caracteres.
     */
    int cantCaracteres, /**
     * The Tamanio fuente.
     */
    tamanioFuente;

    /**
     * Instantiates a new Texto.
     *
     * @param nombre          the nombre
     * @param descripcion     the descripcion
     * @param fechaSubida     the fecha subida
     * @param cantMG          the cant mg
     * @param fuente          the fuente
     * @param cantCaracteres  the cant caracteres
     * @param tamanioFuente    the tamanio fuente
     * @param listaHashtag    the lista hashtag
     * @param listaComentario the lista comentario
     */
    public Texto(String nombre, String descripcion, String fechaSubida, int cantMG, String fuente, int cantCaracteres, int tamanioFuente, ArrayList<String> listaHashtag, ArrayList<String> listaComentario) {
        super(nombre, descripcion, fechaSubida, cantMG,listaHashtag,listaComentario);
        this.fuente = fuente;
        this.cantCaracteres = cantCaracteres;
        this.tamanioFuente = tamanioFuente;
    }


    /**
     * Gets fuente.
     *
     * @return the fuente
     */
    public String getFuente() {
        return this.fuente;
    }

    /**
     * Gets cant caracteres.
     *
     * @return the cant caracteres
     */
    public int getCantCaracteres() {
        return this.cantCaracteres;
    }

    /**
     * Get tamanio fuente int.
     *
     * @return the int
     */
    public int getTamanioFuente() {
        return this.tamanioFuente;
    }
}
