package mine.annotation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * CoreToolProcessor
 */
@Deprecated
public class CoreToolProcessor {
    private List<Class<?>> list;

    public CoreToolProcessor(File file) {
        list = new ArrayList<>();
        String fileName = file.getName();
        // dfs(file);
    }

    // private void dfs(File file) {
    // if (file.isDirectory()) {
    // File[] listFiles = file.listFiles();
    // String fileName;
    // for (File f : listFiles) {
    // path.push(item)
    // dfs(f);
    // }
    // } else {

    // }
    // }

    private void process(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        CoreTool annotation = clazz.getAnnotation(CoreTool.class);
    }
}