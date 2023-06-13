package TipoPublicacion;

import Interfaz.Durable;
import Interfaz.Filtrable;
import GUI.Publicaciones;

import java.util.ArrayList;

public class Video extends Publicacion implements Durable, Filtrable {

    private final int duracion;
    private final int cantcuadros;
    private tipoFiltro filtroAplicado;
    private final String resolucion;

    public Video(String nombre, String descripcion, int cantMG, String resolucion, int duracion, int cantcuadros, ArrayList<String> listaHashtag, ArrayList<String> listaComentario) {
        super(nombre, descripcion, cantMG,listaHashtag,listaComentario);
        this.resolucion = resolucion;
        this.duracion = duracion;
        this.cantcuadros = cantcuadros;
        this.filtroAplicado = tipoFiltro.DEFAULT;
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
            case 1 -> filtroAplicado = tipoFiltro.B_N;
            case 2 -> filtroAplicado = tipoFiltro.CLARENDON;
            case 3 -> filtroAplicado = tipoFiltro.SEPIA;
            default -> filtroAplicado = tipoFiltro.DEFAULT;
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
    }
}
