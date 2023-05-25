import processing.core.PApplet;

import java.util.*;
import java.lang.Math;
import processing.core.PImage;

public class Main extends PApplet{
    public static PApplet app;


    //Card stuff
    public Card[][] myCards = new Card[5][5];
    private Card prevCard;
    private Card thisCard;

    //51 quinton, 60 mitchell, 48 kelsey, 41 mika, 41 anya, 69 emma, 53 raterman, 49 chloey
    //Datasets
    private String[] usingData;
    private ArrayList<boardEntry> usingBoardEntries;
    private ArrayList<boardEntry> javaStudentsBoardEntries = new ArrayList<boardEntry>(Arrays.asList(new boardEntry(51, "quinton",0),
            new boardEntry(60, "mitchell",0), new boardEntry(48,"kelsey",0),
            new boardEntry(41,"mika",0), new boardEntry( 41,"anya",0),
            new boardEntry(69, "my little sister",0),new boardEntry( 53, "raterman",0),
            new boardEntry(49, "chloe",0), new boardEntry(59,"my mom",0)));
    private String[] javaStudents = {"javaStudents/Izzy.png", "javaStudents/Mika.png",
            "javaStudents/Bala.png", "javaStudents/Julia.png","javaStudents/Kailee.png",
            "javaStudents/Caitlyn.png", "javaStudents/Michelle.png","javaStudents/Claire.png",
            "javaStudents/Naya.png","javaStudents/Anya.png", "javaStudents/Anaika.png",
            "javaStudents/Norah.png","javaStudents/Ms.Greyson.png","Java Students"};

    private ArrayList<boardEntry> fruitsBoardEntries = new ArrayList<boardEntry>(Arrays.asList(new boardEntry(0,"person",0)));

    private String[] fruits = {"fruit/Apples.png","fruit/Bananas.png",
            "fruit/Oranges.png","fruit/Grapes.png","fruit/Blueberries.png",
            "fruit/Strawberries.png","fruit/Raspberries.png","fruit/Blackberries.png",
            "fruit/Kiwi.png","fruit/Dragon_fruit.png","fruit/Pear.png",
            "fruit/Mango.png","fruit/Tomato.png", "Fruits"};



    //helpful booleans
    private static boolean STARTSCREEN = true;
    private static boolean ENDSCREEN = false;
    private boolean firstEndScreen = true;
    private boolean onSecond = false;
    private boolean isWrong = false;
    private boolean isRight = false;
    private boolean isClicking = false;
    private boolean clickGreyson = false;
    private boolean enterName = false;


    //helpful ints
    private int countMoves;
    private final int record = 41;
    private int PR;
    private int countDraw;
    private int countFound;
    private int wastedMoves;


    //greyson stuff
    private ArrayList<Card> greysonAdj = new ArrayList<Card>();
    private Card greyson;
    private int greysonX;
    private int greysonY;


    //other
    private String name = "";



    public static void main(String[] args){
        PApplet.main("Main");
    }

    public Main(){
        super();
        app = this;
    }

    public void settings(){
        size(600,800); //4 in-betweens of 15 each, 2 sides of 20 each, and 5 of 100
    }

    public void setup() {
        background(262, 217, 222);
        /*
        Table table = loadTable("javaStudents/leaderboard");
        for (TableRow row : table.rows()) {

        }
        */
    }



    public void setupMyCards(String[] thisDataset, ArrayList<boardEntry> theseBoardEntries){
        usingData = thisDataset;
        usingBoardEntries = theseBoardEntries;
        insertionSort();

        for(int i = 0; i < myCards.length; i++){
            for(int j = 0; j < myCards[0].length; j++){
                int index = i * 5 + j;
                myCards[i][j] = new Card(0,0,usingData[index/2]);
            }
        }
        //scramble();
    }



