public class Algorithms {

    public static void cornerCutting() {
        Point[] newInstance = new Point[Model2D.getInstance().getCurrentInstance().length * 2];
        for(int i = 0; i < Model2D.getInstance().getCurrentInstance().length; i++) {
            newInstance[i * 2] = Model2D.getInstance().getCurrentInstance()[i].clone();
            if(i == Model2D.getInstance().getCurrentInstance().length - 1) {
                double newX = (Model2D.getInstance().getCurrentInstance()[i].getX() + Model2D.getInstance().getCurrentInstance()[0].getX()) / 2;
                double newY = (Model2D.getInstance().getCurrentInstance()[i].getY() + Model2D.getInstance().getCurrentInstance()[0].getY()) / 2;
                newInstance[i * 2 + 1] = new Point(newX, newY);
            } else {
                double newX = (Model2D.getInstance().getCurrentInstance()[i].getX() + Model2D.getInstance().getCurrentInstance()[i + 1].getX()) / 2;
                double newY = (Model2D.getInstance().getCurrentInstance()[i].getY() + Model2D.getInstance().getCurrentInstance()[i + 1].getY()) / 2;
                newInstance[i * 2 + 1] = new Point(newX, newY);
            }
        }
        double tmpX = newInstance[0].getX();
        double tmpY = newInstance[0].getY();
        for(int i = 0; i < newInstance.length; i++) {
            double newX, newY;
            if(i == newInstance.length - 1) {
                newX = (newInstance[i].getX() + tmpX) / 2;
                newY = (newInstance[i].getY() + tmpY) / 2;
            } else {
                newX = (newInstance[i].getX() + newInstance[i + 1].getX()) / 2;
                newY = (newInstance[i].getY() + newInstance[i + 1].getY()) / 2;
            }
            newInstance[i].setX(newX);
            newInstance[i].setY(newY);
        }
        Model2D.getInstance().addPoints(newInstance, "Corner-Cutting");
        Model2D.getInstance().updateView();
    }

    public static void fourPoint(double w) {
        Point[] newInstance = new Point[Model2D.getInstance().getCurrentInstance().length * 2];
        for(int i = 0; i < Model2D.getInstance().getCurrentInstance().length; i++) {
            newInstance[2 * i] = Model2D.getInstance().getCurrentInstance()[i];
            Point mid = new Point((Model2D.getInstance().getCurrentInstance()[i].getX()+ Model2D.getInstance().getCurrentInstance()[i < Model2D.getInstance().getCurrentInstance().length - 1 ? (i + 1) : 0].getX()) / 2,
                    (Model2D.getInstance().getCurrentInstance()[i].getY()+ Model2D.getInstance().getCurrentInstance()[i < Model2D.getInstance().getCurrentInstance().length - 1 ? (i + 1) : 0].getY()) / 2);

            int iTmp = i - 1;
            if(iTmp < 0)
                iTmp = Model2D.getInstance().getCurrentInstance().length - 1;

            Point e_one = subPoint(Model2D.getInstance().getCurrentInstance()[i], Model2D.getInstance().getCurrentInstance()[iTmp]);
            int iPlusOne = i + 1;
            int iPlusTwo = i + 2;
            if(iPlusOne == Model2D.getInstance().getCurrentInstance().length) {
                iPlusOne = 0;
                iPlusTwo = 1;
            } else if(iPlusTwo == Model2D.getInstance().getCurrentInstance().length)
                iPlusTwo = 0;
            Point e_two = subPoint(Model2D.getInstance().getCurrentInstance()[iPlusTwo], Model2D.getInstance().getCurrentInstance()[iPlusOne]);

            Point p = multiply(w, subPoint(e_one, e_two));
            newInstance[(2 * i) + 1] = add(mid, p);
        }
        Model2D.getInstance().addPoints(newInstance, "4-Point");
        Model2D.getInstance().updateView();
    }

    private static Point add(Point a, Point b) {
        return new Point(a.getX() + b.getX(), a.getY() + b.getY());
    }

    private static Point multiply(double w, Point a) {
        return new Point(0.0625 * a.getX(), 0.0625 * a.getY());
    }

    private static Point subPoint(Point a, Point b) {
        return new Point(a.getX() - b.getX(), a.getY() - b.getY());
    }

}
