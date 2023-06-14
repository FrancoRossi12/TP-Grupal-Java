package Interfaz;
import GUI.Publicaciones;

import TipoPublicacion.Publicacion;

/**
 * The interface Durable.
 */
public interface Durable {
    /**
     * Avanzar.
     *
     * @param segundos    the segundos
     * @param publicacion the publicacion
     */
    void avanzar(int segundos, Publicaciones publicacion);

    /**
     * Detener.
     *
     * @param publicacion the publicacion
     */
    void detener(Publicaciones publicacion);
}
