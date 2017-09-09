package userProfile;

import java.time.LocalDate;
import java.util.Date;

public class User {

    private long id;
    private long rating;
    private long fbId;

    private int gender;
    private int age;


    private boolean active;

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
    private LocalDate lastVisit;


    public User() {
        rating = 0;
        fbId = 0;
        gender = 0;
        age = 0;
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
        birthdate = LocalDate.now();
        lastVisit = LocalDate.now();
    }





    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public LocalDate getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDate lastVisit) {
        this.lastVisit = lastVisit;
    }

    @Override
    public String toString() {
        return "E-mail: " + getEmail() + "\n" +
                "Password: " + getPassword() + "\n" +
                "FirstName: " + getFirstName() + "\n" +
                "LastName: " + getLastName() + "\n" +
                "Gender: " + getGender() + "\n" +
                "Orientation: " + getSexOrientation() + "\n" +
                "Rating: " + getRating() + "\n" +
                "Avatar: " + getAvatar() + "\n" +
                "Photos: " + getPhotos() + "\n" +
                "BIO: " + getBio() + "\n" +
                "Interests: " + getTags() + "\n" +
                "Birth Date: " + getBirthdate() + "\n" +
                "Last Online Date: " + getLastVisit() + "\n" +
                "Age: " + getAge() + "\n" +
                "latitude: " + getLatitude() + "\n" +
                "longitude: " + getLongitude() + "\n" +
                "country: " + getCountry() + "\n" +
                "city: " + getCity() + "\n" +
                "FB_ID: " + getFbId() + "\n" +
                "Active ? : " + isActive() + "\n" +
                "ID: " + getId() + "\n";
    }
}
