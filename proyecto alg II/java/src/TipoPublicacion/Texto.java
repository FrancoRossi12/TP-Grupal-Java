package TipoPublicacion;

public class Texto extends Publicacion{
    String fuente;
    int cantCaracteres, tamañoFuente;

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
