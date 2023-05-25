package TipoPublicacion;

import Interfaz.Filtrable;

public class Imagen extends Publicacion implements Filtrable {

    private int resolucion,alto,ancho;

    public Imagen(String nombre, String descripcion, int cantMG, int resolucion, int alto, int ancho) {
        nombre = this.getNombre();
        descripcion = this.getDescripcionPost();
        cantMG = this.getCantMG();
        resolucion = this.resolucion;
        alto = this.alto;
        ancho = this.ancho;
    }



    //metodo aplicar flitro
    public void aplicarFiltro(){
        
    }

    public int getResolucion() {
        return resolucion;
    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }
}
