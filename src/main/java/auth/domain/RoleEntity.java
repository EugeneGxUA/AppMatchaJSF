package auth.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    private String code;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "roleEntity")
    private List<RightsEntity> rights;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "roleEntity")
    private List<PersonRoleEntity> personRoleEntities;

    public List<PersonRoleEntity> getPersonRoleEntities() {
        return personRoleEntities;
    }

    public void setPersonRoleEntities(List<PersonRoleEntity> personRoleEntities) {
        this.personRoleEntities = personRoleEntities;
    }

    public String getCode() {
        return code;

    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<RightsEntity> getRights() {
        return rights;
    }

    public void setRights(List<RightsEntity> rights) {
        this.rights = rights;
    }
}
