public class Salon {
    private int id;
    private String edificio;
    private int capacidad;

    public Salon(int id, String edificio, int capacidad) {
        this.id = id;
        this.edificio = edificio;
        this.capacidad = capacidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Salon{" +
                "id=" + id +
                ", edificio='" + edificio + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }
}
