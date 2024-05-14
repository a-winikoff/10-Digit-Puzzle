class Main {
  public static void main(String[] args) {
    /*Board b = new Board();
    b.printBin();
    b.placeNum(2,1,0,0);
    b.printBin();
    b.placeNum(0,0,0,6);
    b.printBin();

    for (int i=1; i<=50; i++)
      if (b.getBit(i)!=b.getBitMath(i))
        p(Integer.toString(i));*/
    BinBoard b = new BinBoard();
    //b.add(1,3,3,7);
    //b.printBin();
    for (int i=12; i<16; i++){
      b.add(0, i);
      b.printBin();
      b.reset();
    }
    //b.run();
  }
  public static void pl(String s){
    System.out.println(s);
  }
}