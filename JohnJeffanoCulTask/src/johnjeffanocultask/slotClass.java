
package johnjeffanocultask;
interface baseSlot{
        public void setAllPosition(char newSlotPosition, int newRowPosition);   
}

class slotClass implements baseSlot{
    
    char slotPosition;
    int  rowPosition;
    
    public slotClass(){
        slotPosition = ' ';
        rowPosition  =  0; 
    }
    
    /**
     * Set slot and row position
     * @param newSlotPosition character to slot position
     * @param newRowPosition  integer to row position
     */
    public void setAllPosition(char newSlotPosition, int newRowPosition)
    {
      slotPosition =  newSlotPosition;
      rowPosition  =  newRowPosition;  
    } 
}
