public class BingoBall
{
    // Once the letter and number is set on the Bingo ball, they cannot change, so marking final
    private final char bingoBallLetter;
    private final int bingoBallNumber;

    private boolean bingoBallCalled;

    public BingoBall(char bingoBallLetter, int bingoBallNumber)
    {
        this.bingoBallLetter = bingoBallLetter;
        this.bingoBallNumber = bingoBallNumber;
        bingoBallCalled = false;
    }

    public char getBingoBallLetter() {
        return bingoBallLetter;
    }

    public int getBingoBallNumber() {
        return bingoBallNumber;
    }

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
