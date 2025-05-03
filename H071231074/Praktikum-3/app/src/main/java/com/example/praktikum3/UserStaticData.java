package com.example.praktikum3;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStaticData {
    private static final Map<String, User> users = new HashMap<>();
    static final Map<String, List<Feed>> userPosts = new HashMap<>();
    private static final Map<String, List<List<Integer>>> userHighlights = new HashMap<>();


    static {

        // Data untuk user dengan ID "user1"
        // ...
        users.put("login", new User(
                "login",
                "rinaldiruslan",
                "Muh. Rinaldi Ruslan",
                "21 years old",
                String.valueOf(R.drawable.photo1),
                5,
                "100K",
                "50"
        ));
        List<Feed> postsLogin = new ArrayList<>();
        postsLogin.add(new Feed(R.drawable.photo1, R.drawable.photo1, "rinaldiruslan", "0", "0", "0", "login", "kevinardana", "My first post", "", false)); // imagePost berupa Integer (resource ID)
        postsLogin.add(new Feed(R.drawable.photo1, R.drawable.psm, "rinaldiruslan", "0", "0", "0", "login", "kevinardana", "Nice photo", "", false)); // imagePost berupa Integer (resource ID)

        Log.d("UserStaticData", "User1 Posts: " + postsLogin);
        userPosts.put("login", postsLogin);

        List<List<Integer>> highlightsLogin = new ArrayList<>();
        // Highlight 1: ada 2 gambar
        List<Integer> highlightLogin1 = new ArrayList<>();
        highlightLogin1.add(R.drawable.photo1);
        highlightLogin1.add(R.drawable.psm);

        // Highlight 2: ada 1 gambar
        List<Integer> highlightLogin2 = new ArrayList<>();
        highlightLogin2.add(R.drawable.photo1);

        highlightsLogin.add(highlightLogin1);
        highlightsLogin.add(highlightLogin2);

        userHighlights.put("login", highlightsLogin);

        users.put("user1", new User(
                "user1",
                "antony00",
                "Antony Santos",
                "25 anos Brazilian",
                String.valueOf(R.drawable.antony),
                10,
                "10,4JT",
                "971"
        ));
        List<Feed> postsUser1 = new ArrayList<>();
        postsUser1.add(new Feed(R.drawable.antony, R.drawable.santos, "antony00", "264rb", "19", "1.512", "user1", "antony00", "My first post", "", false)); // imagePost berupa Integer (resource ID)
        postsUser1.add(new Feed(R.drawable.antony, R.drawable.mu, "antony00", "77,3rb", "356", "285", "user1", "antony00", "Nice photo", "", false)); // imagePost berupa Integer (resource ID)
        Log.d("UserStaticData", "User1 Posts: " + postsUser1);
        userPosts.put("user1", postsUser1);

        List<List<Integer>> highlightsUser1 = new ArrayList<>();
        List<Integer> highlightsUser1_1 = new ArrayList<>();
        highlightsUser1_1.add(R.drawable.antony);
        highlightsUser1_1.add(R.drawable.mu);
        highlightsUser1.add(highlightsUser1_1);
        userHighlights.put("user1", highlightsUser1);

        // Data untuk user dengan ID "user2"
        users.put("user2", new User(
                "user2",
                "cristiano",
                "Cristiano Ronaldo",
                "40 anos Portugal",
                String.valueOf(R.drawable.cristiano),
                10,
                "652JT",
                "971"
        ));
        List<Feed> postsUser2 = new ArrayList<>();
        postsUser2.add(new Feed(R.drawable.cristiano, R.drawable.cristiano, "cristiano", "3,4JT", "21,7rb", "13,2rb", "user2", "cristiano", "Champion", "", false)); // imagePost berupa Integer (resource ID)
        postsUser2.add(new Feed(R.drawable.juventus, R.drawable.juventus, "cristiano", "1,2JT", "8.430", "1.375", "user2", "cristiano", "My Club", "", false)); // imagePost berupa Integer (resource ID)
        postsUser2.add(new Feed(R.drawable.realmadrid, R.drawable.realmadrid, "cristiano", "6,3JT", "64,4rb", "68,3rb", "user2", "cristiano", "Hala Madrid", "", false)); // imagePost berupa Integer (resource ID)
        Log.d("UserStaticData", "User2 Posts: " + postsUser2);
        userPosts.put("user2", postsUser2);

        List<List<Integer>> highlightsUser2 = new ArrayList<>();
        List<Integer> highlightsUser2_1 = new ArrayList<>();
        highlightsUser2_1.add(R.drawable.cristiano);
        highlightsUser2_1.add(R.drawable.juventus);
        highlightsUser2_1.add(R.drawable.realmadrid);
        highlightsUser2.add(highlightsUser2_1);
        userHighlights.put("user2", highlightsUser2);

        users.put("user3", new User(
                "user3",
                "cristiano",
                "Cristiano Ronaldo",
                "40 anos Portugal",
                String.valueOf(R.drawable.cristiano),
                10,
                "652JT",
                "971"
        ));
        List<Feed> postsUser3 = new ArrayList<>();
        postsUser3.add(new Feed(R.drawable.cristiano, R.drawable.cristiano, "cristiano", "3,4JT", "21,7rb", "13,2rb", "user3", "cristiano", "Champion", "", false)); // imagePost berupa Integer (resource ID)
        postsUser3.add(new Feed(R.drawable.juventus, R.drawable.juventus, "cristiano", "1,2JT", "8.430", "1.375", "user3", "cristiano", "My Club", "", false)); // imagePost berupa Integer (resource ID)
        postsUser3.add(new Feed(R.drawable.realmadrid, R.drawable.realmadrid, "cristiano", "6,3JT", "64,4rb", "68,3rb", "user3", "cristiano", "Hala Madrid", "", false)); // imagePost berupa Integer (resource ID)
        Log.d("UserStaticData", "User2 Posts: " + postsUser2);
        userPosts.put("user3", postsUser3);

        List<List<Integer>> highlightsUser3 = new ArrayList<>();
        List<Integer> highlightsUser3_1 = new ArrayList<>();
        highlightsUser3_1.add(R.drawable.cristiano);
        highlightsUser3_1.add(R.drawable.juventus);
        highlightsUser3_1.add(R.drawable.realmadrid);
        highlightsUser3.add(highlightsUser3_1);
        userHighlights.put("user2", highlightsUser3);

        users.put("user4", new User(
                "user4",
                "cristiano",
                "Cristiano Ronaldo",
                "40 anos Portugal",
                String.valueOf(R.drawable.cristiano),
                10,
                "652JT",
                "971"
        ));
        List<Feed> postsUser4 = new ArrayList<>();
        postsUser4.add(new Feed(R.drawable.cristiano, R.drawable.cristiano, "cristiano", "3,4JT", "21,7rb", "13,2rb", "user4", "cristiano", "Champion", "", false)); // imagePost berupa Integer (resource ID)
        postsUser4.add(new Feed(R.drawable.juventus, R.drawable.juventus, "cristiano", "1,2JT", "8.430", "1.375", "user4", "cristiano", "My Club", "", false)); // imagePost berupa Integer (resource ID)
        postsUser4.add(new Feed(R.drawable.realmadrid, R.drawable.realmadrid, "cristiano", "6,3JT", "64,4rb", "68,3rb", "user4", "cristiano", "Hala Madrid", "", false)); // imagePost berupa Integer (resource ID)
        Log.d("UserStaticData", "User2 Posts: " + postsUser2);
        userPosts.put("user4", postsUser4);

        List<List<Integer>> highlightsUser4 = new ArrayList<>();
        List<Integer> highlightsUser4_1 = new ArrayList<>();
        highlightsUser4_1.add(R.drawable.cristiano);
        highlightsUser4_1.add(R.drawable.juventus);
        highlightsUser4_1.add(R.drawable.realmadrid);
        highlightsUser4.add(highlightsUser4_1);
        userHighlights.put("user4", highlightsUser4);

        Log.d("UserStaticData", "Initial userPosts: " + userPosts);
        // Tambahkan data pengguna statis lainnya di sini jika perlu
    }

    public static User getUser(String userId) {

        return users.get(userId);
    }
    public static List<Feed> getPosts(String userId) {

        return userPosts.get(userId);
    }
    public static List<List<Integer>> getHighlights(String userId) {

        return userHighlights.get(userId);
    }

}