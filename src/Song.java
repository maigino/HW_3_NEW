import java.util.Objects;

public class Song implements Cloneable{
    private final String name;
    private final String artist;
    private Genre genre;
    private int duration;

    public Song(String name, String artist, Genre genre, int duration) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Song)) {
            return false;
        }
        Song otherSong = (Song) other;
        return this.name.equals(otherSong.name) && this.artist.equals(otherSong.artist);
    }

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((name == null) ? 0 : name.hashCode());
//        result = prime * result + ((artist == null) ? 0 : artist.hashCode());
//        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
//        result = prime * result + duration;
//        return result;
//    }

    @Override
    public Song clone() {
        try {
            Song clonedSong = (Song) super.clone();
            return clonedSong;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public enum Genre {
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO
    }
    public String printDuration(int secondDuration) {
        String songDuration;
        if (secondDuration > 59) {
            int minutes = secondDuration / 60;
            int seconds = secondDuration % 60;
            String StringMinutes;
            String StringSeconds;
            StringMinutes = "" + minutes + "";
            if (seconds < 10) {
                StringSeconds = "0" + seconds;
            } else
                StringSeconds = "" + seconds + "";
            return StringMinutes + ":" + StringSeconds;
        }
        else
            songDuration = "00:"+secondDuration;
        return songDuration;
    }
    @Override
    public String toString() {
        return name +", "+ artist +", "+ genre +", "+ printDuration(duration);
    }
}