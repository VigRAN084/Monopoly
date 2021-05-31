import java.util.*;
public class Board {

    public static Board newBoard = new Board();

    private SquareType[][] squareTypes = new SquareType[11][11];

    Hashtable<Integer,int[]> positionMap = new Hashtable<Integer,int[]>();
    //Left Column
    SquareType SQ_0_0 = new FreeParking( 21);
    SquareType SQ_1_0 = new House("Wardell Arena", 17, 75, 123, 140, true, 20);
    SquareType SQ_2_0 = new House("Paper Center", 14, 68, 115, 130, true, 19);
    SquareType SQ_3_0 = new Chance(18);
    SquareType SQ_4_0 = new House("Shopping Center", 14, 68, 115, 130, true, 17);
    SquareType SQ_5_0 = new Railroad("Old Railroad", 20, 68, 140, true, 16);
    SquareType SQ_6_0 = new House("Manhattan Terrace", 12, 64, 109, 118, true, 15);
    SquareType SQ_7_0 = new House("Liverpool Boulevard",12, 64, 109, 118, true, 14);
    SquareType SQ_8_0 = new Utilities(135, "Java Electrics", 13, true);
    SquareType SQ_9_0 = new House("Carnegie Street", 12, 64, 109, 118, true, 12);
    SquareType SQ_10_0 = new Jail(11);


    //Top Row
    SquareType SQ_0_1 = new House("MSJ Property", 22, 90, 140, 155, true, 22);
    SquareType SQ_0_2 = new Chance(23);
    SquareType SQ_0_3 = new House("Fremont Avenue", 22, 90, 140, 155, true, 24);
    SquareType SQ_0_4 = new House("Calculus Mansion", 25, 102, 140, 155, true, 25);
    SquareType SQ_0_5 = new Railroad("Gomes Railroad", 29, 96,150, true, 26);
    SquareType SQ_0_6 = new House("Hopkin's Boulevard", 27, 106, 140, 170, true, 27);
    SquareType SQ_0_7 = new House("Green Town Home",27, 106, 140, 170, true, 28);
    SquareType SQ_0_8 = new Utilities(75, "H20 Aqueducts", 29,true);
    SquareType SQ_0_9 = new House("California Parkway",27, 106, 140, 170, true, 30);
    SquareType SQ_0_10 = new GoToJail(31);

    //Right Column
    SquareType SQ_1_10 = new House("Atlantic Complex", 35, 120, 180, 225, true, 32);
    SquareType SQ_2_10 = new House("Recursive Penthouse", 35, 120, 180, 225, true, 33);
    SquareType SQ_3_10 = new Chance(34);
    SquareType SQ_4_10 = new House("Mirage Estate", 50, 160, 200, 233, true, 35);
    SquareType SQ_5_10 = new Railroad("Expensive Railroad", 40, 150, 220, true, 36);
    SquareType SQ_6_10 = new House("Daytona Palace",50, 160, 200, 233, true, 37);
    SquareType SQ_7_10 = new House("Simmons Park", 58, 176, 222, 245, true, 38);
    SquareType SQ_8_10 = new Tax(39);
    SquareType SQ_9_10 = new House("Beachfront", 58, 176, 222, 245, true, 40);

    //Bottom Row
    SquareType SQ_10_10 = new Go();
    SquareType SQ_10_9 = new House("Greece Home", 6, 48, 95, 105, true, 2);
    SquareType SQ_10_8 = new Chance(3);
    SquareType SQ_10_7 = new House("Santana Road", 6, 48, 95, 105, true, 4);
    SquareType SQ_10_6 = new Tax(5);
    SquareType SQ_10_5 = new House("Nevada Street", 9, 53, 99, 109, true, 6);
    SquareType SQ_10_4 = new Railroad("South Railroad", 9, 53, 109, true, 7);
    SquareType SQ_10_3 = new House("Berkeley Avenue", 14, 59, 102, 111, true, 8);
    SquareType SQ_10_2 = new Chance(9);
    SquareType SQ_10_1 = new House("Retro Apartments", 14, 59, 102, 111, true, 10);


    public Board () {
        initializeSquares();
        initializePositionMap();
    }

