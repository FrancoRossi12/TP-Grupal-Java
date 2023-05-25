package TipoPublicacion;

import Interfaz.Durable;

public class Audio extends Publicacion implements Durable {

    private int duracion,velocidad_bits;

    public Audio(String nombre, String descripcion, int cantMG, int duracion, int velocidad_bits) {
        super( nombre,  descripcion, cantMG);
        duracion = this.duracion;
        velocidad_bits = this.velocidad_bits;
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
