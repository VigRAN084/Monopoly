import java.util.*;
public class Board {

    private Square[][] squares = new Square[11][11];

    //Left Column
    Square SQ_0_0 = new Square(Square.TYPE_FREEPARKING, "Jail", 0, 0, 0, 0, false, 21);
    Square SQ_1_0 = new Square (Square.TYPE_PROPERTY, "Wardell Arena", 17, 75, 123, 140, true, 20);
    Square SQ_2_0 = new Square(Square.TYPE_PROPERTY, "Paper Center", 14, 68, 115, 130, true, 19);
    Square SQ_3_0 = new Square(Square.TYPE_CardDraw, "Chance", 0,0,0,0, false, 18);
    Square SQ_4_0 = new Square(Square.TYPE_PROPERTY, "Shopping Center", 14, 68, 115, 130, true, 17);
    Square SQ_5_0 = new Square(Square.TYPE_PROPERTY, "Old Railroad", 20, 68, 0, 140, true, 16);
    Square SQ_6_0 = new Square(Square.TYPE_PROPERTY, "Manhattan Terrace", 12, 64, 109, 118, true, 15);
    Square SQ_7_0 = new Square(Square.TYPE_PROPERTY, "Liverpool Boulevard",12, 64, 109, 118, true, 14);
    Square SQ_8_0 = new Square(Square.TYPE_UTILITIES, "Java Electrics", 0, 0, 0, 135, true, 13);
    Square SQ_9_0 = new Square(Square.TYPE_PROPERTY, "Carnegie Street", 12, 64, 109, 118, true, 12);
    Square SQ_10_0 = new Square(Square.TYPE_JAIL, "Jail", 0,0,0,0, false,11);


    //Top Row
    Square SQ_0_1 = new Square(Square.TYPE_PROPERTY, "MSJ House", 22, 90, 140, 155, true, 22);
    Square SQ_0_2 = new Square(Square.TYPE_CardDraw, "Chance", 0, 0, 0, 0, false, 23);
    Square SQ_0_3 = new Square(Square.TYPE_PROPERTY, "Fremont Avenue", 22, 90, 140, 155, true, 24);
    Square SQ_0_4 = new Square(Square.TYPE_PROPERTY, "Calculus Mansion", 25, 102, 140, 155, true, 25);
    Square SQ_0_5 = new Square(Square.TYPE_PROPERTY, "Gomes Railroad", 29, 96, 0,150, false, 26);
    Square SQ_0_6 = new Square(Square.TYPE_PROPERTY, "Hopkin's Boulevard", 27, 106, 140, 170, true, 27);
    Square SQ_0_7 = new Square(Square.TYPE_PROPERTY, "Green Town Home",27, 106, 140, 170, true, 28);
    Square SQ_0_8 = new Square(Square.TYPE_UTILITIES, "H20 Aqueducts", 0,0,0,75, true, 29);
    Square SQ_0_9 = new Square(Square.TYPE_PROPERTY, "California Parkway",27, 106, 140, 170, true, 28);
    Square SQ_0_10 = new Square(Square.TYPE_GOTOJAIL, "Go To Jail", 0,0,0,0,false, 31);

    //Right Column
    Square SQ_1_10 = new Square(Square.TYPE_PROPERTY, "Atlantic Complex", 35, 120, 180, 225, true, 32);
    Square SQ_2_10 = new Square(Square.TYPE_PROPERTY, "Recursive Penthouse", 35, 120, 180, 225, true, 33);
    Square SQ_3_10 = new Square(Square.TYPE_CardDraw, "Chance", 0, 0, 0, 0, false, 34);
    Square SQ_4_10 = new Square(Square.TYPE_PROPERTY, "Mirage Estate", 50, 160, 200, 233, true, 35);
    Square SQ_5_10 = new Square(Square.TYPE_PROPERTY, "Expensive Railroad", 40, 150, 0, 220, true, 36);
    Square SQ_6_10 = new Square(Square.TYPE_PROPERTY, "Daytona Palace",50, 160, 200, 233, true, 37);
    Square SQ_7_10 = new Square(Square.TYPE_PROPERTY, "Simmons Park", 58, 176, 222, 245, true, 38);
    Square SQ_8_10 = new Square(Square.TYPE_TAX, "Tax", 50,0,0,0, false, 39);
    Square SQ_9_10 = new Square(Square.TYPE_PROPERTY, "Beachfront", 58, 176, 222, 245, true, 40);

