package Interfaz;

public interface Filtrable {
    void aplicarFiltro(int filtro);

    enum tipoFiltro {
        DEFAULT, B_N, SEPIA, CLARENDON;
    }
    // SE PUEDE DECLARAR EL ENUM EN LA INTERFAZ YA QUE NO ES UNA VARIABLE, SI NO UN
    // TIPO DE DATO
}
