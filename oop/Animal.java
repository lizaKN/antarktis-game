package pgdp.oop;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.File;

public abstract class Animal {
  public boolean alive = true;
  protected int x, y;
  static String filename;
  protected File f;
  protected Image image;

  protected static Animal[][] antarktis;

  public Animal(int x, int y) {
    this.x = x;
    this.y = y;

  }
  public abstract String whatAnimalIs();

  public int changeXY(int x){
    if (x > 40)
      return 0;
    else if(x < 0)
      return 40;
    else return x;
  }

  private boolean hunterAdjacent(int x, int y){
    return antarktis[changeXY(x - 1)][y] != null && antarktis[changeXY(x - 1)][y].canEat(this)
        || antarktis[changeXY(x + 1)][y] != null && antarktis[changeXY(x + 1)][y].canEat(this)
        || antarktis[x][changeXY(y + 1)] != null && antarktis[x][changeXY(y + 1)].canEat(this)
        || antarktis[x][changeXY(y - 1)] != null && antarktis[x][changeXY(y - 1)].canEat(this);
  }

  public  void move() {
    if (!this.alive) return;
    antarktis[x][y] = null;

    if(antarktis[changeXY(x-1)][y] != null && this.canEat(antarktis[changeXY(x - 1)][changeXY(y)]) && !hunterAdjacent(changeXY(x-1),y))
    { antarktis[changeXY(x - 1)][y].alive = false;
      this.x = changeXY(x - 1);
     }
    else if(antarktis[x][changeXY(y - 1)] != null && this.canEat(antarktis[changeXY(x)][changeXY(y - 1)]) && !hunterAdjacent(x,changeXY(y - 1)))
    { antarktis[x][changeXY(y - 1)].alive = false;
      this.y = changeXY(y - 1);
      }
    else if(antarktis[changeXY(x + 1)][y] != null && this.canEat(antarktis[changeXY(x + 1)][changeXY(y)]) && !hunterAdjacent(changeXY(x+1),y)) {
      antarktis[changeXY(x + 1)][y].alive = false;
      this.x = changeXY(x + 1);
    }
    else if(antarktis[x][changeXY(y + 1)] != null && this.canEat(antarktis[changeXY(x)][changeXY(y + 1)]) &&!hunterAdjacent(x,changeXY(y + 1)))
    { antarktis[x][changeXY(y + 1)].alive = false;
      this.y = changeXY(y+1);
    }
    else if(antarktis[changeXY(x - 1)][changeXY(y)] == null && !hunterAdjacent(changeXY(x-1),y))
    { this.x = changeXY(x - 1);
    }
    else if(antarktis[changeXY(x)][changeXY(y - 1)] == null && !hunterAdjacent(x,changeXY(y - 1))) {
      this.y = changeXY(y - 1);
    }
    else if(antarktis[changeXY(x + 1)][changeXY(y)] == null && !hunterAdjacent(changeXY(x + 1),y )) {
      this.x = changeXY(x + 1);
    }
    else if(antarktis[changeXY(x)][changeXY(y + 1)] == null && !hunterAdjacent(x,changeXY(y + 1)))
    {
      this.y = changeXY(y + 1);
    }
    antarktis[this.x][this.y] = this;
  }


  public abstract boolean canEat(Animal animal);

  protected abstract boolean eatenBy(Penguin penguin);
  protected abstract boolean eatenBy(PlayerPenguin playerPenguin);
  protected abstract boolean eatenBy(Whale whale);
  protected abstract boolean eatenBy(LeopardSeal leopardSeal);
  protected abstract boolean eatenBy(Fish fish);

  public static void setAntarktis(Animal[][] antarktis) {
    Animal.antarktis = antarktis;
  }
  // Graphics Stuff - You don't have to do anything here

  private void paintSymbol(Graphics g, Color c, int height, int width) {
    GradientPaint gradient = new GradientPaint(15, 0, c, width, 0, Color.LIGHT_GRAY);
    ((Graphics2D) g).setPaint(gradient);
    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.fillOval((int) (width * 0.3), (int) (height * 0.3), (int) (width * 0.5),
        (int) (height * 0.5));
  }

  public void draw(Graphics g, int height, int width) {
    if (image == null) {
      paintSymbol(g, Color.YELLOW, height, width);
      return;
    }
    ((Graphics2D) g).drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(null),
        image.getHeight(null), null);
  }
}