    public void draw(){
        countDraw++;

        if(STARTSCREEN){
            background(262, 217, 222);

        } else if (ENDSCREEN) {
            if(firstEndScreen) {
                noStroke();
                fill(0,0,0,60);
                rect(0, 0, 600, 800);
                stroke(0,0,0);
                firstEndScreen = false;
            }

            textAlign(CENTER, CENTER);
            fill(0,0,0);
            textSize(80);
            text(countMoves + "!",300, 480);

            textSize(30);
            text(usingData[usingData.length - 1] + " Leaderboard!", 300, 20);


            for (int i = 0; i < usingBoardEntries.size(); i++) {
                usingBoardEntries.get(i).setY(50 + i * 25);
                usingBoardEntries.get(i).display();
            }

            rectMode(CENTER);
            fill(262, 217, 222);
            rect(300, 580, 170, 44);


            fill(160, 120, 255, 100);
            rect(300, 580, 170, 44);
            rectMode(CORNER);

            fill(60, 0, 80);
            textAlign(CENTER, CENTER);
            textSize(25);
            text("Click to retry!", 300, 575);

            drawStatsOutput();

            rectMode(CENTER);
            fill(262, 217, 222);
            rect(300, 400, 400, 44);


            fill(160, 120, 255, 100);
            rect(300, 400, 400, 44);
            rectMode(CORNER);

            fill(60, 0, 80);
            textAlign(LEFT, CENTER);
            textSize(25);
            text("Your username: " + name, 110, 395);

        } else {
            drawBoard();

            if (isWrong) {
                isWrong();
            }
            if (isRight) {
                isRight();
            }
            if (clickGreyson) {
                clickGreyson();
            }
            if (countFound == 25) {
                ENDSCREEN = true;
                enterName = true;
                isClicking = false;

                if(PR == 0){
                    PR = countMoves;
                } else {
                    PR = Math.min(PR, countMoves);
                }
            }
        }
    }


    public void drawBoard(){
        background(262, 217, 222);

        for (int i = 0; i < myCards.length; i++) {
            for (int j = 0; j < myCards[0].length; j++) {
                myCards[i][j].setXY(20 + 115 * j, 20 + 115 * i);
                myCards[i][j].display();
            }
        }
        drawStatsOutput();
    }



    public void mouseClicked(){
        //System.out.println("mouseClicked");
        if(!isClicking){
            if(STARTSCREEN) {
                if (mouseX < 300) {
                    setupMyCards(javaStudents, javaStudentsBoardEntries);
                } else {
                    setupMyCards(fruits, fruitsBoardEntries);
                }
                STARTSCREEN = false;
                isClicking = true;
            } else if (ENDSCREEN){
                if(mouseX >= 215 && mouseX <= 385 && mouseY >= 558 && mouseY <= 602){
                    reset();
                }
            }
        } else {
            for (int i = 0; i < myCards.length; i++) {
                for (int j = 0; j < myCards[i].length; j++) {
                    Card card = myCards[i][j];
                    if (!card.getIsFlipped()) {
                        boolean clickedCard = mouseX > card.getX() && mouseX < card.getX() + 100 && mouseY > card.getY() && mouseY < card.getY() + 100;
                        if (clickedCard) { //clicks on that Card
                            if (card.getImgName().equals(javaStudents[12])){
                                clickGreyson = true;
                                isClicking = false;
                                countDraw = 0;
                                greyson = card;
                                greysonX = i;
                                greysonY = j;

                            } else if (onSecond) {
                                thisCard = card;
                                thisCard.setIsFlipped(true);
                                thisCard.display();

                                if (prevCard.equals(thisCard)) {
                                    isRight = true;
                                    isClicking = false;
                                    countDraw = 0;
                                } else {
                                    isWrong = true;
                                    isClicking = false;
                                    countDraw = 0;
                                }
                                onSecond = !onSecond;
                            } else {
                                prevCard = card;
                                prevCard.setIsFlipped(true);
                                onSecond = !onSecond;
                            }
                            countMoves++;
                        }
                    }
                }
            }
        }
    }


    public void drawStatsOutput(){
        rectMode(CENTER);
        fill(262, 217, 222);
        rect(300, 696, 300, 147);

        fill(160, 120, 255, 150);
        rect(300, 696, 300, 147);
        rectMode(CORNER);

        fill(60, 0, 80);
        textAlign(LEFT, TOP);
        textSize(15);
        text("This moves count: " + countMoves, 160, 635);
        text("Personal record: " + PR, 160, 662);
        text("Record best: " + record, 160, 689);
        text("Currently found: " + countFound, 160, 716);
        text("WASTED MOVES: " + wastedMoves, 160, 743);
    }


    public void isRight(){
        prevCard.isGreen();
        thisCard.isGreen();
        if (countDraw % 7 == 0) {
            countDraw = 0;
            countFound += 2;
            prevCard.display();
            thisCard.display();
            isRight = false;
            isClicking = true;
        }
    }


