import processing.core.PApplet;

public class Sketch extends PApplet {
	
  int CELL_WIDTH = 20; 
  int CELL_HEIGHT = 20;
  int ROW_COUNT = 10;
  int COLUMN_COUNT = 10;
  int MARGIN = 5;

  int SCREEN_WIDTH = CELL_WIDTH * COLUMN_COUNT + MARGIN * (COLUMN_COUNT + 1);
  int SCREEN_HEIGHT = CELL_HEIGHT * ROW_COUNT + MARGIN * (COLUMN_COUNT + 1);

  int[][] intGrid = new int[10][10]; 
  int cellCount;
 
  public void settings() {
    // set window size according to width and height variables which are calculated using the cell and margin dimensions
    size(SCREEN_WIDTH, SCREEN_HEIGHT);
  }

  public void setup() {
    background(0);
  }

  public void draw() {
    // draw grid on the screen
    for (int c  = 0; c < COLUMN_COUNT; c++) {
      for (int r = 0; r < ROW_COUNT; r++) {
        // change colour to green if array value is equal to 1
        if (intGrid[r][c] == 1){
          fill(0, 255, 0);
        }
        else {
          fill(255);
        }
        rect(MARGIN + MARGIN * c + CELL_WIDTH * c, MARGIN + MARGIN * r + CELL_HEIGHT * r, CELL_WIDTH, CELL_HEIGHT);
      }
    }
  }

  public void mousePressed(){
    // variables for grid coordinates from mouse coordinates
    int intGridRow = (int)(mouseY / (MARGIN + CELL_HEIGHT));
    int intGridColumn = (int)(mouseX / (MARGIN + CELL_WIDTH));

    // change colour of cell and surrounding cells when mouse is pressed

    // change colour of cell that mouse is pressing
    if (intGrid[intGridRow][intGridColumn] == 0) {
      intGrid[intGridRow][intGridColumn] = 1;
    }
    else if (intGrid[intGridRow][intGridColumn] == 1){
      intGrid[intGridRow][intGridColumn] = 0;    
    }

    // change colour of cell below the cell that mouse is pressing
    if (intGridRow < ROW_COUNT - 1 && intGrid[intGridRow + 1][intGridColumn] == 0) {
      intGrid[intGridRow + 1][intGridColumn] = 1;
    }
    else if (intGridRow < ROW_COUNT - 1 && intGrid[intGridRow + 1][intGridColumn] == 1) {
      intGrid[intGridRow + 1][intGridColumn] = 0;
    }

    // change colour of cell above the cell that mouse is pressing 
    if (intGridRow > 0 && intGrid[intGridRow - 1][intGridColumn] == 0) {
      intGrid[intGridRow - 1][intGridColumn] = 1;
    }

    else if (intGridRow > 0 && intGrid[intGridRow - 1][intGridColumn] == 1) {
      intGrid[intGridRow - 1][intGridColumn] = 0;
    }

    // change colour of cell right to the cell that mouse is pressing
    if (intGridColumn < COLUMN_COUNT - 1 && intGrid[intGridRow][intGridColumn + 1] == 0) {
      intGrid[intGridRow][intGridColumn + 1] = 1;
    }
    else if (intGridColumn < COLUMN_COUNT - 1 && intGrid[intGridRow][intGridColumn + 1] == 1) {
      intGrid[intGridRow][intGridColumn + 1] = 0;
    }

    // change colour of cell left to the cell that mouse is pressing
    if (intGridColumn > 0 && intGrid[intGridRow][intGridColumn - 1] == 0) {
      intGrid[intGridRow][intGridColumn - 1] = 1;
    }
    else if (intGridColumn > 0 && intGrid[intGridRow][intGridColumn - 1] == 1) {
      intGrid[intGridRow][intGridColumn - 1] = 0;
    } 

    // set the number of selected cells to zero on each mouse press
    cellCount = 0;

    // calculate total selected cells
    for (int r = 0; r < ROW_COUNT; r++) {
      for (int c = 0; c < COLUMN_COUNT; c++) {
        if (intGrid[r][c] == 1){
          cellCount ++;
        }
      }
    }

    // print selected cells
    System.out.println("Total of " + cellCount + " are selected.");

    // loop through rows first, then columns to find selected cells in each row as well as the continous cells in a row
    for (int r = 0; r < ROW_COUNT; r++){

      // number of green cells in a row
      int cellRowCount = 0;

      // number of continuous cells in a row
      int intCont = 0;

      // largest number of continuous cells in a row
      int intMaxCont = 0;

      for (int c = 0; c < COLUMN_COUNT; c++) {
        // increments continuous and green cell counter
        if (intGrid[r][c] == 1) {
          intCont ++;
          cellRowCount ++;

          // replaces max with highest current continuous 
          if (intCont > intMaxCont) {
            intMaxCont = intCont;
          }
        }
        
        // reset the continuous counter if the cell is white
        else if (intGrid[r][c] == 0){
          intCont = 0;
        }
      }

      // print number of continous cells only if it is more tan 2
      if (intMaxCont > 2) {
        System.out.println("There are " + intMaxCont + " continous blocks selected on row " + r + ".");
      }

      // print selected cells in each row
      System.out.println("Row " + r + " has " + cellRowCount + " cells selected.");
    }

    // loop through columns first, then rows to find selected cells in each column
    for (int c = 0; c < COLUMN_COUNT; c++) {
      int cellColCount = 0;
      for (int r = 0; r < ROW_COUNT; r++) {
        if (intGrid[r][c] == 1) {
          cellColCount ++;
        }
      }
      // print number of cells in each row
      System.out.println("Column " + c + " has " + cellColCount + " cells selected.");
    }

    /*
    print "click" and mouse + grid coordinates

    System.out.println("click");
    System.out.print("mouse coordinates: (" + mouseX + ", " + mouseY + "); ");
    System.out.println("grid coordinates: (row:" + intGridRow + ", column:" + intGridColumn + ")");
    */
  }
}
