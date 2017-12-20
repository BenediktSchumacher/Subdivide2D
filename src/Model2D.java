import com.sun.tools.internal.xjc.model.Model;

import java.util.ArrayList;

/**
 * Contains the current model and the history of subdivision
 * and subdivides the current model
 */
public class Model2D {

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
