package com.github.lands;

import com.github.lands.draw.AncientMapDrawer;
import com.github.langgen.LanguageSamples;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author ftomassetti
 */
public class AncientMapDrawerTest {

    private World world;

    @Before
    public void setUp() throws IOException, IncorrectFileException {
        File file = new File("examples-worlds/seed_77.world");
        this.world = PickleSerialization.loadWorld(file);
    }

    @Test
    public void drawAncientMap() throws IOException {
        BufferedImage image = AncientMapDrawer.drawAncientMap(this.world, 2);
        //File outputfile = new File("saved.png");
        //ImageIO.write(image, "png", outputfile);
        assertNotNull(image);
    }
}
