package app.utilities.authentication.registration;

public class StudentAuthentication implements RegistrationMechanism {
    @Override
    public String registrationQuery() {
        return "INSERT INTO student (name, username, password, is_verified) VALUES (?,?,?,FALSE)";
    }
}
