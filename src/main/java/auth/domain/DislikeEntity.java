package auth.domain;

import domain.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "dislikes")
public class DislikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity userEntity;

    private String whoDislike;

    public String getWhoLike() {
        return whoDislike;
    }

    public void setWhoLike(String whoLike) {
        this.whoDislike = whoLike;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
