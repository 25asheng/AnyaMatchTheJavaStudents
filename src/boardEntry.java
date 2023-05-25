public class boardEntry {
    private String name;
    private int y;
    private int score;

    public boardEntry(int theScore, String theName, int theY){
        name = theName;
        score = theScore;
        y = theY;
    }

    public void setY(int newY){
        y = newY;
    }

    public int getScore(){
        return score;
    }

    public String getName(){
        return name;
    }

    public void setScore(int newScore){
        score = newScore;
    }

    public void display(){
        Main.app.fill(0,0,0);
        Main.app.textSize(22);
        Main.app.textAlign(Main.app.LEFT,Main.app.CENTER);
        Main.app.text(score + "          " + name,200,y);
    }
}



//THIS: upload fruit images
//THIS: make starting screen display
//THIS: adjust mouseClicked for starting screen display

//THIS: write up insertion sort technique for arraylist
