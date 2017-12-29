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

}
