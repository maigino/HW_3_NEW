package src;

public interface FilteredSongIterable extends Iterable<Song>{
    void filterArtist(String artist);
    void filterGenre(Enum genre);
    void filterDuration(int duration);
}