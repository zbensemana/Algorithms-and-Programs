package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        public static void main(String[] args) {
            System.out.println("##########1A Tester: test CreateString()#########");
//        String words1 = GuessTheString.createString(4, 1);
            System.out.print("Test Case 1......");
            testCreateBoard(5, 5, 5);

            System.out.println("##########1b Tester: test createRandomObstacle()#########");
            System.out.print("Test Case 1......");
            testCreateRandomObstacle(createBoard(7, 6), 5, 5);

            System.out.println("##########1c Tester: test displayBoard()#########");
            System.out.println("Test Case 1......");
            testDisplaytheboard(createBoard(7, 5));

            System.out.println("##########1d Tester: test Navigation()#########");
            System.out.print("Test Case 1......");
            testNavigattion(createBoard(7, 5), 5);




            System.out.println("######Penalty and Feedback#########");
            System.out.println("write penalty and feedback here ");
            System.out.println("---------------");
            System.out.println();
            System.out.println("######Score#################");
            System.out.println("Total Score: 100");
            System.out.println("Total Penalty: (Please do not forget to add late submission penalty)");
            System.out.println("Your Score: ");
            System.out.println("############################");

        }

        public static void testCreateBoard(int row, int column, int rewards){
            String correct = null;
            String student = null;
            try {
                student = Arrays.deepToString(RobotGo.createBoard(row, column));
                System.out.println("pass");

            } catch (Exception e) {

                System.out.println("fail");
                System.out.println("Exception: " + e);


            }
        }


        public static void testCreateRandomObstacle(char[][] board, int number, int rewards){
            String correct = null;
            String student = null;
            try {
                student = Arrays.deepToString(RobotGo.createRandomObstacle(board, number));
                System.out.println("pass");

            } catch (Exception e) {
                System.out.println("fail");
                System.out.println("Exception: " + e);

            }
        }

        public static void testDisplaytheboard(char[][] board){
            try{
                System.out.println("------------student board---------");
                RobotGo.displayBoard(board);
                System.out.println("pass");

            }catch (Exception e){
                System.out.println("fail");
                System.out.println("Exception: " + e);
            }
        }

        public static void testNavigattion(char[][] board, int rewards){
            String correct = null;
            String student = null;

            try {
                student = Arrays.deepToString(RobotGo.startNavigation(board));
                System.out.println("pass");
            } catch (Exception e) {
                System.out.println("fail");
                System.out.println("Exception: " + e);

            }
        }

        public static char[][] createBoard(int yAxis, int xAxis) {
            char[][] result = new char[yAxis][xAxis];
            for (int i = 0; i < yAxis; i++) {
                if (i == 0 || i == yAxis - 1) {
                    for (int j = 0; j < xAxis; j++) {
                        if (j == 0 || j == xAxis - 1)
                            result[i][j] = '#';
                        else
                            result[i][j] = '#';
                    }
                } else {
                    for (int j = 0; j < xAxis; j++) {
                        if (j == 0 || j == xAxis - 1) {
                            result[i][j] = '#';
                        } else {
                            result[i][j] = ' ';
                        }
                    }
                }
            }
            result[1][1] = '.';
            result[yAxis - 2][xAxis - 2] = 'x';
            return result;
        }

    }

    }
}
