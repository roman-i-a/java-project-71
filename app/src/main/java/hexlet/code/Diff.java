package hexlet.code;

public final class Diff implements Comparable<Diff> {
    // Имя свойства
    private final String property;
    // Значение свойства в первом файле. Если null, то значение было добавлено
    private final Object first;
    // Значение свойства во втором файле
    private final Object second;
    private final boolean hasFirst;
    private final boolean hasSecond;

    public Diff(final String property,
                final Object first,
                final Object second,
                final boolean hasFirst,
                final boolean hasSecond) {
        this.property = property;
        this.first = first;
        this.second = second;
        this.hasFirst = hasFirst;
        this.hasSecond = hasSecond;
    }

    public KeyStatus getKeyStatus() {
        KeyStatus status = KeyStatus.CHANGED;
        if (first == null) {
            status = hasFirst ? KeyStatus.CHANGED : KeyStatus.ADDED;
        } else if (second == null) {
            status = hasSecond ? KeyStatus.CHANGED : KeyStatus.DELETED;
        } else if (first.equals(second)) {
            return KeyStatus.UNCHANGED;
        }
        return status;
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
