package fr.herman.memento.index;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import java.io.DataInput;
import java.io.DataOutput;

import org.testng.annotations.Test;

public class SimpleIndexStructureTest {

	private SimpleIndexStructure structure = new SimpleIndexStructure();

	@Test
	public void testComputeIndexPosition() throws Exception {
		assertThat(structure.computeIndexPosition(10L)).isEqualTo(80L);
	}

	@Test
	public void testReadPosition() throws Exception {
		// given
		DataInput input = given(mock(DataInput.class).readLong()).willReturn(20L).getMock();

		// when
		long position = structure.readPosition(input);

		// then
		then(input).should().readLong();
		assertThat(position).isEqualTo(20L);
	}

	@Test
	public void testWritePosition() throws Exception {
		// given
		DataOutput output = mock(DataOutput.class);

		// when
		structure.writePosition(output, 30L);

		// then
		then(output).should().writeLong(30L);
	}

}
