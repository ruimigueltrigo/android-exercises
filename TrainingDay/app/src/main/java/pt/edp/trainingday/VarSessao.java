package pt.edp.trainingday;

/**
 * Created by e348900 on 16-02-2017.
 */

public abstract class VarSessao {

    public static final int MY_PERMISSIONS_REQUEST_READ_AND_WRITE_EXTERNAL_STORAGE = 123;
    private static int x = 0;

    public static int getX() {
        return x;
    }

    public static void setX(int x) {
        VarSessao.x = x;
    }
}
