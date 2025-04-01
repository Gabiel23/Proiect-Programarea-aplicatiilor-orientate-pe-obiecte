package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class CosFactory extends ObjectFactory{
    @Override
    public Objects createObject(int x, int y) throws IOException {
        Cos cos = new Cos(ImageIO.read(java.util.Objects.requireNonNull(getClass().getResourceAsStream("axe.png"))));
        cos.worldX = x;
        cos.worldY = y;
        return cos;
    }
}
