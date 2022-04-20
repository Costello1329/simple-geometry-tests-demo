javac solution/Point.java solution/Square.java &&
jar -cf solution.jar solution/Point.class solution/Square.class solution/Square\$1.class &&
unlink solution/Point.class &&
unlink solution/Square.class &&
unlink solution/Square\$1.class &&

javac reference/Point.java reference/Square.java &&
jar -cf reference.jar reference/Point.class reference/Square.class reference/Square\$1.class &&
unlink reference/Point.class &&
unlink reference/Square.class &&
unlink reference/Square\$1.class
