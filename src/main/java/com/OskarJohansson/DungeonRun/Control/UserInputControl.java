package com.OskarJohansson.DungeonRun.Control;


import java.util.Scanner;

public class UserInputControl {

    static Scanner scanner = new Scanner(System.in);

    static int inputInt(){
        boolean invalidInput = true;
        int result = 0;

        while (invalidInput) {
            result = checkInputForInvalidInputs(scanner.nextLine());

            if (result != -1) {
                invalidInput = false;
            }
        }
        return result;
    }

    static int checkInputForInvalidInputs(String inputValue) {
        int intValue = 0;

        if (inputValue.isBlank()) {
            System.out.println("Input is empty! Try Again!");
            return -1;

        } else {
            try {
                intValue = inputMustBeAPositiveNumber(Integer.parseInt(inputValue.stripLeading().stripTrailing()));

            } catch (NumberFormatException e) {
                System.out.println("That's not a number! Try Again!");
                return -1;
            }
        }
        return intValue;
    }

    static int inputMustBeAPositiveNumber(int intValue) {

        if (intValue < 0) {
            System.out.println("Input must be a positive number! Try Again!");
            return -1;
        }
        return intValue;
    }

    public static void PRESS_ENTER_TO_CONTINUE() {
        System.out.println("\n \033[42mGet ready for a new round!\033[0m\n \033[42mPress Enter to continue \033[0m\n");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
}


