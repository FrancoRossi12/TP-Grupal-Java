package Perfil;

import TipoPublicacion.Publicacion;

import java.util.ArrayList;
import java.util.List;

public class Album extends Publicacion {

    private int Id_album;
    private List<Publicacion> listaPublicacion;

    Album() {
        super();
        listaPublicacion = new ArrayList<Publicacion>();
    }

}
