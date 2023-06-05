package Perfil;

import TipoPublicacion.Publicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Album {
    private String nombreAlbum;
    private int cantPostsAlbum;
    private List<Publicacion> listaPublicacion;
    private List<Album> subListaAlbums;
    public Album() {
        cantPostsAlbum=0;
        listaPublicacion = new ArrayList<Publicacion>();
    }
    public void setNombreAlbum(String al){nombreAlbum=al;}
    public void setNuevoSubAlbum(){subListaAlbums= new ArrayList<Album>();}

    public Object getNombreAlbum() {
        return nombreAlbum;
    }
}
