package org.uob.a1;

import java.util.Scanner; 

public class Game { 
    
    //displays commands that are available
    public static void commands(){
        System.out.println("\n");
        System.out.println("Commands available: \n");
        System.out.println("move<direction>: move between rooms, <direction> can be north, south, east, west.");
        System.out.println("look: provides instructions for room");
        System.out.println("look<feature>: describes object in a room");
        System.out.println("look<item>: describes an item in inventory");
        System.out.println("score: current score displayed");
        System.out.println("map: map displayed");
        System.out.println("help: commands available displayed");
        System.out.println("yes: to accept");
        System.out.println("no: to decline");
        System.out.println("quit: game ends");
    }
    
    //displays what each item does & how to continue once you've got it
    public static void itemDescription(String item){
        switch(item){
            case "flyer":
                System.out.println("The store clerk opens the back door, where two doors are found. Beach towards the south of station and Desert towards east. Choose direction: ");
                break;
            case "shovel":
                System.out.println("Use shovel to dig sand mounds. Enter any key to continue.");
                System.out.println("Congratulations , you dug through the sand mounds, and found two doors labelled Forest towards east and Crystal Cave towards the south. Enter direction: ");
                break;
            case "key":
                System.out.println("Use key to unlock treasure chest. Enter any key to continue.");
                System.out.println("Congratulations , you can now enter Treasure Room. The room is either towards south or towards west. Choose wisely otherwise you'll have to restart. You can try displaying the map to help. Choose direction: ");
                break;
            case "axe":
                System.out.println("Use axe to chop down trees. Enter any key to continue.");
                System.out.println("Congratulations , you chopped down the correct tree and found two doors found labelled Desert towards the north and Beach towards west. Choose direction: ");
                break;
            case "safety footware":
                System.out.println("Use safety footware in hardware store to prevent injury. Enter any key to continue.");
                System.out.println("Congratulations , you got the right footware you help the owner carry some things and he leads to two doors, labelled Underwater towards the north and Mall rowards west. Choose direction: ");
                break;
            case "ice pick":
                System.out.println("Use ice pick to climb slippery cliffs. Enter any key to continue.");
                System.out.println("Congratulations , you climbed up the cliff and found two doors found labelled Underwater towards east and Mall towards south. Choose direction: ");
                break;
            case "compass":
                System.out.println("Use compass to find water source. Enter any key to continue.");
                System.out.println("Congratulations , you found the water source. There is a door found neraby, labelled Forest towards the south. Enter direction: ");
                break;
            case "snorkeling mask":
                System.out.println("Use snorkeling mask to explore underwater. Enter any key to continue.");
                System.out.println("Congratulations , you used snorkeling mask to swim underwater and found two doors labelled Icy Tundra towards west andd Hardware Store towards south. Choose direction: ");
                break;
            default:
                System.out.println("No information available for this item. Quit the game and restart to try again.");
        }//switch
    }//itemDescription

    //checks if item exists
    public static boolean itemExists(String item){
        switch(item){
            case "flyer":
            case "shovel":
            case "key":
            case "axe":
            case "safety footware":
            case "ice pick":
            case "compass":
            case "snorkeling mask":
                return true;
            default:
                return false;
        }//switch
    }//itemExists

    
    public static int move(String direction, int currentPos, int[][] connectedRooms, 
                             Room[] roomsArray, Map map, Score score, Inventory inv,String[]roomItems){
        int directionEntered=-1;
        switch(direction){
            case "north":
                directionEntered=0;
                break;
            case "east":
                directionEntered=1;
                break;
            case "south":
                directionEntered=2;
                break;
            case "west":
                directionEntered=3;
                break;
        }//switch
        
        if (directionEntered==-1){
            System.out.println("Direction does not exist");
            return currentPos;
        }
        int nextRoom = connectedRooms[currentPos][directionEntered];
        if (nextRoom==-1){
            System.out.println("Room does not exist in that direction");
            return currentPos;
        }
        //when player leaves, remove item from inventory
        String leaving = roomItems[currentPos];
        if((leaving!=null)&&(!(leaving.equals("key")))&&(inv.hasItem(leaving)!=-1)) {
            inv.removeItem(leaving);
        }
        
        //when player moves
        currentPos=nextRoom; //make the next room player is going to the current room
        score.visitRoom(); //update score
        map.placeRoom(roomsArray[currentPos].getPosition(),roomsArray[currentPos].getSymbol());
        System.out.println("Welcome to " + roomsArray[currentPos].getName());
        System.out.println(roomsArray[currentPos].getDescription());
        return currentPos;
    }
    
