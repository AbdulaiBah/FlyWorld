import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Contains several methods that aid in the<br>
 * display and movement of Mosca
 */
public class Fly
{
    protected static final String imgFile = "fly.png";
    protected GridLocation location;
    protected FlyWorld world;
    protected BufferedImage image;


    public Fly(GridLocation loc, FlyWorld fw){
        location = loc;
        world = fw;

        try{
            image = ImageIO.read(new File(imgFile));
        }
        catch (IOException ioe){
            System.out.println("Unable to read image file: " + imgFile);
            System.exit(0);
        }
        location.setFly(this);
    }

    public BufferedImage getImage(){
        return image;
    }

    public GridLocation getLocation(){
        return location;
    }

    public boolean isPredator(){
        return false;
    }

    public String toString(){
        String s = "Fly in world:  " + this.world + "  at location (" + this.location.getRow() + ", " + this.location.getCol() + ")";
        return s;
    }
    
    /**
     * This method <strong>updates</strong> the fly's location in<br>
     * the <strong>world</strong><br>
     * The fly can move in one of the four cardinal (N, S, E, W) directions.<br>
     * You need to make sure that the <strong>updated</strong> location<br>
     * is within the bounds of the world before<br>
     * changing the location of the fly<br>
     *
     * @param direction one of the four cardinal directions<br>
     *        Given as constants in FlyWorldGUI<br><br>
     *        They are:<br>
     *        FlyWorldGUI.NORTH<br>
     *        FlyWorldGUI.SOUTH<br>
     *        FlyWorldGUI.EAST<br>
     *        FlyWorldGUI.WEST<br>
     */
    public void update(int direction){
        int moscaRow = location.getRow();
        int moscaCol = location.getCol();
        
        if(direction == FlyWorldGUI.NORTH) {
			moscaRow = moscaRow - 1; 
	    }
		else if(direction == FlyWorldGUI.SOUTH) {
			moscaRow = moscaRow + 1;
	    }
		else if(direction == FlyWorldGUI.EAST) {
			moscaCol = moscaCol + 1;
	    }
		else if(direction == FlyWorldGUI.WEST) {
			moscaCol = moscaCol - 1;
	    }
		if(world.isValidLoc(moscaRow, moscaCol)) {
	       location.removeFly();
	       location = world.getLocation(moscaRow, moscaCol);
	       location.setFly(this);
        }
    }
}
