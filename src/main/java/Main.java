
import com.formdev.flatlaf.FlatLightLaf;
import view.LoginFrame;
import view.MainView;

import java.io.IOException;

/**
 * @author jean.franz
*/
public class Main {
    public static void main(String[] args) throws Exception {
        FlatLightLaf.setup();
        //MainView tela = new MainView();
        LoginFrame frame = new LoginFrame();
    }
}
