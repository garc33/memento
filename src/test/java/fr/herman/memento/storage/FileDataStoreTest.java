package fr.herman.memento.storage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import java.io.RandomAccessFile;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import fr.herman.memento.io.DataReader;
import fr.herman.memento.io.DataWriter;

@Test(singleThreaded = true)
public class FileDataStoreTest {
	@Mock
	private RandomAccessFile file;
	@InjectMocks
	private FileDataStore fileDataStore;

	@BeforeTest
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWriteRecord() throws Exception {
		// given
		Object data = new Object();
		DataWriter<Object> writer = given(mock(DataWriter.class).write(data, file)).willReturn(10L).getMock();

		// when
		long ptr = fileDataStore.writeRecord(5L, data, writer);

		// then
		assertThat(ptr).isEqualTo(15);
		then(file).should().seek(5L);
		then(writer).should().write(data, file);
	}

	@Test
	public void testReadRecord() throws Exception {
		// given
		Object data = new Object();
		DataReader<Object> reader = given(mock(DataReader.class).read(file)).willReturn(data).getMock();

		// when
		Object result = fileDataStore.readRecord(12L, reader);

		// then
		assertThat(result).isSameAs(data);
		then(file).should().seek(12L);
		then(reader).should().read(file);

	}

	@Test
	public void testClose() throws Exception {
		// when
		fileDataStore.close();

		// then
		then(file).should().close();
	}

}
