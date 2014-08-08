package com.github.lands;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class PickleSerializationTest {

    @Test
    public void canLoadWorldWithoutErrors() throws IOException {
        File file = new File("examples-worlds/seed_77.world");
        World world = PickleSerialization.loadWorld(file);
    }

    @Test
    public void nameIsLoaded() throws IOException {
        File file = new File("examples-worlds/seed_77.world");
        World world = PickleSerialization.loadWorld(file);
        assertEquals("seed_77", world.getName());
    }

    @Test
    public void dimensionIsLoaded() throws IOException {
        File file = new File("examples-worlds/seed_77.world");
        World world = PickleSerialization.loadWorld(file);
        assertEquals(new Dimension(512, 512), world.getDimension());
    }
}