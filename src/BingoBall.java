import javafx.scene.paint.Color;

public class BingoBall
{
    // Once the letter and number is set on the Bingo ball, they cannot change, so marking final
    private final char bingoBallLetter;
    private final int bingoBallNumber;
    private final Color bingoBallColor;

    private boolean bingoBallCalled;

    public BingoBall(char bingoBallLetter, int bingoBallNumber)
    {
        this.bingoBallLetter = bingoBallLetter;
        this.bingoBallNumber = bingoBallNumber;

        switch(bingoBallLetter)
        {
            case 'B':
                this.bingoBallColor = Color.CYAN;
                break;
            case 'I':
                this.bingoBallColor = Color.RED;
                break;
            case 'N':
                this.bingoBallColor = Color.ORANGE;
                break;
            case 'G':
                this.bingoBallColor = Color.LIME;
                break;
            case 'O':
                this.bingoBallColor = Color.YELLOW;
                break;
            default:
                this.bingoBallColor = Color.WHITE;
                break;
        }

        bingoBallCalled = false;
    }

    public char getBingoBallLetter() {
        return bingoBallLetter;
    }

    public int getBingoBallNumber() {
        return bingoBallNumber;
    }

    public Color getBingoBallColor() { return bingoBallColor; }

    public boolean isBingoBallCalled() {
        return bingoBallCalled;
    }

    public void setBingoBallCalled(boolean bingoBallCalled) {
        this.bingoBallCalled = bingoBallCalled;
    }

    @Override
    public String toString()
    {
        return bingoBallLetter + " " + bingoBallNumber;
    }
}
