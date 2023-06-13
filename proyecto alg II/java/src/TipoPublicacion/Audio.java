package TipoPublicacion;

import Interfaz.Durable;
import GUI.Publicaciones;
import java.util.ArrayList;

public class Audio extends Publicacion implements Durable {

    private final int duracion;
    private final int velocidad_bits;

    public Audio(String nombre, String descripcion, int cantMG, int duracion, int velocidad_bits, ArrayList<String> listaHashtag, ArrayList<String> listaComentario) {
        super(nombre, descripcion, cantMG,listaHashtag,listaComentario);
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

    public int getDuracion() {
        return duracion;
    }

    public int getVelocidad_bits() {
        return velocidad_bits;
    }
}
