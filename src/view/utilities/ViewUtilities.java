package view.utilities;

import java.awt.*;

public class ViewUtilities {

    private Font mainFont;
    private Color mainFrameBackgroundColor;

    public ViewUtilities() {
        mainFont = new Font("Arial", Font.PLAIN, 16);
        mainFrameBackgroundColor = new Color(222, 222, 222);
    }

    public Font getMainFont() {
        return mainFont;
    }

    public Color getMainFrameBackgroundColor() {
        return mainFrameBackgroundColor;
    }


}
