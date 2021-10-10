
package johnjeffanocultask;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author Jeffano
 */
public class gameWindow extends JFrame {
    
    //Buttons
    JButton[][] gameButtons = new JButton [7][7];
    
    //Game Board
    private Slot gameBoard[][];  
    
    JPanel outerPanel= new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    JPanel gameScreen;
    JPanel subPanel;
    
    JLabel lblInstruction;
    JButton btnStop;
    
    //Declaring the variables
    int size=7;
    int intPlayerOrder=0;
    static int livingSlotNumber =0;
  
    String strPlayerStatus;
    
    //Declaring the images
    ImageIcon empty = new ImageIcon("bluecircle.png");
    ImageIcon redCounter = new ImageIcon("redcircle.png");
    ImageIcon yellowCounter = new ImageIcon("yellowcircle.png");
    
    //Making the game window
    public gameWindow(){
        super ("Game");
        setSize (1200,850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Placing the game
        gameScreen = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        GridLayout layout1 = new GridLayout(7,7);
        gameScreen.setLayout(layout1);
        
        //Placing the sub panel for extra information
        subPanel = new JPanel();
	subPanel.setPreferredSize(new Dimension(1200,30));
        lblInstruction = new JLabel("Click on any columns to place your counter");
        lblInstruction.setFont(new Font("Arial", Font.BOLD,15));
        subPanel.add(lblInstruction);
        
        //Adding the stop buttin
        btnStop = new JButton("STOP");
        btnStop.setFont(new Font("Arial", Font.BOLD,15));
        subPanel.add(btnStop);
        
        //Adding both panels 
	outerPanel.add(subPanel);
        outerPanel.add(gameScreen);
        
        //Calling the methods
        dynamicAllocation();
        initialBoard();
        
        add(outerPanel);
        setVisible(true);
        
        //Button to stop the game
        btnStop.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                try{
                    showResult(5);
                }
                catch(Exception ex){
                    
                }
            }
        });
    }
    
    
    /**
     * Create 2D dynamic Slot array
     */
    public void dynamicAllocation()
    {
      // Create dynamic Slot array to game board
        gameBoard = new Slot[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j <size; j++)
            {
                gameBoard [i][j]=new Slot();
            }
        }
    }
    
    /**
     * Add buttons to game board 
     */
    public void addButtonsToBoard()
    {
        for(int i=0; i<size; ++i)
        {
            for(int j=0; j<size; ++j)
            {
                gameButtons[i][j] = new JButton(empty); // Empty button
                gameButtons[i][j].addActionListener(new gamePlay());
                gameScreen.add(gameButtons[i][j]);   // Add buttons to panel
            }
        }            
    } 
    
    //Initialing the game board
    public void initialBoard()
    {
        for (int i = size - 2; i >= 0; --i) 
        {
            for (int j = size - 1 ; j>=0; --j)
            {
                gameBoard[i][j].setSlotState(-99);
            }
        }      
        addButtonsToBoard();
    }
    
    /**
     * Game winning state
     * If the four slot is same, user 1 will win the game
     * @param winner integer If the player 1 is equal to 1, otherwise 2
     */
    public void winnerPlayer(int winner)
    {
        for(int i=0; i<size; ++i)
        {         
            for(int j=0; j<size; ++j)
            {     
                if(gameBoard[i][j].getSlotState() == winner)
                {    
                    // CHECK UP TO DOWN POSITIONS
                    if(i+3 < size)
                    {
                        if(gameBoard[i+1][j].getSlotState() == winner && gameBoard[i+2][j].getSlotState() == winner && gameBoard[i+3][j].getSlotState() == winner)  
                        {
                            if(winner==1){
                                showResult(1);
                            }
                                
                            else{
                                showResult(2);
                            }
                                
                        }
                    }
                    // CHECK LEFT TO RIGHT POSITION
                    if(j + 3 < size)
                    { 
                        if(gameBoard[i][j+1].getSlotState() == winner && gameBoard[i][j+2].getSlotState() == winner && gameBoard[i][j+3].getSlotState() == winner)
                        { 
                            if(winner==1){
                                showResult(1); 
                            }
                                
                            else{
                                showResult(2);   
                            } 
                        }
                    }

                    // CHECK DIAGONAL LEFT TO RIGHT POSITION
                    if(i  < size - 3 && j<size-3)
                    {
                        if(gameBoard[i+1][j+1].getSlotState() == winner && gameBoard[i+2][j+2].getSlotState() == winner && gameBoard[i+3][j+3].getSlotState() == winner)
                        {  
                            if(winner==1){
                                showResult(1);
                             
                            }
                                
                            else{
                                showResult(2);
                                
                            }     
                        }   
                    }

                    // CHECK DIAGONAL RIGHT TO LEFT POSITION
                    if(i  < size - 3 && j - 3 >= 0 )
                    {
                        if(gameBoard[i+1][j-1].getSlotState() == winner && gameBoard[i+2][j-2].getSlotState() == winner && gameBoard[i+3][j-3].getSlotState() == winner)
                        {
                            if(winner==1){
                                showResult(1);
                                
                            }
                            else{
                                showResult(2);  
                            }     
                        } 
                    }
                }         
            }             
        } 
    }
    
    //Shows the result when a player has won
    public void showResult(int winnerPlayer){
       
       JFrame frameShowResult = new JFrame();  
       String strOutput = calculateTime(winnerPlayer);
       
       int n=0;
       
       if(winnerPlayer==1)
       {
            ConnectFour.intPlayer1Wins++;
            ConnectFour.intPlayer2Losses++;
           
           n = JOptionPane.showConfirmDialog(frameShowResult, "The Winner is " + ConnectFour.strPlayer1 + 
                    strMessage() + strOutput+ 
                    "\n\nDo You Want To Restart?.\n\n", "End Game", JOptionPane.YES_NO_OPTION);
       }
       if (winnerPlayer==2)
       {
           ConnectFour.intPlayer2Wins++;
           ConnectFour.intPlayer1Losses++;  
           
           n = JOptionPane.showConfirmDialog(frameShowResult, "The Winner is " + ConnectFour.strPlayer2 + 
                    strMessage() + strOutput+ 
                    "\n\nDo You Want To Restart?.\n\n", "End Game", JOptionPane.YES_NO_OPTION);
       }
       
       //Option if the game was stopped midway
       if (winnerPlayer==5)
       {

           n = JOptionPane.showConfirmDialog(frameShowResult, "The Game Has Been Stopped"+ 
                    "\n\nDo You Want To Restart?.\n\n", "End Game", JOptionPane.YES_NO_OPTION);
       }
       
       if(n!=1){
          startAgain();
          
       }
       else {
           finalWindow finalScreen = new finalWindow();
           dispose();
       }
       
    }
    
    //The output message
    public String strMessage(){
        String strMessage = "";
        strMessage = "!\n"+ ConnectFour.strPlayer1 + "'s Wins: " + ConnectFour.intPlayer1Wins + 
                    "\n"+ ConnectFour.strPlayer1 + "'s Losses: " + ConnectFour.intPlayer1Losses +
                    "\n"+ ConnectFour.strPlayer2 + "'s Wins: " + ConnectFour.intPlayer2Wins +
                    "\n"+ ConnectFour.strPlayer2 + "'s Losses: " + ConnectFour.intPlayer2Losses +
                    "\n";
        return strMessage;
    }
    
    
    //Restarts the game
    public void startAgain()
   {
       this.setVisible(false);            // Unvisible previous game board
       ConnectFour.intStartTime = (int) System.currentTimeMillis();  
       gameWindow newGame = new gameWindow(); // New Game Object
   }
    
    //Warning message for wrong press
    public void warningMessage()
    {
        JFrame warning = new JFrame();           
        JOptionPane.showMessageDialog(warning,"Invalid Movement!!\nThis Slot is Already Taken","Warning", JOptionPane.WARNING_MESSAGE);
    }
    
    //Calculating the time for each round
    public String calculateTime(int winnerPlayer){
       int intGameTime = 0;
       String strGameTime;
       String strPlayerName = "";

       ConnectFour.intEndTime = (int) System.currentTimeMillis();    
       intGameTime = ConnectFour.intEndTime - ConnectFour.intStartTime;
       strGameTime = Integer.toString(intGameTime);
       
       String strOutput=" ";
       
       ConnectFour.gameTimes.add(Integer.toString(winnerPlayer)+" "+strGameTime);
       
       int arraySize = ConnectFour.gameTimes.size();
       int[][] playerTimer = new int[arraySize][2];
       
       for(int i =0; i <arraySize;i++){
           strOutput = ConnectFour.gameTimes.get(i);
           playerTimer[i][0] = Integer.parseInt(strOutput.split(" ")[0]);
           playerTimer[i][1] = Integer.parseInt(strOutput.split(" ")[1]);
       }
       
       for (int i=0; i < arraySize ; i++) {
           for (int j = i+1; j < arraySize; j++){
               if (playerTimer[i][1] > playerTimer[j][1]){//Assuming you can compare them this way else make a method to compare
                   int tmp = playerTimer[j][1];
                   int player = playerTimer[j][0];
                   playerTimer[j][0] = playerTimer[i][0];
                   playerTimer[j][1] = playerTimer[i][1];
                   playerTimer[i][1] = tmp;
                   playerTimer[i][0] = player;
               }
           } 
       }
       
       
       if(playerTimer[0][0] ==1){
           strPlayerName = ConnectFour.strPlayer1;
       }
       if(playerTimer[0][0]==2){
           strPlayerName = ConnectFour.strPlayer2;
       }
       
       long seconds = TimeUnit.MILLISECONDS.toSeconds(playerTimer[0][1]);
       
       
       strOutput =  "Quickest Winner with Time: " + seconds + " seconds is " + strPlayerName;
       return strOutput;
    }
    
    /**
     * Set the upper cells to empty slot to listen button
     * @param rowPos integer row position to board
     * @param columnPos integer column position to board
     */ 
    public void setUpperSlotToEmpty(int rowPos, int columnPos)
    {
        try 
        {
            gameBoard[rowPos-1][columnPos].setSlotState(0);    
        }   
        catch (Exception ex) 
        { }      
    }
    
    /**
     * Action listener to game button
     * Player 1 vs Player 2
     */
    private class gamePlay implements ActionListener
    {          
        //@Override
        public void actionPerformed(ActionEvent e)
        {            
            try {
                int eventFlag = 0;
                int flagPlayerOrder=0;

                for(int i=size-1; i>=0; --i)
                {
                    for(int j=0; j<=size-1; ++j)
                    {
                        if(eventFlag==0 && gameButtons[i][j]== e.getSource()) // Get the button component that was clicked
                        {  
                           if(flagPlayerOrder==0 && intPlayerOrder%2==0) 
                           { 
                               // Player 1 Operations                           
                               // Fill the board from down to up
                                for(int k=0; k<=size; ++i)    
                                {
                                    if( gameBoard[i-k][j].getSlotState()==0 && intPlayerOrder%2==0)
                                    {
                                       gameButtons[i-k][j].setIcon(redCounter);          // Set new icon to player 1 
                                       gameBoard[i-k][j].setAllPosition('X', i);  // Set Slot parameters
                                       gameBoard[i-k][j].setSlotState(1);
                                       ++livingSlotNumber;  // Increase living Slot number
                                       winnerPlayer(1);     // Check player 1 winning state
                                       flagPlayerOrder=1;   
                                       eventFlag=1;
                                       break; 
                                    } 
                                }

                                setUpperSlotToEmpty(i,j);   // Set upper Slot to empty 
                                System.out.println("... Player 1 played ... ");
                                lblInstruction.setText("... " + ConnectFour.strPlayer1 + " played ...");
                                ++intPlayerOrder; // Change order from player 1 to player 2
                                break;
                            }

                            // Player 2 operations
                            if(flagPlayerOrder==0 && intPlayerOrder%2==1) 
                            { 
                                for(int k=0; k<=size; ++i)
                                {
                                    if(gameBoard[i-k][j].getSlotState()==0 && intPlayerOrder%2==1)
                                    {
                                       gameButtons[i-k][j].setIcon(yellowCounter);            // Set new icon to player 2
                                       gameBoard[i-k][j].setAllPosition('O', i);    // Set Slot parameters    
                                       gameBoard[i-k][j].setSlotState(2);           // Set Slot state
                                       ++livingSlotNumber;
                                       winnerPlayer(2);
                                       flagPlayerOrder=1;
                                       eventFlag=1;
                                       break;
                                    } 
                                }
                                setUpperSlotToEmpty(i,j);
                                System.out.println("... Player 2 played ... ");
                                lblInstruction.setText("... " + ConnectFour.strPlayer2 + " played ...");
                                ++intPlayerOrder;
                                break;
                            }
                        }
                    }        
                }   
        }
            catch(Exception ex) 
        { 
            
            warningMessage(); 
        }     
       
        } 
    }
}