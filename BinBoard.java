public class BinBoard{
  private long board;
  private static final int[][] numToBoard = instNumToBoardWithRevs(); // int[number][rotation]
  private static int debug = 1;////////////////////////////////////////////////////////////////////
  private static String prevLine = "";//////////////////////////////////////

  public BinBoard(){
    board = (long)Math.pow(2,49);
  }
  public void reset(){
    board = (long)Math.pow(2,49);
  }
  public void reset(long saveState){
    board = saveState;
  }
  public long getBoardState(){
    return board;
  }
  public static int[][] instNumToBoard(){
    return instNumToBoard(4);
  }
  private static int[][] instNumToBoard(int rotRows){ // int[number][rotation(0-3, clockwise from vertical)]
    int[][] numRot = new int[10][rotRows];

    numRot[0][0] = n(0)+n(4)+n(5)+n(13)+n(14)+n(18);
    numRot[0][1] = n(0)+n(1)+n(4)+n(6)+n(9)+n(10);
    numRot[0][2] = numRot[0][0];
    numRot[0][3] = numRot[0][1];

    numRot[1][0] = n(4)+n(13);
    numRot[1][1] = n(0)+n(1);
    numRot[1][2] = n(5)+n(14);
    numRot[1][3] = n(9)+n(10);

    numRot[2][0] = n(0)+n(5)+n(9)+n(13)+n(18);
    numRot[2][1] = n(0)+n(4)+n(5)+n(6)+n(10);
    numRot[2][2] = numRot[2][0];
    numRot[2][3] = numRot[2][1];

    numRot[3][0] = numRot[2][0]+n(4)-n(5);
    numRot[3][1] = numRot[2][1]+n(1)-n(10);
    numRot[3][2] = numRot[2][2]-n(13)+n(14);
    numRot[3][3] = numRot[2][3]-n(0)+n(9);

    numRot[4][0] = numRot[1][0]+n(9)+n(14);
    numRot[4][1] = numRot[1][1]+n(5)+n(9);
    numRot[4][2] = numRot[1][2]+n(4)+n(9);
    numRot[4][3] = numRot[1][3]+n(1)+n(5);

    numRot[5][0] = numRot[3][0]-n(13)+n(14);
    numRot[5][1] = numRot[3][1]-n(0)+n(9);
    numRot[5][2] = numRot[5][0];
    numRot[5][3] = numRot[5][1];

    numRot[6][0] = numRot[5][0]+n(5);
    numRot[6][1] = numRot[5][1]+n(10);
    numRot[6][2] = numRot[5][2]+n(13);
    numRot[6][3] = numRot[5][3]+n(0);

    numRot[7][0] = numRot[1][0]+n(18);
    numRot[7][1] = numRot[1][1]+n(4);
    numRot[7][2] = numRot[1][2]+n(0);
    numRot[7][3] = numRot[1][3]+n(6);

    numRot[8][0] = numRot[0][0]+n(9);
    numRot[8][1] = numRot[0][1]+n(5);
    numRot[8][2] = numRot[8][0];
    numRot[8][3] = numRot[8][1];

    numRot[9][0] = numRot[6][2];
    numRot[9][1] = numRot[6][3];
    numRot[9][2] = numRot[6][0];
    numRot[9][3] = numRot[6][1];

    return numRot;
  }
  public static int[][] instNumToBoardWithRevs(){
    int[][] numRot = instNumToBoard(16);

    numRot[0][4] = numRot[0][0]+numRot[1][3]; // Zero's "reverses" are actually inclusions:
    numRot[0][5] = numRot[0][1]+numRot[1][2]; // 4-7 is zero with the 1 attached
    numRot[0][6] = numRot[0][0]*2+numRot[1][3];
    numRot[0][7] = numRot[0][1]*n(9)+numRot[1][2];
    numRot[0][8] = numRot[0][0]+numRot[7][3]; // 8-11 is zero with the regular 7 attached
    numRot[0][9] = numRot[0][1]+numRot[7][0]*2;
    numRot[0][10] = numRot[0][0]*2+numRot[7][1]*n(9);
    numRot[0][11] = numRot[0][1]*n(9)+numRot[7][2]; // See 12-16 below

    numRot[1][4] = numRot[1][2];
    numRot[1][5] = numRot[1][3];
    numRot[1][6] = numRot[1][0];
    numRot[1][7] = numRot[1][1];

    numRot[2][4] = numRot[5][0];
    numRot[2][5] = numRot[5][1];
    numRot[2][6] = numRot[5][2];
    numRot[2][7] = numRot[5][3];

    numRot[3][4] = numRot[3][2];
    numRot[3][5] = numRot[3][3];
    numRot[3][6] = numRot[3][0];
    numRot[3][7] = numRot[3][1];

    numRot[4][4] = numRot[4][0]-n(4)+n(5);
    numRot[4][5] = numRot[4][1]-n(1)+n(10);
    numRot[4][6] = numRot[4][2]+n(13)-n(14);
    numRot[4][7] = numRot[4][3]+n(0)-n(9);

    numRot[5][4] = numRot[2][0];
    numRot[5][5] = numRot[2][1];
    numRot[5][6] = numRot[2][2];
    numRot[5][7] = numRot[2][3];

    numRot[6][4] = numRot[8][0]-n(14);
    numRot[6][5] = numRot[8][1]-n(9);
    numRot[6][6] = numRot[8][2]-n(4);
    numRot[6][7] = numRot[8][3]-n(1);

    numRot[7][4] = numRot[1][4]+n(18);
    numRot[7][5] = numRot[1][5]+n(4);
    numRot[7][6] = numRot[1][6]+n(0);
    numRot[7][7] = numRot[1][7]+n(6);
    numRot[0][12] = numRot[0][0]+numRot[7][7]*n(9); // 12-15 is zero with the reversed 7 attached
    numRot[0][13] = numRot[0][1]+numRot[7][4];
    numRot[0][14] = numRot[0][0]*2+numRot[7][5];
    numRot[0][15] = numRot[0][1]*n(9)+numRot[7][6]*2;

    numRot[8][4] = numRot[8][0];
    numRot[8][5] = numRot[8][1];
    numRot[8][6] = numRot[8][2];
    numRot[8][7] = numRot[8][3];

    numRot[9][4] = numRot[6][6];
    numRot[9][5] = numRot[6][7];
    numRot[9][6] = numRot[6][4];
    numRot[9][7] = numRot[6][5];

    return numRot;
  }

  public boolean add(int num){
    return add(num, 0, 0, 0);
  }
  public boolean add(int num, int rot){
    return add(num, rot, 0, 0);
  }
  public boolean add(int num, int rot, int colsLeft, int rowsUp){ // colsLeft = x, rowsUp = y
    if (!canAdd(num, rot, colsLeft, rowsUp))
        return false;
    long scale = (long)Math.pow(2,colsLeft/2.0+rowsUp*9/2.0);
    board+=numToBoard[num][rot]*scale;
    return true;
  }
  public boolean canAdd(int num, int rot, int colsLeft, int rowsUp){
    if (rowsUp<0||colsLeft<0||rowsUp>8||colsLeft>6||rowsUp+colsLeft%2==1 // coordinate is out of bounds
        ||(board+numToBoard[num][rot])!=(board^numToBoard[num][rot])) // added number overlaps another number
      return false;
    if (rot%2==0){
      if (rowsUp>6) // colsLeft condition above is the other constraint bounding this box (else is vice versa)
        return false;}
    else
      if (colsLeft>4) // the colsLeft condition only works because of the rowsUp+colsLeft condition above
        return false;
    return true;
  }

  public void run(){ // Brute force algorithms like this may take a while
    int totalReps = 0;
    for (int zRow=0; zRow<9; zRow++) // This is just for a satisfying println
      for (int zCol=0; zCol<7; zCol++)
        for (int zOrient=0; zOrient<2; zOrient++)
          totalReps++;
    int reps = 0;
    long saveState = board;
    for (int zRow=0; zRow<9; zRow++) // zRow is not only a funny way to write 0, but also the row of the placed 0
      for (int zCol=0; zCol<7; zCol++)
        for (int zOrient=0; zOrient<2; zOrient++){
          pl("Currently trying rep "+Integer.toString(++reps)+" out of "+Integer.toString(totalReps)+".");
          board = saveState;
          if (add(0, zOrient, zCol, zRow))
            run(1);
        }
  }
  private void run(int currentNum){
    long saveState = board;
    if (currentNum<7)//////////////////////////////////
      debug++;//////////////////////////////////////////
    if (currentNum==5)////////////////////////////////
      debug=1;////////////////////////////////////////////////
    for (int nRow=0; nRow<9; nRow++)
      for (int nCol=0; nCol<7; nCol++)
        for (int nOrient=0; nOrient<(hasRotMirror(currentNum)?2:4); nOrient++){
          board = saveState;
          if (add(currentNum, nOrient, nCol, nRow)){
            if (currentNum>8&&board==1125899906842623L)//////////Math.pow(2,50)-1////////additional debug statement
              printBin();
            else if (currentNum<9){////////////////////previous debug statement
              if (currentNum==6&&!canAdd(0,0,0,4))////////////////////////////////////
                pl(Integer.toString(debug));////////////////////////////////////////
              run(currentNum+1);}}}
  }
  /*
  public void runWithRevs(){ // A more optimized brute force algorithm, which includes all physical possibilities
    int totalReps = 0;
    for (int zRow=0; zRow<9; zRow++) // This is just for a satisfying println
      for (int zCol=0; zCol<7; zCol++)
        for (int zOrient=0; zOrient<2; zOrient++)
          totalReps++;
    int reps = 0;
    for (int zRow=0; zRow<9; zRow++) // zRow is not only a funny way to write 0, but also the row of the placed 0
      for (int zCol=0; zCol<7; zCol++)
        for (int zOrient=0; zOrient<2; zOrient++){
          pl("Currently trying rep "+Integer.toString(++reps)+" out of "+Integer.toString(totalReps)+".");
          if (add(0, zOrient, zCol, zRow))
            run(1);
        }
  }
  private void runWithRevs(int currentNum){
    for (int nRow=0; nRow<9; nRow++)
      for (int nCol=0; nCol<7; nCol++)
        for (int nOrient=0; nOrient<(hasRotMirror(currentNum)?2:4); nOrient++)
          if (add(currentNum, nOrient, nCol, nRow)){
            if (currentNum>8)
              printBin();
            else
              run(currentNum+1);}
  }*/
  public static boolean hasRotMirror(int num){ // Mirrored on rotation
    return numToBoard[num][0]==numToBoard[num][2];
  }
  public static boolean hasRevMirror(int num){ // Mirrored on reverse (reflection)
    return numToBoard[num][0]==numToBoard[num][6];
  }

  public static int n(int num){ // a bit on the board at position num
    return (int)Math.pow(2,num);
  }
  public String b(int pos){ // get String bit on board at position, reasonable from 0 to 48.
    return Short.toString((short)(board/Math.pow(2,pos)%2));
  }
  public void printBin(){
    String sep = " ╳ "; // separator
    String spc = " ";
    String end = "┃";
    /*
    pl("┏━━━━━━━━━━━━━━━━━━━┓");
    pl("┃ ╳ 0 ╳ 0 ╳ 0 ╳ 0 ╳ ┃");
    pl("┃ 0 ╳ 0 ╳ 0 ╳ 0 ╳ 0 ┃");
    pl("┃ ╳ 0 ╳ 0 ╳ 0 ╳ 0 ╳ ┃");
    pl("┃ 0 ╳ 0 ╳ 0 ╳ 0 ╳ 0 ┃");
    pl("┃ ╳ 0 ╳ 0 ╳ 0 ╳ 0 ╳ ┃");
    pl("┃ 0 ╳ 0 ╳ 0 ╳ 0 ╳ 0 ┃");
    pl("┃ ╳ 0 ╳ 0 ╳ 0 ╳ 0 ╳ ┃");
    pl("┃ 0 ╳ 0 ╳ 0 ╳ 0 ╳ 0 ┃");
    pl("┃ ╳ 0 ╳ 0 ╳ 0 ╳ 0 ╳ ┃");
    pl("┃ 0 ╳ 0 ╳ 0 ╳ 0 ╳ 0 ┃");
    pl("┃ ╳ 0 ╳ 0 ╳ 0 ╳ 0 ╳ ┃");
    pl("┗━━━━━━━━━━━━━━━━━━━┛");
    */
    short p = 48; // pointer
    pl("┏━━━━━━━━━━━━━━━━━━━┓");
    for (int i=0; i<5; i++){
      pl(end+sep+b(p--)+sep+b(p--)+sep+b(p--)+sep+b(p--)+sep+end);
      pl(end+spc+b(p--)+sep+b(p--)+sep+b(p--)+sep+b(p--)+sep+b(p--)+spc+end);}
    pl(end+sep+b(p--)+sep+b(p--)+sep+b(p--)+sep+b(p--)+sep+end);
    pl("┗━━━━━━━━━━━━━━━━━━━┛");
  }
  public String toString(){
    return Long.toString(board);
  }
  public static void p(String s){ // print
    System.out.print(s);
  }
  public static void pl(){ // println
    System.out.println();
  }
  public static void pl(String s){ // println
    if (s.equals(prevLine))//////////////
      return;/////////////////////////
    prevLine = s;//////////////////////////
    System.out.println(s);
  }
}