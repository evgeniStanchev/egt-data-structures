package com.egtinteractive.lists;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import com.egtinteractive.lists.LinkedList;
import com.egtinteractive.lists.List;
import com.egtinteractive.resources.SetupTests;

public class LinkedListTests extends SetupTests {

    @Test
    public void containsTheValue_ShouldReturnTrueIfContains() {
	final List<String> list = new LinkedList<>();
	for (int index = ZERO; index < 50; index++) {
	    list.add("index" + index);
	}
	for (int index = ZERO; index < 50; index++) {
	    list.add("index" + index);
	}
	for (int index = ZERO; index < 50; index++) {
	    assertTrue(list.contains("index" + index));
	}

    }

}
