package hexlet.code;

public class Diff implements Comparable<Diff> {
    // Имя свойста
    private final String property;
    // Значение свойства в первом файле. Если null, то значение было добавлено
    private final Object beforeValue;
    // Значение свойства во втором файле
    private final Object afterValue;

    public Diff(String property, Object beforeValue, Object afterValue) {
        this.property = property;
        this.beforeValue = beforeValue;
        this.afterValue = afterValue;
    }

    public KeyStatus getKeyStatus() {
        if (beforeValue == null) {
            return KeyStatus.ADDED;
        } else if (afterValue == null) {
            return KeyStatus.DELETED;
        } else if (beforeValue.equals(afterValue)) {
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

    public Object getBeforeValue() {
        return beforeValue;
    }

    public Object getAfterValue() {
        return afterValue;
    }
}
