/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackjack;

/**
 *
 * @author kiswo
 */

import java.util.Scanner;


public class BlackjackGame {
    private final Deck deck;
    private final Player player;
    private final Player dealer;
    private final Scanner scanner;

    public BlackjackGame() {
        deck = new BlackjackDeck();
        player = new BlackjackPlayer("Player");
        dealer = new BlackjackPlayer("Dealer");
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("================================================");
        System.out.println("      WELCOME TO THE BLACKJACK, GAMBLERS!       ");
        System.out.println("================================================");

        while (true) {
            player.clearHand();
            dealer.clearHand();
            deck.shuffle();

            // Initial deal
            player.addCard(deck.dealCard());
            player.addCard(deck.dealCard());
            dealer.addCard(deck.dealCard());
            dealer.addCard(deck.dealCard());

            // Player's turn
            while (true) {
                System.out.println(player);
                System.out.println("Dealer's visible card: " + dealer.getVisibleCard());

                if (player.isBust()) {
                    System.out.println("You BUST sorry! Dealer wins.");
                    break;
                }

                System.out.println("Do you want to [H]it or [S]tand?");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("H")) {
                    player.addCard(deck.dealCard());
                } else if (choice.equalsIgnoreCase("S")) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please choose 'H'it or 'S'tand.");
                }
            }

            // Dealer's turn
            if (!player.isBust()) {
                while (dealer.getHandValue() < 17) {
                    dealer.addCard(deck.dealCard());
                }

                System.out.println(dealer);

                if (dealer.isBust() || player.getHandValue() > dealer.getHandValue()) {
                    System.out.println("Congratulations You are the real GAMBLER! You win!");
                } else if (player.getHandValue() == dealer.getHandValue()) {
                    System.out.println("It's a tie!");
                } else {
                    System.out.println("Dealer wins!");
                }
            }

            System.out.println("=====================================================");
            System.out.println("Do you want to play again? (Y/N)");
            String playAgain = scanner.nextLine();

            if (!playAgain.equalsIgnoreCase("Y")) {
                break;
            }
        }

        System.out.println("Thanks for playing! Goodbye !!!");
    }
}