public class StackException extends RuntimeException {
    public StackException(String message) {
        super(message);
    }

    public static class NegativeCapacityException extends StackException {
        public NegativeCapacityException() {
            super("Negative capacity!");
        }
    }

    public static class StackOverflowException extends StackException {
        public StackOverflowException() {
            super("The stack reached its full capacity.");
        }
    }

    public static class EmptyStackException extends StackException {
        public EmptyStackException() {
            super("The stack is empty.");
        }
    }
}