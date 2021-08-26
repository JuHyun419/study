package kakao.blind_2018;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class 방금그곡 {

    static class Music implements Comparable<Music> {
        private final int index;
        private final String musicName;
        private final String musicPlay;

        public Music(int index, String musicName, String musicPlay) {
            this.index = index;
            this.musicName = musicName;
            this.musicPlay = musicPlay;
        }

        // 재생된 시간이 제일 긴 음악순 -> 재생된 시간이 같을 경우 먼저 입력된 음악
        @Override
        public int compareTo(Music o) {
            if (this.musicPlay.length() == o.musicPlay.length()) {
                return this.index - o.index;
            }
            return o.musicPlay.length() - this.musicPlay.length();
        }
    }

    public static String solution(String m, String[] musicinfos) {
        List<Music> myMusic = new ArrayList<>(); // 조건에 일치하는 음악 리스트
        m = sheetReplace(m);

        for (int i = 0; i < musicinfos.length; i++) {
            String[] music = musicinfos[i].split(",");
            int diffMinutes = getDiffTime(music[0], music[1]);
            String musicName = music[2];
            String sheetMusic = sheetReplace(music[3]);

            String musicPlay = "";
            // 재생된 시간이 음악 길이보다 길 때
            if (diffMinutes > sheetMusic.length()) {
                int mok = diffMinutes / sheetMusic.length();
                int rest = diffMinutes % sheetMusic.length();
                for (int j = 0; j < mok; j++) {
                    musicPlay += sheetMusic;
                }
                musicPlay += sheetMusic.substring(0, rest);
            } else {
                musicPlay = sheetMusic.substring(0, diffMinutes);
            }
            // 반복해서 재생된 악보가 네오가 기억한 악보를 포함하는 경우 => 조건 일치
            if (musicPlay.contains(m)) {
                myMusic.add(new Music(i, musicName, musicPlay));
            }
        }

        // 조건에 일치하는 음악이 없을 때
        if (myMusic.size() == 0) {
            return "(None)";
        }
        Collections.sort(myMusic);
        return myMusic.get(0).musicName;
    }

    /* 재생 시각 */
    private static int getDiffTime(String start, String end) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sdf.parse(start);
            endDate = sdf.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) ((endDate.getTime() - startDate.getTime()) / (60 * 1000));
    }

    /* 샵 => 소문자 */
    private static String sheetReplace(String sheet) {
        return sheet.replaceAll("C#", "c")
                .replaceAll("D#", "d")
                .replaceAll("F#", "f")
                .replaceAll("G#", "g")
                .replaceAll("A#", "a");
    }

    public static void main(String[] args) {
        String m = "CC#BCC#BCC#BCC#B";
        String[] info = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};

        System.out.println(solution(m, info));
    }
}
