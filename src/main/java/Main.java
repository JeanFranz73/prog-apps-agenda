
import com.formdev.flatlaf.FlatLightLaf;
import view.LoginFrame;
import view.LoginView;

/**
 * @author jean.franz
 */
public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        LoginFrame view = new LoginFrame();
    }

}
