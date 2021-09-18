package supermercado;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author luis
 */
public class Transportista extends Empleado {

    private String vehiculo;
    private ArrayList<Almacen> almacenes = new ArrayList<Almacen>(); //relacion "trabajar" entre Transportista y Almacen

    public Transportista() {
    }

    public Transportista(Empleado e, String vehiculo) {
        super(e);
        this.vehiculo = vehiculo;
    }

    public Transportista(Empleado e, String vehiculo, ArrayList<Almacen> almacenes) {
        super(e);
        this.vehiculo = vehiculo;
        this.almacenes = almacenes;
    }

    public Transportista(int identificador, String nombre, String direccion, String telefono, String email, String vehiculo) {
        super(identificador, nombre, direccion, telefono, email);
        this.vehiculo = vehiculo;
    }

    public Transportista(int identificador, String nombre, String direccion, String telefono, String email, String vehiculo, ArrayList<Almacen> almacenes) {
        super(identificador, nombre, direccion, telefono, email);
        this.vehiculo = vehiculo;
        this.almacenes = almacenes;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public ArrayList<Almacen> getAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(ArrayList<Almacen> almacenes) {
        this.almacenes = almacenes;
    }

    public boolean clienteAusente(Pedido p, Cliente c) {
        p.setFechaEnvio(Date.valueOf(LocalDate.now()));
        return true;
    }

    /*
    REC7.	(Máx: 2ptos.) Añadir el código necesario para implementar el siguiente caso de uso CU: Finalizar Pedido
     */
    /**
     * *
     * Función que finaliza un Poledido p de un cliente c por el transportista
     * actual En caso de éxito, el pedido queda finalizado por el transportista
     * y el pedido deja de estar físicamente en manos del supermercado. En caso
     * de pago en efectivo se registra un nuevo pago en el sistema y se
     * actualiza el saldo del cliente.
     *
     * @param p El pedido que pretende finalizar el Transportista (pedido ya
     * preparado)
     * @param c El cliente que recibe el pedido (cliente que realizó el pedido
     * p)
     * @return true en caso de éxito o false en caso contrario
     */
    public boolean finalizarPedido(Pedido p, Cliente c) {
        Scanner in = new Scanner(System.in);
        //Paso1: Se comprueba que existe el Cliente c. Si no, se muestra la lista de clientes y se selecciona el correcto
        int clienteConfirmado = -1;
        if (!Cliente.convertir(Utilidades.CLIENTES).contains(c)) {
            System.out.println("No se encuentra el cliente.");
            do {
                System.out.println("Seleccione el cliente:");
                for (int i = 0; i < Utilidades.numClientes; i++) {
                    System.out.println((i + 1) + ": " + Utilidades.CLIENTES[i]);
                    System.out.println("Pulse 0 para CANCELAR");
                }
                clienteConfirmado = in.nextInt();
                if (clienteConfirmado == 0) {
                    return false;
                }
            } while (clienteConfirmado < 1 || clienteConfirmado > Utilidades.numClientes);
            c = Utilidades.CLIENTES[clienteConfirmado - 1];
        }
        //Paso2: Se comprueba que existe el Pedido p y que pertenece al cliente. Si no, se muestra la lista de pedidos y se selecciona el correcto
        int pedidoConfirmado = -1;
        if (!Pedido.convertir(Utilidades.PEDIDOS).contains(p) || p.getCliente() != c) {
            System.out.println("No se encuentra el pedido.");
            do {
                System.out.println("Seleccione el pedido:");
                for (int i = 0; i < Utilidades.numPedidos; i++) {
                    System.out.println((i + 1) + ": " + Utilidades.PEDIDOS[i]);
                    System.out.println("Pulse 0 para CANCELAR");
                }
                pedidoConfirmado = in.nextInt();
                if (pedidoConfirmado == 0) {
                    return false;
                }
            } while (pedidoConfirmado < 1 || pedidoConfirmado > Utilidades.numPedidos);
            p = Utilidades.PEDIDOS[pedidoConfirmado - 1];
        }
        //Paso3: Si el pedido no se ha pagado o el cliente tiene deuda
        if (c.getSaldo() < 0 || p.getPago() == null) {
            System.out.println("Su pedido no ha sido pagado o tiene una deuda.");
            do {
                double importe = 0;
                char tipoPago = ' ';
                do {
                    System.out.println("Debe ralizar un pago. Seleccione el tipo de pago T=tarjeta o E=efectivo.");
                    tipoPago = in.next().charAt(0);
                    System.out.println("Indique el importe del pago en euros (x.xx):");
                    importe = in.nextDouble();
                    if (importe <= 0 || (tipoPago != 'T' && tipoPago != 'E')) {
                        System.out.println("Error: importe o tipo de pago incorrecto.");
                    }
                } while (importe <= 0 || (tipoPago != 'T' && tipoPago != 'E'));
                if (!c.realizarPago(importe, tipoPago, p)) {
                    p.setEstado('F');
                    if (p.isEnvio()) {
                        p.setFechaEnvio(Date.valueOf(LocalDate.now()));
                    }
                    return false;
                }

            } while (c.getSaldo() < 0 || p.getPago() == null);
        }
        //El cliente no tiene deuda y el pedido ha sido pagado -> entrega física del pedido al cliente
        p.setFechaEnvio(Date.valueOf(LocalDate.now()));
        //Paso 4: se establece el estado a 'Recibido' y el Transportista finaliza el pedido exitosamente
        p.setEstado('R');
        p.setTransportista(this);
        return true;
    }

    public String almacenes() {
        String ret = " almacenes:[";
        for (Almacen a : this.almacenes) {
            ret += "" + a.getIdentificador() + "(" + a.getNombre() + "), ";
        }
        return ret + ']';
    }

    @Override
    public String toString() {
        return "Transportista{ " + super.toString() + ", vehiculo=" + vehiculo + "," + almacenes() + '}';
    }
}
