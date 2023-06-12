package src;

public  class StackOverflowException extends StackException {
    public StackOverflowException() {
        super("The stack reached its full capacity.");
    }
}