package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserDetails extends HashMap<UserDetail, Object> {
    public void add(String nume, String email, String password) {
        put(UserDetail.Nume, nume);
        put(UserDetail.Email, email);
        put(UserDetail.Password, password);
    }
}
