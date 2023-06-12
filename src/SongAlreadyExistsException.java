package src;

public class SongAlreadyExistsException extends RuntimeException {
    public SongAlreadyExistsException() {
        super("Song already exists in the playlist.");
    }
}