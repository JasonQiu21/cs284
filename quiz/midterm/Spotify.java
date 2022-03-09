import java.util.ArrayList;
import java.util.Collection;

// A music object is made up 2 string; song name and artist
class Music {
	String song;
	String artist;

	public Music(String song, String artist) {
		this.song = song;
		this.artist = artist;
	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	@Override
	public String toString() {
		return String.format("%-21s: %s", song, artist);
	}
}

// Each node will hold on to a song and the following song in the queue (the next node)
class Node {
	Music song;
	Node next;

	public Node(Music song) {
		this.song = song;
	}

	public Node() {
	}

	@Override
	public String toString() {
		if (song == null) {
			return "";
		}
		return song.toString();
	}
}

public class Spotify {
	Node current;
	Node last;

	/*
	 * TODO: You will take in a list of songs, in the form of arraylist, linkedlist,
	 * etc (Collection) and you will add that to the end of your queue
	 */
	public void addAll(Collection<Music> listOfSongs){
		for(Music m : listOfSongs){
			Node song = new Node(m);
			if(this.current == null || this.last == null){
				this.current = song;
				this.last = song;
			}
			this.last.next = song;
			this.last = song;
		}
		this.last.next = this.current;
	}

	/*
	 * TODO: You will remove the song that is in the 1st position
	 */
	public void removeSong() {
		if(this.current == null || this.last == null){return;}
		this.last.next = this.current.next;
		this.current = this.current.next;
	}

	/*
	 * TODO: You will add a song to the end of the queue
	 */
	public void addSong(Music song) {
		Node node = new Node(song);
		if(this.last == null){
			this.current = node;
			this.last = node;
			node.next = this.current;
		} else {
		node.next = this.current;
		this.last.next = node;
		this.last = node;
		}
	}

	/*
	 * TODO: You will rotate the queue so that we are now listening to the next song
	 */
	public void nextSong() {
		if(this.current == null || this.last == null){return;}
		this.last = this.current;
		this.current = this.current.next;
	}

	/*
	 * TODO: You will rotate the queue so that we are now listening to the previous
	 * song
	 */
	public void prevSong() {
		if(this.current == null || this.last == null){return;}
		while(this.current.next != this.last){
			this.current = this.current.next;
		}
		Node temp = this.current;
		this.current = this.last;
		this.last = temp;
	}

	/*
	 * TODO: You will tell us the song in the 1st position
	 */
	public Music currentSong() {
		if(this.current == null){return null;}
		return this.current.song;
	}

	// use String.format( "%3s) %s\n", i, theCurrentNode );
	// i: the count starting at 1
	// theCurrentNode: current song node
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("=====[Current Queue]=====\n");
		if (current == null) {
			stringBuilder.append("no songs\n");
			return stringBuilder.toString();
		}
		Node temp = current;

		int i = 1;
		while (temp.next != null && temp.next != last.next) {
			stringBuilder.append(String.format("%3s) %s\n", i, temp));
			temp = temp.next;
			i++;
		}

		stringBuilder.append(String.format("%3s) %s\n", i, temp));

		return stringBuilder.toString();

	}


	
	public static void main(String[] args) {
		// Example 1
		ArrayList<Music> songs = new ArrayList<>();
		songs.add(new Music("Happy birthday", "shawn"));
		songs.add(new Music("Space Cadet", "Metro Boomin"));
		songs.add(new Music("100", "The Game"));
		songs.add(new Music("L@D", "A@AP Rocky"));
		songs.add(new Music("Hot Man", "Bobby Shmurda"));
		songs.add(new Music("Men in Paris", "Kanye East and Jay-Y"));
		songs.add(new Music("Im Sad", "Aubrey "));

		String inputString = "[new Music(\"Happy birthday\", \"shawn\"), "
				+ "new Music(\"Space Cadet\", \"Metro Boomin\"), new Music(\"100\", \"The Game\"), "
				+ "new Music(\"L@D\", \"A@AP Rocky\"), new Music(\"Hot Man\", \"Bobby Shmurda\"), "
				+ "new Music(\"Men in Paris\", \"Kanye East and Jay-Y\"), new Music(\"Im Sad\", \"Aubrey \")]";

		Spotify spotify = new Spotify();
		spotify.addAll(songs);

		String answerString = "Happy birthday       : shawn"; // .replaceAll("\\s", "");
		String actualString = spotify.currentSong().toString(); // .replaceAll("\\s", "");
		String expAns = answerString;
		String actAns = actualString;

		if (expAns.replaceAll("\\s", "").equals(actAns.replaceAll("\\s", ""))) {
			System.out.println("Example 1 working");
		} else {
			System.out.println("Issue in example 1");
			System.out.println(spotify.toString());
			System.out.println(expAns);
		}
	}
}
