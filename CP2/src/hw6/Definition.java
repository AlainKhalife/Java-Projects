package hw6;

public class Definition {
	
	private String word, description;
	
	public Definition(String word, String description) {
		this.word = word;
		this.description = description;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		String ans = "Word: " + word + "\nDescription: " + description +"\n--------------------------";
		return ans;
	}

}
