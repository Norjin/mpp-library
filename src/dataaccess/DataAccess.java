package dataaccess;

import java.util.HashMap;

import business.Book;
import business.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public LibraryMember saveLibraryMember(String memberId, LibraryMember lmem);
	public LibraryMember getMember(String name);
	public void saveBook(Book book);
	public Book getBook(String isbn);
}
