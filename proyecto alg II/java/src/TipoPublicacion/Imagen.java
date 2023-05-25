package TipoPublicacion;

import Interfaz.Filtrable;
public class Imagen extends Publicacion implements Filtrable {

    private int resolucion, alto, ancho;
    private TipoFiltro tipoFiltro;

    public Imagen() {
        super();
        resolucion = alto = ancho = 5;
    }

    // metodo reproductor de imagen
    public void reproducir() {
        System.out.println("Inicio publicacion: " + this.getNombre());
        // ACA HABRIA QUE METER TODA LA LOGICA DE LA REPRODUCCION DE LA IMAGEN
        for (int i = 0; i < ancho; i++) {
            System.out.println(".");// TOY HACIENDO PRUEBAS
        }
        System.out.println("Fin publicacion: " + this.getNombre());
    }

    public enum TipoFiltro {
        SEPIA, B_W, CLARENDON, DEFAULT
    }
    @Override
    public void aplicarFiltro(int x) {
        if(x == 0)
            tipoFiltro = tipoFiltro.DEFAULT;
        else if(x == 1)
            tipoFiltro = tipoFiltro.SEPIA;
        else if(x == 2)
            tipoFiltro = tipoFiltro.B_W;
        else
            tipoFiltro = tipoFiltro.CLARENDON;
    }
}
