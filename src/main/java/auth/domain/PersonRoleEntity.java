package auth.domain;

import domain.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "personrole")
public class PersonRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