    //Bottom Row
    Square SQ_10_10 = new Square(Square.TYPE_GO, "Go", 0, 0, 0, 0, false, 1);
    Square SQ_10_9 = new Square(Square.TYPE_PROPERTY, "Greece Home", 6, 48, 95, 105, true, 2);
    Square SQ_10_8 = new Square(Square.TYPE_CardDraw, "Chance", 0,0,0,0, false, 3);
    Square SQ_10_7 = new Square(Square.TYPE_PROPERTY, "Santana Road", 6, 48, 95, 105, true, 2);
    Square SQ_10_6 = new Square(Square.TYPE_TAX, "Tax",50,0,0,0, false, 5);
    Square SQ_10_5 = new Square(Square.TYPE_PROPERTY, "Nevada Street", 9, 53, 99, 109, true, 6);
    Square SQ_10_4 = new Square(Square.TYPE_PROPERTY, "South Railroad", 9, 53, 0, 109, true, 7);
    Square SQ_10_3 = new Square(Square.TYPE_PROPERTY, "Berkeley Avenue", 14, 59, 102, 111, true, 8);
    Square SQ_10_2 = new Square(Square.TYPE_CardDraw, "Chance", 0,0,0,0,false, 9);
    Square SQ_10_1 = new Square(Square.TYPE_PROPERTY, "Retro Apartments", 14, 59, 102, 111, true, 10);

    public void drawBoard (){
        squares[0][0] = SQ_0_0;
        squares[0][1] = SQ_0_1;
        squares[0][2] = SQ_0_2;
        squares[0][3] = SQ_0_3;
        squares[0][4] = SQ_0_4;
        squares[0][5] = SQ_0_5;
        squares[0][6] = SQ_0_6;
        squares[0][7] = SQ_0_7;
        squares[0][8] = SQ_0_8;
        squares[0][9] = SQ_0_9;
        squares[0][10] = SQ_0_10;
        squares[1][0] = SQ_1_0;
        squares[2][0] = SQ_2_0;
        squares[3][0] = SQ_3_10;
        squares[4][0] = SQ_4_0;
        squares[5][0] = SQ_5_0;
        squares[6][0] = SQ_6_0;
        squares[7][0] = SQ_7_0;
        squares[8][0] = SQ_8_0;
        squares[9][0] = SQ_9_0;
        squares[10][0] = SQ_10_0;
        squares[10][1] = SQ_10_1;
        squares[10][2] = SQ_10_2;
        squares[10][3] = SQ_10_3;
        squares[10][4] = SQ_10_4;
        squares[10][5] = SQ_10_5;
        squares[10][6] = SQ_10_6;
        squares[10][7] = SQ_10_7;
        squares[10][8] = SQ_10_8;
        squares[10][9] = SQ_10_9;
        squares[10][10] = SQ_10_10;
        squares[1][10] = SQ_1_10;
        squares[2][10] = SQ_2_10;
        squares[3][10] = SQ_3_10;
        squares[4][10] = SQ_4_10;
        squares[5][10] = SQ_5_10;
        squares[6][10] = SQ_6_10;
        squares[7][10] = SQ_7_10;
        squares[8][10] = SQ_8_10;
        squares[9][10] = SQ_9_10;
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                squares[i][j] = Square.blankSquare();
            }
        }
    }


}
