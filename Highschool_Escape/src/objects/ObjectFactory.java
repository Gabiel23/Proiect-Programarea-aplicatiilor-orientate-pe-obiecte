package objects;

import java.io.IOException;

public abstract class ObjectFactory {

    //crează un obiect
    public Object setObject(int x, int y) throws IOException {

        Object obj;
        obj = createObject(x, y);
        return obj;
    }

    public abstract Objects createObject(int x, int y) throws IOException;
}
