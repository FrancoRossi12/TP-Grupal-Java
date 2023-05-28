package TipoPublicacion;

import Interfaz.Durable;
import Interfaz.Filtrable;

public class Video extends Publicacion implements Durable, Filtrable {

    private int duracion, cantcuadros;
    private tipoFiltro filtroAplicado;
    private String resolucion;

    public Video(String nombre, String descripcion, int cantMG, String resolucion, int duracion, int cantcuadros) {
        super(nombre, descripcion, cantMG);
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

    @Override
    public void aplicarFiltro(int filtro) {
        switch (filtro) {
            case 0:
                filtroAplicado = tipoFiltro.DEFAULT;
                break;
            case 1:
                filtroAplicado = tipoFiltro.B_N;
                break;
            case 2:
                filtroAplicado = tipoFiltro.CLARENDON;
                break;
            case 3:
                filtroAplicado = tipoFiltro.SEPIA;
                break;
            default:
                filtroAplicado = tipoFiltro.DEFAULT;
                break;
        }
    }

    public String getResolucion() {
        return resolucion;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getCantcuadros() {
        return cantcuadros;
    }

    public tipoFiltro getFiltro() {
        return filtroAplicado;
    } // SIRVE PARA FILTRAR (FILTRAR DE AGRUPAR SEGUN CIERTAS CARACTERISTICAS)
      // LAS IMAGENES DE ACUERDO A QUE FILTRO DE IMAGEN POSEEN EJ: B_N, SEPIA,
      // ETC.
}
