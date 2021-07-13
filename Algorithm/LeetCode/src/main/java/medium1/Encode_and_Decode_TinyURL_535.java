package medium1;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Encode_and_Decode_TinyURL_535 {

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        try {
            return URLEncoder.encode(longUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        try {
            return URLDecoder.decode(shortUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    static Integer count=0;
    Map<String,String> map = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode2(String longUrl) {
        String key = count.toString();
        count++;
        map.put(key,longUrl);
        return key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode2(String shortUrl) {
        return map.get(shortUrl);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        Encode_and_Decode_TinyURL_535 codec = new Encode_and_Decode_TinyURL_535();
        System.out.println(codec.encode("https://leetcode.com/problems/design-tinyurl"));
    }

}
