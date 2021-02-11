package pw.proz.utils;

import java.util.ResourceBundle;

public class FxmlUtils {
    public final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("bundles.messages");

    public static ResourceBundle getResourceBundle(){
        return RESOURCE_BUNDLE;
    }
}
