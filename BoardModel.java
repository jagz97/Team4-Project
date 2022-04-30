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


    enum GAMECONDITION
    {
        CONTINUE, AFINISHED, BFINISHED ;
    }
    
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
        	currBoard[i] = new MancalaPit(marblePerPit);
        }
        currBoard[A_MANCALA].clear();
        currBoard[B_MANCALA].clear();
        lastMarbleInMancala = false;
        listeners = new ArrayList<>();
        undos = 0;
    }

    // Constructs an empty BoardModel.
    public BoardModel()
    {

        currBoard = new MancalaPit[NUMBER_OF_PITS];
        lastMarbleInMancala = false;
        listeners = new ArrayList<>();
        undos = 0;

    }

    // initialize
    public void fillInitialBoard(int MarblePerPit)
    {

        for (int i = 0; i < currBoard.length; i++)
        {
            if (i == A_MANCALA || i == B_MANCALA)
                currBoard[i].clear();
            else
                currBoard[i].set(MarblePerPit);

        }
    }


    public MancalaPit[] getCurrBoard()
    {
        return currBoard.clone();
    }

    public int getAmountInPit(int position)
    {
        return currBoard[position].get();
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
            if (currBoard[pitA].get() != 0){
                A_empty = false;
                break;
            } else A_empty = true;
        }

        //Check if all B's pits are empty
        for (int pitB = 7; pitB < B_MANCALA; pitB++){
            if (currBoard[pitB].get() != 0){
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
        if (currBoard[A_MANCALA].get() > currBoard[B_MANCALA].get())
            return 1;
        else if (currBoard[A_MANCALA].get() < currBoard[B_MANCALA].get())
            return 2;
        else return 3;
    }


    public void moveMarblesToMancala(int pitPos, int mancalaPos)
    {

        for (int i = pitPos; i < mancalaPos; i++)
        {
            currBoard[mancalaPos].add(currBoard[i].get());
            currBoard[i].clear();
        }
        for (ChangeListener l : listeners)
            l.stateChanged(new ChangeEvent(this));

    }

    //move
    public void move(int position)
    {

        boolean turnA = true;
        int ownPosition = position;
        if(position > 5)
        {
            turnA = false;
            position = position +1;
            ownPosition = position - 7;
        }
        int marbleAmount = currBoard[position];
        if(marbleAmount != 0)
        {
            prevBoard = currBoard.clone();
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

                int addPosition = position + i;
                if (addPosition == 14) {
                    position = -1*i;//Looping around the board once b6 pit has been reached
                }
                currBoard[position + i] = currBoard[position + i] + 1;
            }
            //set the number of stones in specified pit number to 0
            currBoard[oppoPosition] = 0;

            //A's last stone dropped lands in an empty pit on A's side
            if(turnA && endingPit <= 5 && prevBoard[endingPit] == 0 && !opponMancReached) {
                currBoard[endingPit] = 0;
                int opponStones = currBoard[endingPit + (2*(6-endingPit))];
                currBoard[A_MANCALA] = currBoard[A_MANCALA] + opponStones + 1;
                currBoard[endingPit + (2*(6-endingPit))] = 0;
            }
            // B's last stone dropped in an empty pit on B's side, the same
            if(!turnA && endingPit > 6 && endingPit < 13 && prevBoard[endingPit] == 0 && !opponMancReached) {
                currBoard[endingPit] = 0;
                int opponStones = currBoard[endingPit - (2*(endingPit - 6))];
                currBoard[B_MANCALA] = currBoard[B_MANCALA] + opponStones + 1;
                currBoard[endingPit - (2*(endingPit - 6))] = 0;
            }

            //When A passes through opponents side and reaches A's side with the last stone
            if(turnA && opponMancReached) {
                currBoard[13] = prevBoard[13];
                int nextPitToGetStone = ownPosition + marbleAmount + 1;
                if(nextPitToGetStone > 13) {
                    nextPitToGetStone = nextPitToGetStone - 14;
                }

            }
            //When B passes through opponents side and reaches B's side with the last stone
            if(!turnA && opponMancReached) {
                currBoard[6] = prevBoard[6];
                int nextPitToGetStone = ownPosition + marbleAmount + 1;
                if(nextPitToGetStone > 13) {
                    nextPitToGetStone = nextPitToGetStone - 14;
                }
                currBoard[nextPitToGetStone + 7] = currBoard[nextPitToGetStone + 7] + 1;

            }

            //alert listeners
            for (ChangeListener l : listeners) {
                l.stateChanged(new ChangeEvent(this));
            }
        }
    }


    public void undo() {

        for (MancalaPit pit : currBoard)
        	pit.revert();
        undos ++;
        
        //to alert listeners of change
        for (ChangeListener l : listeners) {
            l.stateChanged(new ChangeEvent(this));
        }
    }

    //Adds a listener to ArrayList of listeners in the model.
    public void attachBoardLayout(ChangeListener l) {

        listeners.add(l);
    }

}