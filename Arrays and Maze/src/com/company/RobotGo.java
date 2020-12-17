package com.company;

import java.util.Arrays;
import java.util.Random;

public class RobotGo {



        public static Random r = new Random(58);

        public static void main(String[] args) {

            char[][] currentBoard = createRandomObstacle(createBoard(10, 20), 100);
            displayBoard(startNavigation(currentBoard));

            System.out.println(Arrays.deepToString(currentBoard));

        }

        public static int[] getRandomCoordinate(int maxY, int maxX) {
            int[] coor = new int[2];
            int y = r.nextInt(maxY);
            int x = r.nextInt(maxX);
            coor[0] = y;
            coor[1] = x;
            return coor;
        }

        // ========================
        // Enter your code below

// Name: Zachary Bensemana
//    Student ID: 260863976

        public static int rowConstant;
        public static int columnConstant;
        public static int verticalDirection = 1;
        public static int horizontalDirection = 0;



        public static char[][] createBoard(int row, int column){

            rowConstant = row;
            columnConstant = column;

            if (column<5 || row<5) {
                throw new IllegalArgumentException ("both y axis and x axis must greater than 5");
            }

            char [][] board = new char [column][row];
            System.out.println(board);

//        Nested loop: makes the first and last entry in each array #, and the top and bottom entire rows.
            for (int i=0; i<column; i++) {
                for (int j=0; j<row; j++) {
                    if (i==0 || i == column - 1)
                        board [i][j] = 35;

                    if (j == 0 || j == row - 1)
                        board [i][j] = 35;
                }
            }
//      adds the first period and the x in their respective corners

            board [1][1] = 46;
            board [column-2][row-2] = 120;

            return board;

        }


        public static char[][] createRandomObstacle (char [][] board, int obstacles){
            int i = obstacles;

//      Checks for the maximum ammount of obstacles by taking the total entries minus the borders and "x" and ".".
//        It only allows the obstacles to be placed where there is no other array entry.
            if (rowConstant*columnConstant - (rowConstant*2 + 2*(columnConstant-2)) - 2 >= i ) {
                while (i > 0) {
                    int coor[] = getRandomCoordinate(columnConstant, rowConstant);
                    if (board[(coor[0])][(coor[1])] == 35 || board[(coor[0])][(coor[1])] == 46 || board[(coor[0])][(coor[1])] == 120) {
                        continue;
                    } else {
                        board[(coor[0])][(coor[1])] = 35;
                        i--;
                    }
                }
            }
            else {
                throw new IllegalArgumentException("Too Many Obstacles");
            }



            return board;
        }

        public static void displayBoard (char[][] board){


//        Nested loop: prints each indvidual entry of the array, and prints a space when the entry is null.
//        Moves to new line when each sub-array is over.
            for(int i = 0; i<columnConstant; i++)
            {
                for(int j = 0; j<rowConstant; j++)
                {
                    if (board [i][j] == 0){
                        System.out.print(" ");
                    }
                    else {
                        System.out.print(board[i][j]);
                    }
                }
                System.out.println();
            }
        }

        public static void turnLeft() {
            if (verticalDirection == 1) {
                verticalDirection = 0;
                horizontalDirection = 1;
            } else if (horizontalDirection == 1) {
                horizontalDirection = 0;
                verticalDirection = -1;
            } else if (verticalDirection == -1) {
                verticalDirection = 0;
                horizontalDirection = -1;
            } else if (horizontalDirection == -1) {
                horizontalDirection = 0;
                verticalDirection = 1;
            }
        }

        public static void turnRight() {
            if (verticalDirection == 1) {
                verticalDirection = 0;
                horizontalDirection = -1;
            } else if (horizontalDirection == -1) {
                horizontalDirection = 0;
                verticalDirection = -1;
            } else if (verticalDirection == -1) {
                verticalDirection = 0;
                horizontalDirection = 1;
            } else if (horizontalDirection == 1) {
                horizontalDirection = 0;
                verticalDirection = 1;
            }
        }


        public static char[][] startNavigation(char[][] board) {
            int x = 1;
            int y = 1;

//      Different code for each possible direction the point can be facing. First checks if there is a right wall, if so it turns right.
//        Then checks if there is a wall in front of it; it turns left until there is no obstacle facing it.

            try {
                while (board[y][x] != 120) {
                    if (verticalDirection==1){
                        if (board[y][x-1] != 35){
                            turnRight();
                        }
                        else if (board[y+1][x]== 35){
                            while (board[y+verticalDirection][x+horizontalDirection]== 35){
                                turnLeft();
                            }
                        }
                        else if (board[y+1][x] == 120)
                            break;
                    }
                    else if (horizontalDirection==1){
                        if (board[y+1][x] != 35){
                            turnRight();
                        }
                        else if (board[y][x+1]== 35){
                            while (board[y+verticalDirection][x+horizontalDirection]== 35){
                                turnLeft();
                            }
                        }
                        else if (board[y][x+1] == 120)
                            break;
                    }

                    else if (verticalDirection==-1){
                        if (board[y][x+1] != 35){
                            turnRight();
                        }
                        else if (board[y-1][x]== 35){
                            while (board[y+verticalDirection][x+horizontalDirection]== 35){
                                turnLeft();
                            }
                        }
                        else if (board[y-1][x] == 120)
                            break;
                    }
                    else if (horizontalDirection==-1){
                        if (board[y-1][x] != 35){
                            turnRight();
                        }
                        else if (board[y][x-1]== 35){
                            while (board[y+verticalDirection][x+horizontalDirection]== 35){
                                turnLeft();
                            }
                        }
                        else if (board[y][x-1] == 120)
                            break;

                    }

//              Moves the point into the obstacle-less position. Checks if it is either back at the start (only happens
//                if there is no exit, as it comes full circle) or if it is at the finish. If true, it breaks.
                    y+=verticalDirection;
                    x+=horizontalDirection;
                    if (board [y][x]==120){
                        break;
                    }
                    else if (y==1&&x==1){
                        break;
                    }
//                If the space is empty, the code places a point.
                    board[y][x] = 46;

                }
                return board;
            } catch (ArrayIndexOutOfBoundsException e) {
                return board;
            }
        }


        // Enter your code above
        // ========================
    }

