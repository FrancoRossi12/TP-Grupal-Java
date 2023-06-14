package Perfil;

import TipoPublicacion.Publicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Album.
 */
public class Album {
    private String nombreAlbum;
    private int cantPostsAlbum;
    private List<Publicacion> listaPublicacion;
    private List<Publicacion> subListaAlbums;

    /**
     * Instantiates a new Album.
     *
     * @param nombreAlbum the nombre album
     */
    public Album(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
        cantPostsAlbum=0;
        listaPublicacion = new ArrayList<Publicacion>();
        subListaAlbums = new ArrayList<Publicacion>();
    }

    /**
     * Set nombre album.
     *
     * @param al the al
     */
    public void setNombreAlbum(String al){nombreAlbum=al;}

    /**
     * Set nuevo sub album.
     */
    public void setNuevoSubAlbum(){subListaAlbums= new ArrayList<Publicacion>();}

    /**
     * Gets nombre album.
     *
     * @return the nombre album
     */
    public Object getNombreAlbum() {
        return this.nombreAlbum;
    }

    /**
     * Gets cant posts album.
     *
     * @return the cant posts album
     */
    public int getCantPostsAlbum() {
        return cantPostsAlbum;
    }

    /**
     * Sets cant posts album.
     *
     * @param cantPostsAlbum the cant posts album
     */
    public void setCantPostsAlbum(int cantPostsAlbum) {
        this.cantPostsAlbum = cantPostsAlbum;
    }

    /**
     * Gets lista publicacion.
     *
     * @return the lista publicacion
     */
    public List<Publicacion> getListaPublicacion() {
        return listaPublicacion;
    }

    /**
     * Sets lista publicacion.
     *
     * @param listaPublicacion the lista publicacion
     */
    public void setListaPublicacion(List<Publicacion> listaPublicacion) {
        this.listaPublicacion = listaPublicacion;
    }

    /**
     * Gets sub lista albums.
     *
     * @return the sub lista albums
     */
    public List<Publicacion> getSubListaAlbums() {
        return subListaAlbums;
    }

    /**
     * Sets sub lista albums.
     *
     * @param subListaAlbums the sub lista albums
     */
    public void setSubListaAlbums(List<Publicacion> subListaAlbums) {
        this.subListaAlbums = subListaAlbums;
    }

    /**
     * Agregar publicacion.
     *
     * @param publicacion the publicacion
     */
    public void agregarPublicacion(Publicacion publicacion) {
        this.listaPublicacion.add(publicacion);
    }

    /**
     * Agregar subpublicacion.
     *
     * @param subpublicacion the subpublicacion
     */
    public void agregarSubpublicacion(Publicacion subpublicacion) {
        this.subListaAlbums.add(subpublicacion);
    }

    /**
     * Gets publicaciones.
     *
     * @return the publicaciones
     */
    public List<Publicacion> getPublicaciones() {
        return listaPublicacion;
    }

    /**
     * Gets subpublicaciones.
     *
     * @return the subpublicaciones
     */
    public List<Publicacion> getSubpublicaciones() {
        return this.subListaAlbums;
    }
}
