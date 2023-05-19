import processing.core.PApplet;
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;

public class Main extends PApplet{
    public static PApplet app;
    public Card[][] myCards = new Card[5][5];
    private Card prevCard = new Card(0,0,"Lorem Ipsum!!");
    private Card thisCard = new Card(0,0,"dolor.");;
    private boolean onSecond = false;
    private String[] names = {"Izzy", "Mika", "Bala", "Julia", "Kailee", "Caitlyn", "Ms.Greyson", "Michelle", "Claire", "Naya", "Anya", "Anaika","Norah"};
    private int countMoves;
    private int countDraw;
    private int countFound;
    private boolean isDone = false;
    private boolean isWrong = false;


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

        for(int i = 0; i < myCards.length; i++){
            for(int j = 0; j < myCards[0].length; j++){
                int index = i * 5 + j;
                if(index < 12){
                    myCards[i][j] = new Card(0,0,names[index/2]);
                } else {
                    myCards[i][j] = new Card(0,0,names[(index+1)/2]);

                }
            }
        }
        myCards[2][2] = new Card(0,0,names[6]);

        //scramble();


    }

    public void draw(){
        countDraw++;
        //System.out.println("draw called, count = " + countDraw);
        for(int i = 0; i < myCards.length; i++) {
            for (int j = 0; j < myCards[0].length; j++) {
                myCards[i][j].setXY(20 + 115 * j, 20 + 115 * i);
                //System.out.println("I am setting the Card with name: " + myCards[i][j].getName() + " at position x = " + (20 + 115 * j) + " and y = " + (20 + 115 * i));
                myCards[i][j].display();
            }
        }

        if(isWrong) {
            isWrong();
        }

        if(countFound == 25){
            isDone = true;

            fill(160,120,255);
            rect(290,695,170,43);

            fill(60,0,80);
            textSize(25);
            text("Click to retry!",300,725);
        }

    }




    public void mouseClicked(){
        System.out.println("mouseClicked");
        System.out.println(onSecond);

        if(!isDone && !isWrong) {

            for (Card[] arr : myCards) {
                for (Card card : arr) {
                    if (!card.getIsFlipped()) {
                        //System.out.println(mouseX > card.getX() && mouseX < card.getX() + 100 && mouseY > card.getY() && mouseY < card.getY() + 100);
                        boolean clickedCard = mouseX > card.getX() && mouseX < card.getX() + 100 && mouseY > card.getY() && mouseY < card.getY() + 100;
                        //boolean doubleClicked = card.isDouble(prevCard);
                        if (clickedCard) { //clicks on that Card
                            if (card.getName().equals("Ms.Greyson")){
                                clickGreyson(card); //to-do: clear prevCard in clickGreyson method
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
                        //System.out.println("INNER");
                    }
                }
                //System.out.println("finished iterating outer loop once");
            }
        } else {
            System.out.println("got to else");
            System.out.println("mouseX: " + mouseX + "\nmouseY: " + mouseY);
            if(mouseX >= 290 && mouseX <= 460 && mouseY >= 695 && mouseY <= 738){
                System.out.println("calling reset");
                reset();
            }
        }

        System.out.println(onSecond);


    }

    public void isRight(){
        prevCard.isGreen();
        thisCard.isGreen();
        countFound += 2;
    }

    public void clickGreyson(Card greyson){
        countFound++;
        greyson.setIsFlipped(true); //fix
    }

    public void isWrong(){
        //System.out.println("at the moment of calling isWrong right before making isRed, thisCard is: " + thisCard.getIsFlipped());
        prevCard.isRed();
        thisCard.isRed();
        //System.out.println("before while loop isWrong, is:" + countDraw);
        if (countDraw % 30 == 0) {
            //System.out.println("when flipping back:" + countDraw);
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

    public void reset(){ //fix repeated use of reset
        System.out.println("reset");
        fill(262, 217, 222); //cover "Click to retry" box
        rect(285,690,180,53);

        prevCard = new Card(0,0,"Lorem Ipsum!!");
        thisCard = new Card(0,0,"dolor.");;
        onSecond = false;
        countMoves = 0;
        countFound = 0;
        countDraw = 0;
        isDone = false;
        isWrong = false;

        //iterate through all to flip back

    }

}