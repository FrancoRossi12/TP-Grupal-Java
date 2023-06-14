package TipoPublicacion;

import Interfaz.Filtrable;

import java.util.ArrayList;

public class Imagen extends Publicacion implements Filtrable {

    private int alto, ancho;

    private tipoFiltro filtroAplicado;

    private String resolucion;

    public Imagen(String nombre, String descripcion, String fechaSubida, int cantMG, String resolucion, int alto, int ancho, ArrayList<String> listaHashtag, ArrayList<String> listaComentario) {
        super(nombre, descripcion, fechaSubida, cantMG,listaHashtag,listaComentario);
        this.resolucion = resolucion;
        this.alto = alto;
        this.ancho = ancho;
        this.filtroAplicado = tipoFiltro.DEFAULT;
    }

    // metodo aplicar flitro, redefinido de la interfaz filtrable
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
