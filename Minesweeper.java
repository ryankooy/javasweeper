package Minesweeper;

import java.awt.BorderLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Minesweeper extends JFrame {
  private static final long serialVersionUID = 1L;
  private final int width, height;
  private final int difficulty;
  private final Cell[][] cells;
  private final Board board;
  private final JButton reset;
  private boolean finished;

  public void select(final int x, final int y) {
    if (cells[x][y].isFlagged())
      return;

    cells[x][y].reveal();
    resetMarks();
    refresh();

    if (cells[x][y].isMine()) {
      lose();
    } else if (won()) {
      win();
    }
  }

  private void setNumbers() {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int count = 0;

        if (i > 0 && j > 0 && cells[i - 1][j - 1].isMine())
          count++;

        if (j > 0 && cells[i][j - 1].isMine())
          count++;

        if (i < width - 1 && j > 0 && cells[i + 1][j - 1].isMine())
          count++;

        if (i > 0 && cells[i - 1][j].isMine())
          count++;

        if (i < width - 1 && cells[i + 1][j].isMine())
          count++;

        if (i > 0 && j < height - 1 && cells[i - 1][j + 1].isMine())
          count++;

        if (j < height - 1 && cells[i][j + 1].isMine())
          count++;

        if (i < width - 1 && j < height - 1 && cells[i + 1][j + 1].isMine())
          count++;

        cells[i][j].setNumber(count);

        if (cells[i][j].isMine())
          cells[i][j].setNumber(-1);

        if (cells[i][j].getNumber() == 0)
          cells[i][j].reveal();
      }
    }

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (i > 0 && j > 0 && cells[i - 1][j - 1].getNumber() == 0)
          cells[i][j].reveal();

        if (j > 0 && cells[i][j - 1].getNumber() == 0)
          cells[i][j].reveal();

        if (i < width - 1 && j > 0 && cells[i + 1][j - 1].getNumber() == 0)
          cells[i][j].reveal();

        if (i > 0 && cells[i - 1][j].getNumber() == 0)
          cells[i][j].reveal();

        if (i < width - 1 && cells[i + 1][j].getNumber() == 0)
          cells[i][j].reveal();

        if (i > 0 && j < height - 1 && cells[i - 1][j + 1].getNumber() == 0)
          cells[i][j].reveal();

        if (j < height - 1 && cells[i][j + 1].getNumber() == 0)
          cells[i][j].reveal();

        if (i < width - 1 && j < height - 1 && cells[i + 1][j + 1].getNumber() == 0)
          cells[i][j].reveal();
      }
    }
  }

  public void mark(final int x, final int y) {
    if (cells[x][y].isFlagged())
      cells[x][y].unflag();
    else if (cells[x][y].isCovered())
      cells[x][y].flag();

    resetMarks();
  }

  private void resetMarks() {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (!cells[i][j].isCovered())
          cells[i][j].unflag();
      }
    }
  }

  public void reset() {
    final Random random = new Random();
    finished = false;

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        final Cell c = new Cell();
        cells[i][j] = c;
        final int r = random.nextInt(100);

        if (r < difficulty) {
          cells[i][j].setMine();
        }
      }
    }

    setNumbers();
  }

  public int getx() {
    return width;
  }

  public int gety() {
    return height;
  }

  public Cell[][] getCells() {
    return cells;
  }

  public void refresh() {
    board.repaint();
  }

  private void win() {
    finished = true;

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        cells[i][j].reveal();

        if (!cells[i][j].isMine())
          cells[i][j].unflag();
      }
    }

    refresh();
    JOptionPane.showMessageDialog(null, "Congrats! You won!");
    reset();
  }

  private void lose() {
    finished = true;

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (!cells[i][j].isCovered())
          cells[i][j].unflag();

        cells[i][j].reveal();
      }
    }

    refresh();
    JOptionPane.showMessageDialog(null, "GAME OVER, MAN.");
    reset();
  }

  private boolean won() {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (cells[i][j].isCovered() && !cells[i][j].isMine())
          return false;
      }
    }

    return true;
  }

  public boolean isFinished() {
    return finished;
  }

public Minesweeper(final int x, final int y, final int d) {
    width = x;
    height = y;
    difficulty = d;
    cells = new Cell[width][height];

    reset();

    board = new Board(this);
    reset = new JButton("Reset");

    add(board, BorderLayout.CENTER);
    add(reset, BorderLayout.SOUTH);

    reset.addActionListener(new Actions(this));

    setTitle("MINESWEEPER");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    pack();
    setVisible(true);
  }
}