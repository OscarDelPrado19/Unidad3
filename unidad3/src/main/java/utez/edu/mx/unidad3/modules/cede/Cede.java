package utez.edu.mx.unidad3.modules.cede;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import utez.edu.mx.unidad3.modules.warehouse.Warehouse;

import java.util.List;

// Atributos propios de la entiddad
// Atributos de relacion
//Constructores
//Getters y setters
@Entity
@Table(name = "cede")
public class Cede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "clave", nullable = false, unique = true)
    private String clave;

    @Pattern(regexp = "^[A-Za-z][\\sA-Za-z]{5,}$", message = "Solo aceptamos letras")
    @NotNull(message = "Favor de ingresar un valor")
    @NotBlank(message = "El estado no puede estar vacío")
    @Column(name = "estado", nullable = false)
    private String estado;

    @Pattern(regexp = "^[A-Za-z][\\sA-Za-z]{5,}$", message = "Solo aceptamos letras")
    @NotNull(message = "Favor de ingresar un valor")
    @NotBlank(message = "El estado no puede estar vacío")
    @Column(name = "municipio", nullable = false)
    private String municipio;

    @OneToMany(mappedBy = "cede")
    @JsonIgnore
    private List<Warehouse> warehouses;

    public Cede() {
    }

    public Cede(Long id, String clave, String estado, String municipio, List<Warehouse> warehouses) {
        this.id = id;
        this.clave = clave;
        this.estado = estado;
        this.municipio = municipio;
        this.warehouses = warehouses;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
