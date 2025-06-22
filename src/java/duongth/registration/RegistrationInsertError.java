/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongth.registration;

import java.io.Serializable;

public class RegistrationInsertError implements Serializable{
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String confirmPasswornErr;
    private String lastnameLengthErr;
    private String usernameIsExisted;

    public RegistrationInsertError() {
    }

    public RegistrationInsertError(String usernameLengthErr, String passwordLengthErr, String confirmPasswornErr, String lastnameLengthErr, String usernameIsExisted) {
        this.usernameLengthErr = usernameLengthErr;
        this.passwordLengthErr = passwordLengthErr;
        this.confirmPasswornErr = confirmPasswornErr;
        this.lastnameLengthErr = lastnameLengthErr;
        this.usernameIsExisted = usernameIsExisted;
    }

    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public String getConfirmPasswornErr() {
        return confirmPasswornErr;
    }

    public String getLastnameLengthErr() {
        return lastnameLengthErr;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public void setConfirmPasswordErr(String confirmPasswornErr) {
        this.confirmPasswornErr = confirmPasswornErr;
    }

    public void setLastnameLengthErr(String lastnameLengthErr) {
        this.lastnameLengthErr = lastnameLengthErr;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
    
}
