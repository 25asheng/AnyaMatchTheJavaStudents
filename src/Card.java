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

    /*
    public boolean isDouble(Card other){
        return this.x == other.getX() && this.y == other.getY();
    }
    */
}
