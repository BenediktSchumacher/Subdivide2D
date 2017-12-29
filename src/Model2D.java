import java.util.ArrayList;
import java.util.Observable;

/**
 * Contains the current model and the history of subdivision
 * and subdivides the current model
 */
public class Model2D extends Observable {

    private ArrayList<Point[]> models;
    private ArrayList<String> descriptions;
    private static Model2D model2D;
    private int current;

    private Model2D() {
        models = new ArrayList();
        descriptions = new ArrayList();
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

    public ArrayList<String> getDescriptions() { return this.descriptions; }

    public void addPoints(Point[] points, String descr) {
        models.add(points);
        descriptions.add(descr);
        current = models.size() - 1;
    }

    public void resetToInsance(int index) {
        current = index;
        while (models.size() > index + 1) {
            models.remove(index + 1);
            descriptions.remove(index + 1);
        }
        assert(models.size() == index + 1);
        setChanged();
        notifyObservers();
    }

    public void updateView() {
        setChanged();
        notifyObservers();
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
