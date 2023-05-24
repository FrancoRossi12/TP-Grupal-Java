package TipoPublicacion;
import java.io.File;

import javax.sound.sampled.*;
import Interfaz.Durable;

public class Audio extends Publicacion implements Durable {

    private int duracion,velocidad_bits;
    
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
