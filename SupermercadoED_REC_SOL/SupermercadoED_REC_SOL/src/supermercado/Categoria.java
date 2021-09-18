package supermercado;

import java.util.Scanner;

/**
 *
 * @author luis 
 * REC2.	(Máx: 2ptos.) Implementar una nueva clase de nombre Categoria
 * para los productos, en la que se almacena un identificador que es único, el
 * código de la categoría expresado mediante una letra y una descripción de la
 * categoría. Añadir los métodos de acceso a tales atributos, los 3
 * constructores recomendados y un método nuevaCategoria que pida por pantalla
 * al usuario todos los datos y éste los introduzca por la entrada estándar,
 * devolviendo un objeto completo.
 */
public class Categoria {

    private long id;
    private char codigo;
    private String descripcion;

    public Categoria() {
    }

    public Categoria(long id, char codigo, String descripcion) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Categoria(Categoria c) {
        this.id = c.getId();
        this.codigo = c.getCodigo();
        this.descripcion = c.getDescripcion();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public char getCodigo() {
        return codigo;
    }

    public void setCodigo(char codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static Categoria nuevaCategoria() {
        Categoria ret = new Categoria();
        Scanner in = new Scanner(System.in);
        System.out.println("Introduzca el codigo para la categoria: (una letra)");
        ret.setCodigo(Character.valueOf(in.next().charAt(0)));
        System.out.println("Introduzca la descripcion para la nueva categoria :");
        ret.setDescripcion(in.nextLine());
        ret.setId(Utilidades.numCategorias + 1);
        return ret;
    }

}
