package Interfaz;
import GUI.Publicaciones;

import TipoPublicacion.Publicacion;

public interface Durable {
    void avanzar(int segundos, Publicaciones publicacion);
    void detener(Publicaciones publicacion);
}
