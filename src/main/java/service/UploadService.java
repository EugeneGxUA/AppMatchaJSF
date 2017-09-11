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


@LocalBean
@Stateless
public class UploadService {

    public UploadService(){
    }


    public void uploadByURL(UserBean user) {
        try {
            URL url;
            URLConnection connection;
            url = new URL(user.getPhotos()); //Формирование url-адреса файла
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
                    this.upload(user, path, "", reader);
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
                    this.upload(user, path, "", reader);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadByForm(UserBean user, Part filePart) {
        try {

            String fileName = filePart.getName().toString();
            ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();

            //Path path = Paths.get(extContext.getRealPath("/WEB-INF/users/" + user.getEmail() + "/" + user.getEmail() + ".jpg"));
            Path path = Paths.get("/private/tmp/library.egaragul/Containers/users/" + user.getEmail() + "/" + user.getEmail() + ".jpg");
            if (new File(path.toString()).exists()) {
                Files.delete(path);
            }
            if (!new File(path.toString()).exists()) {
                Files.createFile(path);
                BufferedInputStream reader = new BufferedInputStream(filePart.getInputStream());
                this.upload(user, path, fileName, reader);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void upload(UserBean user, Path path, String filename, BufferedInputStream reader) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(path.toString()));


        int ch;
        while ((ch = reader.read()) != -1) {
            fos.write(ch);
        }
        user.setAvatar(path.toString());
        user.setAvatarFile(null);
        reader.close();
        fos.flush();
        fos.close();
    }
}