package easy3;

public class Slowest_Key_1629 {

    public char slowestKey(final int[] releaseTimes, final String keysPressed) {
        final int length = releaseTimes.length;
        int max = releaseTimes[0];
        char ch = keysPressed.charAt(0);

        for (int i = 1; i < length; i++) {
            final int duration = releaseTimes[i] - releaseTimes[i - 1];
            if (duration > max) {
                max = duration;
                ch = keysPressed.charAt(i);
            } else if (duration == max) {
                final int maxChar = ch - 'a';
                final int maxDuration = keysPressed.charAt(i) - 'a';
                if (maxDuration > maxChar) {
                    ch = keysPressed.charAt(i);
                }
            }
        }
        return ch;
    }


}