    public void initializeSquares() {
        squareTypes[0][0] = SQ_0_0;
        squareTypes[0][1] = SQ_0_1;
        squareTypes[0][2] = SQ_0_2;
        squareTypes[0][3] = SQ_0_3;
        squareTypes[0][4] = SQ_0_4;
        squareTypes[0][5] = SQ_0_5;
        squareTypes[0][6] = SQ_0_6;
        squareTypes[0][7] = SQ_0_7;
        squareTypes[0][8] = SQ_0_8;
        squareTypes[0][9] = SQ_0_9;
        squareTypes[0][10] = SQ_0_10;
        squareTypes[1][0] = SQ_1_0;
        squareTypes[2][0] = SQ_2_0;
        squareTypes[3][0] = SQ_3_0;
        squareTypes[4][0] = SQ_4_0;
        squareTypes[5][0] = SQ_5_0;
        squareTypes[6][0] = SQ_6_0;
        squareTypes[7][0] = SQ_7_0;
        squareTypes[8][0] = SQ_8_0;
        squareTypes[9][0] = SQ_9_0;
        squareTypes[10][0] = SQ_10_0;
        squareTypes[10][1] = SQ_10_1;
        squareTypes[10][2] = SQ_10_2;
        squareTypes[10][3] = SQ_10_3;
        squareTypes[10][4] = SQ_10_4;
        squareTypes[10][5] = SQ_10_5;
        squareTypes[10][6] = SQ_10_6;
        squareTypes[10][7] = SQ_10_7;
        squareTypes[10][8] = SQ_10_8;
        squareTypes[10][9] = SQ_10_9;
        squareTypes[10][10] = SQ_10_10;
        squareTypes[1][10] = SQ_1_10;
        squareTypes[2][10] = SQ_2_10;
        squareTypes[3][10] = SQ_3_10;
        squareTypes[4][10] = SQ_4_10;
        squareTypes[5][10] = SQ_5_10;
        squareTypes[6][10] = SQ_6_10;
        squareTypes[7][10] = SQ_7_10;
        squareTypes[8][10] = SQ_8_10;
        squareTypes[9][10] = SQ_9_10;
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                squareTypes[i][j] = BlankSquare.blankSquare();
            }
        }
    }

    public void initializePositionMap() {
        positionMap.put(1,new int[]{10,10});
        positionMap.put(2,new int[]{10,9});
        positionMap.put(3,new int[]{10,8});
        positionMap.put(4,new int[]{10,7});
        positionMap.put(5,new int[]{10,6});
        positionMap.put(6,new int[]{10,5});
        positionMap.put(7,new int[]{10,4});
        positionMap.put(8,new int[]{10,3});
        positionMap.put(9,new int[]{10,2});
        positionMap.put(10,new int[]{10,1});
        positionMap.put(11,new int[]{10,0});

        positionMap.put(12,new int[]{9,0});
        positionMap.put(13,new int[]{8,0});
        positionMap.put(14,new int[]{7,0});
        positionMap.put(15,new int[]{6,0});
        positionMap.put(16,new int[]{5,0});
        positionMap.put(17,new int[]{4,0});
        positionMap.put(18,new int[]{3,0});
        positionMap.put(19,new int[]{2,0});
        positionMap.put(20,new int[]{1,0});

        positionMap.put(21,new int[]{0,0});
        positionMap.put(22,new int[]{0,1});
        positionMap.put(23,new int[]{0,2});
        positionMap.put(24,new int[]{0,3});
        positionMap.put(25,new int[]{0,4});
        positionMap.put(26,new int[]{0,5});
        positionMap.put(27,new int[]{0,6});
        positionMap.put(28,new int[]{0,7});
        positionMap.put(29,new int[]{0,8});
        positionMap.put(30,new int[]{0,9});
        positionMap.put(31,new int[]{0,10});

        positionMap.put(32,new int[]{1,10});
        positionMap.put(33,new int[]{2,10});
        positionMap.put(34,new int[]{3,10});
        positionMap.put(35,new int[]{4,10});
        positionMap.put(36,new int[]{5,10});
        positionMap.put(37,new int[]{6,10});
        positionMap.put(38,new int[]{7,10});
        positionMap.put(39,new int[]{8,10});
        positionMap.put(40,new int[]{9,10});
    }

    public SquareType getSquare(int x, int y) {
        if (x < 11 && y < 11 && x >= 0 && y >= 0){
            return squareTypes[x][y];
        }
        else return null;
    }

    public SquareType getSquare(int position) {
        int[] xycoords = positionMap.get(position);
        if (xycoords == null) return null;
        else{
            return getSquare(xycoords[0], xycoords[1]);
        }
    }

    public void printBoard() {
        for (SquareType[] arr: squareTypes){
            for (SquareType s: arr){
                System.out.print(s.printSquare() + " ");
            }
            System.out.println();
        }
    }

}
