import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] slotMachine;
        int balance = 100;
        int payout;
        int bet;
        String playAgain;

        System.out.println("WELCOME TO SLOT MACHINE!");
        System.out.println("Symbols: 🍋 🍒 🍇 7️⃣ 🔔");

        while (balance > 0) {
            System.out.println("Current Balance: $" + balance);
            System.out.print("Place your bet amount: ");
            bet = scanner.nextInt();
            scanner.nextLine();

            if (bet > balance) {
                System.out.println("INSUFFICIENT FUNDS!");
                continue;
            } else if (bet <= 0) {
                System.out.println("Bet must be greater than 0!");
                continue;
            } else {
                balance -= bet;
            }

            System.out.println("Spinning...");
            slotMachine = spinSlotMachine();
            printSlot(slotMachine);
            payout = getPayout(slotMachine, bet);
            if(payout > 0) {
                System.out.println("You won $" + payout);
                balance += payout;
            }
            else {
                System.out.println("You lost this round");
            }
            System.out.print("Do you want to play again? (Y/N): ");
            playAgain = scanner.nextLine().toUpperCase();
            if(!playAgain.equals("Y")) {
                break;
            }
        }

        System.out.println("GAME OVER! You ran out of balance.");
        scanner.close();
    }

    static String[] spinSlotMachine() {
        String[] symbols = {"🍋", "🍒", "🍇", "7️⃣", "🔔"};
        String[] slotMachine = new String[3];
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            slotMachine[i] = symbols[random.nextInt(symbols.length)];
        }
        return slotMachine;
    }

    static void printSlot(String[] row) {
        System.out.println(String.join(" | ", row));
    }
    static int getPayout(String[] slotMachine, int bet) {
        if(slotMachine[0].equals(slotMachine[1]) && slotMachine[1].equals(slotMachine[2])) {
            return switch(slotMachine[0]) {
                case "🍒" -> bet * 4;
                case "🍋" -> bet * 8;
                case "🍇" -> bet * 16;
                case "🔔" -> bet * 20;
                case "7️⃣" -> bet * 40;
                default -> 0;
            };
        }
        else if(slotMachine[0].equals(slotMachine[1]) || slotMachine[1].equals(slotMachine[2]) || slotMachine[0].equals(slotMachine[2])) {
            return switch(slotMachine[0]) {
                case "🍒" -> bet * 2;
                case "🍋" -> bet * 4;
                case "🍇" -> bet * 8;
                case "🔔" -> bet * 10;
                case "7️⃣" -> bet * 20;
                default -> 0;
            };
        }
        return 0;
    }
}
