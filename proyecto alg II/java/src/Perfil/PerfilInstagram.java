package Perfil;

import TipoPublicacion.Publicacion;
import Perfil.Album;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

public class PerfilInstagram {
    private String nombreUsuario, descripcion, contrasenia;
    private int cantPosts,  seguidores, seguidos, cantAlbums;
    private List<Publicacion> listaPublicacion;
    private List<Album> listaAlbums;

    public PerfilInstagram(String nomb, String contra){
        nombreUsuario=nomb;
        contrasenia=contra;
        cantPosts=0;
        cantAlbums=0;
        seguidores=0;
        seguidos=0;
        descripcion="";
        listaPublicacion= new ArrayList<Publicacion>();
        listaAlbums= new ArrayList<Album>();
    }
    public void set_nombreUsuario(String nomb){nombreUsuario=nomb;}
    public void set_descripcionUser(String desc){descripcion=desc;}
}