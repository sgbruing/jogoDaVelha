package com.company;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static String[][] table = {{".",".","."},{".",".","."},{".",".","."}};
    private static int turn = 2;
    private static String letter = "";
    private static String coord = "";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (Objects.equals(checkWinner(), "")) {
            showTable();
            turnAssistant();
        }
        showTable();
        System.out.println("O vencedor é "+checkWinner());

    }

    private static void showTable() {
        for (String[] strings : table) {
            System.out.println(Arrays.toString(strings));
        }
    }

    private static void turnAssistant() {
        if (turn%2==0){
            letter = "X";
        } else {
            letter = "O";
        }
        System.out.println("Vez do "+letter);
        System.out.println("Digite as coordenadas(somente números separados por vírgula): ");
        coord = scanner.next();
        validateCoord(coord,letter);
    }

    private static void validateCoord(String coord, String letter) {
        // Creating array of string length
        char[] charArray = new char[coord.length()];

        // Copy character by character into array
        for (int i = 0; i < coord.length(); i++) {
            charArray[i] = coord.charAt(i);
        }

        System.out.println(charArray[0]);
        System.out.println(charArray[2]);

        int coordA = Integer.parseInt(String.valueOf(charArray[0]));
        int coordB = Integer.parseInt(String.valueOf(charArray[2]));

        if (Objects.equals(table[coordA][coordB], "X") ||
                Objects.equals(table[coordA][coordB], "O")) {
            System.out.println("entrada inválida");
            turnAssistant();
        } else{
            table[coordA][coordB] = letter;
            turn ++;
        }
    }
    private static String checkWinner() {
        if (!Objects.equals(checkSideways(), "")) {
            return checkSideways();
        } else if (!Objects.equals(checkColumn(), "")) {
            return checkColumn();
        } else if (!Objects.equals(checkCross(), "")) {
            return checkCross();
        } else {
            return "";
        }
    }
    private static String checkCross() {
        String x = "X";
        String o = "O";
        String victor = "";
        String pivot = "";
        if (!Objects.equals(table[1][1], ".")){
            pivot = table[1][1];
        }
        boolean check1 = Objects.equals(pivot, table[0][0]) && Objects.equals(pivot, table[2][2]);
        boolean check2 = Objects.equals(pivot, table[0][2]) && Objects.equals(pivot, table[2][0]);
        if (check1 || check2){
            return pivot;
        }
        return "";
    }

    private static String checkSideways() {
        String x = "X";
        String o = "O";
        for (String[] strings : table) {
            int countX = 0;
            int countO = 0;
            for (int c = 0; c < 3; c++) {
                String pivot = strings[c];
                if (Objects.equals(pivot, x)) {
                    countX++;
                } else if (Objects.equals(pivot, o)) {
                    countO++;
                }
            }
            if (countO == 3) {
                return "O";
            } else if (countX == 3) {
                return "X";
            }
        }
        return "";
    }

    private static String checkColumn() {
        String x = "X";
        String o = "O";
        for (int i=0;i<table.length;i++){
            int countX = 0;
            int countO = 0;
            for (int c=0;c<3;c++){
                String pivot = table[c][i];
                if (Objects.equals(pivot, x)){
                    countX ++;
                } else if(Objects.equals(pivot, o)){
                    countO ++;
                }
            }
            if (countO == 3){
                return "O";
            } else if (countX == 3){
                return "X";
            }
        }
        return "";
    }
}
