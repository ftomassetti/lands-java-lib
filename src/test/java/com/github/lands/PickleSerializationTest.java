package com.github.lands;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class PickleSerializationTest {

    private World w77;

    @Before
    public void setUp() throws IOException, IncorrectFileException {
        File file = new File("examples-worlds/seed_77.world");
        w77 = PickleSerialization.loadWorld(file);
    }

    @Test
    public void canLoadWorldWithoutErrors() throws IOException, IncorrectFileException {
        File file = new File("examples-worlds/seed_77.world");
        World world = PickleSerialization.loadWorld(file);
    }

    @Test
    public void nameIsLoaded() throws IOException, IncorrectFileException {
        assertEquals("seed_77", w77.getName());
    }

    @Test
    public void dimensionIsLoaded() throws IOException, IncorrectFileException {
        assertEquals(new Dimension(512, 512), w77.getDimension());
    }

    @Test
    public void temperatureIsLoaded() throws IOException, IncorrectFileException {
        assertNotNull(w77.getTemperature());
    }

    @Test
    public void temperatureThresholdsAreLoaded() throws IOException, IncorrectFileException {
        assertNotNull(w77.getTemperature().thresholds());
    }

    @Test
    public void humidityIsLoaded() throws IOException, IncorrectFileException {
        assertNotNull(w77.getHumidity());
    }

    @Test
    public void humidityQuantilesAreLoaded() throws IOException, IncorrectFileException {
        assertNotNull(w77.getHumidity().quantiles());
    }

}