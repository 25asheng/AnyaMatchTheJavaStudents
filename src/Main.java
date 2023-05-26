import processing.core.PApplet;

import java.util.*;
import java.lang.Math;

public class Main extends PApplet{
    public static PApplet app;


    //Card stuff
    public Card[][] myCards = new Card[5][5];
    private Card prevCard;
    private Card thisCard;

    //51 quinton, 60 mitchell, 48 kelsey, 41 mika, 41 anya, 69 emma, 53 raterman, 49 chloey
    //Datasets
    private String[] usingData;
    private String usingName = "";
    private ArrayList<boardEntry> usingBoardEntries;

    private final String[] javaStudents = {"javaStudents/Izzy.png", "javaStudents/Mika.png",
            "javaStudents/Bala.png", "javaStudents/Julia.png","javaStudents/Kailee.png",
            "javaStudents/Caitlyn.png", "javaStudents/Michelle.png","javaStudents/Claire.png",
            "javaStudents/Naya.png","javaStudents/Anya.png", "javaStudents/Anaika.png",
            "javaStudents/Norah.png","javaStudents/Ms.Greyson.png","Java Students"};

    private final ArrayList<boardEntry> javaStudentsBoardEntries = new ArrayList<>(Arrays.asList(new boardEntry(51, "quinton"),
            new boardEntry(60, "mitchell"), new boardEntry(48,"kelsey"),
            new boardEntry(41,"mika"), new boardEntry( 41,"anya"),
            new boardEntry(69, "my little sister"),new boardEntry( 53, "raterman"),
            new boardEntry(47, "chloe"), new boardEntry(59,"my mom"), new boardEntry(62,"my dad"),
            new boardEntry(32, "diya"), new boardEntry(44,"tucker"), new boardEntry(46,"aasiya"),
            new boardEntry(69, "your mom")));

    private final String[] fruits = {"fruits/Apple.png","fruits/Banana.png",
            "fruits/Orange.png","fruits/Grapes.png","fruits/Blueberries.png",
            "fruits/Strawberries.png","fruits/Raspberries.png","fruits/Blackberries.png",
            "fruits/Kiwi.png","fruits/Dragon_fruit.png","fruits/Pear.png",
            "fruits/Mango.png","fruits/Tomato.png", "Fruits"};

    private final ArrayList<boardEntry> fruitsBoardEntries = new ArrayList<>(Arrays.asList(new boardEntry(44,"ruby"),
            new boardEntry(40,"anya"),new boardEntry(56,"my mom"),new boardEntry(69, "my dad")));

    private final String[] warpedMikas = {"warpedMikas/Mikizzy.png","warpedMikas/Mikbala.png",
            "warpedMikas/Mikulia.png","warpedMikas/Mikailee.png","warpedMikas/Mikaitlyn.png",
            "warpedMikas/Mikelle.png","warpedMikas/Miklaire.png","warpedMikas/Mikaya.png",
            "warpedMikas/Mikanya.png","warpedMikas/Mikaika.png","warpedMikas/Mikorah.png",
            "warpedMikas/Mikreyson.png","warpedMikas/Mikeveryone.png", "Warped Mikas"};

    private final ArrayList<boardEntry> warpedMikasBoardEntries = new ArrayList<>(Arrays.asList(new boardEntry(51,"anya"), new boardEntry(999, "my dad")));

    private final String[] typesOfCats = {"typesOfCats/Calico.png", "typesOfCats/AmericanShorthair.png",
            "typesOfCats/Ginger.png","typesOfCats/Naked.png","typesOfCats/Siamese.png",
            "typesOfCats/Persian.png","typesOfCats/Ragdoll.png","typesOfCats/Munchkin.png",
            "typesOfCats/Tortie.png","typesOfCats/EgyptianMau.png","typesOfCats/RussianBlue.png",
            "typesOfCats/Beluga.png","typesOfCats/Toothless.png", "Cats"};

    private final ArrayList<boardEntry> typesOfCatsBoardEntries = new ArrayList<>(Arrays.asList(new boardEntry(44,"anya"), new boardEntry(59, "my dad")));


    //helpful booleans
    private static boolean STARTSCREEN = true;
    private static boolean SELECTING_TYPE = false;
    private static boolean OVERALL = true;
    private static boolean INSTRUCTIONS = false;
    private static boolean ENDSCREEN = false;
    private boolean onSecond = false;
    private boolean isWrong = false;
    private boolean isRight = false;
    private boolean isClicking = false;
    private boolean clickGreyson = false;
    private boolean enterName = false;
    private boolean page1 = true;
    private boolean page2 = false;



