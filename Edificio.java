import java.util.ArrayList;
import java.util.List;

public class Edificio {
    private String nombre;
    private List<Salon> salones;

    public Edificio(String nombre) {
        this.nombre = nombre;
        this.salones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Salon> getSalones() {
        return salones;
    }

    public void agregarSalon(Salon salon) {
        salones.add(salon);
    }

    public void eliminarSalon(Salon salon) {
        salones.remove(salon);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Edificio{");
        sb.append("nombre='").append(nombre).append('\'');
        sb.append(", salones=").append(salones);
        sb.append('}');
        return sb.toString();
    }
}
