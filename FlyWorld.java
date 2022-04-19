import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.Color;

/**
 * Contains information about the world (i.e., the grid of squares)<br>
 * and handles most of the game play work that is NOT GUI specific
 */
public class FlyWorld
{
    protected int numRows;
    protected int numCols;
    protected GridLocation [][] world;
    protected GridLocation start;
    protected GridLocation goal;
    
    protected Fly mosca;
    protected Predators[] predator;

    /**
     * Reads a file containing information about<br>
     * the grid setup.  Initializes the grid<br>
     * and other instance variables for use by<br>
     * FlyWorldGUI and other pieces of code.
     *
     *@param fileName the file containing the world grid information
     */
    public FlyWorld(String fileName){
        File inputFile = new File(fileName);
        Scanner input = null;
        try {
            input = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        
        String firstRow = input.nextLine();
        String[] firstLine = firstRow.split(" ");

        numRows = Integer.parseInt(firstLine[0]);
        numCols = Integer.parseInt(firstLine[1]);

        world = new GridLocation[numRows][numCols];
        predator = new Predators[0];
        
        for (int i = 0; i<numRows; i++){
            String line = input.nextLine();
            for (int j = 0; j<numCols; j++ ){
                world[i][j] = new GridLocation(i,j);
                if (line.charAt(j) == 's'){
                    world[i][j].setBackground(Color.GREEN);
                    start = world[i][j];
                    mosca = new Fly(start,this);
                }
                else if (line.charAt(j) == 'h'){
                    world[i][j].setBackground(Color.RED);
                    goal = world[i][j];
                }
                else if (line.charAt(j) == 'f'){
                    Frog f = new Frog(world[i][j],this);
                    predator = Arrays.copyOf(predator, predator.length + 1);
                    predator[predator.length - 1] = f; 
                }
                else if (line.charAt(j) == 'a'){
                    world[i][j] = new GridLocation(i,j);
                    Spider s = new Spider(world[i][j],this);
                    predator = Arrays.copyOf(predator, predator.length + 1);
                    predator[predator.length - 1] = s; 
                }
                else{
                    world[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    /**
     * @return int, the number of rows in the world
     */
    public int getNumRows(){
        return numRows;
    }

    /**
     * @return int, the number of columns in the world
     */
    public int getNumCols(){
        return numCols;
    }

    /**
     * Deterimes if a specific row/column location is<br>
     * a valid location in the world (i.e., it is not out of bounds)
     * @param r a row
     * @param c a column
     * @return boolean
     */
    public boolean isValidLoc(int r, int c){
        return ((r >= 0 && r < numRows) && (c >= 0 && c < numCols));
    }

    /**
     * Returns a specific location based on the given row and column
     * @param r the row
     * @param c the column
     * @return GridLocation
     */
    public GridLocation getLocation(int r, int c){
        return world[r][c];
    }

     /**
     * @return FlyWorldLocation, the location of the fly in the world
     */
    public GridLocation getFlyLocation(){
        return mosca.getLocation();
    }

    /**
     * Moves the fly in the given direction (if possible)
     * Checks if the fly got home or was eaten
     * @param direction the direction, N,S,E,W to move
     * @return int, determines the outcome of moving fly<br>
     *              there are three possibilities<br>
     *              1. fly is at home, return ATHOME (defined in FlyWorldGUI)<br>
     *              2. fly is eaten, return EATEN (defined in FlyWorldGUI)<br>
     *              3. fly not at home or eaten, return NOACTION (defined in FlyWorldGUI)
     */
    public int moveFly(int direction){
        mosca.update(direction); 
        GridLocation flyLoc = mosca.getLocation();
        if (flyLoc.equals(goal)){
            return FlyWorldGUI.ATHOME;
        }
        else if (movePredators()){
            return FlyWorldGUI.EATEN;
        }
        return FlyWorldGUI.NOACTION;
    }

    /**
     * Moves all predators. After it moves a predator, checks if it eats fly
     * @return boolean, return true if any predator eats fly, false otherwise
     */
    public boolean movePredators(){
        for (Predators p : predator){ //moves each of the frogs
            p.update();
            if (p.eatsFly()){
                return true;
            }
        }
        return false;
    }
}
