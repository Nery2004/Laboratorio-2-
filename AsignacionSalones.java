import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter; 
import java.io.IOException; 

public class AsignacionSalones {
    private List<Sede> sedes;
    private List<Curso> cursos;

    public AsignacionSalones() {
        this.sedes = new ArrayList<>();
        this.cursos = new ArrayList<>();
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
            scanner.nextLine(); // Consumir la nueva línea

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
    
    // Solicitar datos al usuario
    System.out.print("ID de sede: ");
    int idSede = scanner.nextInt();
    scanner.nextLine(); // Consumir la nueva línea
    
    System.out.print("Edificio: ");
    char edificio = scanner.nextLine().charAt(0);
    
    System.out.print("Nivel: ");
    int nivel = scanner.nextInt();
    
    System.out.print("ID de salón: ");
    int idSalon = scanner.nextInt();
    
    System.out.print("Capacidad: ");
    int capacidad = scanner.nextInt();
    
    // Crear una nueva sede si no existe
    Sede sede = buscarSede(idSede);
    if (sede == null) {
        sede = new Sede(idSede);
        sedes.add(sede);
    }
    
    // Crear un nuevo edificio si no existe
    Edificio edif = sede.buscarEdificio(edificio);
    if (edif == null) {
        edif = new Edificio(String.valueOf(edificio));
        sede.agregarEdificio(edif);
    }
    
    // Crear un nuevo salón y agregarlo al edificio
    Salon salon = new Salon(idSalon, String.valueOf(edificio), capacidad);
    edif.agregarSalon(salon);
    
    System.out.println("Salón agregado exitosamente.");
}

public void cargarCursosDesdeTeclado(Scanner scanner) {
    System.out.println("Ingrese los datos del curso:");
    
    // Solicitar datos al usuario
    System.out.print("ID del curso: ");
    int idCurso = scanner.nextInt();
    scanner.nextLine(); // Consumir la nueva línea
    
    System.out.print("ID de sede: ");
    int idSede = scanner.nextInt();
    scanner.nextLine(); // Consumir la nueva línea
    
    System.out.print("Nombre del curso: ");
    String nombreCurso = scanner.nextLine();
    
    System.out.print("Horario (Ejemplo: 7:00 a.m. Lunes, Viernes): ");
    String horario = scanner.nextLine();
    
    System.out.print("Duración (1 a 3 horas): ");
    int duracion = scanner.nextInt();
    scanner.nextLine(); // Consumir la nueva línea
    
    System.out.print("Días (Separados por comas, Ejemplo: lunes,viernes): ");
    String dias = scanner.nextLine();
    
    System.out.print("Cantidad de estudiantes: ");
    int cantidadEstudiantes = scanner.nextInt();
    
    // Crear un nuevo curso
    Curso curso = new Curso(idCurso, idSede, nombreCurso, horario, duracion, dias, cantidadEstudiantes);
    
    // Agregar el curso a la lista de cursos
    cursos.add(curso);
    
    System.out.println("Curso agregado exitosamente.");
}
public void asignarSalones() {
    // Implementa la lógica de asignación de salones a cursos aquí
    for (Curso curso : cursos) {
        boolean asignado = false;
        for (Sede sede : sedes) {
            for (Edificio edificio : sede.getEdificios()) {
                for (Salon salon : edificio.getSalones()) {
                    // Verificar si el salón es válido para el curso
                    if (salon.puedeAsignarCurso(curso)) {
                        // Asignar el curso al salón
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
    
    // Mostrar cursos asignados
    System.out.println("Cursos asignados a salones:");
    for (Sede sede : sedes) {
        for (Edificio edificio : sede.getEdificios()) {
            for (Salon salon : edificio.getSalones()) {
                if (!salon.getCursosAsignados().isEmpty()) {
                    System.out.println("Sede: " + sede.getIdSede());
                    System.out.println("Edificio: " + edificio.getNombre());
                    System.out.println("Salón: " + salon.getIdSalon());
                    System.out.println("Horario:");
                    // Implementa la lógica para mostrar el horario del salón
                    System.out.println("Cursos:");
                    for (Curso curso : salon.getCursosAsignados()) {
                        System.out.println("ID: " + curso.getIdCurso());
                        System.out.println("Nombre: " + curso.getNombreCurso());
                        System.out.println("Horario: " + curso.getHorario());
                        System.out.println("Cantidad de Estudiantes: " + curso.getCantidadEstudiantes());
                    }
                    System.out.println();
                }
            }
        }
    }
    
    // Mostrar cursos no asignados
    System.out.println("Cursos no asignados:");
    for (Curso curso : cursosNoAsignados) {
        System.out.println("ID: " + curso.getIdCurso());
        System.out.println("Nombre: " + curso.getNombreCurso());
        System.out.println("Horario: " + curso.getHorario());
        System.out.println("Cantidad de Estudiantes: " + curso.getCantidadEstudiantes());
    }
}
public void exportarResultados() {
    try {
        FileWriter csvWriter = new FileWriter("resultados.csv");
        
        // Escribir encabezado del archivo CSV
        csvWriter.append("Sede,Edificio,Salon,Horario,Curso,Estudiantes\n");
        
        // Escribir datos de cursos asignados
        for (Sede sede : sedes) {
            for (Edificio edificio : sede.getEdificios()) {
                for (Salon salon : edificio.getSalones()) {
                    for (Curso curso : salon.getCursosAsignados()) {
                        // Formatear los datos como una fila CSV
                        String filaCSV = sede.getIdSede() + "," + edificio.getNombre() + "," +
                                         salon.getIdSalon() + "," + curso.getHorario() + "," +
                                         curso.getNombreCurso() + "," + curso.getCantidadEstudiantes() + "\n";
                        
                        csvWriter.append(filaCSV);
                    }
                }
            }
        }
        
        csvWriter.flush();
        csvWriter.close();
        
        System.out.println("Resultados exportados exitosamente a resultados.csv");
    } catch (IOException e) {
        System.err.println("Error al exportar resultados: " + e.getMessage());
    }
}

}
