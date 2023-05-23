

import processing.core.PImage;

public class Card {
    private int x;
    private int y;
    private PImage img;
    private boolean isFlipped;

    public Card(int x, int y){
        this.x = x;
        this.y = y;
        isFlipped = false;
    }

    public void display() {
        if(!isFlipped){
            Main.app.stroke(30, 33, 94);
            Main.app.fill(80, 83, 143);
            Main.app.rect(x, y, 100, 100);

        } else {
            Main.app.stroke(30, 33, 94);
            Main.app.fill(157, 159, 204);
            Main.app.rect(x, y, 100, 100);

            //Main.app.fill(30, 33, 94);
            //Main.app.textSize(20);
            Main.app.imageMode(Main.app.CENTER);
            Main.app.image(img, x + 50, y + 50, 100, 100);

        }
    }


    public void setupImage(String imageName) {
        img = Main.app.loadImage(imageName);
    }


    public void isRed(){
        Main.app.stroke(30, 33, 94);
        Main.app.fill(200, 20, 15);
        Main.app.rect(x, y, 100, 100);

        Main.app.fill(66, 8, 3);
        Main.app.textSize(20);
        Main.app.imageMode(Main.app.CENTER);
        Main.app.image(img, x + 50, y + 50, 100, 100);
    }
    public void isGreen(){
        Main.app.stroke(30, 33, 94);
        Main.app.fill(15, 200, 20);
        Main.app.rect(x, y, 100, 100);

        Main.app.fill(3, 66, 8);
        Main.app.textSize(20);
        Main.app.imageMode(Main.app.CENTER);
        Main.app.image(img, x + 50, y + 50, 100, 100);
    }

    public void isYellow(){
        System.out.println("reached isYellow");
        Main.app.stroke(30, 33, 94);
        Main.app.fill(237, 221, 71); //switch to yellow
        Main.app.rect(x, y, 100, 100);

        Main.app.fill(156, 142, 11); //switch to yellow
        Main.app.textSize(20);
        Main.app.imageMode(Main.app.CENTER);
        Main.app.image(img, x + 50, y + 50, 100, 100);
    }

    public PImage getImg(){
        return img;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean getIsFlipped(){
        return isFlipped;
    }

    public void setImg(PImage newImg){
        img = newImg;
    }


    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setIsFlipped(boolean newFlipped){
        isFlipped = newFlipped;
    }


    public boolean equals(Card other){
        System.out.println(this.img);
        System.out.println(other.getImg());
        System.out.println(this.img.equals(other.getImg()));
        return this.img.equals(other.getImg());
    }

}
