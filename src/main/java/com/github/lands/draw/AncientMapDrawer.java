package com.github.lands.draw;

import com.github.lands.Dimension;
import com.github.lands.World;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyTuple;
import org.python.util.PythonInterpreter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigInteger;

/**
 * @author ftomassetti
 */
public class AncientMapDrawer {

    private static int toInt(Object pyInt)
    {
        if (pyInt instanceof Integer) {
            return (Integer)pyInt;
        } else if (pyInt instanceof BigInteger) {
            return ((BigInteger)pyInt).intValue();
        } else {
            throw new RuntimeException("Unexpected instance of "+pyInt.getClass().getCanonicalName());
        }
    }

    private static Color toColor(PyTuple pyColor)
    {
        return new Color(toInt(pyColor.get(0)), toInt(pyColor.get(1)), toInt(pyColor.get(2)));
    }

    private static BufferedImage toJavaImage(Dimension imageDimension, PyObject pythonImage) {
        BufferedImage image = new BufferedImage(imageDimension.getWidth(), imageDimension.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        for (int x=0; x<imageDimension.getWidth(); x++){
            for (int y=0; y<imageDimension.getHeight(); y++){
                PyTuple tuple = new PyTuple(new PyInteger(x), new PyInteger(y));
                PyTuple pixelValue = (PyTuple)pythonImage.__getitem__(tuple);
                Color color = toColor(pixelValue);
                g.setColor(color);
                g.fillRect(x, y, 1, 1);
            }
        }
        return image;
    }

    public static BufferedImage drawAncientMap(World world) {
        PythonInterpreter interpreter = new PythonInterpreter();

        PyObject pythonWorld = world.asPythonObject();

        interpreter.set("world", pythonWorld);
        interpreter.exec("import sys");
        interpreter.exec("sys.path.append(\"python/lands/lands\") ");
        interpreter.exec("from lands.drawing_functions import *");
        interpreter.exec("pixels = {}");
        interpreter.exec("draw_oldmap_on_pixels(world, pixels)");

        return toJavaImage(world.getDimension(), interpreter.get("pixels"));
    }

}
