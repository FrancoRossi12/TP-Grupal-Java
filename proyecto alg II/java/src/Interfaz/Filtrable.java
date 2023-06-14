package Interfaz;

/**
 * The interface Filtrable.
 */
public interface Filtrable {
    /**
     * Aplicar filtro.
     *
     * @param filtro the filtro
     */
    void aplicarFiltro(int filtro);

    /**
     * The enum Tipo filtro.
     */
    enum tipoFiltro {
        /**
         * Default tipo filtro.
         */
        DEFAULT,
        /**
         * B n tipo filtro.
         */
        B_N,
        /**
         * Sepia tipo filtro.
         */
        SEPIA,
        /**
         * Clarendon tipo filtro.
         */
        CLARENDON;
    }
    // SE PUEDE DECLARAR EL ENUM EN LA INTERFAZ YA QUE NO ES UNA VARIABLE, SI NO UN
    // TIPO DE DATO
}