    //to solve puzzle or meet person
    public static void solveRoom(int currentPos, Inventory inv, boolean[]solved,String[]roomItems, Scanner inputDevice, Score score){
        String itemName = roomItems[currentPos];
        
        if (solved[currentPos]){
            if (itemName!=null){
                System.out.println("You've already obtained required item");
                itemDescription(itemName);
            }else {
                System.out.println("You have solved this room already");
            }
            return;
        } if ((currentPos==9)&&(inv.hasItem("key")!=-1)&&(!(solved[9]))) {
            solved[9] = true;
            return;
        } if (currentPos==2||currentPos==3||currentPos==7||currentPos==8){
                System.out.println("Do you want to interact with person in this room");
                System.out.println("Input a command >> \n");
                String response = inputDevice.nextLine().toLowerCase();
                if (response.equals("yes")){
                    itemName = roomItems[currentPos];
                    System.out.println(itemName + " added to inventory");
                    inv.addItem(itemName);
                    solved[currentPos]=true;
                    itemDescription(itemName);
                }
            }else {
            System.out.println("Solve the puzzle to get the " + itemName + " required");
            String answer;
            switch (currentPos){
                case 1:
                        System.out.println("Solve 15*8. You can write in the sand to work it out");
                        answer = inputDevice.nextLine();
                        if (answer.equals("120")) {
                            System.out.println("Correct! " + itemName + " added to inventory");
                            inv.addItem(itemName);
                            solved[currentPos] = true;
                            itemDescription(itemName);
                        } else {
                            System.out.println("Wrong answer.Quit the game and restart to try again.");
                        }
                        break;
                case 4:
                        System.out.println("Solve 2*18. You can write in the mud to work it out");
                    answer = inputDevice.nextLine();
                    if (answer.equals("36")) {
                        System.out.println("Correct! " + itemName + " added to inventory");
                        inv.addItem(itemName);
                        solved[currentPos] = true;
                        itemDescription(itemName);
                    } else {
                        System.out.println("Wrong answer.Quit the game and restart to try again.");
                    }
                    break;
                case 5:
                        System.out.println("Solve this riddle. Hint: use your location as a clue. I am clear as glass, but I am not a window. You can find me in caves, sparkling in the dark. What am I?");
                    answer = inputDevice.nextLine().toLowerCase();
                    if (answer.equals("crystal")) {
                        System.out.println("Correct! " + itemName + " added to inventory");
                        inv.addItem(itemName);
                        solved[currentPos] = true;
                        itemDescription(itemName);
                    } else {
                        System.out.println("Wrong answer.Quit the game and restart to try again.");
                    }
                    break;
                case 6:
                        System.out.println("Solve this riddle. Hint: use your location as a clue. I’m tiny grains that cover the desert floor. I shift with the wind but never run away. What am I?");
                    answer = inputDevice.nextLine().toLowerCase();
                    if (answer.equals("sand")) {
                        System.out.println("Correct! " + itemName + " added to inventory");
                        inv.addItem(itemName);
                        solved[currentPos] = true;
                        itemDescription(itemName);
                    } else {
                        System.out.println("Wrong answer.Quit the game and restart to try again.");
                    }
                    break;
                }//switch
            }//else
    }//solveRoom

    
    public static void main(String args[]) {
       Scanner inputDevice = new Scanner(System.in);
        //creating map
       Map map = new Map(4,5);
       
       //creating positions, rooms & placing them on map
       Position p0 = new Position(1,1);
       Room r0 = new Room("Train Station","Look at the flyer to hand it to the store clerk waiting at the counter at the train station shop",'t',p0);

       Position p1 = new Position(1,3);
       Room r1 = new Room("Beach","Dig sand mounds to find treasure",'b',p1);
       
       Position p2 = new Position(0,2);
       Room r2 = new Room("Mall","Talk to people to find the key",'m',p2);

       Position p3 = new Position(2,2);
       Room r3 = new Room("Hardware Store","Talk to owner to unlock doors",'h',p3);

       Position p4 = new Position(3,3);
       Room r4 = new Room("Forest","Chop down the correct tree",'f',p4);

       Position p5 = new Position(1,4);
       Room r5 = new Room("Crystal Cave","Break crystals to find key",'c',p5);

       Position p6 = new Position(3,1);
       Room r6 = new Room("Desert","Find water source using compass",'d',p6);

       Position p7 = new Position(2,0);
       Room r7 = new Room("Underwater","Talk to people to unlock doors",'u',p7);

       Position p8 = new Position(0,0);
       Room r8 = new Room("Icy Tundra","Talk to people to unlock doors",'i',p8);

       Position p9 = new Position(0,4);
       Room r9 = new Room("Treasure Room","Use key to open treasure chest",'w',p9);

    //creating array for rooms
    Room [] roomsArray = {r0,r1,r2,r3,r4,r5,r6,r7,r8,r9};
                
    //connecting rooms
    int [][] connectedRooms = new int[10][4]; //using i values to store room & j values to store where connected rooms are in relation,-1=no room,0=north,1=east,2=south,3=west
    for (int i=0;i<10;i++){
        for(int j=0;j<4;j++){
            connectedRooms[i][j]=-1;
        }
    }
    connectedRooms[8][2]=2; //to the south (2) of icy tundra (r8) on the map is the mall (r2)
    connectedRooms[8][1]=7; //to the east (1) of icy tundra (r8) on the map is underwater (r7)
        
    connectedRooms[7][3]=8; //to the west (3) of underwater (r7) on the map is icy tundra (r8)
    connectedRooms[7][2]=3; //to the south (2) of underwater (r7) on the map is the hardware store (r3)
        
    connectedRooms[0][1]=6; //to the east (1) of the train station (r0) on the map is desert (r6)
    connectedRooms[0][2]=1; //to the south (2) of the train station (r0) on the map is the beach (r1)
        
    connectedRooms[6][3]=0; //to the west (3) of the desert (r6) on the map is train station (r0)
    connectedRooms[6][2]=4; //to the south (2) of the desert (r6) on the map is the forest (r4)
        
    connectedRooms[2][0]=8; //to the north (0) of the mall (r2) on the map is icy tundra (r8)
    connectedRooms[2][1]=3; //to the east (1) of the mall (r2) on the map is the hardware store (r3)
    connectedRooms[2][2]=9; //to the south (2) of the mall (r2) on the map is the treasure room (r9)
        
    connectedRooms[3][0]=7; //to the north (0) of the hardware store (r3) on the map is underwater (r7)
    connectedRooms[3][3]=2; //to the west (3) of the hardware store (r3) on the map is the mall (r2)

    connectedRooms[1][0]=0; //to the north (0) of the beach (r1) on the map is the train station (r0)
    connectedRooms[1][1]=4; //to the east (1) of the beach (r1) on the map is forest (r4)
    connectedRooms[1][2]=5; //to the south (2) of the beach (r1) on the map is the crystal cave (r5)
        
    connectedRooms[4][0]=6; //to the north (0) of the forest (r4) on the map is the desert (r6)
    connectedRooms[4][3]=1; //to the west (3) of the forest (r4) on the map is beach (r1)
        
    connectedRooms[9][0]=2; //to the north (0) of the treasure room (r9) on the map is the mall (r2)
    connectedRooms[9][1]=5; //to the east (1) of the treasure room (r9) on the map is the crystal cave (r5)
        
    connectedRooms[5][0]=1; //to the north (0) of the crystal cave (r5) on the map is the beach (r1)
    connectedRooms[5][3]=9; //to the west (3) of the crystal cave (r5) on the map is the treasure room (r9)
   
        
    //setting up for new game
    Inventory inv = new Inventory();
    Score score = new Score(1000);
    boolean gameRunning = true;
    boolean [] solved = new boolean[10];
    String [] roomItems = {"flyer", "shovel", "key", "safety footware", "axe", "key", "compass", "snorkeling mask", "ice pick", null}; //stores which rooms have which items

    //game begins
    System.out.println("Welcome to the train station! You are looking for the treasure room on the map");
    System.out.println("Do you want to continue at the Train Station or start at the Icy Tundra?");
    String startingPos=inputDevice.nextLine().toLowerCase();

    int currentPos;
    if (startingPos.equals("icy tundra")){
        currentPos=8;
        System.out.println("Welcome to the Icy Tundra");
        System.out.println(roomsArray[currentPos].getDescription());
        map.placeRoom(roomsArray[currentPos].getPosition(), roomsArray[currentPos].getSymbol());//locations on map only revealed once player enters room
        commands();
        solveRoom(currentPos,inv,solved,roomItems,inputDevice,score);
    }else {
        currentPos=0;
        System.out.println("At the train station, you see a flyer on the ground and you pick it up. It is advertising a challenge to win up to 2000 coins, if you’re ready to push past your comfort zone, take risks and confront danger head-on");
    map.placeRoom(roomsArray[currentPos].getPosition(), roomsArray[currentPos].getSymbol());
    inv.addItem("flyer");
    commands();
    }
        
    while (gameRunning){
            System.out.println("\n");
            System.out.println("Input a command >> \n");
            String input = inputDevice.nextLine().toLowerCase();
            
            //Move
            if (input.startsWith("move")){
                String direction = input.substring(5);
                int previousPos = currentPos;
                currentPos = move(direction,currentPos,connectedRooms,roomsArray,map,score,inv,roomItems); //using method for move so it can be called multiple times throughout

                //if player tries entering treasure room without key
                if((currentPos==9)&&(inv.hasItem("key")==-1)){
                    System.out.println("You need a key to enter the treasure room");
                    currentPos=previousPos;
                    continue;
                }
                //once player reached treasure
                if (currentPos==9){
                    System.out.println("Congratulations, you have opened the treasure chest. You've won.");
                    System.out.println("Score: " + score.getScore());
                    gameRunning = false;
                }
                if (!(gameRunning)){
                    System.out.println("Game over.");
                }
                solveRoom(currentPos,inv,solved,roomItems,inputDevice,score);

            //Look command
            } else if (input.equals("look")){
                System.out.println(roomsArray[currentPos].getDescription());
            } else if (input.startsWith("look")){
                String commandDes=input.substring(5);
                if ((itemExists(commandDes))&&(inv.hasItem(commandDes)!=-1)){
                    itemDescription(commandDes);
                }else {
                    System.out.println("Command you entered does not exist");
                }
            //Inventory command
            } else if (input.equals("inventory")){
                System.out.println("Inventory: " + inv.displayInventory());
    
            //Score command
            } else if (input.equals("score")){
                System.out.println("Score: " + score.getScore());
    
            //Map command   
            } else if (input.equals("map")){
                System.out.println("Map: \n");
                System.out.println(map.display());
                
            //Help command    
            } else if (input.equals("help")){
                commands();
                    
            //Quit command
            } else if (input.equals("quit")){
                gameRunning = false;
                System.out.println("Game over.");
                
            } else {
                System.out.println("Command does not exist");
            }
        }//while
    }//main
}//class     