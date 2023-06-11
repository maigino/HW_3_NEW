import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Playlist implements OrderSongIterable, FilteredSongIterable{
    private List<Song> songs;
    private ScanningOrder scanningOrder;
    private String filterArtist;
    private String filterGenre;
    private int filterDuration;
    public Playlist(){
        this.songs=new ArrayList<>();
        this.scanningOrder = ScanningOrder.ADDING;
        this.filterArtist = null;
        this.filterGenre = null;
        this.filterDuration = -1;
    }

    public void addSong(Song song) {
        for (Song existingSong : songs) {
            if (existingSong.getName().equals(song.getName()) && existingSong.getArtist().equals((song.getArtist()))) {
                throw new SongAlreadyExistsException();
            }
            songs.add(song);
        }
    }
    public boolean removeSong (Song song){
        if (songs.contains(song)) {
            songs.remove(song); //// mimush kaka
            return true;
        } else
            return false;
        }
    @Override
    public Iterator<Song> iterator(){
        List<Song> filteredSongs = filterSongs();
        sortSongs(filteredSongs);
        return new PlaylistIterator(filteredSongs);
    }
    @Override
    public void setScanningOrder(ScanningOrder order){
        this.scanningOrder = order;

    }
    @Override
    public void filterArtist(String artist) {
        this.filterArtist = artist;
    }
    @Override
    public void filterGenre(String genre) {
        this.filterGenre = genre;

    }
    @Override
    public void filterDuration(int duration){
        this.filterDuration = duration;
    }
    private List<Song> filterSongs(){
        List<Song> filteredSongs = new ArrayList<>();
        for(Song song: songs){
            if (filterArtist == null || song.getArtist().equals(filterArtist)) {
                if (filterGenre == null || song.getGenre().equals(filterGenre)) {
                    if (filterDuration == -1 || song.getDuration() <= filterDuration) {
                        filteredSongs.add(song);
                    }
                }
            }
        }
        return filteredSongs;
        }
    private void sortSongs(List<Song> songs) {
        switch (scanningOrder) {
            case NAME:
                songs.sort(Comparator.comparing(Song::getName));
                break;
            case DURATION:
                songs.sort(Comparator.comparing(Song::getDuration));
                break;
            default:
                // No sorting needed for ADDING order
                break;
        }
    }
    @Override
    public String toString() {
        String result = "";
        for (Song song : songs) {
            result += song.toString() + "\n";
        }
        return result;
    }
}

