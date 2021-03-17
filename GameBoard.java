import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
public class GameBoard extends World
{
    //Instance Constants
  
    //Define some directions to make it easier to reference which way the blocks are moving
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
     
    
    public void Intro() { //introducing the user to the game
        System.out.println("Welcome to 2048.");
        System.out.println("To play the game, use the arrow keys to move and merge the blocks.");  
        System.out.println("You lose when you can no longer merge or move any of the blocks.");
        System.out.println("Good Luck!");
        System.out.println("----------------------------------------------------------------------");
}
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public GameBoard()
    {    
        // Create a new world with 4x4 cells with a cell size of 100x100 pixels.
        super(4, 4, 100); 
        Intro();  
        //populate gameboard with x randomly placed objects
        for(int blockCount = 0; blockCount<2;) {
            if(placeRandomBlock()){ 
                blockCount++;
            } else {
                continue;
            }
        }
    }
      
      
    /**
     * Place a block on a random location on the board
     * 
     * @return Returns true if successful, false if not successful
     */
    private boolean placeRandomBlock()
    {
  
        GameSquare block = new GameSquare(); 
        int x = Greenfoot.getRandomNumber(4); 
        int y = Greenfoot.getRandomNumber(4);
          
        //Check to ensure random location is not yet taken, if the spot is free add it to the world
        if (getObjectsAt(x, y, GameSquare.class).isEmpty()) {
            addObject(block,x,y);
            return true;
        } else {
            return false;
        }
    }
      
    /**
     * Act - Check for key presses and tell each block to move itself.
     */
    public void act() 
    {
        //Add key press actoins here
        
        String key = Greenfoot.getKey();
        
        //If a key was pressed...do something
        if (key != null) {
            
            switch(key) {
                case "up": 
                    //Tell the blocks to move up
                    //Start checking from the top, then move downwards
  
                    for (int i=0; i<getWidth(); i++) {
                        for (int j=0; j<getHeight(); j++) {
                            //Get a list containing all of the GameSquare objects at position (i,j)
                            List blockList = getObjectsAt(i,j,GameSquare.class);
                            //Tell the other block object we wish to merge with it.  If successful, delete this block from the game
                            if (blockList.size() == 1) { //Error checking
                                ( (GameSquare)( blockList.get(0) )).move(UP);
                            }
                        }
                    }
                    break;
  
                case "right":
                    //Tell the blocks to move right
                    //Start checking from the right most col, then move left                    
                    for (int i=3; i>=0; i--) {
                        for (int j=0; j<getHeight(); j++) {
                            //Get a list containing all of the GameSquare objects at position (i,j)
                            List blockList = getObjectsAt(i,j,GameSquare.class);
              
                            //Tell the other block object we wish to merge with it.  If successful, delete this block from the game
                            if (blockList.size() == 1) { //Error checking
                                ( (GameSquare)( blockList.get(0) )).move(RIGHT);
                            }
                        }
                    }
                    break;
  
                case "down":
                    //Tell the blocks to move down
                    //Start checking from the bottom, then move up
                    for (int i=3; i>=0; i--) {
                        for (int j=3; j>=0; j--) {
                            //Get a list containing all of the GameSquare objects at position (i,j)
                            List blockList = getObjectsAt(j,i,GameSquare.class);
              
                            //Tell the other block object we wish to merge with it.  If successful, delete this block from the game
                            if (blockList.size() == 1) { //Error checking
                                ( (GameSquare)( blockList.get(0) )).move(DOWN);
                            }
                        }
                    }
                    break;
  
                case "left":
                    //Tell the blocks to move left
                    //Start checking from the left, then move right
                    for (int i=0; i<getWidth(); i++) {
                        for (int j=0; j<getHeight(); j++) {
                            //Get a list containing all of the GameSquare objects at position (i,j)
                            List blockList = getObjectsAt(j,i,GameSquare.class);
              
                            //Tell the other block object we wish to merge with it.  If successful, delete this block from the game
                            if (blockList.size() == 1) { //Error checking
                                ( (GameSquare)( blockList.get(0) )).move(LEFT);
                            }
                        }
                    }
                    break;
  
                }
                  
  
            int count = 0;
            if (numberOfObjects() == 14) { // Warning message
                System.out.println("Warning. You are approaching the maximum number of blocks that can fit on the grid.");
                System.out.println("Carefully make your next few moves in order to avoid losing.");  
                System.out.println("----------------------------------------------------------------------");  }
            if(numberOfObjects() < 16) {
                while (count < 1) {
                    if (placeRandomBlock()) {
                        count++;
                    }
                }
            }
        }
          
    }
}
