import javafx.application.Application;  //abstract class used for JavaFX GUI's
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;              //class for GUI window
import javafx.scene.Scene;              //class for specific view in GUI window
import javafx.event.EventHandler;       //interface for handling events
import javafx.event.ActionEvent;        //class for type of event for action (like button or key pressed)
import javafx.geometry.Pos;             //class for getting positions for alignment
import javafx.scene.text.Font;          //class for creating Font objects


public class Main extends Application implements EventHandler<ActionEvent>  { //inheriting core functionality + this class will handle events

    /*** PROPERTIES ***/
    private boolean allowWindowResize = false;
    private int width = 1200;
    private int height = 700;
    private String windowTitle = "Bingo";
    private Circle bingoBallCircle;
    private Label bingoBallText;

    private BingoGame bingoGame = new BingoGame();

    /*** GUI COMPONENTS ***/
    private BorderPane layout;
    private Button btnNextBingoBall;
    private MenuItem newGame;
    private MenuItem exit;

    /*** DRIVER main ***/
    public static void main(String[] args) {
        Application.launch(args); //method from Application class, must be called to setup javafx application
    }

    /*** OVERRIDDEN Application METHODS ***/
    @Override
    public void start(Stage primaryStage) throws Exception{ //Application automatically calls this method to run (our) main javafx code. passes in primary stage (main window)

        //SETUP COMPONENTS


        //ADD COMPONENTS
        layout = new BorderPane();

        // TOP SECTION
        Node top = buildTop();
        layout.setTop(top);

        // LEFT SECTION
        Node left = buildLeft();
        layout.setLeft(left);
        layout.setMargin(left, new Insets(0, 30, 0, 30));

        // CENTER SECTION
        Node center = buildCenter();
        layout.setCenter(center);

        // BOTTOM SECTION
        Node bottom = buildBottom();
        layout.setBottom(bottom);
        layout.setMargin(bottom, new Insets(50));

        BackgroundImage bgImage = new BackgroundImage(new Image("/resources/bingo-background.jpg", width, height, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        layout.setBackground(new Background(bgImage));

        //SETUP SCENE AND SHOW
        Scene primaryScene = new Scene(layout, width, height); //layout, dimensions of window
        primaryScene.getStylesheets().add("/resources/styles.css");
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle(windowTitle); //setting title of main window
        primaryStage.setResizable(allowWindowResize); //set resizable attribute to window
        primaryStage.show();
    }

    public Node buildTop()
    {
        VBox container = new VBox();

        AnchorPane layout = new AnchorPane();

        // BEGIN BingoBall
        bingoBallText = new Label("");
        bingoBallText.setFont(Font.font("Roboto", 60));
        bingoBallText.setTextFill(Color.BLACK);

        bingoBallCircle = new Circle();
        bingoBallCircle.setRadius(100.0f);
        bingoBallCircle.setFill(Color.WHITE);
        bingoBallCircle.setStroke(Color.BLACK);
        bingoBallCircle.setStrokeWidth(3.0f);

        Circle bingoBallInnerCircle = new Circle();
        bingoBallInnerCircle.setRadius(80.0f);
        bingoBallInnerCircle.setFill(Color.WHITE);
        bingoBallInnerCircle.setStroke(Color.BLACK);
        bingoBallInnerCircle.setStrokeWidth(3.0f);

        StackPane bingoBall = new StackPane();
        bingoBall.getChildren().addAll(bingoBallCircle, bingoBallInnerCircle, bingoBallText);
        // END BingoBall

        btnNextBingoBall = new Button("Next Bingo Ball");
        btnNextBingoBall.setPrefSize(300.0, 100.0);
        btnNextBingoBall.getStyleClass().add("btn-nextBall");
        btnNextBingoBall.setOnAction(this);

        // Anchor label to the top left corner
        AnchorPane.setTopAnchor(bingoBall, 10.0);
        AnchorPane.setLeftAnchor(bingoBall, 10.0);

        // Anchor button to the top right corner
        AnchorPane.setTopAnchor(btnNextBingoBall, 50.0);
        AnchorPane.setRightAnchor(btnNextBingoBall, 215.0);

        layout.setPadding(new Insets(25, 25, 25, 25));
        layout.getChildren().addAll(bingoBall, btnNextBingoBall);

        container.getChildren().addAll(buildMenuBar(), layout);

        return container;
    }

    public Node buildLeft()
    {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        //layout.getChildren().add(bingoBall);

        return layout;
    }

    public Node buildCenter()
    {
        GridPane grid = new GridPane();
        //grid.setHgap(10.0f);
        //grid.setVgap(10.0f);
        //grid.setGridLinesVisible(true);

        char[] bingoBallLetters = {'B','I','N','G','O'};

        for(int i = 0; i < 5; i++)
        {
            StackPane gridSquare = new StackPane();

            float squareSize = 55.0f;

            Rectangle shape = new Rectangle(squareSize, squareSize);
            switch(bingoBallLetters[i])
            {
                case 'B':
                    shape.setFill(Color.CYAN);
                    break;
                case 'I':
                    shape.setFill(Color.RED);
                    break;
                case 'N':
                    shape.setFill(Color.ORANGE);
                    break;
                case 'G':
                    shape.setFill(Color.LIME);
                    break;
                case 'O':
                    shape.setFill(Color.YELLOW);
                    break;
                default:
                    shape.setFill(Color.WHITE);
                    break;
            }
            shape.setStroke(Color.BLACK);
            shape.setStrokeWidth(1.0f);

            Label bingoLetter = new Label(Character.toString(bingoBallLetters[i]));
            bingoLetter.setFont(Font.font("Roboto", FontWeight.EXTRA_BOLD, 36));

            gridSquare.getChildren().addAll(shape, bingoLetter);

            grid.add(gridSquare, 0, i);

            for(int j = 0; j < 15; j++)
            {
                gridSquare = new StackPane();

                shape = new Rectangle(squareSize, squareSize);
                if(bingoGame.getBingoBallsGrid()[i][j].isBingoBallCalled())
                {
                    shape.setFill(Color.YELLOW);
                }
                else
                {
                    shape.setFill(Color.WHITE);
                }
                shape.setStroke(Color.BLACK);
                shape.setStrokeWidth(1.0f);

                Label bingoNumber = new Label(Integer.toString(bingoGame.getBingoBallsGrid()[i][j].getBingoBallNumber()));
                bingoNumber.setFont(Font.font("Roboto", FontWeight.EXTRA_BOLD, 36));

                gridSquare.getChildren().addAll(shape, bingoNumber);

                grid.add(gridSquare, (j+1), i);
            }
        }

        VBox layout = new VBox();
        layout.setAlignment(Pos.TOP_LEFT);
        layout.getChildren().add(grid);

        return layout;
    }

    public Node buildBottom()
    {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);



        //layout.getChildren().addAll(btnNextBingoBall);

        return layout;
    }

    public MenuBar buildMenuBar()
    {
        MenuBar menuBar = new MenuBar();

        // File
        Menu file = new Menu("File");

        // File >> New Game
        newGame = new MenuItem("New Game");
        newGame.setOnAction(this);

        // File >> Exit
        exit = new MenuItem("Exit");
        exit.setOnAction(this);


        file.getItems().addAll(newGame, exit);
        menuBar.getMenus().addAll(file);
        return menuBar;
    }

    /*** OVERRIDDEN EventHandler METHODS ***/
    @Override
    public void handle(ActionEvent actionEvent)
    {
        //generic method used to handle when events occur (like handle button click)
        //good practice to identify source, in case you have multiple event types/sources
        //System.out.println(actionEvent.getSource());

        // Which button did you push???
        if(actionEvent.getSource() == btnNextBingoBall)
        {
            BingoBall nextBall = bingoGame.nextBingoBall();
            bingoBallText.setText(nextBall.toString());
            bingoBallCircle.setFill(nextBall.getBingoBallColor());

            // Refresh Bingo Board Grid
            Node center = buildCenter();
            layout.setCenter(center);
        }

        if(actionEvent.getSource() == newGame)
        {
            bingoBallText.setText("");
            bingoGame.resetGame();
            // Refresh Bingo Board Grid
            Node center = buildCenter();
            layout.setCenter(center);
        }

        if(actionEvent.getSource() == exit)
        {
            System.exit(0);
        }
    }
}