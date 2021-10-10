/**
 * @author Jeffano
 * @Date: August 25, 2020
 * Program Name: Connect 4 Game
 * Program Description: A connect 4 game is made where the goal is to get 4 of the counters in a row/column/diagonally.
 */

package johnjeffanocultask;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ConnectFour extends JFrame{
    
    //Declaring the variables
    JLabel lblTitle;
    JLabel lblWelcome1;
    JLabel lblWelcome2;
    JLabel lblPlayer1;
    JLabel lblPlayer2;
    
    JPanel mainScreen = new JPanel();
    
    
    
    JButton btnPlay = new JButton ("PLAY");
    JButton btnExit = new JButton ("EXIT");
    
    JTextField txtPlayer1;
    public static String strPlayer1;
    
    JTextField txtPlayer2;
    public static String strPlayer2;
    
    public static int intPlayer1Wins = 0;
    public static int intPlayer1Losses= 0;
    public static int intPlayer2Wins = 0;
    public static int intPlayer2Losses = 0;
    public static int intTies = 0;
    
    public static int intStartTime=0;
    public static int intEndTime=0;
    
    public static ArrayList<String> gameTimes = new ArrayList<String>();
        
    //Creating the connect 4 object
    public ConnectFour(){
        super ("Connect 4 Game");
        setSize (900,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Setting the layout
        mainScreen.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
        
        //Adding a label
        lblTitle = new JLabel(new ImageIcon("Connect4Logo.png"));
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 0;
	c.gridy = 0;
        c.gridwidth = 3;
	mainScreen.add(lblTitle, c);
        
        //Adding a label
        lblWelcome1 = new JLabel("Welcome To Connect 4!");
        lblWelcome1.setFont(new Font("Arial", Font.BOLD,30));
        lblWelcome1.setForeground(Color.red);
	c.fill = GridBagConstraints.CENTER;
	c.gridx = 0;   
	c.gridy = 1;
        c.ipady = 40; 
        c.gridwidth = 3;
	mainScreen.add(lblWelcome1, c);
        
        //Adding a label
        lblWelcome2 = new JLabel("Please enter your names and press play to start the game");
        lblWelcome2.setFont(new Font("Arial", Font.BOLD,25));
        lblWelcome2.setForeground(Color.blue);
	c.fill = GridBagConstraints.CENTER;
	c.gridx = 0;    
	c.gridy = 2;
        c.ipady = 40;
        c.gridwidth = 3;
	mainScreen.add(lblWelcome2, c);
        
        //Adding a label
        lblPlayer1 = new JLabel("Player 1: "); 
        lblPlayer1.setFont(new Font("Arial", Font.BOLD,20));
	c.fill = GridBagConstraints.EAST;
	c.gridx = 0;
	c.gridy = 3;
        c.ipady = 40;
        c.gridwidth = 1;
	mainScreen.add(lblPlayer1, c);
        
        //Adding a textfield
        txtPlayer1 = new JTextField(30);
        txtPlayer1.setFont(new Font("Arial",Font.PLAIN,20));
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 1;   
	c.gridy = 3;
        c.ipady = 20;
        c.gridwidth =2;
	mainScreen.add(txtPlayer1, c);
        
        //Adding a label
        lblPlayer2 = new JLabel("Player 2: "); 
        lblPlayer2.setFont(new Font("Arial", Font.BOLD,20));
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 0;
	c.gridy = 4;
        c.ipady = 40;
        c.gridwidth = 1;
	mainScreen.add(lblPlayer2, c);
        
        //Adding a textfield
        txtPlayer2 = new JTextField(30);
        txtPlayer2.setFont(new Font("Arial",Font.PLAIN,20));
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 1;
	c.gridy = 4;
        c.ipady = 20; 
        c.gridwidth = 2;
	mainScreen.add(txtPlayer2, c);
        
        //Adding a button
        btnPlay = new JButton ("PLAY");
        btnPlay.setFont(new Font("Arial", Font.BOLD,20));
        c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 2;
        c.gridy = 5;
        c.ipady = 20;   
        c.ipadx = 10;
        c.gridwidth = 1;
	mainScreen.add(btnPlay, c);
        
        //Adding a label
        btnExit = new JButton ("EXIT");
        btnExit.setFont(new Font("Arial", Font.BOLD,20));
        c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 3;
        c.gridy = 5;
        c.ipady = 20;    
        c.gridwidth = 1;
	mainScreen.add(btnExit, c);
        
        add(mainScreen);
        
        setVisible(true); 
       
        //Opening the game with a button press
        btnPlay.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                try{
                    gameWindow newgame = new gameWindow();
                    
                    if(txtPlayer1.getText().equals("")){
                        strPlayer1 = "Player 1";
                    }
                    else{
                        strPlayer1 = txtPlayer1.getText();
                    }
                    
                    if(txtPlayer2.getText().equals("")){
                        strPlayer2 = "Player 2";
                    }
                    else{
                        strPlayer2 = txtPlayer2.getText();
                    }
                    
                    newgame.setVisible(true);
                    dispose();
                    intStartTime = (int) System.currentTimeMillis(); 
                    
                }
                catch(Exception ex){
                    
                }
            }
        }); 
        
        //Stops the application once exit is pressed
        btnExit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                try{
                    System.exit(0);
                }
                catch(Exception ex){
                    
                }
            }
        });
    }
    
    public static void main (String args[]){
        ConnectFour frame = new ConnectFour();
    }
    
}
