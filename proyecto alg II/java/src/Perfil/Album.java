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
    public void set_nombreAlbum(String al){nombreAlbum=al;}
    public void set_nuevoSubAlbum(){subListaAlbums= new ArrayList<Album>();}
}
