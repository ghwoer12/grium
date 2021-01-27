package com.project.four.model.dto;

public class UserDto {
    /*

 * email VARCHAR(30) NOT NULL, name VARCHAR(20) NOT NULL, password VARCHAR(20)
 * NOT NULL, photo LONGBLOB, phone VARCHAR(15) NOT NULL,
 */
private String userid;
private String email;
private String password;
private String name;
private String photo;
private String phone;

public UserDto(String userid, String email, String password, String name, String photo, String phone) {
    super();
    this.userid = userid;
    this.email = email;
    this.password = password;
    this.name = name;
    this.photo = photo;
    this.phone = phone;
}

public UserDto() {
    super();
}

public String getUserid() {
    return userid;
}

public void setUserid(String userid) {
    this.userid = userid;
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

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getPhoto() {
    return photo;
}

public void setPhoto(String photo) {
    this.photo = photo;
}

public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

@Override
public String toString() {
    return "UserDto [userid=" + userid + ", email=" + email + ", password=" + password + ", name=" + name
            + ", photo=" + photo + ", phone=" + phone + "]";
}
}