import java.util.Objects;
import java.util.Scanner;

public class Connect4_v1 {
    // 2 player
    // TODO:finish the method to print the board
    /*
    TODO: Human method that allows program to take move from the user, your method should  be able to validate the usser's input
     until the user enters a valid move
     */
    /*
    TODO: write a check Possible method that checks  a move is or isn't possible, if a coloumn
     is already full, return false else return true
     */
    String [][]board;
    Boolean winner;
    Boolean draw;
    int winningPlayer;
    int playerTurn;

    public Connect4_v1(){
        winningPlayer = 0;
        draw =false;
        playerTurn =1;
        board = new String[6][7];
        newBoard();
        displayBoard();

    }

    public void newBoard(){
        for(int i = 0;i <6; i++){
            for(int j =0; j<7; j++){
                board[i][j]=" * ";

            }

        }
    }

    public void displayBoard(){
        System.out.println(" ");
        System.out.println("======== Connect4 -==========");
        System.out.println(" ");
        for(int i =0; i<6; i++){
            for(int j=0;j<7;j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean checkPossible(String input){
        return((Objects.equals(input,"1")||(Objects.equals(input,"2")||(Objects.equals(input,"3")
                ||(Objects.equals(input,"4")||(Objects.equals(input,"5")
                ||(Objects.equals(input,"6")||(Objects.equals(input,"7")))))))));
    }
    public boolean isColumnFull(int column){
        return(board[0][column-1]==" X " || board[0][column-1]==" O ");
    }

    private int getNextAvailableSlot(int column){
        int position = 5;
        boolean found= false;
        while(position >= 0 && !found){
        if(!Objects.equals(board[position][column]," X ")&&!Objects.equals(board[position][column]," O ")){
            found= true;

        }else{
            position--;
        }

        }
        return position;
    }
    public void swapPlayerTurn(){
        if(playerTurn ==1){
            playerTurn=2;
        }else{
            playerTurn =1;
        }
    }

    public void placePiece(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Player "+ playerTurn + " - please select which column to place your piece(1-7) : ");
        String input = sc.nextLine();
        while(!checkPossible(input) || isColumnFull(Integer.parseInt(input))){
            while(!checkPossible(input)){
                System.out.println("invalid input - please select a column from 1-7");
                input = sc.nextLine();
            }
            while(isColumnFull(Integer.parseInt(input))){
                System.out.println("Column full, choose another column");
                input = sc.nextLine();
                if(!checkPossible(input)){
                    break;
                }

            }
        }
        int columnChoice = Integer.parseInt(input) -1;
        System.out.println("Next available row in column: " + getNextAvailableSlot(columnChoice));
        String pieceToPlace;
        if(playerTurn == 1){
            pieceToPlace = " X ";
        }else{
            pieceToPlace= " O ";
        }
        board[getNextAvailableSlot(columnChoice)][columnChoice]=pieceToPlace;
        swapPlayerTurn();
        displayBoard();


    }
    public boolean isBoardFull(){
        boolean full = true;
        for(int i = 0; i < 1; i++){
            for(int j = 0; j < 7 ; j++){
                if(board[i][j] != " X " && board[i][j]!= " O "){
                    full = false;
                }
            }
        }
        return full;
    }
    public String checkVerticalWinner(){
        String symbol = null;
        for(int i =0; i< 3; i++){
            for(int  j =0; j < 7 ; j++){
                if(board[i][j]==board[i+1][j] && (board[i][j]==board[i+2][j])&& (board[i][j]==board[i+3][j])){
                    if(board[i][j]==" X " || board[i][j]==" O "){
                        symbol = board[i][j];
                    }


                }
            }
        }
        return symbol;
    }

    public String checkHorizontalWinner(){
        String symbol = null;
        for(int i =0; i< 6; i++){
            for(int  j =0; j < 4 ; j++){
                if(board[i][j]==board[i][j+1] && (board[i][j]==board[i][j+2])&& (board[i][j]==board[i][j+3])){
                    if(board[i][j]==" X " || board[i][j]==" O "){
                        symbol = board[i][j];
                    }


                }
            }
        }
        return symbol;
    }

    public String checkLeftDiagonalWinner(){
        String symbol = null;
        for(int i =0; i< 3; i++){
            for(int  j =0; j < 4 ; j++){
                if(board[i][j]==board[i+1][j+1] && (board[i][j]==board[i+2][j+2])&& (board[i][j]==board[i+3][j+3])){
                    if(board[i][j]==" X " || board[i][j]==" O "){
                        symbol = board[i][j];
                    }


                }
            }
        }
        return symbol;
    }
    public String checkRightDiagonalWinner(){
        String symbol = null;
        for(int i =0; i< 3; i++){
            for(int  j =3; j < 7 ; j++){
                if(board[i][j]==board[i+1][j-1] && (board[i][j]==board[i+2][j-2])&& (board[i][j]==board[i+3][j-3])){
                    if(board[i][j]==" X " || board[i][j]==" O "){
                        symbol = board[i][j];
                    }


                }
            }
        }
        return symbol;
    }

    public int checkForWinner(){
        int winner =0;
        String symbol = " ";
        if(checkVerticalWinner()==" X " || checkVerticalWinner() ==" O "){
            symbol = checkVerticalWinner();
        }else if (checkHorizontalWinner()==" X " || checkHorizontalWinner() ==" O "){
            symbol = checkHorizontalWinner();

        }else if (checkRightDiagonalWinner()==" X " || checkRightDiagonalWinner() ==" O "){
            symbol = checkRightDiagonalWinner();

        }else if(checkLeftDiagonalWinner()==" X " || checkLeftDiagonalWinner() ==" O "){
            symbol = checkLeftDiagonalWinner();

        }

        if(symbol== " X "  ){
            winner = 1;
        }else if (symbol == " O "){
            winner =2;
        }
        return winner;
    }

    public boolean checkForDraw(){
        return(isBoardFull()==true &&(checkForWinner()!= 1 && checkForWinner()!=2));
    }

    public void showWinnner(){
        System.out.println("you won " + winningPlayer);
    }

    public void playGame(){
        while(winningPlayer ==0 ){
            winningPlayer = checkForWinner();
            draw = checkForDraw();
            if(winningPlayer ==1){
                showWinnner();
                break;
            }else if (winningPlayer == 2 ){
                showWinnner();
                break;
            }else if (draw == true ){
                System.out.println("it's a draw");
                break;
            }
            placePiece();
        }
    }







    public static void main(String[] args) {
        Connect4_v1 c4 = new Connect4_v1();
        c4.playGame();

    }













}
