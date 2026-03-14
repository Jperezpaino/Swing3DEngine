package es.noa.rad.test.aplication.component.tree.demo;

import java.io.File;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import static javax.management.Query.value;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FileSysBrowser extends JFrame {

    public JTree fileTree;
    private FileSystemModel fileSystemModel;
    static String REPO = "C:/Noa/Workspace Photon/.metadata";
    static String[] REPO_PATH = REPO.split("/", -1);
    static int repoPathIdx = 0;
    private JTextArea fileDetailsTextArea = new JTextArea();

    public FileSysBrowser(String directory) {
        System.out.println(Arrays.toString(REPO_PATH));
        setTitle("FileSysBrowser");
        fileDetailsTextArea.setEditable(false);
        fileSystemModel = new FileSystemModel(new File(directory));
        fileTree = new JTree(fileSystemModel);
        fileTree.setEditable(true);
        fileTree.addTreeSelectionListener(new TreeSelectionListener() {
            
            public void valueChanged(TreeSelectionEvent event) {
//                System.out.println(fileTree.getLastSelectedPathComponent());
//                File file = (File) fileTree.getLastSelectedPathComponent();
//                fileDetailsTextArea.setText(getFileDetails(file));
            }
        });
        getContentPane().add(new JScrollPane(fileTree));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(640, 480);
        setVisible(true);
    }

    private DefaultMutableTreeNode buildNodeFromString(String path) {
        String[] s = path.split("/");
        File myFilePath = new File(path);

        DefaultMutableTreeNode node, lastNode = null, root = null;
        for (String str : s) {
            if (str.indexOf(":") != -1) {
                str = str.substring(0, str.indexOf(":"));
            }
            node = new DefaultMutableTreeNode(str);
            if (root == null) {
                root = node;
            }
            if (lastNode != null) {
                lastNode.add(node);
            }
            lastNode = node;
        }
        return root;
    }


    public static void main(String args[]) {

        FileSysBrowser fileSysBrowser = new FileSysBrowser("/");

        JTree jt = fileSysBrowser.fileTree;

//        jt.expandRow(21);
//        jt.expandRow(32);
//        TreePath pathForRow = jt.getPathForRow(4);
//        System.out.println(pathForRow);        
        DefaultMutableTreeNode node = fileSysBrowser.buildNodeFromString("repo/dinesh/trunk");

        TreePath pathForRow = new TreePath(node.getLastLeaf().getPath());
        System.out.println("pathForRow : " + pathForRow);
        jt.setSelectionPath(pathForRow);
    }
}

class FileSystemModel implements TreeModel {

    private File root;

    private final Vector listeners = new Vector();

    public FileSystemModel(File rootDirectory) {
        root = rootDirectory;
    }

    
    public Object getRoot() {
        return root;
    }

    
    public Object getChild(Object parent, int index) {
        File directory = (File) parent;
        String[] children = directory.list();
        Arrays.sort(children);
        String currentChild = children[index];
        return new TreeFile(directory, currentChild);
    }

    
    public int getChildCount(Object parent) {        
        File file = (File) parent;
        if (file.isDirectory()) {
            String[] fileList = file.list();
            if (fileList != null) {
                return file.list().length;
            }
        }
        return 0;
    }

    
    public boolean isLeaf(Object node) {
        if (node.getClass() == DefaultMutableTreeNode.class) {
            DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) node;
            System.out.println("chidcount ->" + dmtn.getChildCount());
            System.out.println(dmtn.getChildCount()==0);
            return dmtn.getChildCount() == 0;
        } else {
            File file = (File) node;
            return file.isFile();
        }
    }

    
    public int getIndexOfChild(Object parent, Object child) {
        File directory = (File) parent;
        File file = (File) child;
        String[] children = directory.list();
        for (int i = 0; i < children.length; i++) {
            if (file.getName().equals(children[i])) {
                return i;
            }
        }
        return -1;

    }

    
    public void valueForPathChanged(TreePath path, Object value) {
        File oldFile = (File) path.getLastPathComponent();
        String fileParentPath = oldFile.getParent();
        String newFileName = (String) value;
        File targetFile = new File(fileParentPath, newFileName);
        oldFile.renameTo(targetFile);
        File parent = new File(fileParentPath);
        int[] changedChildrenIndices = {getIndexOfChild(parent, targetFile)};
        Object[] changedChildren = {targetFile};
        fireTreeNodesChanged(path.getParentPath(), changedChildrenIndices, changedChildren);

    }

    private void fireTreeNodesChanged(TreePath parentPath, int[] indices, Object[] children) {
        TreeModelEvent event = new TreeModelEvent(this, parentPath, indices, children);
        Iterator iterator = listeners.iterator();
        TreeModelListener listener = null;
        while (iterator.hasNext()) {
            listener = (TreeModelListener) iterator.next();
            listener.treeNodesChanged(event);
        }
    }

    
    public void addTreeModelListener(TreeModelListener listener) {
        listeners.add(listener);
    }

    
    public void removeTreeModelListener(TreeModelListener listener) {
        listeners.remove(listener);
    }

    private class TreeFile extends File {

        public TreeFile(File parent, String child) {
            super(parent, child);
        }

        
        public String toString() {
            return getName();
        }
    }
}