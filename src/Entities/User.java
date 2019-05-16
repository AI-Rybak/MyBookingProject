package Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Getter @Setter
public class User implements Serializable {
    private long id_user;
    private String nameUser;
    private String surnameUser;
    private String mail;
    private String login;
    private String password;

    public User(String nameUser, String surnameUser, String mail, String login, String password) {
        this.nameUser = nameUser;
        this.surnameUser = surnameUser;
        this.mail = mail;
        this.login = login;
        this.password = password;
    }
//    public String getNameUser () {
//        return nameUser;
//    }



}

