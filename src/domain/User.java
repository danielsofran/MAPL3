package domain;

public class User extends Entity<Long> {
    private String name;
    private String password;
    private String email;

    public User() {}
    public User(Long id){
        super(id);
    }

    public User(User user){
        super(user.getId());
        this.name = user.name;
        this.password = user.password;
        this.email = user.email;
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [id=" + getId() + ", name=" + name + ", password=" + password + ", email=" + email + "]";
    }
}
