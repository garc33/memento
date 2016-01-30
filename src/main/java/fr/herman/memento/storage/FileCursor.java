package fr.herman.memento.storage;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

@Component
public class FileCursor {

	private AtomicLong cursor;
	
	
	public long getCurrent(){
		return cursor.get();
	}
	
	public void update(long newValue){
		cursor.set(newValue);
	}
}
