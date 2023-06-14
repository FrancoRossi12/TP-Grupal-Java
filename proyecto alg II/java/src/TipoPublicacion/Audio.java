package TipoPublicacion;

import Interfaz.Durable;
import GUI.Publicaciones;
import java.util.ArrayList;

/**
 * The type Audio.
 */
public class Audio extends Publicacion implements Durable {

    private int duracion,velocidad_bits;

    /**
     * Instantiates a new Audio.
     *
     * @param nombre          the nombre
     * @param descripcion     the descripcion
     * @param fechaSubida     the fecha subida
     * @param cantMG          the cant mg
     * @param duracion        the duracion
     * @param velocidad_bits  the velocidad bits
     * @param listaHashtag    the lista hashtag
     * @param listaComentario the lista comentario
     */
    public Audio(String nombre, String descripcion, String fechaSubida, int cantMG, int duracion, int velocidad_bits, ArrayList<String> listaHashtag, ArrayList<String> listaComentario) {
        super(nombre, descripcion, fechaSubida, cantMG,listaHashtag,listaComentario);
        this.duracion = duracion;
        this.velocidad_bits = velocidad_bits;
    }


    // metodo avanzar y detener
    @Override
    public void avanzar(int segundos, Publicaciones publicacion) {
        publicacion.setPausado(false);
    }
    @Override
    public void detener(Publicaciones publicacion) {
        publicacion.setPausado(true);
    }

    /**
     * Gets duracion.
     *
     * @return the duracion
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Gets velocidad bits.
     *
     * @return the velocidad bits
     */
    public int getVelocidad_bits() {
        return velocidad_bits;
    }
}
