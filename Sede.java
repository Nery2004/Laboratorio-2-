import java.util.ArrayList;
import java.util.List;

public class Sede {
    private int id;
    private List<Edificio> edificios;

    public Sede(int id) {
        this.id = id;
        this.edificios = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Edificio> getEdificios() {
        return edificios;
    }

    public void agregarEdificio(Edificio edificio) {
        edificios.add(edificio);
    }

    public void eliminarEdificio(Edificio edificio) {
        edificios.remove(edificio);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sede{");
        sb.append("id=").append(id);
        sb.append(", edificios=").append(edificios);
        sb.append('}');
        return sb.toString();
    }
}
