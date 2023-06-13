package TipoPublicacion;

import java.util.ArrayList;

public class Texto extends Publicacion{
    String fuente;
    int cantCaracteres, tamanioFuente;

    public Texto(String nombre, String descripcion, int cantMG, String fuente, int cantCaracteres, int tamanioFuente, ArrayList<String> listaHashtag, ArrayList<String> listaComentario) {
        super(nombre, descripcion, cantMG,listaHashtag,listaComentario);
        this.fuente = fuente;
        this.cantCaracteres = cantCaracteres;
        this.tamanioFuente = tamanioFuente;
    }




    public String getFuente() {
        return this.fuente;
    }

    public int getCantCaracteres() {
        return this.cantCaracteres;
    }

    public int getTamanioFuente() {
        return this.tamanioFuente;
    }
}
