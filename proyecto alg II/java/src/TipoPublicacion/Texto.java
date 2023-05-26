package TipoPublicacion;

public class Texto extends Publicacion{
    String fuente;
    int cantCaracteres, tamañoFuente;

    public Texto(String nombre, String descripcion, int cantMG, String fuente, int cantCaracteres, int tamañoFuente) {
        super( nombre,  descripcion, cantMG);
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
