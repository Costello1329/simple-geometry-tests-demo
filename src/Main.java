import testlib.core.Tester;

import java.nio.file.Path;
import java.util.Arrays;

public class Main {
    public static void main (final String[] args) {
        if (args.length != 2) {
            System.out.println("Expected to receive 2 paths to jar archives (reference jar and solution jar)");
            System.exit(1);
        }

        Tester.test(
            Tests.class,
            Arrays.stream(args).map(Path::of).toArray(Path[]::new),
            new String[][] {
                new String[] { "reference.Point", "reference.Square" },
                new String[] { "solution.Point", "solution.Square" }
            }
        );
    }
}
