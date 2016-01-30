package fr.herman.memento.storage;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;

import org.springframework.stereotype.Component;

@Component
public class RandomAccessFileStorageWriter implements StorageWriter {

	private RandomAccessFile file;
	
	private char[] buffer;

	@Override
	public long writeData(long cursor, Reader data)  {
		try {
			int length=0;
			file.seek(cursor);
			int current=0;
			while((current=data.read(buffer))!=-1){
				file.write(null, length, current);
				length+=current;
			}
			return cursor+length;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
