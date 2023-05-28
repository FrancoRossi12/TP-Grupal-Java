package TipoPublicacion;

import Interfaz.Filtrable;

public class Imagen extends Publicacion implements Filtrable {

    private int alto, ancho;

    private tipoFiltro filtroAplicado;

    private String resolucion;

    public Imagen(String nombre, String descripcion, int cantMG, String resolucion, int alto, int ancho) {
        super(nombre, descripcion, cantMG);
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
