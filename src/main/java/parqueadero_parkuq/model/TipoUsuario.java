package parqueadero_parkuq.model;

/**
 * Enumeración que define los diferentes tipos de usuarios del parqueadero.
 * Cada tipo puede tener asociado un nivel de descuento diferente.
 */
public enum TipoUsuario {

    /**
     * Usuario que es estudiante.
     */
    ESTUDIANTE,

    /**
     * Usuario que es docente.
     */
    DOCENTE,

    /**
     * Usuario que es personal administrativo.
     */
    ADMINISTRATIVO,

    /**
     * Usuario que es un visitante sin afiliación directa.
     */
    VISITANTE
}