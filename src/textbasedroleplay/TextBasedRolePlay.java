/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textbasedroleplay;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author gunth
 */
public class TextBasedRolePlay {

    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
        // System objects
        Scanner in = new Scanner(System.in);
        Random randomizer = new Random();

        // Game Variables and Assignments
        String [] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin"};
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;
        String enemy; // Enemy name in an attack
        int enemyHealth; // Enemy health in an attack
        
      
        // Player Variables and Assignments
        int health = 100; // Players starting health 25 more than the Enemy
        int attackDamage = 50; // twice as much as the enemy
        int numberOfHealthPotions = 3;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50; // Percentage 

        // Game Variables
        boolean running = true;
        boolean validInput;
        String userInput = null;
        int damageDealt; //Gameplay variable for random number of player damage dealth
        int damageTaken; // Gameplay variable for random number of enemy damage dealt

        System.out.println("Welcome to the Dungeon!!");

        GAME: 
        while (running) {
            // Output line to show player that there is a new situation coming up
            System.out.println("--------------------------------------------------");

            // Use the imported random to get a random number
            enemyHealth = randomizer.nextInt(maxEnemyHealth); // Gives random # between 0 and 75
            enemy = enemies[randomizer.nextInt(enemies.length)]; 
            System.out.println("\t# "+ enemy + " has appeared #\n ");

            while (enemyHealth > 0) {
                battleInstructions( health, enemy, enemyHealth); // method call to battleInsytructions
                String valids = "1,2,3";
                userInput = getCommand(valids);
          
                
                if (userInput.equals("1")) {
                    damageDealt = randomizer.nextInt(attackDamage); // max 25 for enemy, 50 for player
                    damageTaken = randomizer.nextInt(enemyAttackDamage);
                    System.out.println(damageDealt + "Dealt /n Taken " + damageTaken);
                    enemyHealth -= damageDealt;
                    health -= damageTaken;

                    System.out.println("\t> You strike the "+ enemy + " for "+damageDealt + " damage.");
                    System.out.println("\t> You receive "+ damageTaken + " in retaliation!");

                    if (health < 1) {
                        System.out.println("\t> You have taken too much damage to go on! " + 
                                            "You must exit the cave to heal yourself.");
                        break;
                    }
                }
                else if (userInput.equals("2")) {
                    if (numberOfHealthPotions > 0) {
                        health += healthPotionHealAmount;
                        numberOfHealthPotions --;
                        System.out.println("\t> You drink a health potion for \n" + healthPotionHealAmount +
                                        "\t> Your HP is now \n" + health +
                                        "\t> You have " + numberOfHealthPotions + " remaining. \n");
                    }
                    else {
                        System.out.println("\t> You don't have any health potions left! \n" +
                                            "\t> Defeat an enemy to get more points and a chance for more potions.\n");
                    }

                }
                else if (userInput.equals("3")) {
                    System.out.println("\t> You run away from "+ enemy +"!");
                    continue GAME;

                }
                else {
                    System.out.println("\t> Invalid input.");
                } // End of user input if/else 

                
                
                if (health < 1 ) {
                    System.out.println("\t> You limp out of the dungeon, weak from battle");
                    break; // Goes out of battle loop.
                }
                // health must be greater than 0 and that means you won. Kind of silly?
                System.out.println("--------------------------------------------------");
                System.out.println("\t> You have defeated the "+ enemy +"!");
                System.out.println("\t> Your HP is now " + health +
                                        "\n\t> You have " + numberOfHealthPotions + " potions remaining. \n");
                if (randomizer.nextInt(100) < healthPotionDropChance) {
                    numberOfHealthPotions ++;
                    System.out.println("\t> The "+ enemy +" dropped a health potion!\n" +
                                        "\t> You now have " + numberOfHealthPotions + " health potion(s).");
                }
               

            }// End of the battle loop
            
            System.out.println("--------------------------------------------------");
            System.out.println("\t What would you like to do?");
            System.out.println("\t1 Continue fighting");
            System.out.println("\t2 Exit dungeon");

            do {
                validInput = false; // This could have been done intially, but it is safer here if we move the code.
                userInput = in.nextLine();
                if (userInput.equals("1") || userInput.equals("2")) {
                    validInput = true;
                }

            }while (!validInput); // Loops as long as (!False) so while validInput is False.

            if (userInput.equals("1")) {
                System.out.println("\t You continue on your adventure");
            }else if (userInput.equals("2")) {
                System.out.println("\t You exit the dungeon successfully!");
                break;
            }


        }// End of the GAME loop
       System.out.println("--------------------------------------------------");
       System.out.println("\t Thanks for playing");


    }// End of public void main
   public static void battleInstructions(int health, String enemy, int enemyHealth){
        System.out.println("\tYour HP:"+ health);
        System.out.println("\t" + enemy +"'s HP: "+ enemyHealth);
        System.out.println("\nWhat would you like to do?");
        System.out.println("\t1 Attack");
        System.out.println("\t2 Drink health potion");
        System.out.println("\t3 Run");
   }
   public static String getCommand (String validStuff){
       boolean validInput =false;
       String [] validVals; // Locally defined variable, so new each time!
       validVals = validStuff.split(","); //Splits apart the string to smaller strings: "1,2,3" becomes array.
                                          // with validVals[0]= "1" and validVals[1] ="2"
       String temp;
       Scanner getLn =  new Scanner(System.in) ;
       
       do {
             temp = getLn.nextLine();
             
            for(int i =0; i<validVals.length; i++){
               if (validVals[i].equals(temp)){
                    validInput = true;
               }
                    
            } // End of For loop
            if (!validInput) {
                System.out.println("Invalid command, please look above for your options.");
            }

            }while (!validInput); // Loops as long as (!False) so while validInput is False.
       return temp;
   }
}// End of Class TextBasedGame

