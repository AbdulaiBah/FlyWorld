import java.util.Arrays;

/**
 * Handles display, movement, and fly eating capabalities for frogs
 */
public class Frog extends Predators
{
    
    protected static final String imgFile = "frog.png";

    public Frog(GridLocation loc, FlyWorld fw) {
        super(loc, fw, imgFile);
    }

    /**
     * Generates a list of <strong>ALL</strong> possible legal moves<br>
     * for a frog.<br>
     * You should select all possible grid locations from<br>
     * the <strong>world</strong> based on the following restrictions<br>
     * Frogs can move one space in any of the four cardinal directions but<br>
     * 1. Can not move off the grid<br>
     * 2. Can not move onto a square that already has frog on it<br>
     * GridLocation has a method to help you determine if there is a frog<br>
     * on a location or not.<br>
     *
     * @return GridLocation[] a collection of legal grid locations from<br>
     * the <strong>world</strong> that the frog can move to
     */
    @Override
    public GridLocation[] generateLegalMoves(){
        int row = location.getRow();
		int col = location.getCol();
        GridLocation[] legalMoves = new GridLocation[0];

        if(world.isValidLoc(row+1,col)) { 
			if(!world.getLocation(row+1,col).hasPredator()) {
                legalMoves = Arrays.copyOf(legalMoves, legalMoves.length + 1);
                legalMoves[legalMoves.length - 1] = world.getLocation(row+1,col);
		    }
	    }
		else if(world.isValidLoc(row-1,col)) {
			if(!world.getLocation(row-1,col).hasPredator()) {
				legalMoves = Arrays.copyOf(legalMoves, legalMoves.length + 1);
                legalMoves[legalMoves.length - 1] = (world.getLocation(row-1,col));
		    }
	    }
		else if(world.isValidLoc(row,col+1)) {
			if(!world.getLocation(row,col+1).hasPredator()) {
				legalMoves = Arrays.copyOf(legalMoves, legalMoves.length + 1);
                legalMoves[legalMoves.length - 1] = (world.getLocation(row,col+1));
		    }
	    }
		else if(world.isValidLoc(row,col-1)) {
			if(!world.getLocation(row,col-1).hasPredator()) {
			    legalMoves = Arrays.copyOf(legalMoves, legalMoves.length + 1);
                legalMoves[legalMoves.length - 1] =(world.getLocation(row,col-1));
		    }
	    }
        return legalMoves;
    }

    /**
     * This method helps determine if a frog is in a location<br>
     * where it can eat a fly or not. A frog can eat the fly if it<br>
     * is on the same square as the fly or 1 spaces away in<br>
     * one of the cardinal directions
     *
     * @return boolean true if the fly can be eaten, false otherwise
     */ 
    @Override
    public boolean eatsFly()
    {
        int row = location.getRow();
		int col = location.getCol();
        GridLocation flyloc = world.getFlyLocation();

        if (flyloc.equals(location)) {
			return true;
        }
        if (flyloc.getRow()+1 == row && flyloc.getCol() == col){ 
            return true;
        }
        if (flyloc.getRow() == row && flyloc.getCol()+1 == col){
            return true;
        }
        if (flyloc.getRow()-1 == row && flyloc.getCol() == col){
            return true;
        }
        if (flyloc.getRow() == row && flyloc.getCol()-1 == col){
            return true;
        }
        return false;
    }   
}
