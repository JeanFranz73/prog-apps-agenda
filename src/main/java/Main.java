
import com.formdev.flatlaf.FlatLightLaf;
import view.LoginFrame;
import view.SplashLogin;

/**
 * @author jean.franz
 */
public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        SplashLogin splash = new SplashLogin();
        try {
            // Make JWindow appear for 10 seconds before disappear
            Thread.sleep(1000);
            splash.dispose();
            LoginFrame view = new LoginFrame();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
