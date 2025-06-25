package utez.edu.mx.unidad3.modules.cede;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "municipio", nullable = false)
    private String municipio;

    @OneToMany(mappedBy = "cede")
    @JsonIgnore
    private List<Warehouse> warehouses;
}
