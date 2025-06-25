package utez.edu.mx.unidad3.modules.warehouse;

import jakarta.persistence.*;
import utez.edu.mx.unidad3.modules.cede.Cede;
import utez.edu.mx.unidad3.modules.clients.Client;

@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "clave", nullable = false,unique = true)
    private String clave;

    @Column(name = "sell_price",nullable = false)
    private Double sell_price;

    @Column(name = "rent_price",nullable = false)
    private Double rent_price;

    @ManyToOne
    @JoinColumn(name = "id_cede", nullable = false)
    private Cede cede;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    public Warehouse() {
    }

    public Warehouse(Long id, String clave, Double sell_price, Double rent_price, Cede cede, Client client) {
        this.id = id;
        this.clave = clave;
        this.sell_price = sell_price;
        this.rent_price = rent_price;
        this.cede = cede;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Double getSell_price() {
        return sell_price;
    }

    public void setSell_price(Double sell_price) {
        this.sell_price = sell_price;
    }

    public Double getRent_price() {
        return rent_price;
    }

    public void setRent_price(Double rent_price) {
        this.rent_price = rent_price;
    }

    public Cede getCede() {
        return cede;
    }

    public void setCede(Cede cede) {
        this.cede = cede;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
