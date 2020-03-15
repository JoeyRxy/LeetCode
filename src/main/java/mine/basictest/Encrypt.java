package mine.basictest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Encrypt
 */
public class Encrypt {
    private static final byte[] key = { 31, 79, 113, 97, 77 };

    private static final int bufSize = 1024;

    private static void move(File from, File to) throws IOException {
        FileInputStream in = new FileInputStream(from);
        FileOutputStream os = new FileOutputStream(to);
        int len;
        byte[] b = new byte[bufSize];
        while ((len = in.read(b)) != -1) {
            os.write(b, 0, len);
        }
        in.close();
        os.close();
        from.delete();
    }

    public static void encrypt(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        File tmp = new File(file.getParent(), UUID.randomUUID() + file.getName());
        FileOutputStream os = new FileOutputStream(tmp);
        int c;
        byte[] b = new byte[bufSize];
        while ((c = in.read(b)) != -1) {
            for (int i = 0; i < c; i++) {
                for (int j = 0; j < key.length; j++) {
                    b[i] ^= key[j];
                }
            }
            os.write(b, 0, c);
        }
        in.close();
        os.close();
        move(tmp, file);
    }

    public static void decrypt(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        File tmp = new File(file.getParent(), UUID.randomUUID() + file.getName());
        FileOutputStream os = new FileOutputStream(tmp);

        int c;
        byte[] b = new byte[bufSize];
        while ((c = in.read(b)) != -1) {
            for (int i = 0; i < c; i++) {
                for (int j = 0; j < key.length; j++) {
                    b[i] ^= key[j];
                }
            }
            os.write(b, 0, c);
        }
        in.close();
        os.close();
        move(tmp, file);
    }

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/1.txt");
        encrypt(file);
        decrypt(file);
    }
}