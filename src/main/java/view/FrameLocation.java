package view;

import javax.swing.*;
import java.awt.*;

public class FrameLocation {
    private FrameLocation() {
    }

    public static void setFrameLocation(JFrame frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}
