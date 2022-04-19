
import java.util.Arrays;

/**
 *  Handles display, movement, and fly eating capabalities for spiders
 */
public class Spider extends Predators{
    
    protected static final String imgFile = "spiders.png";

    /**
     * Intializes spider object
     * @param loc
     * @param fw
     */
    public Spider(GridLocation loc, FlyWorld fw) {
        super(loc, fw, imgFile);
    }

    /**
     * Generates a list of <strong>ALL</strong> possible legal moves<br>
     * for a frog.<br>
     * You should select all possible grid locations from<br>
     * the <strong>world</strong> based on the following restrictions<br>
     * 1. Can not move off the grid<br>
     * 2. Can not move onto a square that already has Spider on it<br>
     * GridLocation has a method to help you determine if there is a Spider<br>
     * on a location or not.<br>
     *
     * @return GridLocation[] a collection of legal grid locations from<br>
     * the <strong>world</strong> that the Spider can move to
     */
    @Override
    public GridLocation[] generateLegalMoves() {
        GridLocation flyLoc = world.getFlyLocation();
        int flyRow = flyLoc.getRow();
        int flyCol = flyLoc.getCol();
        int row = location.getRow();
		int col = location.getCol();
        GridLocation[] legalMoves = new GridLocation[0];
        
        if (row > flyRow){
            if(world.isValidLoc(row-1,col) && (!world.getLocation(row-1,col).hasPredator())){
                legalMoves = Arrays.copyOf(legalMoves, legalMoves.length + 1);
                legalMoves[legalMoves.length - 1] = (world.getLocation(row-1,col));
            }
        }
        else if (row < flyRow){
            if(world.isValidLoc(row+1,col) && (!world.getLocation(row+1,col).hasPredator())){
                legalMoves = Arrays.copyOf(legalMoves, legalMoves.length + 1);
                legalMoves[legalMoves.length - 1] = (world.getLocation(row+1,col));
            }
        }
        if (col > flyCol){
            if(world.isValidLoc(row,col-1) && (!world.getLocation(row,col-1).hasPredator())){
                legalMoves = Arrays.copyOf(legalMoves, legalMoves.length + 1);
                legalMoves[legalMoves.length - 1] = (world.getLocation(row,col-1));
            }
        }
        else if (col < flyCol){
            if(world.isValidLoc(row,col+1) && (!world.getLocation(row,col+1).hasPredator())){
                legalMoves = Arrays.copyOf(legalMoves, legalMoves.length + 1);
                legalMoves[legalMoves.length - 1] = (world.getLocation(row,col+1));
            }
        }
		return legalMoves;
    }

    /**
     * This method helps determine if a Spider is in a location<br>
     * where it can eat a fly or not. A Spider can eat the fly if it<br>
     * is on the same square as the fly
     *
     * @return boolean true if the fly can be eaten, false otherwise
     */ 
    @Override
    public boolean eatsFly(){
        GridLocation flyloc = world.getFlyLocation();
        GridLocation spiLoc = this.getLocation();

        return (spiLoc.equals(flyloc));
    }   
}
