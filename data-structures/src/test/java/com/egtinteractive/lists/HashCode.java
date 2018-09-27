package com.egtinteractive.lists;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.lists.ArrayList;
import com.egtinteractive.lists.LinkedList;
import com.egtinteractive.lists.List;
import com.egtinteractive.resources.SetupTests;

public class HashCode extends SetupTests {

    private static final int ZERO = 0;

    @DataProvider(name = "list")
    public Object[][] createList() {
	return new Object[][] { { new ArrayList<>() }, { new LinkedList<>() },

	};
    }

    @Test(dataProvider = "twoLists")
    public void twoObjectsAreEqual_ThenTheirHashCodeMustBeTheSame(final List<Integer> A, final List<Integer> B) {
	int value = 0;
	for (int index = ZERO; index < RANDOM_POSITIVE_INT; index++) {
	    value = ThreadLocalRandom.current().nextInt();
	    A.add(value);
	    B.add(value);
	}
	assertTrue(A.equals(B));
	assertTrue(A.hashCode() == B.hashCode());
    }

    @Test(dataProvider = "list")
    public void invokingHashCodeMoreThanOnce_mustReturnTheSameIntValue(final List<Integer> list) {
	for (int index = ZERO; index < 50; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	assertTrue(list.hashCode() == list.hashCode());
    }

}
