package de.tency.adashboard;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * System controller bean
 * <code>sposys.xhtml</code>
 */
@ManagedBean(name = "controller")
@RequestScoped
public class ADashboardController {

    /**
     * default empty constructor
     */
    public ADashboardController() {
    }

    /**
     * SPOSys Navigation
     */
    
    /**
     * Link back to login
     */
    public String goLogin() {
        return "login";
    }

    /**
     * Link back to home
     */
    public String goHome() {
        return "home";
    }

    /**
     * System logout
     */
    public String goLogout() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance()
                .getExternalContext();
        ec.invalidateSession();
        ec.redirect("home.jsf");

        return null;
    }

}