    public void clickGreyson(){
        if(countDraw == 1){ //first time called
            greyson.setIsFlipped(true); //fix

            if(onSecond){
                prevCard.setIsFlipped(false);
                onSecond = false;
            }

            if(greysonX != 0){
                greysonAdj.add(myCards[greysonX - 1][greysonY]);
            }
            if(greysonX != myCards.length - 1){
                greysonAdj.add(myCards[greysonX + 1][greysonY]);
            }
            if(greysonY != 0){
                greysonAdj.add(myCards[greysonX][greysonY - 1]);
            }
            if(greysonY != myCards[0].length - 1){
                greysonAdj.add(myCards[greysonX][greysonY + 1]);
            }

            for(Card adjCard: greysonAdj){
                if(adjCard.getWasted() == 0){
                    adjCard.wasted();
                }
            }
        }

        for(Card adjCard: greysonAdj){
            adjCard.isYellow();
        }

        if(countDraw % 100 == 0){
            countFound++;
            clickGreyson = false;
            isClicking = true;
            countDraw = 0;
        }
    }


    public void isWrong(){
        prevCard.isRed();
        thisCard.isRed();

        if (countDraw % 30 == 0) {
            prevCard.setIsFlipped(false);
            thisCard.setIsFlipped(false);
            prevCard.wasted();
            thisCard.wasted();
            if(prevCard.getWasted() >= 2){
                wastedMoves++;
            }
            if(thisCard.getWasted() >= 2){
                wastedMoves++;
            }
            countDraw = 0;

            prevCard.display();
            thisCard.display();
            isWrong = false;
            isClicking = true;
        }
    }



    private void swap(int i, int j, int m, int n){
        Card temp = myCards[i][j];
        myCards[i][j] = myCards[m][n];
        myCards[m][n] = temp;
    }

    private void swap(int i, int j){
        boardEntry temp = usingBoardEntries.get(i);
        usingBoardEntries.set(i,usingBoardEntries.get(j));
        usingBoardEntries.set(j,temp);
    }

    private void insertionSort(){
        for (int i = 1; i < usingBoardEntries.size(); i++){
            int key = usingBoardEntries.get(i).getScore();
            int j = i - 1;
            while(j >= 0 && usingBoardEntries.get(j).getScore() > key){
                swap(j+1,j);
                j--;
            }
        }
    }


    /*
    private int binarySearch(int bottom, int top, String target) {
        if (bottom <= top) {
            int mid = (bottom + top) / 2;
            if (usingBoardEntries.get(mid).getName().equals(target)) {
                return mid;
            } else if (thisList.get(mid).getPrice() < targetPrice) {
                for(int i = bottom; i <= mid; i++){
                    thisList.get(i).turnGrey();
                }
                bottom = mid + 1;
            } else {
                for(int i = mid; i <= top; i++){
                    thisList.get(i).turnGrey();
                }
                top = mid - 1;
            }
        }
        if(bottom > top) {
            finishedSearching = true;
        }
        return -1;

    }

     */



    private int selectionSort(String target){
        for(int i = 0; i < usingBoardEntries.size(); i++){
            if(usingBoardEntries.get(i).getName().equals(target)){
                return i;
            }
        }
        return -1;
    }





    public void scramble(){
        for(int i = 0; i < myCards.length; i++){
            for(int j = 0; j < myCards[i].length; j++){
                int x = (int)(Math.random() * myCards.length);
                int y = (int)(Math.random() * myCards[i].length);
                swap(i,j,x,y);
            }
        }
    }



    public void reset(){
        System.out.println("reset");
        greysonAdj = new ArrayList<Card>();

        scramble();

        prevCard = null;
        thisCard = null;
        onSecond = false;
        countMoves = 0;
        countFound = 0;
        countDraw = 0;
        wastedMoves = 0;
        ENDSCREEN = false;
        firstEndScreen = true;
        isClicking = true;
        isWrong = false;

        //don't reset STARTSCREEN

        for(int i = 0; i < myCards.length; i++) {
            for (int j = 0; j < myCards[0].length; j++) {
                myCards[i][j].setIsFlipped(false);
            }
        }
    }

    public void keyPressed(){
        if(enterName){
            if((int)key == 10){
                enterName = false;
                System.out.println("note to self: add score " + countMoves + " and name " + name + " to my dataset!");

                int index = selectionSort(name);
                if(index == -1) {
                    usingBoardEntries.add(new boardEntry(countMoves, name, 0));
                } else {
                    usingBoardEntries.get(index).setScore(PR);
                }

                insertionSort();
                drawBoard();
                firstEndScreen = true;

            } else if (key == 8 && name.length() != 0) {
                name = name.substring(0,name.length()-1);
            } else if ((int)key != 65535){
                name = name + key;
            }
        } else if (key == 'r'){
            reset();
        }
    }


}