    //helpful ints
    private int countMoves;
    private final int record = 32;
    private int PR = 999;
    private int countDraw;
    private int countFound;
    private int wastedMoves;


    //greyson stuff
    private ArrayList<Card> greysonAdj = new ArrayList<>();
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
    }



    public void setupMyCards(String[] thisDataset, ArrayList<boardEntry> theseBoardEntries){
        usingData = thisDataset;
        usingBoardEntries = theseBoardEntries;
        usingName = usingData[usingData.length - 1];
        isClicking = true;
        PR = 999;
        insertionSort();

        for(int i = 0; i < myCards.length; i++){
            for(int j = 0; j < myCards[0].length; j++){
                int index = i * 5 + j;
                myCards[i][j] = new Card(0,0,usingData[index/2]);
            }
        }
        reset();
        scramble();
    }



    public void draw() {
        countDraw++;

        if (!usingName.equals("")) {
            drawBoard();
        } else {
            background(262, 217, 222);
        }

        if (OVERALL) {
            overall();
        } else if (SELECTING_TYPE){
            selectingType();
        } else if (INSTRUCTIONS){
            instructions();
        } else if (ENDSCREEN) {
            fill(0,0,0,170);
            rect(0, 0, 600, 800);

            fill(255,255,255); //place score name
            textSize(18);
            textAlign(LEFT,CENTER);
            text("place" + "        " + "score" + "         " + "name",200,50);

            rectMode(CENTER);


            if(page1) {
                page1();
            } else if (page2){
                page2();
            }

            textSize(30); //Name Leaderboard!
            text(usingName + " Leaderboard!", 300, 20);

            textSize(80);//52!
            text(countMoves + "!",300, 480);


            textAlign(LEFT, CENTER);
            fill(180, 150, 255);
            rect(300, 420, 400, 44); //Your username: my dad :)

            fill(60, 0, 80);
            textSize(25);
            text("Your username: " + name, 110, 415);


            fill(180, 150, 255);//Click to retry!
            rect(300, 580, 170, 44);
            rectMode(CORNER);

            fill(60, 0, 80);
            textAlign(CENTER, CENTER);
            textSize(25);
            text("Click to retry!", 300, 575);

            drawStatsOutput();

        } else {

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

                PR = Math.min(PR, countMoves);

            }
        }
    }


    public void drawBoard(){
        background(262, 217, 222);

        fill(60, 0, 80);
        textAlign(CENTER, CENTER);
        textSize(30);
        text("Play the " + usingName + " Board!", 300, 25);


        for (int i = 0; i < myCards.length; i++) {
            for (int j = 0; j < myCards[0].length; j++) {
                myCards[i][j].setXY(20 + 115 * j, 50 + 20 + 115 * i);
                myCards[i][j].display();
            }
        }

        fill(180, 150, 255);
        circle(550,50,80);
        fill(255,255,255);
        text("menu",550,45);
        rectMode(CORNER);

        drawStatsOutput();

    }


    public void drawStatsOutput(){
        rectMode(CENTER);
        fill(180, 150, 255);
        rect(300, 716, 300, 147);
        rectMode(CORNER);

        fill(60, 0, 80);
        textAlign(LEFT, TOP);
        textSize(15);
        text("This moves count: " + countMoves, 160, 655);
        text("Personal record: " + PR, 160, 682);
        text("Record best: " + record, 160, 709);
        text("Currently found: " + countFound, 160, 736);
        text("WASTED MOVES: " + wastedMoves, 160, 763);
    }


    public void page1(){
        for (int i = 0; i < Math.min(13,usingBoardEntries.size()); i++) { //1 41 mika
            usingBoardEntries.get(i).setIndex(i + 1);
            usingBoardEntries.get(i).display();
        }

        fill(180, 150, 255);
        rect(350,370,90,22);
        fill(255,255,255);
        textAlign(CENTER, CENTER);
        text("Next page", 350,368);
    }

    public void page2(){
        if(usingBoardEntries.size() >= 13) {
            for (int i = 13; i < usingBoardEntries.size(); i++) { //1 41 mika
                usingBoardEntries.get(i).setIndex(i + 1);
                usingBoardEntries.get(i).display();
            }
        }
        fill(180, 150, 255);
        rect(250,370,90,22);
        fill(255,255,255);
        textAlign(CENTER, CENTER);
        text("Back page", 250,368);
    }


    public void overall(){
        fill(0,0,0,170);
        rectMode(CENTER);
        textAlign(CENTER,CENTER);
        textSize(30);
        rect(300, 400, 600, 800);

        fill(180, 150, 255);
        rect(300,250,250,60);
        fill(255,255,255);
        text("Select Matching Type",300,250,200,60);

        fill(180, 150, 255);
        rect(300,350,250,60);
        fill(255,255,255);
        text("Instructions",300,350,200,60);

        if(!usingName.equals("")) {
            fill(180, 150, 255);
            rect(300, 450, 250, 60);
            fill(255,255,255);
            text("Return to game", 300, 450, 200, 60);

            fill(180, 150, 255);
            rect(300, 550, 250, 60);
            fill(255,255,255);
            text("Retry", 300, 550, 200, 60);
        }

        /*
        ok so here's the plan of action
        you're going to:
        make 4 buttons
            Select matching type/difficulty
                a reminder that if they switch types their current data will not save
                deal with PR--> make sure it gets preset
                handle leaderboard
         */
    }

    public void selectingType(){
        fill(0,0,0,170);
        rectMode(CENTER);
        textAlign(CENTER,CENTER);
        textSize(30);
        rect(300, 400, 600, 800);

        fill(180, 150, 255);
        circle(100,100,80);
        fill(0,0,0);
        textAlign(CENTER,CENTER);
        text("back",100,100);

        fill(180, 150, 255);
        rect(300,250,250,60);
        fill(255,255,255);
        text("Fruits",300,250,200,60);

        fill(180, 150, 255);
        rect(300,350,250,60);
        fill(255,255,255);
        text("Cats",300,350,200,60);

        fill(180, 150, 255);
        rect(300, 450, 250, 60);
        fill(255,255,255);
        text("Java Students", 300, 450, 200, 60);

        fill(180, 150, 255);
        rect(300, 550, 250, 60);
        fill(255,255,255);
        text("Warped Mikas", 300, 550, 200, 60);
    }

    public void instructions(){
        fill(0,0,0,170);
        rectMode(CENTER);
        textSize(40);
        rect(300, 400, 600, 800);

        fill(180,140,50);
        rect(300,400,500,700);


        textAlign(CENTER,TOP);
        fill(0,0,0);
        text("Instructions", 300,70);
        textAlign(LEFT,TOP);
        textSize(15);
        text("          my trackpad responds better if you \n" +
                        "\"tap\" rather than \"click,\" the effect \n" +
                        "will be the same\n" +
                "           there are 12 pairs of cards and 1 \n" +
                        "unique card shuffled face down in this 5x5 board\n" +
                "           you may flip over cards to see what \n" +
                        "they are\n" +
                "           you may only flip over two unmatched cards \n" +
                        "at once\n" +
                "           same pairs will be kept, wrong pairs \n" +
                        "will be flipped back down\n" +
                "           the objective is to flip over all \n" +
                        "the cards\n" +
                "           the unique card will display the cards \n" +
                        "directly adjacent to it for a short time, so pay\n" +
                        "attention!\n" +
                "           press r to reset\n" +
                "           when done, enter a name to see yourself\n" +
                        "on the leaderboard\n" +
                "           if playing again, press enter still to \n" +
                        "update your score\n" +
                "           memorize your PR so that I can manually \n" +
                        "add it later, or it will not be kept in the \n" +
                        "confines of our history"
                , 120, 130);

        fill(180, 150, 255);
        circle(100,70,80);
        fill(0,0,0);
        textAlign(CENTER,CENTER);
        text("back",100,70);
    }


    public void isRight(){
        prevCard.isGreen();
        thisCard.isGreen();
        if (countDraw % 7 == 0) {
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


    private int sequentialSearch(String target){
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
        greysonAdj = new ArrayList<>();

        scramble();

        prevCard = null;
        thisCard = null;
        onSecond = false;
        countMoves = 0;
        countFound = 0;
        countDraw = 0;
        wastedMoves = 0;
        ENDSCREEN = false;
        enterName = false;
        isClicking = true;
        isWrong = false;

        page1 = true;
        page2 = false;


        for(Card[] row: myCards) {
            for (Card card: row) {
                card.setIsFlipped(false);
                card.setWasted(0);
            }
        }
    }


    public void mouseClicked(){
        if(!isClicking){
            if(STARTSCREEN) {
                if(INSTRUCTIONS){
                    double radius = Math.sqrt(Math.pow(mouseX - 100, 2) + Math.pow(mouseY - 70,2));
                    if(radius <= 40.0){
                        INSTRUCTIONS = false;
                        OVERALL = true;
                    }
                } else if(SELECTING_TYPE){
                    double radius = Math.sqrt(Math.pow(mouseX - 100, 2) + Math.pow(mouseY - 100,2));
                    if(radius <= 40.0){
                        SELECTING_TYPE = false;
                        OVERALL = true;

                        if(usingName.equals("")) {
                            noStroke();
                            fill(262, 217, 222);
                            rect(300, 500, 250, 260);
                        }
                    }
                    if(mouseX >= 175 && mouseX <= 425 && mouseY >= 220 && mouseY <= 280){
                        //set selecting type as equal to true
                        setupMyCards(fruits, fruitsBoardEntries);
                        SELECTING_TYPE = false;
                        STARTSCREEN = false;
                    } else if(mouseX >= 175 && mouseX <= 425 && mouseY >= 320 && mouseY <= 380){
                        setupMyCards(typesOfCats, typesOfCatsBoardEntries);
                        SELECTING_TYPE = false;
                        STARTSCREEN = false;
                    } else if(mouseX >= 175 && mouseX <= 425 && mouseY >= 420 && mouseY <= 480){
                        setupMyCards(javaStudents, javaStudentsBoardEntries);
                        SELECTING_TYPE = false;
                        STARTSCREEN = false;
                    } else if(mouseX >= 175 && mouseX <= 425 && mouseY >= 520 && mouseY <= 580){
                        setupMyCards(warpedMikas, warpedMikasBoardEntries);
                        SELECTING_TYPE = false;
                        STARTSCREEN = false;
                    }
                } else if(OVERALL){
                    if(mouseX >= 175 && mouseX <= 425 && mouseY >= 220 && mouseY <= 280){
                        SELECTING_TYPE = true;
                        OVERALL = false;
                    } else if(mouseX >= 175 && mouseX <= 425 && mouseY >= 320 && mouseY <= 380){
                        INSTRUCTIONS = true;
                        OVERALL = false;
                    } else if(!usingName.equals("") && mouseX >= 175 && mouseX <= 425 && mouseY >= 420 && mouseY <= 480){
                        OVERALL = false;
                        isClicking = true;
                        STARTSCREEN = false;
                    } else if(!usingName.equals("")&& mouseX >= 175 && mouseX <= 425 && mouseY >= 520 && mouseY <= 580){
                        reset();
                        OVERALL = false;
                        STARTSCREEN = false;
                        isClicking = true;
                    }
                }
            } else if (ENDSCREEN) {
                if (mouseX >= 215 && mouseX <= 385 && mouseY >= 558 && mouseY <= 602) {
                    reset();
                }
                if (page1 && mouseX >= 315 && mouseX <= 385 && mouseY >= 359 && mouseY <= 381) {
                    page2 = true;
                    page1 = false;
                } else if (page2 && mouseX >= 215 && mouseX <= 285 && mouseY >= 359 && mouseY <= 381) {
                    page1 = true;
                    page2 = false;
                }
            }
        } else {
            double radius = Math.sqrt(Math.pow(mouseX - 550, 2) + Math.pow(mouseY - 50,2));
            if(radius <= 40.0){
                STARTSCREEN = true;
                OVERALL = true;
                isClicking = false;
                INSTRUCTIONS = false;
                ENDSCREEN = false;
                SELECTING_TYPE = false;
            }

            for (int i = 0; i < myCards.length; i++) {
                for (int j = 0; j < myCards[i].length; j++) {
                    Card card = myCards[i][j];
                    if (!card.getIsFlipped()) {
                        if (card.clickedOn(mouseX, mouseY)) { //clicks on that Card
                            if (card.getImgName().equals(usingData[12])){
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
                                isClicking = false;
                                countDraw = 0;

                                if (prevCard.equals(thisCard)) {
                                    isRight = true;
                                } else {
                                    isWrong = true;
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



    public void keyPressed(){
        if(enterName){
            if((int)key == 10){
                enterName = false;
                System.out.println("add score " + countMoves + " and name " + name + " to my dataset!");

                int index = sequentialSearch(name);
                if(index == -1) {
                    usingBoardEntries.add(new boardEntry(countMoves, name));
                } else {
                    usingBoardEntries.get(index).setScore(Math.min(usingBoardEntries.get(index).getScore(), countMoves));
                }

                insertionSort();
                drawBoard();

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