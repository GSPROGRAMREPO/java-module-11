package finals_project;

public class UniqueWord implements Comparable<UniqueWord>{
	String m_word;
	int m_occurences;
	
	/**
	 * <b>Constructs and initializes a word object</b>
	 * Implements <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html">comparable</a>.
	 * @param word <- The Word whose occurrences as being counted.
	 * @param occurences <- The number of times the word appears in the txt file.
	 */
	
	public UniqueWord(String word, int occurences) {
		m_word = word;
		m_occurences = occurences;
		
	}
	
	public int getOccurences() {
		
		return m_occurences;
	}
	
	public int compareTo(UniqueWord compareWord) {
		
		int compareOccurence = compareWord.getOccurences();
		return compareOccurence - m_occurences;
		
	}
	
	public String getWord() {
		
		return m_word;
	}
	
	@Override
    public String toString() { 
        return m_word + " " + m_occurences;
    } 

}
