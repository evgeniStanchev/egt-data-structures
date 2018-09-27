package com.egtinteractive.lists;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;
import org.testng.annotations.Test;

import com.egtinteractive.lists.List;
import com.egtinteractive.resources.SetupTests;

public class ClearTests extends SetupTests {

    @Test(dataProvider = "lists")
    public void clearMethodRemoveAllElements_SizeShouldBeZERO(final List<Integer> list) {
	final int size = RANDOM_POSITIVE_INT;

	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	list.clear();

	final int actual = list.size();
	final int expected = 0;
	assertEquals(actual, expected);
    }

}
