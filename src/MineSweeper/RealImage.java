package MineSweeper;

import java.net.URL;

/**
 * Created by bartek on 2017-01-29.
 */
public class RealImage implements Image {

    private URL fileName;

    public RealImage(URL fileName){
        this.fileName = fileName;
        //display();
    }

    @Override
    public URL display() {
        System.out.println("Displaying " + fileName);
        return fileName;
    }

}
