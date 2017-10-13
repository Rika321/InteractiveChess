package controller;
import java.awt.Color;

import junit.framework.TestCase;
import model.ChessBoard;
import utility.PrintFormat;
import utility.Status;

import java.io.File;
import java.util.Scanner;

public class GameTest extends TestCase {
    public void testPrintBoard() throws Exception {
    }

    public void testMain() throws Exception {
        PrintFormat format = new PrintFormat();
        File file = new File("C:\\Users\\rika\\Desktop\\CS242\\Assignment1.1\\src\\data\\input_data");
        if (file.createNewFile()){
            System.out.println("File is created!");
        }else{
            System.out.println("File already exists.");
        }
        Scanner reader = new Scanner(file);
        System.out.println("Welcome to ChessGame!");
        ChessBoard myChessBoard = new ChessBoard(false);
        ChessBoard testChessBoard = new ChessBoard(false);
        testChessBoard.moveChessPiece(5,6,5,5);
        testChessBoard.moveChessPiece(4,1,4,3);
        testChessBoard.moveChessPiece(6,6,6,5);
        testChessBoard.moveChessPiece(3,0,7,4);
        for (int i = 0; i < 10; i++){
            if (i == 4){
                assertEquals(true, myChessBoard.isEqual(testChessBoard));
            }
            Color currColor = (i%2 == 0)?Color.WHITE : Color.BLACK;
            format.printBoard(myChessBoard);
            GamePlay currPlay = new GamePlay();
            Status result = currPlay.currGamePlay(reader, myChessBoard, currColor);
            if (result != Status.VALID_MOVE){
                assertEquals(result, Status.RANDOM_ERROR);
                System.out.println(result);
                break;
            } else {
                myChessBoard = currPlay.proceedGame(currColor);
            }
        }
    }

    public void testMain2() throws Exception {
        PrintFormat format = new PrintFormat();
        File file = new File("C:\\Users\\rika\\Desktop\\CS242\\Assignment1.1\\src\\data\\input_data2");
        if (file.createNewFile()){
            System.out.println("File is created!");
        }else{
            System.out.println("File already exists.");
        }
        Scanner reader = new Scanner(file);
        System.out.println("Welcome to ChessGame!");
        ChessBoard myChessBoard = new ChessBoard(true);
        ChessBoard testChessBoard = new ChessBoard(true);

        testChessBoard.moveChessPiece(0,5,0,1);
        testChessBoard.moveChessPiece(0,2,0,7);
        testChessBoard.moveChessPiece(7,5,6,4);
        testChessBoard.moveChessPiece(7,2,6,3);
        testChessBoard.moveChessPiece(5,6,5,5);
        testChessBoard.moveChessPiece(4,1,4,3);
        for (int i = 0; i < 10; i++){
            if (i == 6){
                assertEquals(true, myChessBoard.isEqual(testChessBoard));
            }
            Color currColor = (i%2 == 0)?Color.WHITE : Color.BLACK;
            format.printBoard(myChessBoard);
            GamePlay currPlay = new GamePlay();
            Status result = currPlay.currGamePlay(reader, myChessBoard, currColor);
            if (result != Status.VALID_MOVE){
                assertEquals(result, Status.RANDOM_ERROR);
                System.out.println(result);
                break;
            } else {
                myChessBoard = currPlay.proceedGame(currColor);
            }
        }
    }

}