import java.util.ArrayList;
import java.util.List;

public class AsignacionSalones {
    private List<Sede> sedes;
    private List<Curso> cursos;

    public AsignacionSalones() {
        this.sedes = new ArrayList<>();
        this.cursos = new ArrayList<>();
    }

    public static void main(String[] args) {
        AsignacionSalones asignador = new AsignacionSalones();

        // Ejemplo: Crear una sede y un edificio
        Sede sede = new Sede(1);
        Edificio edificio = new Edificio("Edificio A");
        sede.agregarEdificio(edificio);

        // Ejemplo: Crear un salón
        Salon salon = new Salon(101, "A", 30);
        edificio.agregarSalon(salon);

        // Ejemplo: Crear un curso
        Curso curso = new Curso(1, "Programación", "Lunes 9:00 AM", 2, List.of("Lunes"), 20);
        asignador.cursos.add(curso);

        // Ejemplo: Generar informe
        asignador.generarInforme();
    }

    public void generarInforme() {
        // Implementa la lógica para generar el informe aquí
        System.out.println("Informe generado.");
    }

    // Otros métodos para cargar archivos CSV, asignar salones, etc.
}
