import processing.core.PApplet;
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;
import processing.core.PImage;

public class Main extends PApplet{
    public static PApplet app;
    public Card[][] myCards = new Card[5][5];
    private Card prevCard = new Card(0,0);
    private Card thisCard = new Card(0,0);;
    private boolean onSecond = false;
    private String[] imgNames = {"Izzy.png", "Mika.png", "Bala.png", "Julia.png",
            "Kailee.png", "Caitlyn.png", "Ms.Greyson.png", "Michelle.png",
            "Claire.png", "Naya.png", "Anya.png", "Anaika.png","Norah.png"};
    private ArrayList<Card> greysonAdj = new ArrayList<Card>();
    private int countMoves;
    private int countDraw;
    private int countFound;
    private boolean isDone = false;
    private boolean isWrong = false;
    private boolean clickGreyson = false;
    private Card greyson;
    private int greysonX;
    private int greysonY;

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
        System.out.println("OG frameRate is: " + frameRate);
        background(262, 217, 222);

        for(int i = 0; i < myCards.length; i++){
            for(int j = 0; j < myCards[0].length; j++){
                int index = i * 5 + j;
                myCards[i][j] = new Card(0,0);
                if(index < 12){
                    myCards[i][j].setupImage(imgNames[index/2]);
                } else {
                    myCards[i][j].setupImage(imgNames[(index+1)/2]);

                }
            }
        }
        myCards[2][2].setupImage(imgNames[6]);

        //scramble();
    }



    public void draw(){
        countDraw++;
        for(int i = 0; i < myCards.length; i++) {
            for (int j = 0; j < myCards[0].length; j++) {
                myCards[i][j].setXY(20 + 115 * j, 20 + 115 * i);
                myCards[i][j].display();
            }
        }


        if(isWrong) {
            isWrong();
        }

        if(clickGreyson){
            clickGreyson();
        }


        if(countFound == 25){
            isDone = true;

            fill(160,120,255);
            rect(290,695,170,44);

            fill(60,0,80);
            textAlign(CENTER);
            textSize(25);
            text("Click to retry!",375,725);
        }

    }




    public void mouseClicked(){
        System.out.println("mouseClicked");

        if(!isDone && !isWrong && !clickGreyson) {

            for (int i = 0; i < myCards.length; i++) {
                for (int j = 0; j < myCards[i].length; j++) {
                    Card card = myCards[i][j];
                    if (!card.getIsFlipped()) {
                        boolean clickedCard = mouseX > card.getX() && mouseX < card.getX() + 100 && mouseY > card.getY() && mouseY < card.getY() + 100;
                        if (clickedCard) { //clicks on that Card
                            if (card.getImgName().equals(imgNames[6])){
                                clickGreyson = true;
                                countDraw = 0;
                                greyson = card;
                                greysonX = i;
                                greysonY = j;

                            } else if (onSecond) {
                                thisCard = card;
                                thisCard.setIsFlipped(true);
                                thisCard.display();

                                if (prevCard.equals(thisCard)) {
                                    isRight();
                                } else {
                                    isWrong = true;
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
        } else {
            if(mouseX >= 290 && mouseX <= 460 && mouseY >= 695 && mouseY <= 738){
                reset();
            }
        }



    }

    public void isRight(){
        prevCard.isGreen();
        thisCard.isGreen();
        countFound += 2;
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
        }

        for(Card adjCard: greysonAdj){
            adjCard.isYellow();
        }

        if(countDraw % 100 == 0){
            //flip back cards that are not isFlipped
            countFound++;
            clickGreyson = false;
            countDraw = 0;
        }
    }

    public void isWrong(){
        prevCard.isRed();
        thisCard.isRed();
        if (countDraw % 30 == 0) {
            prevCard.setIsFlipped(false);
            thisCard.setIsFlipped(false);
            countDraw = 0;

            prevCard.display();
            thisCard.display();
            isWrong = false;
        }
    }

    private void swap(int i, int j, int m, int n){
        Card temp = myCards[i][j];
        myCards[i][j] = myCards[m][n];
        myCards[m][n] = temp;
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

        fill(262, 217, 222); //cover "Click to retry" box
        noStroke();
        rect(285,690,180,53);

        scramble();

        prevCard = new Card(0,0);
        thisCard = new Card(0,0);;
        onSecond = false;
        countMoves = 0;
        countFound = 0;
        countDraw = 0;
        isDone = false;
        isWrong = false;


        for(int i = 0; i < myCards.length; i++) {
            for (int j = 0; j < myCards[0].length; j++) {
                myCards[i][j].setIsFlipped(false);
            }
        }

    }


}