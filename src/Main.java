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
    private int count;
    private boolean isDone = true;


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

        myCards[0][0] = new Card(0,0,"Izzy");
        myCards[0][1] = new Card(0,0,"Izzy");
        myCards[0][2] = new Card(0,0,"Mika");
        myCards[0][3] = new Card(0,0,"Mika");
        myCards[0][4] = new Card(0,0,"Bala");

        myCards[1][0] = new Card(0,0,"Bala");
        myCards[1][1] = new Card(0,0,"Julia");
        myCards[1][2] = new Card(0,0,"Julia");
        myCards[1][3] = new Card(0,0,"Kailee");
        myCards[1][4] = new Card(0,0,"Kailee");

        myCards[2][0] = new Card(0,0,"Caitlyn");
        myCards[2][1] = new Card(0,0,"Caitlyn");
        myCards[2][2] = new Card(0,0,"Ms.Greyson");
        myCards[2][3] = new Card(0,0,"Michelle");
        myCards[2][4] = new Card(0,0,"Michelle");

        myCards[3][0] = new Card(0,0,"Claire");
        myCards[3][1] = new Card(0,0,"Claire");
        myCards[3][2] = new Card(0,0,"Naya");
        myCards[3][3] = new Card(0,0,"Naya");
        myCards[3][4] = new Card(0,0,"Anya");

        myCards[4][0] = new Card(0,0,"Anya");
        myCards[4][1] = new Card(0,0,"Anaika");
        myCards[4][2] = new Card(0,0,"Anaika");
        myCards[4][3] = new Card(0,0,"Norah");
        myCards[4][4] = new Card(0,0,"Norah");

    }

    public void draw(){

        for(int i = 0; i < myCards.length; i++) {
            for (int j = 0; j < myCards[0].length; j++) {
                myCards[i][j].setXY(20 + 115 * j, 20 + 115 * i);
                //System.out.println("I am setting the Card with name: " + myCards[i][j].getName() + " at position x = " + (20 + 115 * j) + " and y = " + (20 + 115 * i));
                myCards[i][j].display();
            }
        }



    }

    public void mouseClicked(){
        System.out.println("mouseClicked");
        System.out.println(onSecond);

        if(isDone) {
            for (Card[] arr : myCards) {
                for (Card card : arr) {
                    if (!card.getIsFlipped()) {
                        //System.out.println(mouseX > card.getX() && mouseX < card.getX() + 100 && mouseY > card.getY() && mouseY < card.getY() + 100);
                        boolean clickedCard = mouseX > card.getX() && mouseX < card.getX() + 100 && mouseY > card.getY() && mouseY < card.getY() + 100;
                        //boolean doubleClicked = card.isDouble(prevCard);
                        if (clickedCard) { //clicks on that Card
                            if (onSecond) {
                                thisCard = card;
                                thisCard.setIsFlipped(true);
                                thisCard.display();

                                if (prevCard.equals(thisCard)) {
                                    isRight();
                                } else {
                                    isWrong();
                                }


                            } else {
                                prevCard = card;
                                prevCard.setIsFlipped(true);
                            }
                            onSecond = !onSecond;
                            count++;
                        }
                        //System.out.println("INNER");
                    }
                }
                //System.out.println("finished iterating outer loop once");
            }
        }

        System.out.println(onSecond);


    }

    public void isRight(){

    }

    public void isWrong(){
        System.out.println("at the momemnt of calling isWrong right before making isRed, thisCard is: " + thisCard.getIsFlipped());
        prevCard.isRed();
        thisCard.isRed();

        System.out.println("delaying");
        delay(2500);//half a second paused
        System.out.println("delaying");
        prevCard.setIsFlipped(false);
        thisCard.setIsFlipped(false);

        /*
        //somehow delay this implementation by a second?;
        //find a way to put a pause on all actions once done in a ROBUST way that doesn't just involve dozens of if statements and booleans



        prevCard.display();
        thisCard.display();
        */



    }


}