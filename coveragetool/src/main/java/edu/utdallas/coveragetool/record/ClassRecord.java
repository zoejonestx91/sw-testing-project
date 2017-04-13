package edu.utdallas.coveragetool.record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import edu.utdallas.coveragetool.agent.UnitListener;

/**
 * Created by z on 3/20/17.
 */
public class ClassRecord implements Comparable<ClassRecord> {
    int id;
	String name;
    
    public ClassRecord(int id, String name) {
    	this.id = id;
    	this.name = name;
    }

	@Override
	public int compareTo(ClassRecord o) {
		return name.compareTo(o.name);
	}

    public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
