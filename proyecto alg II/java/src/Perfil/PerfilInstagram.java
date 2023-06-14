package Perfil;

import TipoPublicacion.Publicacion;
import Perfil.Album;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Perfil instagram.
 */
public class PerfilInstagram {
    private String nombreUsuario, descripcion;
    private int cantPosts,  seguidores, seguidos, cantAlbums;
    private List<Publicacion> listaPublicacion;
    private List<Album> listaAlbums;

    /**
     * Instantiates a new Perfil instagram.
     *
     * @param nombreUsuario    the nombre usuario
     * @param descripcion      the descripcion
     * @param seguidores       the seguidores
     * @param seguidos         the seguidos
     * @param cantPosts        the cant posts
     * @param cantAlbums       the cant albums
     * @param listaPublicacion the lista publicacion
     * @param listaAlbums      the lista albums
     */
    public PerfilInstagram(String nombreUsuario, String descripcion,int seguidores,int seguidos,int cantPosts,int cantAlbums,List<Publicacion> listaPublicacion,List<Album> listaAlbums) {
        this.nombreUsuario = nombreUsuario;
        this.cantPosts = cantPosts;
        this.cantAlbums = cantAlbums;
        this.seguidores = seguidores;
        this.seguidos = seguidos;
        this.descripcion = descripcion;
        this.listaPublicacion = listaPublicacion;
        this.listaAlbums = listaAlbums;
    }

    /**
     * Gets cant posts.
     *
     * @return the cant posts
     */
    public int getCantPosts() {
        return cantPosts;
    }

    /**
     * Sets cant posts.
     *
     * @param cantPosts the cant posts
     */
    public void setCantPosts(int cantPosts) {
        this.cantPosts = cantPosts;
    }

    /**
     * Gets seguidores.
     *
     * @return the seguidores
     */
    public int getSeguidores() {
        return seguidores;
    }

    /**
     * Sets seguidores.
     *
     * @param seguidores the seguidores
     */
    public void setSeguidores(int seguidores) {
        this.seguidores = seguidores;
    }

    /**
     * Gets seguidos.
     *
     * @return the seguidos
     */
    public int getSeguidos() {
        return seguidos;
    }

    /**
     * Sets seguidos.
     *
     * @param seguidos the seguidos
     */
    public void setSeguidos(int seguidos) {
        this.seguidos = seguidos;
    }

    /**
     * Gets cant albums.
     *
     * @return the cant albums
     */
    public int getCantAlbums() {
        return cantAlbums;
    }

    /**
     * Sets cant albums.
     *
     * @param cantAlbums the cant albums
     */
    public void setCantAlbums(int cantAlbums) {
        this.cantAlbums = cantAlbums;
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
     * Gets nombre usuario.
     *
     * @return the nombre usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Sets nombre usuario.
     *
     * @param nombreUsuario the nombre usuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Gets descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets descripcion.
     *
     * @param descripcion the descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets lista albums.
     *
     * @return the lista albums
     */
    public List<Album> getListaAlbums() {
        return listaAlbums;
    }

    /**
     * Sets lista albums.
     *
     * @param listaAlbums the lista albums
     */
    public void setListaAlbums(List<Album> listaAlbums) {
        this.listaAlbums = listaAlbums;
    }

}