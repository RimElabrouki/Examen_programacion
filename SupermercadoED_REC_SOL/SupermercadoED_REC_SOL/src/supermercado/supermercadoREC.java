package supermercado;

import java.util.ArrayList;

public class supermercadoREC {

    public static void main(String[] args) {
        /**
         * REC1.	(Máx: 1pto.) Implementar el constructor de copia para la clase
         * Cliente y utilizarlo en la función principal para crear un nuevo
         * objeto de esta clase a partir de los datos de otro objeto ya
         * existente del fichero Utilidades.java.
         */
        Cliente c1 = new Cliente(Utilidades.CLIENTES[0]);

        /**
         * REC6.	(Máx: 1pto.) En la función principal, implementar un código para
         * que se muestre el nombre, el código y las existencias de los
         * productos que no han formado parte nunca de un descuento
         *
         */
        ArrayList<Producto> productosEnAlgunDescuento = new ArrayList<Producto>();
        ArrayList<Producto> productosEnNingunDescuento = Producto.convertir(Utilidades.PRODUCTOS);
        for(Descuento d: Utilidades.DESCUENTOS){
            for(Producto p: d.getProductos())
                if(!productosEnAlgunDescuento.contains(p))
                    productosEnAlgunDescuento.add(p);
        }
        for(Producto p: productosEnAlgunDescuento){
            if(productosEnNingunDescuento.contains(p)){
                productosEnNingunDescuento.remove(p);
            }
        }
        System.out.println("Los productos que no han formado parte nunca de un descuento son:");
        if(productosEnNingunDescuento.isEmpty())
            System.out.println("No hay ninguno.");
        else{
            for(Producto p: productosEnNingunDescuento)
                System.out.println(p);
        }
        
    }

    /**
     * REC4.	(Máx: 1pto.) Implementar un método que devuelva la lista de
     * Transportistas que trabajen en el almacén “Sotano”.
     */
    public ArrayList<Transportista> transportistasSotano() {
        ArrayList<Transportista> ret = new ArrayList<Transportista>();
        Almacen sotano = Almacen.buscarByNombre("Sotano");
        if (sotano != null) {
            for (Empleado e : Utilidades.EMPLEADOS) {
                if (e instanceof Transportista) {
                    for (Almacen a : ((Transportista) e).getAlmacenes()) {
                        if (a.equals(sotano)) {
                            if (!ret.contains(a)) {
                                ret.add((Transportista) e);
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    /**
     * 5.	(Máx: 1pto.) Implementar un método que devuelva la lista de Clientes
     * que aún no se han suscrito.
     */
    public ArrayList<Cliente> clientesNoSuscritos() {
        ArrayList<Cliente> ret = new ArrayList<Cliente>();
        for (Cliente c : Utilidades.CLIENTES) {
            if (!c.isSuscripcion()) {
                if (!ret.contains(c)) {
                    ret.add(c);
                }
            }
        }
        return ret;
    }
}
