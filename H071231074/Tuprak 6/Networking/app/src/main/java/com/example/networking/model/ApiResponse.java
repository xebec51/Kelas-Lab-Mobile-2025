package com.example.networking.model;

import java.util.List;

public class ApiResponse { // brfungsi untuk menampung response dari api
    private Info info;
    private List<Character> results;

    public ApiResponse(Info info, List<Character> results) { // constructor
        this.info = info;
        this.results = results;
    }

    public Info getInfo() { return info; }
    public List<Character> getResults() { return results; }

    public static class Info { // kelas yang representasi info dari response
        private int pages;

        public Info(int pages) {
            this.pages = pages;
        }

        public int getPages() { return pages; }
    }
}
