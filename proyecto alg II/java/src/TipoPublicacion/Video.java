package TipoPublicacion;

import Interfaz.Durable;
import Interfaz.Filtrable;
import GUI.Publicaciones;

import java.util.ArrayList;

public class Video extends Publicacion implements Durable, Filtrable {

    private int duracion, cantcuadros;
    private tipoFiltro filtroAplicado;
    private String resolucion;

    public Video(String nombre, String descripcion, String fechaSubida, int cantMG, String resolucion, int duracion, int cantcuadros, ArrayList<String> listaHashtag, ArrayList<String> listaComentario) {
        super(nombre, descripcion, fechaSubida, cantMG,listaHashtag,listaComentario);
        this.resolucion = resolucion;
        this.duracion = duracion;
        this.cantcuadros = cantcuadros;
        this.filtroAplicado = tipoFiltro.DEFAULT;
    }

    /**
     * Permitir avanzar, detener, y aplicar filtros.
     */
    public void reproducir() {

    }
@Override
public void avanzar(int segundos, Publicaciones publicacion) {
    publicacion.setPausado(false);
}
@Override
    public void detener(Publicaciones publicacion) {
        publicacion.setPausado(true);
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
