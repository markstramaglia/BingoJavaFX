import java.util.ArrayList;
import java.util.Random;

public class BingoGame
{
    private BingoBall[][] bingoBallsGrid = new BingoBall[5][15];
    private ArrayList<BingoBall> uncalledBingoBalls = new ArrayList<BingoBall>();
    private ArrayList<BingoBall> calledBingoBalls = new ArrayList<BingoBall>();
    private Random random = new Random();

    public BingoGame()
    {
        char[] bingoBallLetters = {'B','I','N','G','O'};
        int bingoBallNumber = 1;

        for(int i = 0; i < bingoBallLetters.length; i++)
        {
            for(int j = 0; j < 15; j++)
            {
                BingoBall ball = new BingoBall(bingoBallLetters[i], bingoBallNumber);
                bingoBallsGrid[i][j] = ball;
                uncalledBingoBalls.add(ball);
                bingoBallNumber++;
            }
        }
    }

    public void resetGame()
    {
        bingoBallsGrid = new BingoBall[5][15];
        uncalledBingoBalls = new ArrayList<BingoBall>();
        calledBingoBalls = new ArrayList<BingoBall>();

        char[] bingoBallLetters = {'B','I','N','G','O'};
        int bingoBallNumber = 1;

        for(int i = 0; i < bingoBallLetters.length; i++)
        {
            for(int j = 0; j < 15; j++)
            {
                BingoBall ball = new BingoBall(bingoBallLetters[i], bingoBallNumber);
                bingoBallsGrid[i][j] = ball;
                uncalledBingoBalls.add(ball);
                bingoBallNumber++;
            }
        }
    }

    public BingoBall nextBingoBall()
    {
        BingoBall ball;

        if(uncalledBingoBalls.size() > 0)
        {
            int index = random.nextInt(uncalledBingoBalls.size());
            ball = uncalledBingoBalls.remove(index);
            ball.setBingoBallCalled(true);
            calledBingoBalls.add(ball);
        }
        else
        {
            ball = new BingoBall('?', 0);
        }

        return ball;
    }

    public BingoBall[][] getBingoBallsGrid()
    {
        return bingoBallsGrid;
    }

    public ArrayList<BingoBall> getCalledBingoBalls()
    {
        return calledBingoBalls;
    }
}
