public class TicTacToe
{
    //copy over your constructor from the Tic Tac Toe Board activity in the previous lesson!
    String[][] board = new String[3][3];
    public TicTacToe(){
        for(int i = 0; i < 3; i ++){
            for(int c = 0; c < 3; c++){
                board[i][c] = "-";
            }
        }
    }
    public int turn;

    //this method returns the current turn
    public int getTurn()
    {
        return turn;
    }

    /*This method prints out the board array on to the console
     */
    public void printBoard()
    {
        System.out.println("  0 1 2");
        for(int i = 0; i < 3; i ++){
            System.out.print(i + " ");
            for(int c = 0; c < board[i].length; c++){
                System.out.print(board[i][c] + " ");
            }
            System.out.println();
        }
    }

    //This method returns true if space row, col is a valid space
    public boolean pickLocation(int row, int col)
    {
        if((board[row][col]).equals("-")) return true;
        else return false;
    }

    //This method places an X or O at location row,col based on the int turn
    public void takeTurn(int row, int col)
    {
        if(turn%2 == 0) board[row][col] = "X";
        else board[row][col] = "O";
        turn++;
    }

    //This method returns a boolean that returns true if a row has three X or O's in a row
    public boolean checkRow()
    {
        for(int i = 0; i < 3; i++){
            if(board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !(board[i][0].equals("-")))
                return true;
        }
        return false;
    }

    //This method returns a boolean that returns true if a col has three X or O's
    public boolean checkCol()
    {
        for(int i = 0; i < 3; i++){
            if(board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !(board[0][i].equals("-")))
                return true;
        }
        return false;
    }

    //This method returns a boolean that returns true if either diagonal has three X or O's
    public boolean checkDiag()
    {
        if(board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !(board[0][0].equals("-"))){
            return true;
        }
        if(board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !(board[0][2].equals("-"))){
          return true;
        }
        return false;
    }
    //This method returns a boolean that checks if someone has won the game
    public boolean checkWin()
    {
        if(checkDiag() || checkCol() || checkRow()) return true;
        else return false;
    }
    public boolean checkTie(){
        boolean tie = true;
        for(int i = 0; i < 3; i++){
            for(int c = 0; c < 3; c++){
                if(board[i][c].equals("-")) tie = false;
            }
        }
        return tie;
    }
    private String getWinnerString(){
        for(int i = 0; i < 3; i++){
            if(board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !(board[0][i].equals("-")))
                return board[0][i];
        }
        if(board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !(board[0][0].equals("-"))){
            return board[0][0];
        }
        if(board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !(board[0][2].equals("-"))){
            return board[0][2];
        }
        for(int i = 0; i < 3; i++){
            if(board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !(board[0][i].equals("-")))
                return board[0][i];
        }
        return "Error";
    }
    public int getWinner(){
        if(getWinnerString().equals("X")) return 1;
        else if(getWinnerString().equals("O")) return 2;
        else return -1;
    }
}
