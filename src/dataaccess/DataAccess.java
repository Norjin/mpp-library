package dataaccess;

import java.util.HashMap;

import business.Book;
import business.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveLibraryMember(String memberId, LibraryMember lmem);
	public LibraryMember getMember(String name);
}
