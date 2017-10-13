package controller;

import model.ChessBoard;
import model.Move;
import model.Piece;
import utility.Status;
import view.PieceView;

import java.awt.*;
import java.util.Vector;

public class Frame {
    public ChessBoard currBoard;
    public Color currColor;
    public Move  currMove;
    //public Piece     currPiece;
    public PieceView origBut;
    public PieceView destBut;
    public int origX;
    public int origY;
    public int destX;
    public int destY;


    /**
     * constructor for Frame
     * @param currBoard
     * @param currColor
     */
    public Frame(ChessBoard currBoard, Color currColor){
        this.currBoard = currBoard;
        this.currColor = currColor;
        this.origBut = null;
        this.destBut = null;
    }



    /**
     * return game end condition
     * @param currBoard
     * @param currColor
     * @return game end condition
     */
    public Status checkEnding(ChessBoard currBoard, Color currColor){
        this.currMove = new Move();
        if (currMove.isBeingStalemate(currBoard, currColor)){
            return Status.DRAW;
        }
        else if (currMove.isCheckmate(currBoard, currColor)){
            return Status.LOST;
        }
        else if (currMove.isBeingChecked(currBoard, currColor)){
            return Status.BEING_CHECK;
        }
        else{
            return Status.DEFAULT;
        }
    }



    /**
     * analyze current move and return game end condition
     * @param currBoard
     * @param currColor
     * @return
     */
    public Status analyzeMove(ChessBoard currBoard, Color currColor){
        this.currMove = new Move();
//        if (currMove.isBeingStalemate(currBoard, currColor)){
//            return Status.DRAW;
//        }
//        else if (currMove.isCheckmate(currBoard, currColor)){
//            System.out.print(currColor);
//            return Status.LOST;
//        }
//        //-------------------------------------------------------------
//        if (currMove.isBeingChecked(currBoard, currColor)){
//            System.out.println("You are being checked");
//        }
        //-------------------------------------------------------------
        origX = (Integer)(this.origBut.getClientProperty("xPos"));
        origY = (Integer)(this.origBut.getClientProperty("yPos"));
        destX = (Integer)(this.destBut.getClientProperty("xPos"));
        destY = (Integer)(this.destBut.getClientProperty("yPos"));
        if (currMove.isValidMove(currBoard.getPiece(origX, origY), currBoard,  destX, destY, true)){
            return Status.VALID_MOVE;
        }
        else {
            return Status.INVALID_DESTINATION;
        }
    }

    /**
     * select original piece, return the status(successful or fail)
     * @param origBut
     * @return
     */
    public Status selectOrig(PieceView origBut){
        if(origBut.getClientProperty("piece") == null){
            return Status.FAIL;
        }
        else if ( ((Piece)origBut.getClientProperty("piece")).pieceColor != currColor){
            return Status.FAIL;
        }
        else {
            this.origBut = origBut;
            origX = (Integer)(this.origBut.getClientProperty("xPos"));
            origY = (Integer)(this.origBut.getClientProperty("yPos"));
            return Status.SUCCESS;
        }
    }

    /**
     * select destination piece, return the status(successful or fail)
     * @param destBut
     * @return
     */
    public Status selectDest(PieceView destBut){
        if(destBut.getClientProperty("position") == null){
            return Status.FAIL;
        } else {
            this.destBut = destBut;
            return Status.SUCCESS;
        }
    }

    /**
     * @return all the available positions of current piece
     */
    public Vector<Integer> availableMoves(){
        Move currMove = new Move();
        Vector<Integer> availableMoves = currMove.availableMoves(currBoard.getPiece(origX,origY), this.currBoard, true);
        return availableMoves;
    }

    /**
     * @return the chessboard condition of next frame
     */
    public Frame nextFrame(){
        origX = (Integer)(this.origBut.getClientProperty("xPos"));
        origY = (Integer)(this.origBut.getClientProperty("yPos"));
        destX = (Integer)(this.destBut.getClientProperty("xPos"));
        destY = (Integer)(this.destBut.getClientProperty("yPos"));

        ChessBoard nextBoard = new ChessBoard(currBoard);
        nextBoard.getPiece(origX,origY).isFirst = false;
        nextBoard.moveChessPiece(this.origX, this.origY, destX, destY);
        Frame nextFrame = new Frame(nextBoard, this.currColor == Color.WHITE?Color.BLACK:Color.WHITE);
        return nextFrame;
    }


}
