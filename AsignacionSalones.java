import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class AsignacionSalones {
    private List<Sede> sedes;
    private List<Curso> cursos;
    private List<Curso> cursosNoAsignados;

    public AsignacionSalones() {
        this.sedes = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.cursosNoAsignados = new ArrayList<>();
    }
    public Sede buscarSede(int idSede) {
        for (Sede sede : sedes) {
            if (sede.getId() == idSede) {
                return sede;
            }
        }
        return null;
    }

    public Edificio buscarEdificio(Sede sede, char edificio) {
        for (Edificio edif : sede.getEdificios()) {
            if (edif.getNombre().charAt(0) == edificio) {
                return edif;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        AsignacionSalones asignador = new AsignacionSalones();
        Scanner scanner = new Scanner(System.in);

        boolean salir = false;

        while (!salir) {
            System.out.println("Menu Principal:");
            System.out.println("1. Cargar información de salones desde el teclado");
            System.out.println("2. Cargar información de cursos desde el teclado");
            System.out.println("3. Asignar salones a cursos");
            System.out.println("4. Generar informe");
            System.out.println("5. Exportar resultados a CSV");
            System.out.println("6. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    asignador.cargarSalonesDesdeTeclado(scanner);
                    break;
                case 2:
                    asignador.cargarCursosDesdeTeclado(scanner);
                    break;
                case 3:
                    asignador.asignarSalones();
                    break;
                case 4:
                    asignador.generarInforme();
                    break;
                case 5:
                    asignador.exportarResultados();
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        }

        System.out.println("¡Gracias por utilizar el programa!");
    }

    public void cargarSalonesDesdeTeclado(Scanner scanner) {
        System.out.println("Ingrese los datos del salón:");

        System.out.print("ID de sede: ");
        int idSede = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Edificio: ");
        char edificio = scanner.nextLine().charAt(0);

        System.out.print("Nivel: ");
        int nivel = scanner.nextInt();

        System.out.print("ID de salón: ");
        int idSalon = scanner.nextInt();

        System.out.print("Capacidad: ");
        int capacidad = scanner.nextInt();

        Sede sede = buscarSede(idSede);
        if (sede == null) {
            sede = new Sede(idSede);
            sedes.add(sede);
        }

        Edificio edif = buscarEdificio(sede, edificio);
        if (edif == null) {
            edif = new Edificio(String.valueOf(edificio));
            sede.agregarEdificio(edif);
        }

        Salon salon = new Salon(idSalon, String.valueOf(edificio), capacidad);
        edif.agregarSalon(salon);

        System.out.println("Salón agregado exitosamente.");
    }

    public void cargarCursosDesdeTeclado(Scanner scanner) {
        System.out.println("Ingrese los datos del curso:");

        System.out.print("ID del curso: ");
        int idCurso = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID de sede: ");
        int idSede = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre del curso: ");
        String nombreCurso = scanner.nextLine();

        System.out.print("Horario (Ejemplo: 7:00 a.m. Lunes, Viernes): ");
        String horario = scanner.nextLine();

        System.out.print("Duración (1 a 3 horas): ");
        int duracion = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Días (Separados por comas, Ejemplo: lunes,viernes): ");
        String diasString = scanner.nextLine();
        List<String> dias = Arrays.asList(diasString.split(","));

        System.out.print("Cantidad de estudiantes: ");
        int cantidadEstudiantes = scanner.nextInt();

        Curso curso = new Curso(idCurso, idSede, nombreCurso, horario, duracion, dias, cantidadEstudiantes);
        cursos.add(curso);

        System.out.println("Curso agregado exitosamente.");
    }

public void asignarSalones() {
        for (Curso curso : cursos) {
            boolean asignado = false;
            for (Sede sede : sedes) {
                for (Edificio edificio : sede.getEdificios()) {
                    for (Salon salon : edificio.getSalones()) {
                        if (salon.puedeAsignarCurso(curso)) {
                            salon.asignarCurso(curso);
                            asignado = true;
                            break;
                        }
                    }
                    if (asignado) {
                        break;
                    }
                }
                if (asignado) {
                    break;
                }
            }
            if (!asignado) {
                cursosNoAsignados.add(curso);
            }
        }

        System.out.println("Asignación de salones completada.");
    }

    public void generarInforme() {
        System.out.println("Informe de Asignación de Salones:");

        System.out.println("Cursos asignados a salones:");
        for (Sede sede : sedes) {
            for (Edificio edificio : sede.getEdificios()) {
                for (Salon salon : edificio.getSalones()) {
                    if (!salon.getCursosAsignados().isEmpty()) {
                        System.out.println("Sede: " + sede.getId());
                        System.out.println("Edificio: " + edificio.getNombre());
                        System.out.println("Salón: " + salon.getId());
                        System.out.println("Horario:");
                        for (Curso curso : salon.getCursosAsignados()) {
                            System.out.println("ID: " + curso.getId());
                            System.out.println("Nombre: " + curso.getNombre());
                            System.out.println("Horario: " + curso.getHorario());
                            System.out.println("Cantidad de Estudiantes: " + curso.getCantidadEstudiantes());
                        }
                        System.out.println();
                    }
                }
            }
        }

        System.out.println("Cursos no asignados:");
        for (Curso curso : cursosNoAsignados) {
            System.out.println("ID: " + curso.getId());
            System.out.println("Nombre: " + curso.getNombre());
            System.out.println("Horario: " + curso.getHorario());
            System.out.println("Cantidad de Estudiantes: " + curso.getCantidadEstudiantes());
            System.out.println();
        }
    }
public void exportarResultados() {
        try {
            FileWriter cursosCsvWriter = new FileWriter("cursos.csv");
            FileWriter salonesCsvWriter = new FileWriter("salones.csv");

            // Escribir encabezado del archivo CSV de cursos
            cursosCsvWriter.append("Sede,Edificio,Salon,Horario,Curso,Estudiantes\n");

            // Escribir encabezado del archivo CSV de salones
            salonesCsvWriter.append("ID,Edificio,Nivel,Salon,Capacidad\n");

            // Escribir datos de cursos asignados
            for (Sede sede : sedes) {
                for (Edificio edificio : sede.getEdificios()) {
                    for (Salon salon : edificio.getSalones()) {
                        for (Curso curso : salon.getCursosAsignados()) {
                            // Formatear los datos como una fila CSV para cursos
                            String filaCursoCSV = sede.getId() + "," + edificio.getNombre() + "," +
                                                 salon.getIdSalon() + "," + curso.getHorario() + "," +
                                                 curso.getNombreCurso() + "," + curso.getCantidadEstudiantes() + "\n";

                            cursosCsvWriter.append(filaCursoCSV);
                        }
                    }
                }
            }

            // Escribir datos de los salones
            for (Sede sede : sedes) {
                for (Edificio edificio : sede.getEdificios()) {
                    for (Salon salon : edificio.getSalones()) {
                        // Formatear los datos como una fila CSV para salones
                        String filaSalonCSV = salon.getIdSalon() + "," + edificio.getNombre() + "," +
                                             salon.getNivel() + "," + salon.getNombreSalon() + "," +
                                             salon.getCapacidad() + "\n";

                        salonesCsvWriter.append(filaSalonCSV);
                    }
                }
            }

            cursosCsvWriter.flush();
            cursosCsvWriter.close();

            salonesCsvWriter.flush();
            salonesCsvWriter.close();

            System.out.println("Archivos CSV 'cursos.csv' y 'salones.csv' exportados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al exportar resultados: " + e.getMessage());
        }
}
}
