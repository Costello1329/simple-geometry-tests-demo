import testlib.core.Test;
import testlib.core.TestContext;
import testlib.components.*;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tests {
    @Test(number = 1, name = "Point tests", repeat = 100)
    public static void point (final TestContext testContext) {
        final Supplier<List<Entity>> coordinateSupplier =
            () -> Collections.nCopies(2, new Entity(random.nextDouble(), double.class));

        final Generator pointGenerator = new Generator(
            testContext,
            testContext.getClassesToTest()[0],
            Arrays.asList(coordinateSupplier, coordinateSupplier)
        );

        final EntityComparator pointEntityComparator = new EntityComparator(testContext, Arrays.asList(
            simpleDoubleCompare(Collections.singletonList(new MethodCall("x"))),
            simpleDoubleCompare(Collections.singletonList(new MethodCall("y")))
        ));

        generalTest(testContext, "point", pointGenerator, pointEntityComparator, OperationsApplier.empty(testContext));
    }

    @Test(number = 2, name = "Square tests", repeat = 100)
    public static void square (final TestContext testContext) {
        final Supplier<List<Entity>> coordinateSupplier =
            () -> Collections.nCopies(2, new Entity(random.nextDouble(), double.class));

        final Generator pointGenerator = new Generator(
            testContext,
            testContext.getClassesToTest()[0],
            Arrays.asList(coordinateSupplier, coordinateSupplier)
        );

        final Generator squareGenerator = new Generator(
            testContext,
            testContext.getClassesToTest()[1],
            Arrays.asList(pointGenerator, pointGenerator)
        );

        final Function<Integer, MethodCall> callGet =
            i -> new MethodCall("get", Collections.singletonList(new Entity(i, int.class)));

        final EntityComparator squareEntityComparator = new EntityComparator(testContext, Arrays.asList(
            simpleDoubleCompare(Arrays.asList(new MethodCall("center"), new MethodCall("x"))),
            simpleDoubleCompare(Arrays.asList(new MethodCall("center"), new MethodCall("y"))),
            simpleDoubleCompare(Collections.singletonList(new MethodCall("perimeter"))),
            simpleDoubleCompare(Collections.singletonList(new MethodCall("area"))),
            simpleDoubleCompare(Arrays.asList(new MethodCall("vertices"), callGet.apply(0), new MethodCall("x"))),
            simpleDoubleCompare(Arrays.asList(new MethodCall("vertices"), callGet.apply(0), new MethodCall("y"))),
            simpleDoubleCompare(Arrays.asList(new MethodCall("vertices"), callGet.apply(1), new MethodCall("x"))),
            simpleDoubleCompare(Arrays.asList(new MethodCall("vertices"), callGet.apply(1), new MethodCall("y"))),
            simpleDoubleCompare(Arrays.asList(new MethodCall("vertices"), callGet.apply(2), new MethodCall("x"))),
            simpleDoubleCompare(Arrays.asList(new MethodCall("vertices"), callGet.apply(2), new MethodCall("y"))),
            simpleDoubleCompare(Arrays.asList(new MethodCall("vertices"), callGet.apply(3), new MethodCall("x"))),
            simpleDoubleCompare(Arrays.asList(new MethodCall("vertices"), callGet.apply(3), new MethodCall("y")))
        ));

        generalTest(
            testContext,
            "square",
            squareGenerator,
            squareEntityComparator,
            new OperationsApplier(testContext, getOperations(random.nextInt(50, 100)))
        );
    }

    private static void generalTest (
        final TestContext testContext,
        final String instanceName,
        final Generator generator,
        final EntityComparator entityComparator,
        final OperationsApplier operationsApplier
    ) {
        final List<Entity> instances = generator.get();
        testContext.logLine(String.format(
            "final %s %s = %s;",
            instances.get(0).instanceClass().getSimpleName(),
            instanceName,
            instances.get(0).presentation()
        ));
        entityComparator.compare(instanceName, instances);

        while (operationsApplier.hasNext()) {
            operationsApplier.apply(instanceName, instances);
            entityComparator.compare(instanceName, instances);
        }
    }

    private static EntityComparatorRecord simpleDoubleCompare (final List<MethodCall> methodCallChain) {
        return new EntityComparatorRecord(
            methodCallChain,
            (firstNumber, secondNumber) -> Math.abs((double) firstNumber - (double) secondNumber) < 1e-3,
            "The values are not close to each other"
        );
    }

    private static List<MethodCall> getOperations (final int count) {
        return IntStream.range(0, count).mapToObj(i ->
            switch (random.nextInt(0, 3)) {
                case 0 -> new MethodCall("translate", List.of(
                    new Entity(random.nextDouble(-100., 100.), double.class),
                    new Entity(random.nextDouble(-100., 100.), double.class)
                ));
                case 1 -> new MethodCall("rotate", List.of(
                    new Entity(random.nextDouble(-100., 100.), double.class)
                ));
                case 2 -> new MethodCall("scale", List.of(
                    new Entity((random.nextBoolean() ? -1. : 1.) * random.nextDouble(.1, 10.), double.class)
                ));
                default -> null;
            }
        ).collect(Collectors.toList());
    }

    private static final SplittableRandom random = new SplittableRandom();
}
