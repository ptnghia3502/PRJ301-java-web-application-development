
package cuongnvt.Account;

import java.io.Serializable;

public class AccountDTO implements Serializable {
   private String Username;
   private String Password;
   private boolean Role;
   private String Fullname;

    public AccountDTO() {
    }

    public AccountDTO(String Username, String Password, boolean Role, String Fullname) {
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
        this.Fullname = Fullname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public boolean isRole() {
        return Role;
    }

    public void setRole(boolean Role) {
        this.Role = Role;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }
   
}
