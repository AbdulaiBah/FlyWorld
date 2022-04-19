import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *  Handles display, movement, and fly eating capabalities for predators
 */
public abstract class Predators {

    protected String imgFile;
    protected GridLocation location;
    protected FlyWorld world;
    protected BufferedImage image;

    /**
     * Creates and sets the image of the predator to the game grid
     * @param loc
     * @param fw
     * @param img
     */
    public Predators(GridLocation loc, FlyWorld fw, String img){
        location = loc;
        world = fw;
        imgFile = img;

        try {
            image = ImageIO.read(new File(imgFile));
        }
        catch (IOException ioe) {
            System.out.println("Unable to read image file: " + imgFile);
            System.exit(0);
        }
        location.setPredators(this);
    }

    /**
     * @return BufferedImage the image of the Spider
     */
    public BufferedImage getImage(){
        return image;
    }

    /**
     * @return GridLocation the location of the Spider
     */
    public GridLocation getLocation(){
        return location;
    }

    /**
     * @return boolean, always true
     */
    public boolean isPredator(){
        return true;
    }

    /**
    * Returns a string representation of this predator showing
    * the location coordinates and the world.
    *
    * @return the string representation
    */
    public String toString(){
        String s = "Predator in world:  " + this.world + "  at location (" + this.location.getRow() + ", " + this.location.getCol() + ")";
        return s;
    }
    
    public abstract GridLocation[] generateLegalMoves();
    
    /**
     * This method helps determine if a predator is in a location<br>
     * where it can eat a fly or not. A predator can eat the fly if it<br>
     * is on the same square as the fly
     *
     * @return boolean true if the fly can be eaten, false otherwise
     */ 
    public abstract boolean eatsFly();
    
    /**
     * This method updates the predators's position.<br>
     * It should randomly select one of the legal locations(if there any)<br>
     * and set the predators's location to the chosen updated location.
     */
    public void update(){
        GridLocation[] legalMoves = generateLegalMoves();
        Random rand = new Random();

        int loc = rand.nextInt(legalMoves.length);
        GridLocation predators = legalMoves[loc];
        location.removePredators(); 
        predators.setPredators(this); 
        location = predators;
    }
}