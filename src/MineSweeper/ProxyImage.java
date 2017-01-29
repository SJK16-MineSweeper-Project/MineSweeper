package MineSweeper;

import java.net.URL;

/**
 * Created by bartek on 2017-01-29.
 */
public class ProxyImage implements Image{

    private RealImage realImage;
    private URL fileName;

    public ProxyImage(URL fileName){
        this.fileName = fileName;
    }

    @Override
    public URL display() {
        if(realImage == null){
            System.out.println("Real image is null");
            realImage = new RealImage(fileName);
        }
        return realImage.display();
    }
}
