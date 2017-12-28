import com.sun.tools.internal.xjc.model.Model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Contains the current model and the history of subdivision
 * and subdivides the current model
 */
public class Model2D extends Observable {

    private ArrayList<Point[]> models;
    private static Model2D model2D;
    private int current;

    private Model2D() {
        models = new ArrayList();
        current = -1;
    }

    public static Model2D getInstance() {
        if (model2D == null)
            model2D = new Model2D();
        return model2D;
    }

    public Point[] getCurrentInstance() {
        if (current < 0)
            throw new IndexOutOfBoundsException("model is empty");
        if (current >= models.size())
            throw new IndexOutOfBoundsException("current index out of bounds");
        return models.get(current);
    }

    public void addPoints(Point[] points) {
        models.add(points);
        current = models.size() - 1;
    }

    public void cornerCutting() {
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
        addPoints(newInstance);
        setChanged();
        notifyObservers();
        // System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Size: " + models.size() + "\n");
        for (Point[] points : models) {
            for (Point point : points)
                stringBuilder.append(point.toString() + "; ");
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
