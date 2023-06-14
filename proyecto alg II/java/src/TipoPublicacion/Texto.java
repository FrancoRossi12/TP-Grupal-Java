package TipoPublicacion;

import java.util.ArrayList;
import java.util.List;

public class Texto extends Publicacion{
    String fuente;
    int cantCaracteres, tamañoFuente;

    public Texto(String nombre, String descripcion, String fechaSubida, int cantMG, String fuente, int cantCaracteres, int tamañoFuente, ArrayList<String> listaHashtag, ArrayList<String> listaComentario) {
        super(nombre, descripcion, fechaSubida, cantMG,listaHashtag,listaComentario);
        this.fuente = fuente;
        this.cantCaracteres = cantCaracteres;
        this.tamañoFuente = tamañoFuente;
    }




    public String getFuente() {
        return this.fuente;
    }

    public int getCantCaracteres() {
        return this.cantCaracteres;
    }

    public int getTamañoFuente() {
        return this.tamañoFuente;
    }
}
