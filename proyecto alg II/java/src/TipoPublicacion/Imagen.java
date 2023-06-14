package TipoPublicacion;

import Interfaz.Filtrable;

import java.util.ArrayList;

/**
 * The type Imagen.
 */
public class Imagen extends Publicacion implements Filtrable {

    private int alto, ancho;

    private tipoFiltro filtroAplicado;

    private String resolucion;

    /**
     * Instantiates a new Imagen.
     *
     * @param nombre          the nombre
     * @param descripcion     the descripcion
     * @param fechaSubida     the fecha subida
     * @param cantMG          the cant mg
     * @param resolucion      the resolucion
     * @param alto            the alto
     * @param ancho           the ancho
     * @param listaHashtag    the lista hashtag
     * @param listaComentario the lista comentario
     */
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

    /**
     * Gets resolucion.
     *
     * @return the resolucion
     */
    public String getResolucion() {
        return resolucion;
    }

    /**
     * Gets alto.
     *
     * @return the alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Gets ancho.
     *
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Gets filtro.
     *
     * @return the filtro
     */
    public tipoFiltro getFiltro() {
        return filtroAplicado;
    }
}
