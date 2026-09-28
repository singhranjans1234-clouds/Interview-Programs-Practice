import java.io.*;

/** Q5: Thread-safe singleton (double-checked locking) hardened against serialization, cloning, reflection. */
public class Q05_ThreadSafeSingleton {

    static final class Singleton implements Serializable, Cloneable {
        private static final long serialVersionUID = 1L;
        private static volatile Singleton instance; // volatile prevents reordering of construction / publication

        private Singleton() {
            if (instance != null) // blocks reflection attack
                throw new IllegalStateException("Use getInstance()");
        }

        static Singleton getInstance() {
            Singleton local = instance;              // one volatile read on the fast path
            if (local == null) {
                synchronized (Singleton.class) {
                    local = instance;
                    if (local == null) instance = local = new Singleton();
                }
            }
            return local;
        }

        // Deserialization would otherwise create a new object.
        private Object readResolve() { return getInstance(); }

        @Override protected Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException("Singleton cannot be cloned");
        }
    }

    public static void main(String[] args) throws Exception {
        Singleton a = Singleton.getInstance();
        System.out.println("Same instance: " + (a == Singleton.getInstance()));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) { oos.writeObject(a); }
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()))) {
            System.out.println("Same after deserialization: " + (a == ois.readObject()));
        }
        try { a.clone(); } catch (CloneNotSupportedException e) { System.out.println("Clone blocked: " + e.getMessage()); }

        var ctor = Singleton.class.getDeclaredConstructor();
        ctor.setAccessible(true);
        try { ctor.newInstance(); } catch (Exception e) { System.out.println("Reflection blocked: " + e.getCause().getMessage()); }
        // Note: an enum singleton (enum S { INSTANCE }) is the simplest fully-safe alternative.
    }
}
