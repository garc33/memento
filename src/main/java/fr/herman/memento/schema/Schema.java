package fr.herman.memento.schema;

import fr.herman.memento.io.DataReader;
import fr.herman.memento.io.DataWriter;

public interface Schema<T> extends DataReader<T>, DataWriter<T> {

}
