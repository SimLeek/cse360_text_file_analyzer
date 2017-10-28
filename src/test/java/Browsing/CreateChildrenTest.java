package Browsing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CreateChildrenTest {
    private CreateChildren spawner;
    private DefaultMutableTreeNode root;

    @BeforeEach
    void setUp()
            throws IOException
    {
        File test_file = new File(".");
        root = new DefaultMutableTreeNode();
        spawner = new CreateChildren(test_file, root);
    }

    @Test
    void testRun() {
        spawner.run();
        assertTrue(root.getDepth()>1);
    }

}