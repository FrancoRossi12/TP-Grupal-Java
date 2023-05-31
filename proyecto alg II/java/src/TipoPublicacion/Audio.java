package TipoPublicacion;

import Interfaz.Durable;

import java.util.ArrayList;

public class Audio extends Publicacion implements Durable {

    private int duracion,velocidad_bits;

    public Audio(String nombre, String descripcion, int cantMG, int duracion, int velocidad_bits, ArrayList<String> listaHashtag, ArrayList<String> listaComentario) {
        super(nombre, descripcion, cantMG,listaHashtag,listaComentario);
        this.duracion = duracion;
        this.velocidad_bits = velocidad_bits;
    }


    // metodo avanzar y detener
    public void avanzar(int segundos) {

    }
    public void detener() {
        
    }

    public int getDuracion() {
        return duracion;
    }

    public int getVelocidad_bits() {
        return velocidad_bits;
    }
}
