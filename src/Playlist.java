package src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Playlist implements OrderSongIterable, FilteredSongIterable, Cloneable{
    private List<Song> songs;
    private ScanningOrder scanningOrder;
    private String filterArtist;
    private String filterGenre;
    private int filterDuration;
    private int size;
    public Playlist(){
        this.songs=new ArrayList<>();
        this.scanningOrder = ScanningOrder.ADDING;
        this.filterArtist = null;
        this.filterGenre = null;
        this.filterDuration = -1;
        this.size = 0;
    }

    public void addSong(Song song)  {
        for (Song existingSong : songs) {
            if (existingSong.equals(song)) {
                throw new SongAlreadyExistsException();
            }
        }
        size++;
        songs.add(song);
    }
    public boolean removeSong (Song song) {
        int index=0;
        for (Song existingSong : songs){
            if(existingSong.equals(song)){
                songs.remove(index);
                return true;
            }
            index++;
        }
        size--;
        return false;
    }
    public void setScanningOrder(ScanningOrder order){
        this.scanningOrder = order;

    }
    public void filterArtist(String artist) {
        this.filterArtist = artist;
    }

    @Override
    public void filterGenre(Enum genre) {

    }

    public void filterGenre(String genre) {
        this.filterGenre = genre;

    }
    public void filterDuration(int duration){
        this.filterDuration = duration;
    }
    @Override
    public Playlist clone(){
        int index = 0;
        try {
            Playlist clonedPlaylist = (Playlist) super.clone();
            for (Song originalSong : songs) {
                try{
                    System.out.println(index++);
                    clonedPlaylist.addSong(originalSong.clone());
                }
                catch(SongAlreadyExistsException e){
                  System.out.println("caught on"+originalSong.toString());  
                }
                }
            return clonedPlaylist;
            }
        catch (CloneNotSupportedException e) {
            // This block should not be reached if Playlist implements Cloneable
            return null;
        }
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
                songs.sort(Comparator.comparing(Song::getName)
                        .thenComparing(Song::getArtist));
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
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Playlist)) {
            return false;
        }

        Playlist otherPlaylist = (Playlist) other;

        if (songs == null) {
            if (otherPlaylist.songs != null) {
                return false;
            }
        } else if (!songs.equals(otherPlaylist.songs)) {
            return false;
        }

        if (filterArtist == null) {
            if (otherPlaylist.filterArtist != null) {
                return false;
            }
        } else if (!filterArtist.equals(otherPlaylist.filterArtist)) {
            return false;
        }

        if (filterGenre == null) {
            if (otherPlaylist.filterGenre != null) {
                return false;
            }
        } else if (!filterGenre.equals(otherPlaylist.filterGenre)) {
            return false;
        }

        return filterDuration == otherPlaylist.filterDuration;
    }
    @Override
    public String toString() {
        String result = "";
        for (Song song : songs) {
            result += song.toString();
        }
        return result;
    }
    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }
    private class PlaylistIterator implements Iterator<Song> {
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            return currentIndex < size - 1;
        }
        @Override
        public Song next() {
            if (!hasNext()) {
                throw new EmptyStackException();
            }
            currentIndex++;
            return songs.get(currentIndex-1);
        }
    }
}
