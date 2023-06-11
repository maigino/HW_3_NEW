public interface FilteredSongIterable extends Iterable<Song>{
    void filterArtist(String artist);
    void filterGenre(String genre);
    void filterDuration(int duration);
}
