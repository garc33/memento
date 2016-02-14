package fr.herman.memento.index;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.io.RandomAccessFile;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test(singleThreaded = true)
public class RandomAccessIndexTest {
	@Mock
	private RandomAccessFile file;

	@Mock
	private SimpleIndexStructure structure;

	@InjectMocks
	private RandomAccessIndex randomAccessIndex;

	@BeforeTest
	public void createRandomAccessIndex() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetPosition() throws Exception {
		// given
		given(structure.readPosition(file)).willReturn(123456789L);
		given(structure.computeIndexPosition(10L)).willReturn(80L);

		// when
		long position = randomAccessIndex.getPosition(10L);

		// then
		then(file).should().seek(80L);
		assertThat(position).isEqualTo(123456789L);
	}

	@Test
	public void testSetPosition() throws Exception {
		// given
		given(structure.computeIndexPosition(20L)).willReturn(160L);

		// when
		randomAccessIndex.setPosition(20L, 123456789L);

		// then
		then(file).should().seek(160L);
		then(structure).should().writePosition(file, 123456789L);
	}

	@Test
	public void testClose() throws Exception {
		// when
		randomAccessIndex.close();

		// then
		then(file).should().close();
	}

}
