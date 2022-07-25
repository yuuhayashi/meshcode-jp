package osm.surveyor.task.tools;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PreflistConverterTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String[] args = {
			"src/test/resource/preflist.json",
			"preflist.geojson"
		};
		try {
			PreflistConverter.main(args);
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}

}
