package dao.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserProfile {
    private int id;
    private String name;
    private String photoUrl;

    // Конструктор
    public UserProfile(int id, String name, String photoUrl) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
    }
    static UserProfile fromRs(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String photoUrl = rs.getString("photoUrl");
        return new UserProfile(id, name, photoUrl);
    }

    // Геттери і сеттери
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
