package TipoPublicacion;

import Interfaz.Filtrable;

public class Imagen extends Publicacion implements Filtrable {

    private int resolucion,alto,ancho;

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
