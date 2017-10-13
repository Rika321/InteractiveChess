package controller;
import java.awt.Color;
import model.*;
import utility.PrintFormat;
import utility.Status;

import java.util.Scanner;

public class GamePlay {
    public Scanner reader;
    public ChessBoard currBoard;
    public Piece      currPiece;
    public Color      currColor;
    public Move currMove;
    public int destX;
    public int destY;


    /**
     * default constructor for GamePlay
     */
    public GamePlay() {
        this.reader    = null;
        this.currBoard = null;
        this.currColor = null;
        this.currMove  = null;
    }


    /**
     * proceed controller with stored move
     * @param currColor
     * @return
     */
    public ChessBoard proceedGame(Color currColor){
        currBoard.moveChessPiece(this.currPiece.xPos, this.currPiece.yPos, this.destX, this.destY);
        this.currPiece.isFirst = false;
        return currBoard;
    }


    /**
     * get the designate move by commandline or file,
     * check whether it's valid and controller end condition,
     * you have 3 chances to enter correct moves or the controller will end;
     * @param reader
     * @param currBoard
     * @param currColor
     * @return
     */
    public Status currGamePlay(Scanner reader, ChessBoard currBoard, Color currColor){
        this.reader = reader;
        this.currBoard = currBoard;
        this.currColor = currColor;
        this.currMove  = null;

        Status result = Status.DEFAULT;
        for (int i = 0; i < 3; i++){
            result = analyzeMove(reader, currBoard, currColor);
            if(result == Status.VALID_MOVE || result == Status.LOST || result == Status.DRAW) {
                return result;
            } else {
                System.out.println(result);
                continue;
            }
        }
        return Status.RANDOM_ERROR;
    }

    /**
     * a safe version to use Scanner.nextIn
     * @param reader
     * @return
     */
    public int safeScan(Scanner reader){
        if (reader.hasNext()){
            return reader.nextInt();
        } else {
            return 0;
        }
    }

    /**
     * help function for current move
     * @param reader
     * @param currBoard
     * @param currColor
     * @return
     */
    public Status analyzeMove(Scanner reader, ChessBoard currBoard, Color currColor){
        int origX, origY;
        PrintFormat format = new PrintFormat();
        this.currMove = new Move();
        if (currMove.isBeingStalemate(currBoard, currColor)){
            return Status.DRAW;
        }
        else if (currMove.isCheckmate(currBoard, currColor)){
            System.out.print(currColor);
            return Status.LOST;
        }
        else if (currMove.isBeingChecked(currBoard, currColor)){
            System.out.println("You are being checked");
        }
        System.out.print(currColor);
        System.out.println(" Please enter orginal X:");
        origX = safeScan(reader); //reader.nextInt();
        System.out.print(currColor);
        System.out.println(" Please enter orignal Y:");
        origY = safeScan(reader); //reader.nextInt();

        this.currPiece = currBoard.getPiece(origX,origY);
        if(this.currPiece == null){
            return Status.NO_SUCH_PIECE;
        }
        else if (this.currPiece.pieceColor != currColor){
            return  Status.NOT_YOUR_PIECE;
        }
        format.printAvalPosi(this.currPiece, this.currBoard);

        System.out.print(currColor);
        System.out.println(" Please enter destination X:");
        this.destX = safeScan(reader); //reader.nextInt();
        System.out.print(currColor);
        System.out.println(" Please enter destination Y:");
        this.destY = safeScan(reader); //reader.nextInt();
        if (currMove.isValidMove(this.currPiece, currBoard,  this.destX, this.destY, true)){
            return Status.VALID_MOVE;
        }
        else {
            return Status.INVALID_DESTINATION;
        }
    }

}
