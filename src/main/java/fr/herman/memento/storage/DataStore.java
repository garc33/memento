package fr.herman.memento.storage;

import java.io.Closeable;
import java.io.IOException;

import fr.herman.memento.io.DataReader;
import fr.herman.memento.io.DataWriter;

public interface DataStore extends Closeable {
	<T> long writeRecord(long index, T data, DataWriter<T> writer) throws IOException;

	<T> T readRecord(long index, DataReader<T> reader) throws IOException;

}
