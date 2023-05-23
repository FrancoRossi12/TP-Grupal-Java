package TipoPublicacion;

import Interfaz.Filtrable;

public class Imagen extends Publicacion implements Filtrable {

    private int resolucion, alto, ancho, filtro;

    public Imagen() {
        super();
        resolucion = alto = ancho = filtro = 5;
    }

    // metodo aplicar flitro
    public void aplicarFiltro(int filtro) {// la variable int filtro es una posicion dentro del vector de filtros disponibles

    }

    public static void main(String[] args) {
        Imagen foto = new Imagen();// ESTO ES PARA PROBAR NOMAS
        foto.reproducir();
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

    @Override
    public void aplicarFiltro() {

    }
}
