package TipoPublicacion;

import Interfaz.Durable;
import Interfaz.Filtrable;

public class Video extends Publicacion implements Durable, Filtrable {
    
    private int resolucion,duracion,cantcuadros;

    public Video(int resolucion, int duracion, int cantcuadros) {
        resolucion = this.resolucion;
        duracion = this.duracion;
        cantcuadros = this.cantcuadros;
    }

    public Video(String nombre, String descripcion, int cantMG, int resolucion, int duracion, int cantcuadros) {
        nombre = this.getNombre();
        descripcion = this.getDescripcionPost();
        cantMG = this.getCantMG();
        resolucion = this.resolucion;
        duracion = this.duracion;
        cantcuadros = this.cantcuadros;
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
