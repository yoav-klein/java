
class CountWordsContainsP {
    public static void main(String[] args) {
        String[] words = {"Pickle", "Grass", "George", "Rhapsody", "Roap", "Pirate"};
        int numWords = words.length;
        int count = 0;
    search:
        for(int i = 0; i < numWords; ++i) {
            String current = words[i].toLowerCase();
            int wordLength = current.length();
            for(int j = 0; j < wordLength; ++j) {
                if(current.charAt(j) == 'p') {
                    ++count;
                    continue search;
                }
            }
        }

        System.out.println("Number of words that contain P: " + count);
    }
}