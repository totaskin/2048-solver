package fi.hy.demo;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class Game2048Test {

	@Test
	void testBot() {
		assertThat(2, is(equalTo(2)));
	}

}
