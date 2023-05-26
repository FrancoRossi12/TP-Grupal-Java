package TipoPublicacion;

import Interfaz.Filtrable;

public class Imagen extends Publicacion implements Filtrable {

    private int alto,ancho;
    private String resolucion;

    public Imagen(String nombre, String descripcion, int cantMG, String resolucion, int alto, int ancho) {
        super( nombre,  descripcion, cantMG);
        this.resolucion = resolucion;
        this.alto = alto;
        this.ancho = ancho;
    }



    //metodo aplicar flitro
    public void aplicarFiltro(){
        
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
}
