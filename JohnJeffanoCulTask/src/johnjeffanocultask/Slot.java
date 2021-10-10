/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johnjeffanocultask;

public class Slot extends slotClass{
    
    private int  slotState;
    
    
    public Slot(){
        
        
        slotState = 0;
    }
    
    //Seting the slot to -99
    public void  setSlotState(int newSlotState)
    {
        slotState = newSlotState;     
    }

    /**
     * Getter to slot state
     * @return slot state
     */
    public int getSlotState()
    {
        return slotState;
    }
}
