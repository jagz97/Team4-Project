package mancala;
/**
 * @authors Wilson, Guoman
 */

import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class BoardModel
{

    private static final int NUMBER_OF_PITS = 14;

    private ArrayList<ChangeListener> listeners = new ArrayList<>();
    private static final int A_MANCALA = 6;
    private static final int B_MANCALA = 13;

    private MancalaPit[] currBoard;
    private boolean lastMarbleInMancala;
    private int prevMovesThisTurn;
    private int undoA;
    private int undoB;
    private int movesThisTurn;
    private boolean turnA;
    private String message;



    public MancalaPit[] getModel()
    {
        return currBoard;
    }

    /**
     * constrate method
     * @param marblePerPit marbles amount of every pit
     */
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
        undoA = 0;
        undoB = 0;
        movesThisTurn = 0;
        prevMovesThisTurn = 0;
        turnA = true;
        message = "Player A's Turn";
    }

    /**
     * Constructs an empty BoardModel.
     */
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
        undoA = 0;
        undoB = 0;
        movesThisTurn = 0;
        prevMovesThisTurn = 0;
        turnA = true;
        message = "Player A's Turn";
    }

    /**
     * update the listeners
     */
    public void updateListeners(){
        for(ChangeListener l: listeners){
            l.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * add the changeListeners
     * @param l the listener to change
     */
    public void addListener(ChangeListener l){
        listeners.add(l);
    }

    /**
     * initial the board
     * @param MarblePerPit marbles amount of every pit
     */
    public void fillInitialBoard(int MarblePerPit)
    {

        for (int i = 0; i < currBoard.length; i++)
        {
            if (!(i == A_MANCALA || i == B_MANCALA))
            {
                currBoard[i].setCurrentStone(MarblePerPit);
                currBoard[i].setPrevStoneCount(MarblePerPit);
                if  (i < A_MANCALA) currBoard[i].addListener();
            }
        }
    }

    /***
     * get current board
     * @return current board
     */
    public MancalaPit[] getCurrBoard()
    {
        return currBoard;
    }

    /**
     * get mothod to get the marbles amount of every pit
     * @param position a specific pit position
     * @return the marbles amount
     */
    public int getAmountInPit(int position)
    {
        return currBoard[position].getCurrentStone();
    }

    /**
     * check whether it is a last marble
     * @return
     */
    public boolean isLastMarbleInMancala()
    {
        return lastMarbleInMancala;
    }

    /**
     * game end method
     * @return empty
     */
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


    /**
     * display the winner
     * @param emptyPitFlag empty
     */
    public void displayWinner(int emptyPitFlag)
    {

        if (emptyPitFlag == 1)
            moveMarblesToMancala(7, B_MANCALA);

        else if (emptyPitFlag == 2)
            moveMarblesToMancala(0, A_MANCALA);


        //Compare number of stones in two mancalas
        if (currBoard[A_MANCALA].getCurrentStone() > currBoard[B_MANCALA].getCurrentStone())
            message = "Player A Wins!";
        else if (currBoard[A_MANCALA].getCurrentStone() < currBoard[B_MANCALA].getCurrentStone())
            message = "Player B Wins!";
        else message = "It's a tie!";
        updateListeners();
    }

    /**
     * marble move to mancala
     * @param pitPos pit position
     * @param mancalaPos mancala position
     */
    public void moveMarblesToMancala(int pitPos, int mancalaPos)
    {

        for (int i = pitPos; i < mancalaPos; i++)
        {
            currBoard[mancalaPos].add(currBoard[i].getCurrentStone());
            currBoard[i].clear();
        }
    }

    /**
     * movement of marbles
     * @param position pit position
     */
    public void move(int position)
    {
        int ownPosition = position;
        if(!turnA)
        {
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
            int x = 0;
            for(int i = 1; i <= marbleAmount; i++) {
                if (ownPosition + i == 13)
                {
                    currBoard[position + i].setPrevStoneCount(currBoard[position + i].getCurrentStone());
                    marbleAmount++;
                    continue;
                }
                int addPosition = position + i;
                if (addPosition == 14)
                    position = -1*i;//Looping around the board once b6 pit has been reached
                currBoard[position + i].add(1);
                x = i;
            }
            x++;
            if (x <= 14)
            {
                for(int i = x; i <= 14; i++) {
                    int addPosition = position + i;
                    if (addPosition == 14)
                        position = -1*i;//Looping around the board once b6 pit has been reached
                    currBoard[position + i].setPrevStoneCount(currBoard[position + i].getCurrentStone());
                    x = i;
                }
            }

            //set the number of stones in specified pit number to 0
            currBoard[oppoPosition].clear();

            //A's last stone dropped lands in an empty pit on A's side
            if(turnA && endingPit <= 5 && currBoard[endingPit].getPrevstonecount()==0 && !opponMancReached) {
                currBoard[endingPit].clearLeavePrev();
                currBoard[A_MANCALA].add(currBoard[endingPit + (2*(6-endingPit))].getCurrentStone() + 1);
                currBoard[endingPit + (2*(6-endingPit))].clear();
            }
            // B's last stone dropped in an empty pit on B's side, the same
            if(!turnA && endingPit > 6 && endingPit < 13 && currBoard[endingPit].getPrevstonecount() == 0 && !opponMancReached) {
                currBoard[endingPit].clearLeavePrev();
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
            prevMovesThisTurn = movesThisTurn;
            movesThisTurn ++;
            if (turnA) undoB = 0;
            if (!turnA) undoA = 0;
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
        if (x == 1)
        {
            message = "Player B's Turn";
            turnA = false;
        }
        if (x == 0)
        {
            message = "Player A's  Turn";
            turnA = true;
        }
        for (MancalaPit pit : currBoard)
        {
            if (pit.getType() == x)
                pit.addListener();
            else pit.removeListener();
        }
        prevMovesThisTurn = movesThisTurn;
        movesThisTurn = 0;
        updateListeners();
    }

    public void undo()
    {
        if (!(undoA == 3 || undoB == 3))
        {
            for (MancalaPit pit : currBoard)
                pit.revert();
            if ((turnA && movesThisTurn != 0))
                undoA++;
            if ((!turnA && movesThisTurn != 0))
                undoB++;
            if ((turnA && movesThisTurn == 0))
            {
                undoB++;
                movesThisTurn = prevMovesThisTurn;
                switchTurn(1);
                movesThisTurn = prevMovesThisTurn;
            }
            if ((!turnA && movesThisTurn == 0))
            {
                undoA++;
                movesThisTurn = prevMovesThisTurn;
                switchTurn(0);
                movesThisTurn = prevMovesThisTurn;
            }
        }

    }

    /**
     * get messege method
     * @return messege
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * get the previous method
     * @returnthe previous method
     */
    public MancalaPit[] getPrevBoard()
    {
        return currBoard.clone();
    }
}
