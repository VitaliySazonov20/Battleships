import java.util.Random;
public class Ship {
    Random random = new Random();
    int size;
    int x ;
    int y ;
    boolean vertical = random.nextBoolean();
    boolean available;
    int[][] board;
    int shipId;
    int[][] availabilityBoard;
    public Ship(int size,int[][] board, int[][] availabilityBoard) {
        this.size=size;
        this.board=board;
        this.availabilityBoard=availabilityBoard;
        available = false;
        reset();
    }
    public void reset(){
        if (vertical) {
            while (!available) {
                x=random.nextInt(10);
                y=random.nextInt(10);
                shipId = Integer.parseInt(Integer.toString(x)+Integer.toString(y))+100;
                for (int i = 0; i < size; i++) {
                    if ((y+i<10)&&(availabilityBoard[y + i][x] ==0)) {
                        available = true;
                    }
                    else {
                        available = false;
                        break;
                    }
                }
            }
            for (int i=0; i<size;i++){
                board[y+i][x]=shipId;
            }
            for(int i=0; i<size;i++) {
                availabilityBoard[y + i][x] = shipId;
                if(y+1+i<10)
                    availabilityBoard[y+1+i][x]=shipId;
                if(y-1+i>-1)
                    availabilityBoard[y-1+i][x]=shipId;
                if(x+1<10) {
                    availabilityBoard[y + i][x + 1] = shipId;
                    if(y-1+i>-1){
                        availabilityBoard[y + i-1][x + 1] = shipId;
                    }
                    if(y+1+i<10){
                        availabilityBoard[y + i+1][x + 1] = shipId;
                    }
                }
                if(x-1>-1) {
                    availabilityBoard[y + i][x - 1] = shipId;
                    if(y-1+i>-1){
                        availabilityBoard[y + i-1][x -1] = shipId;
                    }
                    if(y+1+i<10){
                        availabilityBoard[y + i+1][x - 1] =shipId;
                    }
                }
            }

        }else{
            while (!available) {
                x=random.nextInt(10);
                y=random.nextInt(10);
                shipId = Integer.parseInt(Integer.toString(x)+Integer.toString(y))+100;
                for (int i = 0; i < size; i++) {
                    if ((x+i<10)&&availabilityBoard[y][x+i] == 0) {
                        available = true;
                    }else {
                        available = false;
                        break;
                    }
                }
            }
            for (int i=0; i<size;i++){
                board[y][x+i]=shipId;}
            for(int i=0; i<size;i++) {
                availabilityBoard[y][x+i] = shipId;
                if(y+1<10)
                {
                    availabilityBoard[y+1][x+i]=shipId;
                    if(x-1+i>-1){
                        availabilityBoard[y+1][x+i-1]=shipId;
                    }
                    if(x+1+i<10){
                        availabilityBoard[y+1][x+i+1]=shipId;
                    }
                }

                if(y-1>-1) {
                    availabilityBoard[y - 1][x + i] = shipId;
                    if(x-1+i>-1){
                        availabilityBoard[y-1][x+i-1]=shipId;
                    }
                    if(x+1+i<10){
                        availabilityBoard[y-1][x+i+1]=shipId;
                    }
                }
                if(x+1+i<10)
                    availabilityBoard[y][x+1+i]=shipId;
                if(x-1+i>-1)
                    availabilityBoard[y][x-1+i]=shipId;
            }
        }
    }
}
