package pgdp.oop;

import java.util.Objects;

public class PlayerPenguin extends Penguin {
    public PlayerPenguin(int x, int y) {
        super(x, y);
    }

    @Override
    public String whatAnimalIs() {
        return "PlPenguin";
    }

    public boolean canEat(Animal animal) {
        return animal != null && animal.eatenBy(this);
    }

    public boolean move(int newX, int newY){
        newX = changeXY(newX);
        newY = changeXY(newY);

        antarktis[x][y] = null;

        if (antarktis[newX][newY] == null || this.canEat(antarktis[newX][newY])) {
            this.x = newX;
            this.y = newY;
        }
        if (antarktis[newX][newY] != null && !antarktis[newX][newY].whatAnimalIs().equals("Fish")) {
            return true;
        }
        if (antarktis[newX][newY] == null) {
            antarktis[newX][newY] = this;
        }
        if (antarktis[newX][newY].whatAnimalIs().equals("Fish")) {
            antarktis[newX][newY].alive = false;
            antarktis[newX][newY] = this;
        }
        return false;
    }
}
