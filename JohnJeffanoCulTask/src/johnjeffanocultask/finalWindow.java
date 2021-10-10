/**
 *
 * @author Jeffano
 */
package johnjeffanocultask;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;



public class finalWindow extends JFrame{
    
    int arraySize = ConnectFour.gameTimes.size();
    ArrayList<Integer> playerTimer = new ArrayList<Integer>();
   
    //Declaring the Variables
    JLabel lblThankyou;
    JLabel lblFind;
    JLabel lblGameNum;
    
    JPanel finalScreen = new JPanel();
    
    String strOutput = "";
    
    ArrayList <Integer> playerScore1 = new ArrayList <Integer>();
    ArrayList <Integer> playerScore2 = new ArrayList <Integer>();
    
    JTextField txtGameNum;
    
    JButton btnP1;
    JButton btnP2;
    JButton btnClose;
    
    
    
    public finalWindow(){
        super("Thank You");
        setSize (700,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        finalScreen.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
        
        try{
            FileReader fr = new FileReader("thankyou.txt");
            BufferedReader br = new BufferedReader(fr);   
            String strLine;
            
            while ((strLine = br.readLine()) != null){ 
                strOutput += strLine;    
                
            }
            br.close();
        }
        catch (IOException e){}
        //Reading a thank you message
        lblThankyou = new JLabel(strOutput); 
        lblThankyou.setFont(new Font("Arial", Font.BOLD,20));
        lblThankyou.setForeground(Color.blue);
	c.fill = GridBagConstraints.CENTER;
	c.gridx = 0;
	c.gridy = 0;
        c.ipady = 40;
        c.gridwidth = 4;
	finalScreen.add(lblThankyou, c);
        
        lblFind = new JLabel("Select the player to find the number of wins ");
        lblFind.setFont(new Font("Arial", Font.BOLD,15));
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 0;
	c.gridy = 1;
        c.ipady = 40;
        c.gridwidth = 1;
	finalScreen.add(lblFind, c);
        
        btnP1 = new JButton(ConnectFour.strPlayer1);
        btnP1.setFont(new Font("Arial", Font.BOLD,15));
        c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 1;
        c.gridy = 1;
        c.ipady = 20;    
        c.gridwidth = 1;
	finalScreen.add(btnP1, c);
        
        btnP2 = new JButton(ConnectFour.strPlayer2);
        btnP2.setFont(new Font("Arial", Font.BOLD,15));
        c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 2;
        c.gridy = 1;
        c.ipady = 20;    
        c.gridwidth = 1;
	finalScreen.add(btnP2, c);
        
        //Close button to close game
        btnClose = new JButton("CLOSE");
        btnClose.setFont(new Font("Arial", Font.BOLD,15));
        c.fill = GridBagConstraints.CENTER;
	c.gridx = 1;
        c.gridy = 2;
        c.ipady = 20;    
        c.gridwidth = 1;
	finalScreen.add(btnClose, c);
        
        add(finalScreen);
        setVisible(true); 
        
       
       for(int i =0; i <arraySize;i++){
           strOutput = ConnectFour.gameTimes.get(i);
           playerTimer.add(Integer.parseInt(strOutput.split(" ")[0]));
       }
       
       
       btnP1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                try{
                    int intWinResult = linearSearch(1);
                    JFrame Winners = new JFrame();           
                    JOptionPane.showMessageDialog(Winners, ConnectFour.strPlayer1 + " has " + intWinResult + " wins.","Wins", JOptionPane.INFORMATION_MESSAGE);
                }
                catch(Exception ex){
                    
                }
            }
        });
       
       btnP2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                try{
                    int intWinResult = linearSearch(2);
                    JFrame Winners = new JFrame();           
                    JOptionPane.showMessageDialog(Winners, ConnectFour.strPlayer2 + " has " + intWinResult + " wins.","Wins", JOptionPane.INFORMATION_MESSAGE);
                }
                catch(Exception ex){
                    
                }
            }
        });
       
        //Closing the actual game
        btnClose.addActionListener(new ActionListener()
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
   
    public int linearSearch (int intPlayerNum) {   
        int intWinCounts = 0;
        
        for (int i = 0; i < ConnectFour.gameTimes.size(); i++){
            
            if (playerTimer.get(i) == intPlayerNum){
                
                intWinCounts++;
            }
        }    
        return intWinCounts;
    }
}
        
        
        
        
        
        
        
        
    

