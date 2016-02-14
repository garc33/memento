package fr.herman.memento.index;

import java.io.IOException;

public interface Index extends AutoCloseable {
	long getPosition(long id) throws IOException;

	void setPosition(long id, long position) throws IOException;
}
