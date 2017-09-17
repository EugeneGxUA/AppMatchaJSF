package auth.domain;


import domain.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class LikesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity userEntity;

    private String whoLike;

    public String getWhoLike() {
        return whoLike;
    }

    public void setWhoLike(String whoLike) {
        this.whoLike = whoLike;
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
