package TipoPublicacion;

import Interfaz.Durable;
import Interfaz.Filtrable;

public class Video extends Publicacion implements Durable, Filtrable {
    
    private int resolucion,duracion,cantcuadros;

    public Video(String nombre, String descripcion, int cantMG, int resolucion, int duracion, int cantcuadros) {
        super( nombre,  descripcion, cantMG);
        this.resolucion = resolucion;
        this.duracion = duracion;
        this.cantcuadros = cantcuadros;
    }

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

    public int getResolucion() {
        return resolucion;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getCantcuadros() {
        return cantcuadros;
    }
}
