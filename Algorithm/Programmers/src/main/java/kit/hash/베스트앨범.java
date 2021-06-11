package kit.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO:
public class 베스트앨범 {
    public static int[] solution(String[] genres, int[] plays) {
        List<Integer> list = new ArrayList<>();
        int length = genres.length;
        Map<String, Integer> map = getSumOfGenres(genres, plays);
        Album[] albums = getAlbums(genres, plays, map);

        // 1) 재생 노래 횟수가 같을경우 => 고유 번호가 낮은 순으로 오름차순
        // 2) 재생된 장르 수가 같을경우 => 재생 노래 횟수 순 내림차순
        // * 재생된 장르 수(오름차순) -> 장르 내에서 재생된 노래 횟수(오름차순) -> 노래 별 고유 번호(내림차순)
        Arrays.sort(albums, (i1, i2) -> {
            if (i2.sum == i1.sum) {
                if (i2.plays == i1.plays) {
                    return Integer.compare(i1.index, i2.index);
                }
                return Integer.compare(i2.plays, i1.plays);
            }
            return Integer.compare(i2.sum, i1.sum);
        });

        for (int i = 0; i < length; i++) {
            System.out.println(albums[i].toString());
        }

        int count = 1;
        list.add(albums[0].index);
        for (int i = 1; i < length; i++) {
            if (count == 2 && albums[i].genres.equals(albums[i - 1].genres)) {
                continue;
            }
            if (!albums[i].genres.equals(albums[i - 1].genres)) {
                count = 0;
            }

            list.add(albums[i].index);
            count ++;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private static Album[] getAlbums(String[] genres, int[] plays, Map<String, Integer> map) {
        Album[] albums = new Album[genres.length];

        for (int i = 0; i < genres.length; i++) {
            Album album = new Album(genres[i], plays[i], map.get(genres[i]), i);
            albums[i] = album;
        }
        return albums;
    }

    private static Map<String, Integer> getSumOfGenres(String[] genres, int[] plays) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }
        return map;
    }

    static class Album implements Comparable<Album> {
        String genres;
        int plays;
        int sum;
        int index;

        private Album() {
        }

        public Album(String genres, int plays, int sum, int index) {
            this.genres = genres;
            this.plays = plays;
            this.sum = sum;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Album{" +
                    "genres='" + genres + '\'' +
                    ", plays=" + plays +
                    ", sum=" + sum +
                    ", index=" + index +
                    '}';
        }

        @Override
        public int compareTo(Album o) {
            if (o.sum == this.sum) {
                if (o.plays == this.plays) {
                    return Integer.compare(this.index, o.index);
                }
                return Integer.compare(o.plays, this.plays);
            }
            return Integer.compare(o.sum, this.sum);
        }
    }

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        int[] result = solution(genres, plays);
        System.out.println(Arrays.toString(result));
    }
}
