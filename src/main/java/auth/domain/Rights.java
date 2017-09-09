package auth.domain;


import javax.persistence.*;

@Entity
@Table(name = "rights")
public class Rights {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private ResourceEntity resourceEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ResourceEntity getResourceEntity() {
        return resourceEntity;
    }

    public void setResourceEntity(ResourceEntity resourceEntity) {
        this.resourceEntity = resourceEntity;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
