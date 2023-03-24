//EHAB ABDALLA - TOWERS OF HANOI IN JAVA
import java.util.ArrayList;
import java.util.Scanner;

class Hanoi {
    public static void main(String[] args){
        ArrayList<Integer>[] b = new ArrayList[3];
        for(int i=0; i<3; i++){
            b[i] = new ArrayList<Integer>();
        }
        int n;
        System.out.println("Please enter the # of Rings to move: ");
        Scanner input = new Scanner(System.in);
        n = input.nextInt();

        for(int i=0; i<36; i++){
            System.out.print("-");
        }
        System.out.println();

        int from=0;
        int ClosestTower = (n%2 == 0 ? 2 : 1 );
        int FurthestTower = (n%2 == 0 ? 1 : 2);
        int to = ClosestTower;
        int Candidate = 1;
        int MoveNum = 0;

        for(int i= n+1; i>= 1; i--){
            b[0].add(i);
        }
            b[1].add(n+1);
            b[2].add(n+1);

        while(b[1].size() < n+1){
            System.out.print("Move #" + ++MoveNum);
            System.out.print(": Transfer Ring " + Candidate);
            System.out.print(" from tower " + (char)(from + 'A'));
            System.out.println(" to tower " + (char)(to + 'A'));

            b[to].add(b[from].get(b[from].size()-1));
            b[from].remove(b[from].size()-1);       
            
            if (b[(to + 1) % 3].get(b[(to + 1) % 3].size()-1) < b[(to + 2) % 3].get(b[(to + 2) % 3].size()-1)) {
                from = (to + 1) % 3;
            }
            else {
                from = (to + 2) % 3;
            }

            Candidate = b[from].get(b[from].size() - 1);
            
            if (b[(from + ClosestTower) % 3].get(b[(from + ClosestTower) % 3].size()-1) > Candidate) { 
                to = (from + ClosestTower) % 3; //Determining the closest tower where the ring can be placed.
            } else {
                to = (from + FurthestTower) % 3;
            }
            
        }

    }

}

