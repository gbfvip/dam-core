package dam.cache;

import java.util.Arrays;

public class ArgsWrapper {
    private Object[] args;

    public ArgsWrapper(Object[] args) {
        this.args = args;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof ArgsWrapper && this.equals((ArgsWrapper) o);
    }

    public boolean equals(ArgsWrapper that) {
        return Arrays.equals(args, that.args);

    }

    @Override
    public int hashCode() {
        return args != null ? Arrays.hashCode(args) : 0;
    }

    @Override
    public String toString() {
        return "ArgsWrapper{" +
                "args=" + Arrays.toString(args) +
                '}';
    }
}
