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

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public List<String> getDias() {
        return dias;
    }

    public void setDias(List<String> dias) {
        this.dias = dias;
    }

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }

    public void setCantidadEstudiantes(int cantidadEstudiantes) {
        this.cantidadEstudiantes = cantidadEstudiantes;
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
