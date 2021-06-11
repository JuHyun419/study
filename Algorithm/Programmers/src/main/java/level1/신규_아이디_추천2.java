package level1;

public class 신규_아이디_추천2 {
    public static String solution(String newId) {
        return new KakaoId(newId)
                .toLowerCase()
                .filter()
                .toSingleDot()
                .removeFirstLastDot()
                .notBlank()
                .notGraterThan16()
                .notLessThan2()
                .build();
    }

    private static class KakaoId {
        private String s;

        KakaoId(String s) {
            this.s = s;
        }

        private KakaoId toLowerCase() {
            s = s.toLowerCase();
            return this;
        }

        private KakaoId filter() {
            // "[^\w\-.]*"
            s = s.replaceAll("[^a-z\\d\\-_.]*", "");
            return this;
        }

        private KakaoId toSingleDot() {
            // "[.]{2.}"
            s = s.replaceAll("\\.{2,}", ".");
            return this;
        }

        private KakaoId removeFirstLastDot() {
            s = s.replaceAll("^[.]|[.]$", "");
            return this;
        }

        private KakaoId notBlank() {
            s = s.isEmpty() ? "a" : s;
            return this;
        }

        private KakaoId notGraterThan16() {
            if (s.length() >= 16) {
                s = s.substring(0, 15);
            }
            s = s.replaceAll("[.]$", "");
            return this;
        }

        private KakaoId notLessThan2() {
            if (s.length() <= 2) {
                while (s.length() != 3) {
                    s += s.charAt(s.length() - 1);
                }
            }
            return this;
        }

        private String build() {
            return s;
        }
    }
}