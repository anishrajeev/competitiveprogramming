public class apcsa {
    public static void main(String[] args){
        String smith  = "Smith";
        String Anderson  = "Anderson";
    }
    public int spin(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }
    public void playRound(){
        int user = spin(1, 10);
        int computer = spin(2, 8);
        if(user > computer){System.out.println("You win! " + (user-computer) + " points");}
        else if(computer > user){System.out.println("You loose. " + (user-computer) + " points");}
        if(computer==user){
            int user1 = spin(1, 10) + user;
            int computer1 = spin(2, 8) + computer;
            if(user1 > computer1){System.out.println("You win! " + (user1-computer1) + " points");}
            else if(computer1 > user1){System.out.println("You loose. " + (user1-computer1) + " points");}
            else System.out.println("Tie. 0 points");
        }
    }
}
