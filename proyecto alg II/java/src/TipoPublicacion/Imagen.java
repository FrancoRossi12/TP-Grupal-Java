package TipoPublicacion;

import Interfaz.Filtrable;

import java.util.ArrayList;

public class Imagen extends Publicacion implements Filtrable {

    private final int alto;
    private final int ancho;

    private tipoFiltro filtroAplicado;

    private final String resolucion;

    public Imagen(String nombre, String descripcion, int cantMG, String resolucion, int alto, int ancho, ArrayList<String> listaHashtag, ArrayList<String> listaComentario) {
        super(nombre, descripcion, cantMG,listaHashtag,listaComentario);
        this.resolucion = resolucion;
        this.alto = alto;
        this.ancho = ancho;
        this.filtroAplicado = tipoFiltro.DEFAULT;
    }

    // metodo aplicar flitro, redefinido de la interfaz filtrable
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

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }

    public tipoFiltro getFiltro() {
        return filtroAplicado;
    }
}
