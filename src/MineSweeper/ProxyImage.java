package MineSweeper;

import javax.swing.*;
import java.net.URL;

/**
 * Created by bartek on 2017-01-29.
 */
public class ProxyImage implements Image{

    private RealImage realImage;
    private URL urlString;

    public ProxyImage(URL urlString){
        this.urlString = urlString;
    }

    @Override
    public ImageIcon display() {
        if(realImage == null){
            System.out.println("Real image is null");
            realImage = new RealImage(urlString);
        }
        return realImage.display();
    }
}
