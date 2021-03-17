import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
  
public class GameSquare extends Actor
{
    //Instance Constants
      
    //Define some directions to make it easier to reference which way the blocks are moving
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;

    //Define a debugging variable (See video linked in assignment outline)
    private final boolean debug = false;
      
    //Instance Variables    
    private int value;
          
    //Constructor
    public GameSquare()
    {
        this(Math.random() < 0.9 ? 2 : 4); //10% chance new block will have value of 4
    }
      
    public GameSquare(int valueln)
    {
        setValue(valueln);
        displayValue();
    }
      
    /**
     * Tell the block to move in the given direction until it hits an obstacle
     * 
     * @param The direction in which the block is to move (UP = 0; RIGHT = 1; DOWN = 2; LEFT = 3;
     */
    public void move(int direction) 
    {
        //check if can move
        int movable = canMove(direction);
          
        //if moveable, start a loop
        while (movable > 0) {
        
            //Get current coordinates
            int x = getX();
            int y = getY();
            //Change x and y values to the "new" location based on direction
            if (direction == UP) {
                y -= 1; }
            if (direction == RIGHT) {
                x += 1; }
            if (direction == DOWN) {
                y += 1; }
            if (direction == LEFT) {
                x -= 1; }        
              
            //If Nothing in the way - move the block
            if (movable == 1) {  
                setLocation(x,y);
                movable = canMove(direction);
                continue;
                //return;
            }
            //Merge the blocks
            else {
                if (!getWorld().getObjectsAt(x,y,GameSquare.class).isEmpty()) {
                    GameSquare block = (GameSquare)getWorld().getObjectsAt(x,y,GameSquare.class).get(0);
                    
                    if(merge(block.getValue())) {
                       getWorld().removeObject(block);
                       setLocation(x,y);
                       movable = canMove(direction);
                       continue; }
                    else {
                        movable = canMove(direction);
                        movable = 0;
                        continue; }
                        }
               else {
                    movable = canMove(direction);
                    movable = 0;
                    continue; 
                }
            }
        }
          
          
        //can't move, so don't move.
        return;
    }
  
    /**
     * Sets the value of the game square to the value of the input parameter.
     * Will only work if the value is a factor of 2
     * 
     * @param The number to use as the new value
     * @return If the number was set correctly return true, otherwise return false
     */
      
    public boolean setValue(int valueln) {
        if(valueln % 2 == 0) {
            value = valueln;
            displayValue();
            return true; }
        else {
            return false;
        }
    }
      
    /**
     * Merge with another block and combine values.
     * Will only work if the two blocks are of the same value
     * 
     * @param The value of the block to be added
     * 
     * @return Return true if the merge is successful.
     */
  
    public boolean merge(int valueln) {
        int blockValue = getValue();
        if (blockValue == valueln) {
            value = blockValue*2;
            setValue(value);
            displayValue();
            if (value >= 2048){ //if the user wins the game
                Greenfoot.stop(); 
                System.out.println("You've won the game.");
                System.out.println("You can either choose to keep going or start a new game by clicking Reset"); }
            return true; }
        else {
            return false;
        }
    }
      
    /**
     * Returns the current value of the gameSquare
     * 
     * @return The current value (int) of the game square
     */
      
    public int getValue() {
        return this.value; 
    }
  
    /**
     * Checks to see if the block can move
     * 
     * @return int value representing what is in the space to be moved to.  0 -> Path Blocked, 1 -> Empty Space, int>1 value of block in the space to be moved to.
     */
    private int canMove(int direction)
    {
        //Get World
        World world = getWorld();
          
        //Get x and y values of current object
        int x = getX();
        int y = getY();
          
        //Change x and y values to the "new" location based on direction
        if (direction == UP) {
            y -= 1; }
        if (direction == RIGHT) {
            x += 1; }
        if (direction == DOWN) {
            y += 1; }
        if (direction == LEFT) {
            x -= 1; }
        
          
        //Test for outside border
        if (y<0 || x>3 || x<0 || y>3) {
            return 0;
        }
          
        //Check to see if there is a block in the way
        if(!world.getObjectsAt(x, y, GameSquare.class).isEmpty()) {
            GameSquare block = (GameSquare)world.getObjectsAt(x,y,GameSquare.class).get(0);
            return block.getValue();
        }
        ; 
        return 1;
    }
      
    /**
     * displayValue - Displays the current value of a block in an image, then sets that image to the block display image
     */  
    private void displayValue() 
    {
        getValue();     //this section allows for different values to be assigned different colours 
        GreenfootImage displayImage;         
        displayImage = new GreenfootImage( Integer.toString(value), 50, Color.BLACK, Color.WHITE); // this happens when value = 2    
        if (value >= 4){
            displayImage = new GreenfootImage( Integer.toString(value), 50, Color.RED, Color.WHITE);    }
        if (value >= 16){
            displayImage = new GreenfootImage( Integer.toString(value), 50, Color.GREEN, Color.WHITE);   }
        if (value >= 64){
            displayImage = new GreenfootImage( Integer.toString(value), 50, Color.ORANGE, Color.WHITE);    }
        if (value >= 256){
            displayImage = new GreenfootImage( Integer.toString(value), 50, Color.BLUE, Color.WHITE);    }
        if (value >= 1024) {
            displayImage = new GreenfootImage( Integer.toString(value), 50, Color.CYAN, Color.WHITE);    }  
        if (value >= 2048) {
            displayImage = new GreenfootImage( Integer.toString(value), 50, Color.MAGENTA, Color.WHITE);    } 
         
        //Add the image as the new image for this object
        setImage(displayImage);
        GreenfootImage cell = new GreenfootImage(50, 50); 

          
    }
}
