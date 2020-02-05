package Minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Actions implements ActionListener, MouseListener {
  private final Minesweeper mine;
  int mineCount, flagCount;

  public void mouseEntered(final MouseEvent e) {

  }

  public void mouseExited(final MouseEvent e) {

  }

  public void mousePressed(final MouseEvent e) {

  }

  public void mouseReleased(final MouseEvent e) {

  }

  public Actions(final Minesweeper m) {
    mine = m;
  }

  public void actionPerformed(final ActionEvent e) {
    mine.reset();
    mine.refresh();
  }

  public void mouseClicked(final MouseEvent e) {
    if (e.getButton() == 1) {
      final int x = e.getX() / 20;
      final int y = e.getY() / 20;

      mine.select(x, y);
    }

    if (e.getButton() == 2) {
      mineCount = 0;
      flagCount = 0;
    }

    if (e.getButton() == 3) {
      final int x = e.getX() / 20;
      final int y = e.getY() / 20;

      mine.mark(x, y);
    }

    mine.refresh();
  }
}