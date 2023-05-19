public class Card {
    private int x;
    private int y;
    private String name;
    private boolean isFlipped;

    public Card(int x, int y, String theName){
        this.x = x;
        this.y = y;
        name = theName;
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

            Main.app.fill(30, 33, 94);
            Main.app.textSize(20);
            Main.app.text(name, x + 30, y + 60);

        }
    }

    public void isRed(){
        Main.app.stroke(30, 33, 94);
        Main.app.fill(200, 20, 15);
        Main.app.rect(x, y, 100, 100);

        Main.app.fill(66, 8, 3);
        Main.app.textSize(20);
        Main.app.text(name, x + 30, y + 60);
    }
    public void isGreen(){
        Main.app.stroke(30, 33, 94);
        Main.app.fill(15, 200, 20);
        Main.app.rect(x, y, 100, 100);

        Main.app.fill(3, 66, 8);
        Main.app.textSize(20);
        Main.app.text(name, x + 30, y + 60);
    }

    public void isYellow(){
        Main.app.stroke(30, 33, 94);
        Main.app.fill(15, 200, 20); //switch to yellow
        Main.app.rect(x, y, 100, 100);

        Main.app.fill(3, 66, 8); //switch to yellow
        Main.app.textSize(20);
        Main.app.text(name, x + 30, y + 60);
    }

    public String getName(){
        return name;
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

    public void setName(String newName){
        name = newName;
    }


    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setIsFlipped(boolean newFlipped){
        isFlipped = newFlipped;
    }


    public boolean equals(Card other){
        return this.name.equals(other.getName());
    }

}
