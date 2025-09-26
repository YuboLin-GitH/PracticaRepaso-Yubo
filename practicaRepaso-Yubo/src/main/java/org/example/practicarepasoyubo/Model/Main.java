package org.example.practicarepasoyubo.Model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * ClassName: Main
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author Yubo
 * @Create 20/09/2025 17:53
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean quireSalir = false;
        GestorUsuarios gestor = new GestorUsuarios();



        while (!quireSalir) {
            System.out.println("\n-------Sistema de  Academia informática --------");
            System.out.println("  1  - Dar de alta a un nuevo Usuario");
            System.out.println("  2  - Listar y mostrar los usuarios");
            System.out.println("  3  - Eliminar usuario");
            System.out.println("  4  - Establecer el salario de un Profesor");
            System.out.println("  5  - Salir");
            System.out.println("  6  - Generar nómina de un trabajador");
            System.out.print("Opción es: ");

            int opcion;
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            }catch (Exception e) {
                System.out.println("Error: Ingrese un número entre 1 y 6.");
                scanner.nextLine();
                continue;
            }


            switch (opcion) {
                case 1:
                    darAltaUsuario(gestor, scanner);
                    break;
                case 2:
                    listarUsuarios(gestor, scanner);
                    break;
                case 3:
                    eliminarUsuarios(gestor, scanner);
                    break;
                case 4:
                    calcularSalarioProfesor(gestor, scanner);
                    break;
                case 5:
                    quireSalir = true;
                    break;
                case 6:
                    generarNomina(gestor, scanner);
                    break;
                default:
                    System.out.println("opcion invalida, tiene que ser 1-6");
                    System.out.println();
            }

        }



        scanner.close();
    }


    private static void darAltaUsuario(GestorUsuarios gestor, Scanner scanner) {
        System.out.println("----------Agregar un nuevo usuario---------------");

        try {

            System.out.println("tipo de usuario: 0 - Estudiante, 1 - Profesor");
            int tipo = scanner.nextInt();
            scanner.nextLine();



            System.out.print("Introduzca su ID: ");
            String entrada = scanner.nextLine();
            int id;
            try {
                id = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                return;
            }

            System.out.println("Introduzca su nombre：");
            String nombre = scanner.nextLine();
            System.out.println("Introduzca su email：");
            String email = scanner.nextLine();
            System.out.println("Introduzca su contraseña：");
            String password = scanner.nextLine();




            if (tipo == 0) {

                System.out.print("Introduzca su curso：");
                String curso = scanner.nextLine();
                Estudiante estudiante = new Estudiante(id, nombre, email, password , curso);
                System.out.println();
                gestor.agregarUsuario(estudiante);
                System.out.println();

            }else if (tipo == 1) {
                System.out.println("Introduzca la especialidad: ");
                String especialidad = scanner.nextLine();

                System.out.println("Introduzca la fecha de cominzacion (forma:dd/mm/yyyy)");
                String fechaString = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fecha = LocalDate.parse(fechaString, formatter);

                System.out.println("Introduzca la IRPF (si es 20% introduzca: 20) ");
                int IRPF = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Seleccione una posición（0=DIRECTOR，1=SECRETARIO，2=PROFESOR）：");
                System.out.println("0 - DIRECTOR");
                System.out.println("1 - SECRETARIO");
                System.out.println("2 - PROFESOR（por defecto）");
                System.out.print("Selecciona una opcion: ：");
                String cargoString = scanner.nextLine().trim();
                Cargo cargo;
                if (cargoString.isEmpty()) {
                    cargo = Cargo.Profesor;
                } else {
                    switch (cargoString) {
                        case "0":
                            cargo = Cargo.Director;
                            break;
                        case "1":
                            cargo = Cargo.Secretario;
                            break;
                        case "2":
                            cargo = Cargo.Profesor;
                            break;
                        default:
                            System.out.println("Entrada no válida, configuración predeterminada Profesor");
                            cargo = Cargo.Profesor;
                    }
                }
                Profesor profesor = new Profesor(id, nombre, email, password ,especialidad,
                        fecha, IRPF, cargo);


                System.out.println();
                gestor.agregarUsuario(profesor);
                System.out.println();

            }else{
                System.out.println("Error: Tipo de usuario invalido.");
            }


        } catch (Exception e) {
            System.out.println("error：" + e.getMessage());
        }
    }


    private static void listarUsuarios(GestorUsuarios gestor, Scanner scanner) {
        System.out.println("\n----------Listar Usuarios---------------");
        System.out.println("Seleccione tipo de usuario para listar：");
        System.out.println("0 - Estudiantes");
        System.out.println("1 - Profesores");
        System.out.print("Opción：");

        int tipo = scanner.nextInt();
        scanner.nextLine();
        if (tipo != 0 && tipo != 1) {
            System.out.println("Error: Opción inválida, debe ser 0 (Estudiantes) o 1 (Profesores)");
            System.out.println();
            return;
        }

            List<String> usuarios = gestor.listarUsuarios(tipo);
            System.out.println("--- Resultado ---");
            for (String u : usuarios) {
                System.out.println(u);
            }
            System.out.println();

    }

    private static void eliminarUsuarios(GestorUsuarios gestor, Scanner scanner) {
        System.out.println("\n----------Eliminar Usuarios---------------");


        System.out.print("Introduzca el ID del usuario que desea eliminar: ");
        String idIntroduce = scanner.nextLine();

        int id;
        try {
            id = Integer.parseInt(idIntroduce);
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número ");
            return;
        }

        try {
            gestor.eliminarUsuario(id);
            System.out.println("id :" + id + " del usuario eliminado");

        }catch (Exception e) {
            System.out.println("Error al eliminar el usuario: " + e.getMessage());
        }
        System.out.println();

    }

    private static void calcularSalarioProfesor(GestorUsuarios gestor, Scanner scanner) {
        System.out.println("\n----------Calcular Salario Profesor---------------");

        System.out.print("Introduzca el ID del profesor: ");
        String idProfesor = scanner.nextLine();
        int id;
        try {
            id = Integer.parseInt(idProfesor);
        } catch (NumberFormatException e) {
            System.out.println("Error: ID debe ser un número válido");
            return;
        }

        Profesor profesorEncontrado = null;

        for (Usuario usuario : gestor.getUsuarios()) {

            if (usuario instanceof Profesor && usuario.getId() == id) {
                profesorEncontrado = (Profesor) usuario;
                break;
            }
        }


        if (profesorEncontrado == null) {
            System.out.println("Error: Profesor no encontrado con ID " + id);
            return;
        }

        System.out.print("Introduzca fecha de cálculo (dd/mm/yyyy，Enter=hoy): ");
        String fechaStr = scanner.nextLine();
        LocalDate fechaCalculo;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            fechaCalculo = fechaStr.isEmpty() ? LocalDate.now() : LocalDate.parse(fechaStr, formatter);
        } catch (Exception e) {
            System.out.println("Error: Formato de fecha inválido（dd/mm/yyyy）");
            return;
        }

        double salario = profesorEncontrado.calcular_salario(fechaCalculo);
        System.out.printf("\n--- Salario de %s ---%n", profesorEncontrado.getNombre());
        System.out.printf("Fecha de cálculo: %s%n", fechaCalculo.format(formatter));
        System.out.printf("Salario neto: %.2f €%n", salario);
        System.out.println();
    }

    private static void generarNomina(GestorUsuarios gestor, Scanner scanner) {
        System.out.println("\n----------Generar Nómina de Trabajador---------------");

        System.out.print("Introduzca el ID del profesor: ");
        String idStr = scanner.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("Error: ID debe ser un número válido");
            return;
        }


        Profesor profesor = null;
        for (Usuario u : gestor.getUsuarios()) {
            if (u instanceof Profesor && u.getId() == id) {
                profesor = (Profesor) u;
                break;
            }
        }
        if (profesor == null) {
            System.out.println("Error: Profesor no encontrado con ID " + id);
            return;
        }


        System.out.print("Introduzca fecha de la nómina (dd/mm/yyyy): ");
        String fechaStr = scanner.nextLine().trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNomina;
        try {
            fechaNomina = LocalDate.parse(fechaStr, formatter);
        } catch (Exception e) {
            System.out.println("Error: Formato de fecha inválido（dd/mm/yyyy）");
            return;
        }


        try {
            String rutaFichero = profesor.generarNomina(fechaNomina);
            System.out.println("Nómina generada con éxito en: " + rutaFichero);
        } catch (IOException e) {
            System.out.println("Error al generar la nómina: " + e.getMessage());
        }
    }
}
