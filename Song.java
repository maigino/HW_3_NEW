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
    public boolean equals(Object other){ 
        if (!(other instanceof Song)) {
            return false;
        }
        Song otherSong = (Song) other;
        if (otherSong.name.equals(this.name) && otherSong.artist.equals(this.artist)) {
            return true;
        }
        return false;
    }
    @Override
     public int hashCode() {
        return Integer.hashCode(duration);
    }
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
            if (minutes < 10) {
                StringMinutes = "0" + minutes;
            } else
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
        return "(name: " + name + ", artist: " + artist + ", genre: " + genre + ", duration: " + printDuration(duration)+")";
    }
}
