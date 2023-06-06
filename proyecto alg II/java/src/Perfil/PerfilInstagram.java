package Perfil;

import TipoPublicacion.Publicacion;
import Perfil.Album;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

public class PerfilInstagram {
    private String nombreUsuario, descripcion;
    private int cantPosts,  seguidores, seguidos, cantAlbums;
    private List<Publicacion> listaPublicacion;
    private List<Album> listaAlbums;

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

    public int getCantPosts() {
        return cantPosts;
    }

    public void setCantPosts(int cantPosts) {
        this.cantPosts = cantPosts;
    }

    public int getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(int seguidores) {
        this.seguidores = seguidores;
    }

    public int getSeguidos() {
        return seguidos;
    }

    public void setSeguidos(int seguidos) {
        this.seguidos = seguidos;
    }

    public int getCantAlbums() {
        return cantAlbums;
    }

    public void setCantAlbums(int cantAlbums) {
        this.cantAlbums = cantAlbums;
    }

    public List<Publicacion> getListaPublicacion() {
        return listaPublicacion;
    }

    public void setListaPublicacion(List<Publicacion> listaPublicacion) {
        this.listaPublicacion = listaPublicacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Album> getListaAlbums() {
        return listaAlbums;
    }

    public void setListaAlbums(List<Album> listaAlbums) {
        this.listaAlbums = listaAlbums;
    }

}