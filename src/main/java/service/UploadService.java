package service;

import auth.UserBean;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;


@LocalBean
@Stateless
public class UploadService {

    public UploadService(){
    }


    public void uploadByURL(UserBean user) {
        try {
            URL url;
            URLConnection connection;
            url = new URL(user.getPhoto1()); //Формирование url-адреса файла
            connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            if (user.getFbId() != 0) {
                Path path = Paths.get("/nfs/2016/e/egaragul/IdeaProjects/AppMatchaJSF/web/WEB-INF/users/" + user.getFbId() + "/" + user.getFbId() + ".jpg");
                if (!new File("/nfs/2016/e/egaragul/IdeaProjects/AppMatchaJSF/web/WEB-INF/users/" + user.getFbId()).exists()) {
                    new File("/nfs/2016/e/egaragul/IdeaProjects/AppMatchaJSF/web/WEB-INF/users/" + user.getFbId()).mkdir();
                }
                if (!new File(path.toString()).exists()) {
                    Files.createFile(path);
                    BufferedInputStream reader = new BufferedInputStream(connection.getInputStream());
                    this.upload(path, reader);
                }
            }
            else {
                Path path = Paths.get("/nfs/2016/e/egaragul/IdeaProjects/AppMatchaJSF/web/WEB-INF/users/" + user.getId() + "/" + user.getId() + ".jpg");
                if (new File("/nfs/2016/e/egaragul/IdeaProjects/AppMatchaJSF/web/WEB-INF/users/" + user.getId()).exists()) {
                    new File("/nfs/2016/e/egaragul/IdeaProjects/AppMatchaJSF/web/WEB-INF/users/" + user.getId()).mkdir();
                }
                if (new File(path.toString()).exists()) {
                    Files.createFile(path);
                    BufferedInputStream reader = new BufferedInputStream(connection.getInputStream());
                    this.upload(path, reader);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadByForm(UserBean user) {
        try {
            if (user.getAvatarFile() != null) {
                Path path = Paths.get("/private/tmp/library.egaragul/Containers/MAPP/apache2/htdocs/users/" + user.getEmail() + "/" + user.getEmail() + ".jpg");
                if (new File(path.toString()).exists()) {
                    Files.delete(path);
                }
                if (!new File(path.toString()).exists()) {
                    Files.createFile(path);
                    BufferedInputStream reader = new BufferedInputStream(user.getAvatarFile().getInputStream());
                    this.upload(path, reader);
                    user.setAvatar("http://localhost:9995/users/" + user.getEmail() + "/" + user.getEmail() + ".jpg");
                    user.setAvatarFile(null);
                }
            }
            if (user.getPhoto1File() != null) {
                Instant time = Instant.now();

                Path photoPath = Paths.get("http://localhost:9995/users/" + user.getEmail() + "/" + time + ".jpg");
                Path path = Paths.get("/private/tmp/library.egaragul/Containers/MAPP/apache2/htdocs/users/" + user.getEmail() + "/" + time.toString() + ".jpg");
                if (new File(path.toString()).exists()) {
                    Files.delete(path);
                }
                if (!new File(path.toString()).exists()) {
                    Files.createFile(path);
                    BufferedInputStream reader = new BufferedInputStream(user.getPhoto1File().getInputStream());
                    this.upload(path, reader);
                    user.setPhoto1(photoPath.toString());
                    user.setPhoto1File(null);
                }
            }
            if (user.getPhoto2File() != null) {
                Instant time = Instant.now();

                Path photoPath = Paths.get("http://localhost:9995/users/" + user.getEmail() + "/" + time + ".jpg");
                Path path = Paths.get("/private/tmp/library.egaragul/Containers/MAPP/apache2/htdocs/users/" + user.getEmail() + "/" + time.toString() + ".jpg");
                if (new File(path.toString()).exists()) {
                    Files.delete(path);
                }
                if (!new File(path.toString()).exists()) {
                    Files.createFile(path);
                    BufferedInputStream reader = new BufferedInputStream(user.getPhoto2File().getInputStream());
                    this.upload(path, reader);
                    user.setPhoto2(photoPath.toString());
                    user.setPhoto2File(null);
                }
            }
            if (user.getPhoto3File() != null) {
                Instant time = Instant.now();

                Path photoPath = Paths.get("http://localhost:9995/users/" + user.getEmail() + "/" + time + ".jpg");
                Path path = Paths.get("/private/tmp/library.egaragul/Containers/MAPP/apache2/htdocs/users/" + user.getEmail() + "/" + time.toString() + ".jpg");
                if (new File(path.toString()).exists()) {
                    Files.delete(path);
                }
                if (!new File(path.toString()).exists()) {
                    Files.createFile(path);
                    BufferedInputStream reader = new BufferedInputStream(user.getPhoto3File().getInputStream());
                    this.upload(path, reader);
                    user.setPhoto3(photoPath.toString());
                    user.setPhoto3File(null);
                }
            }
            if (user.getPhoto4File() != null) {
                Instant time = Instant.now();

                Path photoPath = Paths.get("http://localhost:9995/users/" + user.getEmail() + "/" + time + ".jpg");
                Path path = Paths.get("/private/tmp/library.egaragul/Containers/MAPP/apache2/htdocs/users/" + user.getEmail() + "/" + time.toString() + ".jpg");
                if (new File(path.toString()).exists()) {
                    Files.delete(path);
                }
                if (!new File(path.toString()).exists()) {
                    Files.createFile(path);
                    BufferedInputStream reader = new BufferedInputStream(user.getPhoto3File().getInputStream());
                    this.upload(path, reader);
                    user.setPhoto4(photoPath.toString());
                    user.setPhoto4File(null);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void upload(Path path, BufferedInputStream reader) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(path.toString()));


        int ch;
        while ((ch = reader.read()) != -1) {
            fos.write(ch);
        }

        reader.close();
        fos.flush();
        fos.close();
    }
}