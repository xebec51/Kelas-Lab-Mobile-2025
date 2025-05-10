// Path: data/BookDataSource.java
package com.example.libraryapp.data;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class BookDataSource {

    private static final List<Book> books = new ArrayList<>();

    static {
        // 15 buku dummy dengan cover placeholder
        books.add(new Book("Atomic Habits", "James Clear", 2018, "Buku tentang perubahan kebiasaan", Uri.parse("android.resource://com.example.libraryapp/drawable/atomic_habits"), false));
        books.add(new Book("The Alchemist", "Paulo Coelho", 1988, "Petualangan seorang anak gembala mencari harta", Uri.parse("android.resource://com.example.libraryapp/drawable/the_alchemist"), false));
        books.add(new Book("Sapiens", "Yuval Noah Harari", 2014, "Sejarah umat manusia", Uri.parse("android.resource://com.example.libraryapp/drawable/sapiens"), false));
        books.add(new Book("Rich Dad Poor Dad", "Robert Kiyosaki", 1997, "Pendidikan finansial dari dua sudut pandang", Uri.parse("android.resource://com.example.libraryapp/drawable/rich_dad_poor_dad"), false));
        books.add(new Book("Deep Work", "Cal Newport", 2016, "Kunci produktivitas dalam dunia digital", Uri.parse("android.resource://com.example.libraryapp/drawable/deep_work"), false));
        books.add(new Book("Filosofi Teras", "Henry Manampiring", 2018, "Penerapan stoikisme modern", getDummyUri(), false));
        books.add(new Book("You Do You", "Fellexandro Ruby", 2021, "Buku tentang pengembangan diri", Uri.parse("android.resource://com.example.libraryapp/drawable/you_do_you"), false));
        books.add(new Book("Think Again", "Adam Grant", 2021, "Seni berpikir ulang", Uri.parse("android.resource://com.example.libraryapp/drawable/think_again"), false));
        books.add(new Book("Ikigai", "Hector Garcia", 2016, "Rahasia hidup panjang dan bahagia", Uri.parse("android.resource://com.example.libraryapp/drawable/ikigai"), false));
        books.add(new Book("Start With Why", "Simon Sinek", 2009, "Menginspirasi aksi melalui ide besar", Uri.parse("android.resource://com.example.libraryapp/drawable/start_with_why"), false));
        books.add(new Book("The Power of Habit", "Charles Duhigg", 2012, "Bagaimana kebiasaan terbentuk dan berubah", Uri.parse("android.resource://com.example.libraryapp/drawable/the_power_of_habbit"), false));
        books.add(new Book("Man’s Search for Meaning", "Viktor Frankl", 1946, "Filsafat dan psikologi di kamp konsentrasi", Uri.parse("android.resource://com.example.libraryapp/drawable/meaning"), false));
        books.add(new Book("Dare to Lead", "Brené Brown", 2018, "Kepemimpinan berani dan rentan", Uri.parse("android.resource://com.example.libraryapp/drawable/dare_to_lead"), false));
        books.add(new Book("The Subtle Art of Not Giving a F*ck", "Mark Manson", 2016, "Hidup dengan perspektif berbeda", Uri.parse("android.resource://com.example.libraryapp/drawable/subtle"), false));
        books.add(new Book("Educated", "Tara Westover", 2018, "Perjuangan keluar dari kebodohan sistematis", Uri.parse("android.resource://com.example.libraryapp/drawable/educated"), false));
    }

    private static Uri getDummyUri() {
        return Uri.parse("android.resource://com.example.libraryapp/drawable/placeholder");
    }

    public static List<Book> getAllBooks() {
        return books;
    }

    public static void updateBookLiked(Book updatedBook, boolean liked) {
        for (Book b : books) {
            if (b.getTitle().equals(updatedBook.getTitle()) &&
                    b.getAuthor().equals(updatedBook.getAuthor())) {
                b.setLiked(liked);
                break;
            }
        }
    }

    public static void addBook(Book book) {
        books.add(0, book); // tambah di awal (buku baru muncul di atas)
    }
}
