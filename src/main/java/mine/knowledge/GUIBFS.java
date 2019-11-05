package mine.knowledge;

import java.util.List;

import javax.swing.*;

/**
 * UIBFS
 * <p>
 * 按层显示多叉树
 */
public class GUIBFS extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public GUIBFS() {

    }

    Node root;

    public void bfs() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                GUIBFS uibfs = new GUIBFS();
                uibfs.setTitle("BFS with GUI");
                uibfs.setDefaultCloseOperation(EXIT_ON_CLOSE);
                uibfs.setSize(900, 1000);
                uibfs.setVisible(true);
            }
        });
    }
}

class Node {
    Node p;
    List<Node> children;
}