import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class PlaylistIterator implements Iterator<Song> {
    private List<Song> songs;
    private int currentIndex;

    public PlaylistIterator(List<Song> songs) {
        this.songs = songs;
        this.currentIndex = 0;
    }
    public boolean hasNext(){
        return currentIndex < songs.size();
    }
    public Song next(){
        if(!hasNext()){
            throw new NoSuchElementException();

        }
        return songs.get(currentIndex++);
    }
}