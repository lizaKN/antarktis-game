package pgdp.oop;

import java.awt.Toolkit;
import java.io.File;

public class Fish extends Animal {
  static String filename = "fish.png";

  public String whatAnimalIs(){
    return "Fish";
  }

  private boolean hunterAdjacent(int x, int y){
    return antarktis[changeXY(x - 1)][y] != null && antarktis[changeXY(x - 1)][y].canEat(this) || antarktis[changeXY(x + 1)][y] != null
            && antarktis[changeXY(x + 1)][y].canEat(this) || antarktis[x][changeXY(y + 1)] != null
            && antarktis[x][changeXY(y + 1)].canEat(this) || antarktis[x][changeXY(y - 1)] != null
            && antarktis[x][changeXY(y - 1)].canEat(this);
  }
  public void move(){
    if (!this.alive) return;
    antarktis[x][y] = null;
    if(antarktis[changeXY(x)][changeXY(y - 1)] == null && !hunterAdjacent(x,changeXY(y - 1))) {
      this.y = changeXY(y - 1);
    }
    else if(antarktis[changeXY(x + 1)][changeXY(y)] == null && !hunterAdjacent(changeXY(x + 1),y )) {
      this.x = changeXY(x + 1);
    }
    else if(antarktis[changeXY(x)][changeXY(y + 1)] == null && !hunterAdjacent(x,changeXY(y + 1)))
    { this.y = changeXY(y + 1);
    }
    else if(antarktis[changeXY(x - 1)][changeXY(y)] == null && !hunterAdjacent(changeXY(x-1),y))
    { this.x = changeXY(x - 1);
    }
    antarktis[this.x][this.y] = this;
  }

  public Fish(int x, int y) {
    super(x, y);

    f = new File(filename);
    image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
  }

  public boolean canEat(Animal animal) {
    return animal.eatenBy(this);
  }

  @Override
  protected boolean eatenBy(Penguin penguin) {
    return true;
  }

  @Override
  protected boolean eatenBy(PlayerPenguin playerPenguin) {
    return true;
  }

  @Override
  protected boolean eatenBy(Whale whale) {
    return true;
  }

  @Override
  protected boolean eatenBy(LeopardSeal leopardSeal) {
    return true;
  }

  @Override
  protected boolean eatenBy(Fish fish) {
    return false;
  }
}
