package auth.domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "resource")
public class ResourceEntity {
    @Id
    private String id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "resourceEntity")
    private List<RightsEntity> rights;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
