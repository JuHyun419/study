package kit.hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 베스트앨범2 {

    static class Music implements Comparable<Music> {
        String genre;
        int play;
        int allPlays;
        int index;

        public Music(String genre, int play, int allPlays, int index) {
            this.genre = genre;
            this.play = play;
            this.allPlays = allPlays;
            this.index = index;
        }

        @Override
        public int compareTo(Music o) {
            if (this.allPlays == o.allPlays) {
                if (this.play == o.play) {
                    return Integer.compare(this.index, o.index);
                }
                return Integer.compare(o.play, this.play);
            }
            return Integer.compare(o.allPlays, this.allPlays);
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        List<Music> list = new ArrayList<>();
        List<Integer> albums = new ArrayList<>();
        Map<String, Integer> play = new HashMap<>();

        // 장르별 재생된 수
        for (int i = 0; i < genres.length; i++) {
            play.put(genres[i], play.getOrDefault(genres[i], 0) + plays[i]);
        }

        for (int i = 0; i < genres.length; i++) {
            list.add(new Music(genres[i], plays[i], play.get(genres[i]), i));
        }

        Collections.sort(list);
        int count = 1;
        albums.add(list.get(0).index);

        for (int i = 1; i < genres.length; i++) {
            // 두 곡을 담고, 현재 곡과 이전 곡이 동일한 경우 -> 건너뛰기
            if (count == 2 && list.get(i).genre.equals(list.get(i - 1).genre)) {
                continue;
            }
            // 현재 곡과 이전 곡이 다른경우 -> count 초기화
            if (!list.get(i).genre.equals(list.get(i - 1).genre)) {
                count = 0;
            }
            albums.add(list.get(i).index);
            count++;
        }
        return albums.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        베스트앨범2 s = new 베스트앨범2();
    }
}
