package auth.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    private String code;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "role")
    private List<Rights> rights;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "role")
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

    public List<Rights> getRights() {
        return rights;
    }

    public void setRights(List<Rights> rights) {
        this.rights = rights;
    }
}
