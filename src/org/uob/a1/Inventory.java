package org.uob.a1;

public class Inventory {
    final int MAX_ITEMS = 10;
    private int currentItems;
    private String [] items;

    public Inventory(){
        this.currentItems=0;
        this.items=new String[MAX_ITEMS];
    }
    
    public void addItem(String item){
        if (currentItems<MAX_ITEMS){
            for (int x=0;x<MAX_ITEMS;x++){
                if (items[x]==null){
                    items[x]=item;
                    currentItems++;
                    break;
                }//inner if
            }//for
        }//outer if
    }//addItem
    
    public int hasItem(String item){
        for (int x=0;x<MAX_ITEMS;x++){
            if ((items[x]!=null)&&(items[x].equals(item))){
                return x;
            }//if
        }//for
        return -1;
    }
    
    public void removeItem(String item){
        int removedIndex=-1;
        if (currentItems>0){
            for (int x=0;x<MAX_ITEMS;x++) {
                if ((items[x]!=null)&&(items[x].equals(item))){
                    items[x]=null;
                    removedIndex=x;
                    currentItems--;
                    break;
                }//inner if
            }//for
        }// outerif
        if (removedIndex!=-1){
            for (int y=removedIndex;y<MAX_ITEMS-1;y++){
                items[y]=items[y+1];
            }
            items[MAX_ITEMS-1]=null;
        }//if
    }//removeItem

    public String displayInventory(){
        String outString="";
        for (int x=0;x<currentItems;x++){
            outString+=items[x]+" ";
        }//for
        return outString;
    }//displayInventory
}//class