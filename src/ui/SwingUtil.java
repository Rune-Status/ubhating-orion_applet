package ui;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import utils.OperationSystem;

public class SwingUtil {
    public static void setIcons(JFrame f) {
        f.setIconImage(Images.getResource("/ui/icon.png"));

        if(OperationSystem.getOS() == OperationSystem.MAC) {
            /** Adds the dock icon to mac users */
            try {
                Class<?> util = Class.forName("com.apple.eawt.Application");
                Object application = util.getMethod("getApplication", new Class[] { }).invoke(null);
                Method setDockIconImage = util.getMethod("setDockIconImage", new Class[] { Image.class });
                setDockIconImage.invoke(application, Images.getResource("/ui/icon.png"));
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
