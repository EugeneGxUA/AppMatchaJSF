package domain;


import auth.UserBean;
import auth.domain.PersonRoleEntity;
import userProfile.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Entity // Указание что данная сущность которая сохраняеться в БД, без анотации не сработает
@Table(name = "users")
public class UserEntity {


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<PersonRoleEntity> personRoleEntities;

    public List<PersonRoleEntity> getPersonRoleEntities() {
        return personRoleEntities;
    }

    public void setPersonRoleEntities(List<PersonRoleEntity> personRoleEntities) {
        this.personRoleEntities = personRoleEntities;
    }


    private long id;

    private long rating;
    private long fbId;

    private int gender;


    private boolean active;
    private boolean authenticate;

    @Id
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String sexOrientation;
    private String longitude;
    private String latitude;
    private String city;
    private String country;
    private String avatar;
    private String photo1;
    private String photo2;
    private String photo3;
    private String photo4;
    private String bio;
    private String tags;
    private LocalDate birthdate;
    private LocalDateTime lastVisit;

    public UserEntity() {
        rating = 0;
        fbId = 0;
        gender = 0;

        active = false;

        email = "";
        password = "";
        firstName = "";
        lastName = "";
        sexOrientation = "";
        longitude = "";
        latitude = "";
        city = "";
        country = "";
        avatar = "";
        photo1 = "";
        photo2 = "";
        photo3 = "";
        photo4 = "";
        bio = "";
        tags = "";
        lastVisit = LocalDateTime.now();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getFbId() {
        return fbId;
    }

    public void setFbId(long fbId) {
        this.fbId = fbId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public String getSexOrientation() {
        return sexOrientation;
    }

    public void setSexOrientation(String sexOrientation) {
        this.sexOrientation = sexOrientation;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhotos() {
        return photo1;
    }

    public void setPhotos(String photos) {
        this.photo1 = photos;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getPhoto4() {
        return photo4;
    }

    public void setPhoto4(String photo4) {
        this.photo4 = photo4;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDateTime getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
    }

    public boolean isAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(boolean authenticate) {
        this.authenticate = authenticate;
    }

    public UserBean toDto(UserBean user) {

        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setGender(this.getGender());
        user.setId(this.getId());
        user.setFbId(this.getFbId());
        user.setCity(this.getCity());
        user.setCountry(this.getCountry());
        user.setPhoto1(this.getPhoto1());
        user.setPhoto2(this.getPhoto2());
        user.setPhoto3(this.getPhoto3());
        user.setPhoto4(this.getPhoto4());
        user.setLatitude(this.getLatitude());
        user.setLongitude(this.getLongitude());
        user.setActive(this.isActive());
        user.setSexOrientation(this.getSexOrientation());
        user.setRating(this.getRating());
        user.setAvatar(this.getAvatar());
        user.setBio(this.getBio());
        user.setTags(this.getTags());
        user.setBirthdate(this.getBirthdate());
        user.setLastVisit(this.getLastVisit());
        user.setAuthenticate(this.isAuthenticate());

        return user;
    }

    public void fromDto(UserBean user) {

        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setGender(user.getGender());
        this.setId(user.getId());
        this.setFbId(user.getFbId());
        this.setCity(user.getCity());
        this.setCountry(user.getCountry());
        this.setPhoto1(user.getPhoto1());
        this.setPhoto2(user.getPhoto2());
        this.setPhoto3(user.getPhoto3());
        this.setPhoto4(user.getPhoto4());
        this.setLatitude(user.getLatitude());
        this.setLongitude(user.getLongitude());
        this.setActive(user.isActive());
        this.setSexOrientation(user.getSexOrientation());
        this.setRating(user.getRating());
        this.setAvatar(user.getAvatar());
        this.setBio(user.getBio());
        this.setTags(user.getTags());
        this.setBirthdate(user.getBirthdate());
        this.setLastVisit(user.getLastVisit());
        this.setAuthenticate(user.isAuthenticate());
    }
}
