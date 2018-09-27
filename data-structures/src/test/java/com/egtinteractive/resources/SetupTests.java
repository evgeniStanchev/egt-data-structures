package com.egtinteractive.resources;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;

import com.egtinteractive.lists.ArrayList;
import com.egtinteractive.lists.LinkedList;
import com.interactive.maps.ChainingHashMap;
import com.interactive.maps.ProbingHashMap;
import com.interactive.trees.BinaryTree;

public class SetupTests {

    protected final static int ZERO = 0;
    protected final static int RANDOM_INT = ThreadLocalRandom.current().nextInt();
    protected final static int RANDOM_NEGAVITE_INT = ThreadLocalRandom.current().nextInt(-65536, ZERO);
    protected static int RANDOM_POSITIVE_INT = ThreadLocalRandom.current().nextInt(1, 50);
    protected final static int RANDOM_INT_BIGGER_THAN_10 = ThreadLocalRandom.current().nextInt(10, 50);
    protected final static int RANDOM_INT_LESS_THAN_50 = ThreadLocalRandom.current().nextInt(50);

    @DataProvider(name = "list")
    public Object[][] createList() {
	return new Object[][] { { new ArrayList<>() }, { new LinkedList<>() }, };
    }

    @DataProvider(name = "lists")
    public Object[][] createData1() {
	return new Object[][] { { new ArrayList<>() }, { new LinkedList<>() }, };
    }

    @DataProvider(name = "twoLists")
    public Object[][] createLists() {
	return new Object[][] { { new ArrayList<>(), new ArrayList<>() }, { new LinkedList<>(), new LinkedList<>() } };
    }

    @DataProvider(name = "threeLists")
    public Object[][] createThreeLists() {
	return new Object[][] { { new ArrayList<>(), new ArrayList<>(), new ArrayList<>() },
		{ new LinkedList<>(), new LinkedList<>(), new LinkedList<>() } };
    }

    @DataProvider(name = "map")
    public Object[][] createMap() {
	return new Object[][] { { new ProbingHashMap<>() }, };
    }

    @DataProvider(name = "maps")
    public Object[][] createTwoTypeMaps() {
	return new Object[][] { { new ChainingHashMap<>() }, { new ProbingHashMap<>() }, };
    }

    @DataProvider(name = "twoMaps")
    public Object[][] createTwoMaps() {
	return new Object[][] { { new ProbingHashMap<>(), new ProbingHashMap<>() }, };
    }

    @DataProvider(name = "threeMaps")
    public Object[][] createThreeMaps() {
	return new Object[][] { { new ProbingHashMap<>(), new ProbingHashMap<>(), new ProbingHashMap<>() }, };
    }

    @DataProvider(name = "trees")
    public Object[][] createTree() {
	return new Object[][] { { new BinaryTree<>() }, };
    }
}
