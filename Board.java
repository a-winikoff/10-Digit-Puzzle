import java.util.*;

public class Board{
  private String[][] board;
  private long bin; // bin bit(1) is board[0][1]
  private static final short boardBits = 50; // number of bits that the board can use

  // Init
  public Board(){ //Board is a 9X11 node place, only half are used (boardBits total)
    bin=(long)Math.pow(2, boardBits); // makes the first bit a "spare" (boardBits trailing "0"s)
    board = new String[11][9];
    for (int row=0; row<board.length; row++)
      for (int col=0; col<board[row].length; col++)
        board[row][col] = ((row+col)%2==0)?new String("·"):new String("-");
  }

  // basic math and operations
  private static String getBit(String bits, int ind){
    return bits.substring(ind,ind+1);
  }
  private static long v(int ind){ // first real value at ind==1 and (0,1), ind==0 is the board's leading bit
    return (long)Math.pow(2, (boardBits-ind));
  }
  private static long f(){ // gets the leading bit of the board
    return v(0);
  }

  // bin manip methods
  public boolean getBit(int row, int col){ // return a bit as a boolean
    String bits = Long.toBinaryString(bin);
    int ind = row/2*9 + row%2*4 + col/2 + 1;
    return bits.substring(ind, ind+1).equals("1");
  }
  public boolean getBit(int ind){ // return a bit as a boolean
    String bits = Long.toBinaryString(bin);
    return bits.substring(ind, ind+1).equals("1");
  }
  public boolean getBitMath(int ind){ // return a bit as a boolean
    return bin%Math.pow(2,boardBits-ind)==1;
  }
  public boolean canPlace(int num, int rot, int left, int down){ // incomplete
    long numComp = getNum(num, rot, left, down)-f(); // just make an add and an xor operation
    //for (int ind=1; ind<Long.toBinaryString(numComp).length(); ind++)
    //  if (Long.toBinaryString(numComp).charAt(ind)==49&&getBit(ind)) // ASCII(49) == '1'
    //    return false;
    return true;
  }
  public void placeNum(int num, int rot, int left, int down){ // (0,0) is the top left corner
    if (true||canPlace(num, rot, left, down))//////////////////////////////
      bin+=getNum(num, rot, left, down)-f();
  }

  // Board print methods
  public void print(){
    for (String[] row:board){
      for (String node:row)
        System.out.print(node+" ");
      System.out.println("\b");
    }
    System.out.println();
  }
  public void printBin(){
    System.out.println(Long.toBinaryString(bin));//////////////////////////////////////
    String bs="b ";
    String sbs=" b ";
    String sb=" b";
    String bits = Long.toBinaryString(bin);
    int ind=1;
    for (int line=0; line<board.length; line++)
      if (line%2==0)
        System.out.println(bs+getBit(bits,ind++)+sbs+ getBit(bits,ind++)+sbs+ getBit(bits,ind++)+sbs+ getBit(bits,ind++)+sb);
      else
        System.out.println(getBit(bits,ind++)+sbs+ getBit(bits,ind++)+sbs+ getBit(bits,ind++)+sbs+ getBit(bits,ind++)+sbs+ getBit(bits,ind++));
    System.out.println();
  }
  public void printBoth(){
    String bs="b ";
    String sbs=" b ";
    String sb=" b";
    String bits = Long.toBinaryString(bin);
    int ind=1;
    for (int line=0; line<board.length; line++)
      if (line%2==0) {
        for (int node=0; node<board[line].length; node++)
          System.out.print(board[line][node]+" ");
        System.out.print("  ");
        System.out.println(bs+getBit(bits,ind++)+sbs+ getBit(bits,ind++)+sbs+ getBit(bits,ind++)+sbs+ getBit(bits,ind++)+sb);
      }
      else {
        for (int node=0; node<board[line].length; node++)
          System.out.print(board[line][node]+" ");
        System.out.print("  ");
        System.out.println(getBit(bits,ind++)+sbs+ getBit(bits,ind++)+sbs+ getBit(bits,ind++)+sbs+ getBit(bits,ind++)+sbs+ getBit(bits,ind++));
      }
    System.out.println();
  }

  // getNum methods
  private long getNum(int num, int rot, int left, int down){///////////////
    long numCode = getNum(num, rot);
    int shift = left + down*4 + (down+1)/2;
    System.out.println(Long.toBinaryString(numCode)+"; "+shift);////////////////////////////
    numCode = (long)((numCode-f())/Math.pow(2, shift)+f());
    System.out.println(Long.toBinaryString(numCode));////////////////////////////
    return numCode;
  }
  private long getNum(int num, int rot){ // returns a num in the top left corner, 0<=rot<=3 clockwise by 90 degrees for each
    if (num==0){
      if (rot%2==0)
        return f()+ v(1)+v(5)+v(6)+v(14)+v(15)+v(19);
      return f()+ v(1)+v(2)+v(5)+v(7)+v(10)+v(11);
    }
    if (num==1){
      if (rot==0)
        return f()+ v(6)+v(15);
      if (rot==1)
        return f()+ v(1)+v(2);
      if (rot==2)
        return f()+ v(5)+v(14);
      return f()+ v(10)+v(11);
    }
    if (num==2){
      if (rot%2==0)
        return f()+ v(1)+v(6)+v(10)+v(14)+v(19);
      return f()+ v(1)+v(5)+v(6)+v(7)+v(11);
    }
    return 0;
  }
}