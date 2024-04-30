import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exp3s8simonriffo {
    //Arrays de ventas, asientos y descuentos
    static List<Venta> ventas = new ArrayList<>();
    static String[] asientos = new String[40];
    static List<Descuento> descuentos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String opcion;

        inicializarAsientos();
        
        descuentos.add(new Descuento("estudiante", 0.10));
        descuentos.add(new Descuento("tercera edad", 0.15));

        do {
            System.out.println("Bienvenido al Teatro Moro!");
            System.out.println("Venga a disfrutar de nuestra funcion");
            System.out.println("Que desea hacer?");
            System.out.println("1. Comprar Entrada");
            System.out.println("2. Salir");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    comprarEntrada(scanner);
                    break;
                case "2":
                    System.out.println("Gracias por visitar el Teatro Moro.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción del menú.");
            }
        } while (!opcion.equals("2"));
    }

    public static void comprarEntrada(Scanner scanner) {
        // Variables para almacenar información de la venta
        int precioBase = 0;
        double descuento = 0;
        String ubicacion = "";
        String categoria, entrada, continuar;

        do {
            System.out.println("Por favor, ingrese su edad:");
            int edad = Integer.parseInt(scanner.nextLine());

            if (edad < 18) {
                entrada = "estudiante";
            } else if (edad >= 60) {
                entrada = "tercera edad";
            } else {
                entrada = "general";
            }

            System.out.println("Usted califica como " + entrada + " para la entrada.");

            System.out.println("Que categoria necesitas?");
            System.out.println("Por favor escribir la opcion en minuscula");
            System.out.println("Zona A - Vip");
            System.out.println("Zona B - Platea baja");
            System.out.println("Zona C - Platea alta");
            System.out.println("Zona D - Palcos");

            categoria = scanner.nextLine();

            switch (categoria.toLowerCase()) {
                case "vip":
                    precioBase = entrada.equalsIgnoreCase("estudiante") ? 13000 : 25000;
                    ubicacion = "Zona A - Vip";
                    break;
                case "platea baja":
                    precioBase = entrada.equalsIgnoreCase("estudiante") ? 10000 : 19000;
                    ubicacion = "Zona B - Platea baja";
                    break;
                case "platea alta":
                    precioBase = entrada.equalsIgnoreCase("estudiante") ? 9000 : 11000;
                    ubicacion = "Zona C - Platea alta";
                    break;
                case "palcos":
                    precioBase = entrada.equalsIgnoreCase("estudiante") ? 6500 : 7200;
                    ubicacion = "Zona D - Palcos";
                    break;
                default:
                    System.out.println("Categoria invalida.");
                    return;
            }

            for (Descuento desc : descuentos) {
                if (desc.getTipo().equals(entrada)) {
                    descuento = desc.getPorcentaje();
                    break;
                }
            }

            // Aplicar descuento
            double total = precioBase - (precioBase * descuento);

            System.out.println("Boleta de la compra:");
            System.out.println("Ubicación del asiento: " + ubicacion);
            System.out.println("Precio base de la entrada: $" + precioBase);
            System.out.println("Descuento aplicado: " + (descuento * 100) + "%");
            System.out.println("Precio final a pagar: $" + total);

            System.out.println("¿Desea comprar otra entrada? (si/no)");
            continuar = scanner.nextLine();

        } while (continuar.equalsIgnoreCase("si"));
    }

    public static void inicializarAsientos() {
        for (int i = 0; i < asientos.length; i++) {
            asientos[i] = "Disponible";
        }
    }

    static class Venta {
        private int idVenta;
        private String ubicacionAsiento;
        private String detallesCliente;

        public Venta(int idVenta, String ubicacionAsiento, String detallesCliente) {
            this.idVenta = idVenta;
            this.ubicacionAsiento = ubicacionAsiento;
            this.detallesCliente = detallesCliente;
        }
    }

    static class Descuento {
        private String tipo;
        private double porcentaje;

        public Descuento(String tipo, double porcentaje) {
            this.tipo = tipo;
            this.porcentaje = porcentaje;
        }

        // Getters
        public String getTipo() {
            return tipo;
        }

        public double getPorcentaje() {
            return porcentaje;
        }
    }
}