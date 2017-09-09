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



    @GeneratedValue(strategy = GenerationType.AUTO) //Автоматическая генерация ID
    private long id;

    private long rating;
    private long fbId;

    private int gender;


    private boolean active;

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
    private String photos;
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
        photos = "";
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
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
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

    //    @Override
//    public String toString() {
//        return "E-mail: " + getEmail() + "\n" +
//                "Password: " + getPassword() + "\n" +
//                "FirstName: " + getFirstName() + "\n" +
//                "LastName: " + getLastName() + "\n" +
//                "Gender: " + getGender() + "\n" +
//                "Age: " + getAge() + "\n" +
//                "latitude: " + getLatitude() + "\n" +
//                "longitude: " + getLongitude() + "\n" +
//                "country: " + getCountry() + "\n" +
//                "city: " + getCity() + "\n" +
//                "Photo: " + getPhoto() + "\n" +
//                "FB_ID: " + getFbId() + "\n" +
//                "Active ? : " + getActive() + "\n" +
//                "ID: " + getId() + "\n";
//    }

    public UserBean toDto() {
        UserBean user = new UserBean();

        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setGender(this.getGender());
        user.setId(this.getId());
        user.setFbId(this.getFbId());
        user.setCity(this.getCity());
        user.setCountry(this.getCountry());
        user.setPhotos(this.getPhotos());
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
        this.setPhotos(user.getPhotos());
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

    }
}
