package com.cabit.Cab_It.model;

import java.time.LocalDateTime;
import java.util.*;

public class Advertisement
{
    /*
     * Basic POJO class for advertisement
     * */
    private String id;
    private LocalDateTime introducedDateTime;
    private String content;
    private byte[] photo;

    // Access privilege controlling data
    public static final Map<String, Set<Role>> CRUD_PRIVILEGES = new HashMap<>()
    {
        {
            put("create", Set.of(
                    Role.ADMIN
            )); //Defines which role(s) could be able to insert an advertisement
            put("read", Set.of(
                    Role.ADMIN, Role.EMPLOYEE, Role.CUSTOMER
            )); //Defines which role(s) could be able to access an advertisement
            put("update", Set.of(
                    Role.ADMIN
            )); //Defines which role(s) could be able to update an advertisement
            put("delete", Set.of(
                    Role.ADMIN
            )); //Defines which role(s) could be able to delete an advertisement
        }
    };

    public Advertisement() {
    }

    public Advertisement(LocalDateTime introducedDateTime, String content, byte[] photo) {
        this.introducedDateTime = introducedDateTime;
        this.content = content;
        this.photo = photo;
    }

    public Advertisement(String id, LocalDateTime introducedDateTime, String content, byte[] photo) {
        this.id = id;
        this.introducedDateTime = introducedDateTime;
        this.content = content;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getIntroducedDateTime() {
        return introducedDateTime;
    }

    public void setIntroducedDateTime(LocalDateTime introducedDateTime) {
        this.introducedDateTime = introducedDateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public static Map<String, Set<Role>> getCrudPrivileges() {
        return CRUD_PRIVILEGES;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id='" + id + '\'' +
                ", introducedDateTime=" + introducedDateTime +
                ", content='" + content +
                '}';
    }
}
