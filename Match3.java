import java.util.Random;
import java.util.Scanner;

public class Match3{

    static int board[][] = new int[10][10];
    int TotalScore = 0;
    static int numberOfMovesLeft = 7;
    private static int afterSwapDisplayText = 0;
    public static void main(String...args){
        Match3 obj = new Match3();
        obj.FillOurArray();
    }

    //0-red, 1-green, 2-blue
    void FillOurArray(){
        Random rand = new Random(); 

        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                board[i][j]= rand.nextInt(3);
            }
        }
        DisplayBoard();
    }

    void DisplayBoard(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.printf(Integer.toString(board[i][j]) + "\t");
            }
            System.out.printf("\n \n");
        }
        AskUser();
    }

    void AskUser(){
        Scanner s = new Scanner(System.in);

        System.out.println("Enter 1 to tap on matches. Enter 2 to swap elements");
        System.out.println("Number of moves left: " + numberOfMovesLeft);
        System.out.println("Total score: "+TotalScore+" \n");
        int choice = s.nextInt();

        switch(choice){
            case 1:
                System.out.println("Where is your element:");
                System.out.println("Which row do you want to select?");
        
                int i = s.nextInt();
            
                System.out.println("Which column do you want to select?");
            
                int j = s.nextInt();
            
                RunLogic(i-1, j-1, 0);
                break;
            case 2:
                System.out.println("Which element do you want to swap?");
                System.out.println("Which row do you want to select?");
        
                int first_i = s.nextInt();
                
                System.out.println("Which column do you want to select?");
        
                int first_j = s.nextInt();

                System.out.println("What should it be swapped with?");
                System.out.println("Which row do you want to select?");
        
                int second_i = s.nextInt();
                
                System.out.println("Which column do you want to select?");
        
                int second_j = s.nextInt();

                if(Math.abs(first_i - second_i) <= 1 && Math.abs(first_j - second_j) <=1){
                    SwapElements(first_i-1, first_j-1, second_i-1, second_j-1);
                }
                else{
                    System.out.println("Please select adjacent elements.");
                    AskUser();
                }

                break;
            default:
                System.out.println("Incorrect choice! \n");
                AskUser();
                break;

        }

        s.close();

    }

    void SwapElements(int first_i, int first_j, int second_i, int second_j){
        numberOfMovesLeft--;

        int temp = board[first_i][first_j];

        board[first_i][first_j]=board[second_i][second_j];
        board[second_i][second_j] = temp;

        RunLogic(first_i, first_j, 1);
        RunLogic(second_i, second_j, 1);

        DisplayBoard();
        
    }

    void RunLogic(int selected_r, int selected_c, int state){

        Random rand = new Random(); 

        if(state==0){
            System.out.printf("Selected (" + (selected_r+1) + ","+(selected_c+1) +") \n");
        }
        
        int rowScore = 0;
        int columnScore = 0;
        int countToScanRow = 0;
        int countToScanColumn = 0;

        //checking for next row match
        while(selected_r!=9){
            if(board[selected_r][selected_c] == board[selected_r+1][selected_c]){
                selected_r++;
                rowScore+=5;
                countToScanRow++;
            }
            else{
                break;
            }
        }
            
        //checking for previous row match
        while(selected_r !=0){
            if(board[selected_r][selected_c] == board[selected_r-1][selected_c]){
                selected_r--;
                rowScore+=5;
                countToScanRow++;
            }
            else{
                break;
            }
        }

        //check for below column match
        while(selected_c != 9){
            if(board[selected_r][selected_c] == board[selected_r][selected_c+1]){
                selected_c++;
                columnScore+=5;
                countToScanColumn++;
            }
            else{
                break;
            }
        }
        
        //check for above column match
        while(selected_c !=0){
            if(board[selected_r][selected_c] == board[selected_r][selected_c-1]){
                selected_c--;
                columnScore+=5;
                countToScanColumn++;
            }
            else{
                break;
            }
        }

        //change the selected elements board
        if(rowScore>=15 && columnScore<15){
            int temp = selected_r+countToScanRow;
            for(int i=selected_r;i<=temp;i++){
                if(i==10){
                    break;
                }
                board[i][selected_c]=rand.nextInt(3);
            }
            System.out.println("\n You scored "+rowScore+"! \n");
            TotalScore+= rowScore;
            System.out.println("\n Total score: "+TotalScore+" \n");
            DisplayBoard();
        }
        else if(columnScore>=15 && rowScore<15){
            int temp = selected_c+countToScanColumn;
            for(int i=selected_c;i<temp;i++){
                if(i==10){
                    break;
                }
                board[selected_r][i]=rand.nextInt(3); 
            }
            System.out.println("\n You scored "+columnScore+"! \n");
            TotalScore+= columnScore;
            System.out.println(" Total score: "+TotalScore+" \n");
            DisplayBoard();
        }
        else if(columnScore>=15 && rowScore>=15){
            int tempRow = selected_r+countToScanRow;
            int tempColumn = selected_c +countToScanColumn;
            for(int i=selected_r;i<tempRow;i++){
                if(i==10){
                    break;
                }
                for(int j=selected_c;j<tempColumn;j++){
                    if(j==10){
                        break;
                    }
                    board[i][j]=rand.nextInt(3);
                }
            }
            System.out.println("\n You scored "+(columnScore+rowScore)+"! \n");
            TotalScore+= (columnScore+rowScore);
            System.out.println("\n Total score: "+TotalScore+" \n");
            DisplayBoard();
        }
        else{
            if(state==0){
                System.out.println(" Please select a valid element. \n");
            }
            else{
                if(afterSwapDisplayText==1){
                    System.out.println("No new matches found.");
                    afterSwapDisplayText=0;
                }
                else{
                    afterSwapDisplayText++;
                }
            }
            
            System.out.println("\n Total score: "+TotalScore+" \n");
            DisplayBoard();
        }     

    }

    
}