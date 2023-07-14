package hexlet.code;

public class Diff implements Comparable<Diff> {
    // Имя свойста
    private final String property;
    // Значение свойства в первом файле. Если null, то значение было добавлено
    private final Object first;
    // Значение свойства во втором файле
    private final Object second;

    public Diff(String property, Object first, Object second) {
        this.property = property;
        this.first = first;
        this.second = second;
    }

    public KeyStatus getKeyStatus() {
        if (first == null) {
            return KeyStatus.ADDED;
        }
        if (second == null) {
            return KeyStatus.DELETED;
        }
        if (first.equals(second)) {
            return KeyStatus.UNCHANGED;
        }
        return KeyStatus.CHANGED;
    }

    @Override
    public int compareTo(Diff diff) {
        return this.property.compareTo((diff).getProperty());
    }

    public String getProperty() {
        return property;
    }

    public Object getFirst() {
        return first;
    }

    public Object getSecond() {
        return second;
    }
}
