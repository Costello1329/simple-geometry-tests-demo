import testlib.core.Tester;

import java.nio.file.Path;

public class Main {
    public static void main (final String[] args) {
        Tester.test(
            Tests.class,
            new Path[] { Path.of("reference.jar"), Path.of("solution.jar") },
            new String[][] {
                new String[] { "reference.Point", "reference.Square" },
                new String[] { "solution.Point", "solution.Square" }
            }
        );
    }
}
