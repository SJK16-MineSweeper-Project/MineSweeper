/**
 * Created by bartek on 2017-01-24.
 */
import java.util.*;

public class Singleton {

    private static Singleton firstInstance = null;

    private ArrayList<String[]> scoreList;

    // Created to keep users from instantiation
    // Only Singleton will be able to instantiate this class

    private Singleton() { }

    // We could make getInstance a synchronized method to force
    // every thread to wait its turn. That way only one thread
    // can access a method at a time. This can really slow everything
    // down though
    // public static synchronized Singleton getInstance()

    public static Singleton getInstance() {
        if(firstInstance == null) {

            // Here we just use synchronized when the first object
            // is created

            synchronized(Singleton.class){

                if(firstInstance == null) {
                    // If the instance isn't needed it isn't created
                    // This is known as lazy instantiation

                    firstInstance = new Singleton();
                    firstInstance.scoreList = new ArrayList<>();
                    System.out.println("Creating singleton instance");
                }

            }

        }
        // Under either circumstance this returns the instance
        return firstInstance;
    }


    public void setScoreList(String[] playerScore) {
        firstInstance.scoreList.add(playerScore);
    }

    public ArrayList<String[]> getScoreList() {
        return firstInstance.scoreList;
    }
}