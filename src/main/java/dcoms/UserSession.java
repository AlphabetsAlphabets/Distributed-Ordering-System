package dcoms;

import java.io.Serializable;

public class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;

    public String username;

    public UserSession(String username) {
        this.username = username;
    }
}
