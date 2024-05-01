package assignments.assignment3;

import assignments.assignment3.systemCLI.AdminSystemCLI;
import assignments.assignment3.systemCLI.CustomerSystemCLI;
import assignments.assignment3.systemCLI.UserSystemCLI;

public class LoginManager {
    private final AdminSystemCLI adminSystem;
    private final CustomerSystemCLI customerSystem;

    public LoginManager(AdminSystemCLI adminSystem, CustomerSystemCLI customerSystem) {
        this.adminSystem = adminSystem;
        this.customerSystem = customerSystem;
    }

    /**
     *
     * @param role merupakan peran dari user yang login
     * @return objek UserSystemCLI yang sesuai dengan peran user
     * Method ini akan mengembalikan objek UserSystemCLI yang sesuai dengan peran user
     */
    public UserSystemCLI getSystem(String role){
        if(role.equals("Customer")){
            return adminSystem;
        }else{
            return customerSystem;
        }
    }
}
// DDP_D_2106165660_TheoAnandalemuel_TP3
