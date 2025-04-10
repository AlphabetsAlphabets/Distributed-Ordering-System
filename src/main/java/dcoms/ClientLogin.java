package dcoms;

public interface ClientLogin {
    boolean authenticate(String username, String password);
    void showError(String message);
    void onLoginSuccess();
}
