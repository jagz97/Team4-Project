import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class BoardModel
{

    private static final int NUMBER_OF_PITS = 14;


    private static final int A_MANCALA = 6;
    private static final int B_MANCALA = 13;

    private MancalaPit[] currBoard;
    private ArrayList<ChangeListener> listeners;
    private boolean lastMarbleInMancala;
    private int undos;
    private int movesThisTurn;


    public MancalaPit[] getModel()
    {
        return currBoard;
    }


    public BoardModel(int marblePerPit)
    {
        currBoard = new MancalaPit[NUMBER_OF_PITS];
        int i = 0;
        while (i < NUMBER_OF_PITS)
        {
            currBoard[i] = new MancalaPit(marblePerPit, this, 0);
            i++;
        }
        currBoard[A_MANCALA].clear();
        currBoard[B_MANCALA].clear();
        lastMarbleInMancala = false;
        listeners = new ArrayList<>();
        undos = 0;
        movesThisTurn = 0;
    }

    // Constructs an empty BoardModel.
    public BoardModel()
    {

        currBoard = new MancalaPit[NUMBER_OF_PITS];
        int i = 0;
        while (i < A_MANCALA)
        {
            currBoard[i] = new MancalaPit(0, this, 0);
            i++;
        }
        currBoard[i] = new MancalaPit(0, this, 2);
        i++;
        while (i < B_MANCALA)
        {
            currBoard[i] = new MancalaPit(0, this, 1);
            i++;
        }
        currBoard[i] = new MancalaPit(0, this, 3);
        lastMarbleInMancala = false;
        listeners = new ArrayList<>();
        undos = 0;
        movesThisTurn = 0;
    }

    // initialize
    public void fillInitialBoard(int MarblePerPit)
    {

        for (int i = 0; i < currBoard.length; i++)
        {
            if (!(i == A_MANCALA || i == B_MANCALA)) 
            {
            	currBoard[i].setCurrentStone(MarblePerPit);
            	if  (i < A_MANCALA) currBoard[i].addListener();
            }
        }
    }


    public MancalaPit[] getCurrBoard()
    {
        return currBoard;
    }

    public int getAmountInPit(int position)
    {
        return currBoard[position].getCurrentStone();
    }


    public boolean isLastMarbleInMancala()
    {
        return lastMarbleInMancala;
    }


    public int isGameOver()
    {

        boolean A_empty = false, B_empty = false;

        //Check if all A's pits are empty
        for (int pitA = 0; pitA < A_MANCALA; pitA++){
            if (currBoard[pitA].getCurrentStone() != 0){
                A_empty = false;
                break;
            } else A_empty = true;
        }

        //Check if all B's pits are empty
        for (int pitB = 7; pitB < B_MANCALA; pitB++){
            if (currBoard[pitB].getCurrentStone() != 0){
                B_empty = false;
                break;

            } else B_empty = true;
        }

        if (A_empty) return 1;
        else if (B_empty) return 2;

        return 0;
    }




    public int displayWinner(int emptyPitFlag)
    {

        if (emptyPitFlag == 1)
            moveMarblesToMancala(7, B_MANCALA);

        else if (emptyPitFlag == 2)
            moveMarblesToMancala(0, A_MANCALA);


        //Compare number of stones in two mancalas
        if (currBoard[A_MANCALA].getCurrentStone() > currBoard[B_MANCALA].getCurrentStone())
            return 1;
        else if (currBoard[A_MANCALA].getCurrentStone() < currBoard[B_MANCALA].getCurrentStone())
            return 2;
        else return 3;
    }


    public void moveMarblesToMancala(int pitPos, int mancalaPos)
    {

        for (int i = pitPos; i < mancalaPos; i++)
        {
            currBoard[mancalaPos].add(currBoard[i].getCurrentStone());
            currBoard[i].clear();
        }
    }

    //move
    public void move(int position)
    {

        boolean turnA = true;
        int ownPosition = position;
        if(position > 5)
        {
            turnA = false;
            //position = position +1; //Causing the top pits to move from the wrong starting point
            ownPosition = position - 7;
        }
        int marbleAmount = currBoard[position].getCurrentStone();
        if(marbleAmount != 0)
        {
            //prevBoard = currBoard.clone();
            lastMarbleInMancala = false;

            if(ownPosition + marbleAmount == 6)
            {
                lastMarbleInMancala = true;

            }
            int oppoPosition = position;
            int endingPit = position + marbleAmount;

            boolean opponMancReached = false;
            if(ownPosition + marbleAmount >= 13) {
                opponMancReached = true;
            }
            //remove stones from chosen pit and redistribute them
            for(int i = 1; i <= marbleAmount; i++) {
            	if (ownPosition + i == 13) 
            	{
            		marbleAmount++;
            		continue;
            	}
                int addPosition = position + i;
                if (addPosition == 14)
                    position = -1*i;//Looping around the board once b6 pit has been reached
                currBoard[position + i].add(1);
            }
            //set the number of stones in specified pit number to 0
            currBoard[oppoPosition].clear();

            //A's last stone dropped lands in an empty pit on A's side
            if(turnA && endingPit <= 5 && currBoard[endingPit].getPrevstonecount()==0 && !opponMancReached) {
                currBoard[endingPit].clear();
                currBoard[A_MANCALA].add(currBoard[endingPit + (2*(6-endingPit))].getCurrentStone() + 1);
                currBoard[endingPit + (2*(6-endingPit))].clear();
            }
            // B's last stone dropped in an empty pit on B's side, the same
            if(!turnA && endingPit > 6 && endingPit < 13 && currBoard[endingPit].getPrevstonecount() == 0 && !opponMancReached) {
                currBoard[endingPit].clear();
                currBoard[B_MANCALA].add(currBoard[endingPit - (2*(endingPit - 6))].getCurrentStone() + 1);
                currBoard[endingPit - (2*(endingPit - 6))].clear();
            }

            //When A passes through opponents side and reaches A's side with the last stone
            if(turnA && opponMancReached)
            {
                int mAmount= currBoard[13].getPrevstonecount();
                int cAmount= currBoard[13].getCurrentStone();
                mAmount=cAmount;
                int nextPitToGetStone = ownPosition + marbleAmount + 1;
                if(nextPitToGetStone > 13) {
                    nextPitToGetStone = nextPitToGetStone - 14;
                }
            }
            //When B passes through opponents side and reaches B's side with the last stone
            if(!turnA && opponMancReached) {
                int BSide=currBoard[6].getPrevstonecount();
                int ASide= currBoard[6].getPrevstonecount();
                ASide = BSide;
                int nextPitToGetStone = ownPosition + marbleAmount + 1;
                if(nextPitToGetStone > 13) {
                    nextPitToGetStone = nextPitToGetStone - 14;
                }
                int stone = currBoard[nextPitToGetStone + 7].getCurrentStone();
                stone = stone + 1;
            }
            movesThisTurn ++;
            if (movesThisTurn == 1) undos = 0;
            if (!lastMarbleInMancala) 
            {
            	if (turnA)
            		switchTurn(1);
            	else switchTurn(0);
            }
            int gameOver = isGameOver();
            if (gameOver != 0)
            	displayWinner(gameOver);
        }
    }

    
    /**
     * adds mouse listeners to given player's pits, and removes from the other
     * @param x - player to switch turn to
     */
    public void switchTurn(int x) 
    {
    	for (MancalaPit pit : currBoard) 
    	{
    		if (pit.getType() == x)
    			pit.addListener();
    		else pit.removeListener();
    	}
    }

    public void undo() {
    	if (undos < 3) 
    	{
    		for (MancalaPit pit : currBoard)
                pit.revert();
            undos ++;
    	}
    }

    //Adds a listener to ArrayList of listeners in the model.
    public void attachBoardLayout(ChangeListener l) {

        listeners.add(l);
    }

    public MancalaPit[] getPrevBoard()
    {
        return currBoard.clone();
    }
}