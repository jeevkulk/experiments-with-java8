package puzzle.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ComparePlayer {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        Player[] player = new Player[n];
        Checker checker = new Checker();

        for(int i = 0; i < n; i++){
            player[i] = new Player(scan.next(), scan.nextInt());
        }
        scan.close();

        Arrays.sort(player, checker);
        for(int i = 0; i < player.length; i++){
            System.out.printf("%s %s\n", player[i].name, player[i].score);
        }
    }

}

class Checker implements Comparator<Player> {

    @Override
    public int compare(Player p1, Player p2) {
        if (p1.getScore() == p2.getScore()) {
            return p1.getName().compareTo(p2.getName());
        } else if (p1.getScore() > p2.getScore()) {
            return -1;
        } else {
            return 1;
        }
    }
}

class Player {
    public String name;
    public int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}