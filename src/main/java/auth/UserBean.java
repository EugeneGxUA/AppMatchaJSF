package auth;



import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.servlet.http.Part;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Named
@SessionScoped
public class UserBean implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO) //Автоматическая генерация ID
    private long id;
    private long rating;
    private long fbId;

    private int gender;

    private boolean active;
    private boolean authenticate;

   // private String initialRequestURI;

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
    private String birth;
    private LocalDateTime lastVisit;
    private Part avatarFile;
    private Part photo1File;
    private Part photo2File;
    private Part photo3File;
    private Part photo4File;


    public UserBean() {
        rating = 0;
        fbId = 0;
        gender = 0;
        active = false;
        birth = "";
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

    @EJB
    private SignInBean signInBean;

    @EJB
    private SignUpBean signUpBean;

    @EJB
    private ProfileUpdateBean profileUpdateBean;

    @EJB
    private LogOutBean logOutBean;

    public boolean doSignIn() {
        if (email == null || password == null) {
            return false;
        }

        if (signInBean.doSignIn(this)) {
            this.toString();
            return true;
        }
        return false;
    }

    public boolean doSignUp() {
        return signUpBean.doSignUp(this);
    }

    public boolean doProfileUpdate() {
        profileUpdateBean.doProfileUpdate(this);
        return true;
    }

    public boolean doLogOut() {
        return logOutBean.doLogOut(this);
    }

    public boolean letsMatcha() {
        return signInBean.doMatcha(this);
    }

    public boolean doUploadPhoto() {
        profileUpdateBean.doUploadPhoto(this);
        return true;
    }

    //TODO -> finish work with JSON Location
    public void getClosestStations() {
        String[] strings = {"abc", "def", "ghi"};
        Gson gson = new Gson();

    }





    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public String getSexOrientation() {
        return sexOrientation;
    }

    public void setSexOrientation(String sexOrientation) {
        this.sexOrientation = sexOrientation;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getBirth() {
        if (this.birthdate != null) {
            this.birth = this.birthdate.toString();
        }
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.setBirthdate(LocalDate.parse(birth, formatter));
    }

    public Part getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(Part avatarFile) {

        this.avatarFile = avatarFile;
    }

    public boolean isAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(boolean authenticate) {
        this.authenticate = authenticate;
    }

    public Part getPhoto1File() {
        return photo1File;
    }

    public void setPhoto1File(Part photo1File) {
        this.photo1File = photo1File;
    }

    public Part getPhoto2File() {
        return photo2File;
    }

    public void setPhoto2File(Part photo2File) {
        this.photo2File = photo2File;
    }

    public Part getPhoto3File() {
        return photo3File;
    }

    public void setPhoto3File(Part photo3File) {
        this.photo3File = photo3File;
    }

    public Part getPhoto4File() {
        return photo4File;
    }

    public void setPhoto4File(Part photo4File) {
        this.photo4File = photo4File;
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
                "Photos: " + getPhoto1() + "\n" +
                "BIO: " + getBio() + "\n" +
                "Interests: " + getTags() + "\n" +
                "Birth Date: " + getBirthdate() + "\n" +
                "Last Online Date: " + getLastVisit() + "\n" +
                "latitude: " + getLatitude() + "\n" +
                "longitude: " + getLongitude() + "\n" +
                "country: " + getCountry() + "\n" +
                "city: " + getCity() + "\n" +
                "FB_ID: " + getFbId() + "\n" +
                "Active ? : " + isActive() + "\n" +
                "ID: " + getId() + "\n";
    }
}
