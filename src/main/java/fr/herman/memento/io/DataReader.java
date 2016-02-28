package fr.herman.memento.io;

import java.io.DataInput;
import java.io.IOException;

public interface DataReader<T> {
	T read(DataInput input) throws IOException;
}
