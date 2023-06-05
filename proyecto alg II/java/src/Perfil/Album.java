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
    public Album(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
        cantPostsAlbum=0;
        listaPublicacion = new ArrayList<Publicacion>();
    }
    public void setNombreAlbum(String al){nombreAlbum=al;}
    public void setNuevoSubAlbum(){subListaAlbums= new ArrayList<Album>();}

    public Object getNombreAlbum() {
        return this.nombreAlbum;
    }

    public int getCantPostsAlbum() {
        return cantPostsAlbum;
    }

    public void setCantPostsAlbum(int cantPostsAlbum) {
        this.cantPostsAlbum = cantPostsAlbum;
    }

    public List<Publicacion> getListaPublicacion() {
        return listaPublicacion;
    }

    public void setListaPublicacion(List<Publicacion> listaPublicacion) {
        this.listaPublicacion = listaPublicacion;
    }

    public List<Album> getSubListaAlbums() {
        return subListaAlbums;
    }

    public void setSubListaAlbums(List<Album> subListaAlbums) {
        this.subListaAlbums = subListaAlbums;
    }
}
