import java.util.List;

public class Curso {
    private int id;
    private String nombre;
    private String horario;
    private int duracion;
    private List<String> dias;
    private int cantidadEstudiantes;

    public Curso(int id, String nombre, String horario, int duracion, List<String> dias, int cantidadEstudiantes) {
        this.id = id;
        this.nombre = nombre;
        this.horario = horario;
        this.duracion = duracion;
        this.dias = dias;
        this.cantidadEstudiantes = cantidadEstudiantes;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHorario() {
        return horario;
    }

    public int getDuracion() {
        return duracion;
    }

    public List<String> getDias() {
        return dias;
    }

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", horario='" + horario + '\'' +
                ", duracion=" + duracion +
                ", dias=" + dias +
                ", cantidadEstudiantes=" + cantidadEstudiantes +
                '}';
    }
}
