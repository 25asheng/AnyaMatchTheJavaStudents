public class boardEntry {
    private final String name;
    private int index;
    private int score;

    public boardEntry(int theScore, String theName){
        name = theName;
        score = theScore;
    }

    public void setIndex(int newIndex){
        index = newIndex;
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

    public void display() { //assume is going into black fill, text left center, and text size 18
        if (index < 14) {
            Main.app.text(index + "                " + score + "                " + name, 200, 50 + index * 20);
        } else {
            Main.app.text(index + "                " + score + "                " + name, 200, 50 + (index-13) * 20);
        }
    }
}



//THIS: upload fruit images
//THIS: make starting screen display
//THIS: adjust mouseClicked for starting screen display