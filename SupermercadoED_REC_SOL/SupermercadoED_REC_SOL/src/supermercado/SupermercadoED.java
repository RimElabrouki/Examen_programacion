package supermercado;

import java.util.ArrayList;
import java.util.Scanner;

public class SupermercadoED {

    public static void main(String[] args) {
        System.out.println("INICIO MAIN");
        Scanner in = new Scanner(System.in);

        Cliente[] clientes = Utilidades.CLIENTES;
        int numClientes = clientes.length;

        for (int i = 0; i < clientes.length; i++) {
            clientes[i].toString();
        }
        
        Cliente c3 = Utilidades.CLIENTES[2];

        double importe = 0.0;
        do {
            System.out.println("Introduzca un importe y le mostramos todos los productos que podría adquirir:");
            importe = in.nextDouble();

            if (importe <= 0.0) {
                System.out.println("Valor no válido! Introduzca el importe en el formato x.xx");
            }
        } while (importe <= 0.0);
        for (int i = 0; i < Utilidades.numProductos; i++) {
            Producto p = Utilidades.PRODUCTOS[i];
            if (p.getPrecio() <= importe) {
                System.out.println("Usted puede comprar: " + p.getNombre() + "(" + p.getPrecio() + "euros).");
            }
        }

        //Realizar un pedido
        Pedido pedidoC3 = c3.realizarPedido();

        /**
         *
         * Ejercicio2.	(Máx: 0,75ptos.) En la función principal, definir una
         * variable de tipo colección de objetos Almacen que inicialmente
         * contenga los 3 almacenes ya existentes en el sistema. Luego, pedir al
         * usuario que introduzca varios nuevos almacenes (al menos uno) que se
         * irán añadiendo a esa colección. Cuando el usuario termine de
         * introducir almacenes, el programa principal mostrará de uno en uno
         * los datos de cada objeto Almacen de la colección.
         *
         */
        ArrayList<Almacen> almacenes = Almacen.convertir(Utilidades.ALMACENES);
        for (int i = 0; i < 3; i++) {
            System.out.println("Introduzca un nuevo almacen:");
            almacenes.add(Almacen.nuevoAlmacen());
        }
        System.out.println("Los almacenes guardados en el programa son:");
        for (Almacen a : almacenes) {
            System.out.println("Datos de almacen:" + a);
        }

        /**
         * *
         * Ejercicio 6.	(Máx: 1pto.) En la función principal, implementar un
         * código para que se muestre el nombre, el código y las existencias de
         * cada producto que se almacena en el “Polígono”.
         */
        Almacen poligono = Almacen.buscarByNombre("Poligono");
        if (poligono != null) {
            System.out.println("Los productos del almacen \"Poligono\" son:");
            for (int cont = 0; cont < Utilidades.numProductos; cont++) {
                Producto p = Utilidades.PRODUCTOS[cont];
                if (p.getAlmacen().equals(poligono)) {
                    System.out.println(p);
                }
            }

        }

        System.out.println("FIN MAIN");
    }

    public ArrayList<Producto> precioMenorQue(double precio) {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        for (int i = 0; i < Utilidades.numProductos; i++) {
            double precioProducto = Utilidades.PRODUCTOS[i].getPrecio();
            if (precioProducto < precio) {
                productos.add(Utilidades.PRODUCTOS[i]);
            }
        }
        return productos;
    }

    /**
     * *
     *
     * Ejercicio 4.	(Máx: 1,5pto.) Implementar un método que, dado un Producto, devuelva
     * una lista de Clientes que hayan pedido ese Producto alguna vez. EXTRA:
     * +0,5 si la lista devuelta no tiene duplicados de Clientes.
     *
     *
     * @param p Producto para buscar
     * @return clientes que pidieron el Producto p alguna vez.
     */
    public ArrayList<Cliente> clientesPidieron(Producto p) {
        ArrayList<Cliente> ret = new ArrayList<Cliente>();
        int contLP = 0; //contador para recorrer las lineas de pedidos
        //Recorremos todos los clientes
        for (int i = 0; i < Utilidades.numClientes; i++) {
            Cliente c = Utilidades.CLIENTES[i];
            for (int j = contLP; j < Utilidades.numLineas; j++) {
                contLP++;
                LineaPedido lp = Utilidades.LINEAS[j];
                if (lp.getProducto().equals(p)) {
                    //Hay alguna linea que contiene el producto
                    if (lp.idPedido().getCliente().equals(c)) {
                        //El pedido pertenece al cliente sobre el que iteramos
                        if (!ret.contains(c)) //para evitar duplicados
                        {
                            ret.add(c);
                        }
                        break;
                    }
                }
            }
        }
        return ret;
    }

    /**
     * *
     *
     * Ejercicio 5.	(Máx: 1pto.) Implementar un método que devuelva la lista de
     * Productos a los que se les ha aplicado algún descuento alguna vez. EXTRA:
     * +0,5 si la lista devuelta no tiene duplicados de Productos. *
     *
     * @return lista de productos promocionados alguna vez
     */
    public ArrayList<Producto> productosPromocionadosAlgunaVez() {
        ArrayList<Producto> ret = new ArrayList<Producto>();
        for (int j = 0; j < Utilidades.numDescuentos; j++) {
            Descuento d = Utilidades.DESCUENTOS[j];
            for (int k = 0; k < d.getProductos().size(); k++) {
                if (!ret.contains(d.getProductos().get(k))) {
                    ret.add(d.getProductos().get(k));
                }
            }
        }
        return ret;
    }

}
