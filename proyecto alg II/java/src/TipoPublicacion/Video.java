package TipoPublicacion;

import Interfaz.Durable;
import Interfaz.Filtrable;

public class Video extends Publicacion implements Durable, Filtrable {

    private int resolucion,duracion,cantcuadros;

    /**
     * Permitir avanzar, detener, y aplicar filtros.
     */
    public void reproducir() {

    }
    public void avanzar(int segundos) {

    }
    public void detener() {
        
    }
    public void aplicarFiltro(){
        
    }
}
