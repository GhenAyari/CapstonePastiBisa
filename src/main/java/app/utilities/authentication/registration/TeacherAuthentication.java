package app.utilities.authentication.registration;

public class TeacherAuthentication implements RegistrationMechanism {
    @Override
    public String registrationQuery() {
        return "INSERT INTO teacher (name, username, password, is_verified) VALUES (?,?,?,FALSE)";
    }
}
