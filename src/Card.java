

import processing.core.PImage;

public class Card {
    private int x;
    private int y;
    private int wasted;
    private final PImage img;
    private final String imgName;
    private boolean isFlipped;

    public Card(int x, int y, String imageName){
        this.x = x;
        this.y = y;
        isFlipped = false;
        img = Main.app.loadImage(imageName);
        imgName = imageName;
    }


    public boolean clickedOn(int mouseX, int mouseY){
        return mouseX > x  && mouseX < x + 100 && mouseY > y && mouseY < y + 100;
    }

    public void display() {
        if(!isFlipped){
            Main.app.rectMode(Main.app.CORNER);
            Main.app.stroke(30, 33, 94);
            Main.app.fill(80, 83, 143);
            Main.app.rect(x, y, 100, 100);

        } else {
            Main.app.imageMode(Main.app.CENTER);
            Main.app.image(img, x + 50, y + 50, 100, 100);

        }
    }



    public void isRed(){

        Main.app.tint(255, 0, 0, 150);
        Main.app.imageMode(Main.app.CENTER);
        Main.app.image(img, x + 50, y + 50, 100, 100);
        Main.app.noTint();// turns off tint effect after the image is displayed
    }
    public void isGreen(){
        Main.app.tint(0, 255, 0, 150);
        Main.app.imageMode(Main.app.CENTER);
        Main.app.image(img, x + 50, y + 50, 100, 100);
        Main.app.noTint();// turns off tint effect after the image is displayed
    }

    public void isYellow(){

        Main.app.tint(160, 140, 10, 150);
        Main.app.imageMode(Main.app.CENTER);
        Main.app.image(img, x + 50, y + 50, 100, 100);
        Main.app.noTint();// turns off tint effect after the image is displayed

        //Main.app.fill(156, 142, 11); //switch to yellow
    }

    public void wasted(){
        wasted++;
    }

    public int getWasted(){
        return wasted;
    }
    public void setWasted(int newWasted){
        wasted = newWasted;
    }

    public String getImgName(){
        return imgName;
    }

    public boolean getIsFlipped(){
        return isFlipped;
    }



    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setIsFlipped(boolean newFlipped){
        isFlipped = newFlipped;
    }


    public boolean equals(Card other){
        return this.imgName.equals(other.getImgName());
    }

}
