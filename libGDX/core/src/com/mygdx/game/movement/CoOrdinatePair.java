package com.mygdx.game.movement;

public class CoOrdinatePair {

  private final int x;

  private final int y;

  public CoOrdinatePair(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CoOrdinatePair that = (CoOrdinatePair) o;
    return x == that.x && y == that.y;
  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }
}
