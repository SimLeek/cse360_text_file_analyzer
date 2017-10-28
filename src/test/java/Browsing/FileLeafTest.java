package Browsing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FileLeafTest {
    private FileLeaf leaf;

    @BeforeEach
    void setUp()
            throws IOException
    {
        File test_file = File.createTempFile("test", "file");
        leaf = new FileLeaf(test_file);
    }

    @Test
    void testToString() {
        String leaf_str = leaf.toString();
        assertTrue(leaf_str.contains("test"));
    }

}