package concurrent._2;

/**
 * 描述:
 * 使用final关键字
 *
 * @author Noah
 * @create 2019-09-24 09:50
 */
class Fo {
    _2_final t1;
}

public class _2_final {

    final int x;
    int y;

    Fo obj;

    public _2_final() {
        x = 100;
        y = 10;
        //bad
        obj = new Fo();
        obj.t1 = this;
    }

    public static void main(String[] args) {
        _2_final app = new _2_final();
        System.out.println("obj,x=" + app.obj.t1.x + ",y=" + app.obj.t1.y);
        System.out.println("this,x=" + app.x + ",y=" + app.y);
    }
}
