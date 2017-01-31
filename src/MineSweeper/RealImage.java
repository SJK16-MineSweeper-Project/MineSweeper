package MineSweeper;

import javax.swing.*;
import java.net.URL;

/**
 * Created by bartek on 2017-01-29.
 */
public class RealImage implements Image {

    private URL urlString;
    private ImageIcon iconName;

    public RealImage(URL urlString){
        this.urlString = urlString;
        System.out.println("Creating real image");
        loadImage();
    }

    @Override
    public ImageIcon display() {
        System.out.println("Displaying real image");
        return iconName;
    }

    public ImageIcon loadImage() {
        System.out.println("Loading real image");
        iconName = new ImageIcon(urlString);
        return iconName;
    }
}
