public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for(int i=0;i<word.length();i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }
    public boolean isPalindrome(String word){
        Deque<Character> pDeque = wordToDeque(word);
        while (pDeque.size()>1) {
            Character first = pDeque.removeFirst();
            Character last = pDeque.removeLast();
            if (first != last) {
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> pDeque = wordToDeque(word);
        while (pDeque.size()>1) {
            Character first = pDeque.removeFirst();
            Character last = pDeque.removeLast();
            if (!cc.equalChars(first, last)) {
                return false;
            }
        }
        return true;
    }
}
