package huffmancode;

import java.io.*;
import java.util.Map;

/**
 * 根据赫夫曼编码压缩/解压文件
 */
public class HuffmanFileZipUnZip {
    public static void main(String[] args) {
        //zipFile("C:\\Users\\yws\\Desktop\\sun.bmp", "C:\\Users\\yws\\Desktop\\sun.zip");
        unZipFile("C:\\Users\\yws\\Desktop\\sun.zip", "C:\\Users\\yws\\Desktop\\sun2.bmp");
    }


    /**
     * 压缩文件
     * @param srcFile 源文件
     * @param destFile 压缩后的文件
     */
    public static void zipFile(String srcFile, String destFile) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream ops = null;
        try{
            fis = new FileInputStream(srcFile);
            byte[] b = new byte[fis.available()];
            fis.read(b);

            //文件读取到的数组，进行赫夫曼编码压缩
            byte[] zipBytes = HuffmanCode.huffmanZip(b);

            //使用对象流输出
            fos = new FileOutputStream(destFile);
            ops = new ObjectOutputStream(fos);
            ops.writeObject(zipBytes);
            ops.writeObject(HuffmanCode.huffmanCodeMap);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
                fos.close();
                ops.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("压缩成功");
    }


    /**
     * 解压缩文件
     * @param srcFile
     * @param destFile
     */
    public static void unZipFile(String srcFile, String destFile) {
        try (FileInputStream fis = new FileInputStream(srcFile);
             ObjectInputStream ops = new ObjectInputStream(fis);
             FileOutputStream fos = new FileOutputStream(destFile)) {

            //对象流输入(压缩后的字节数组)
            byte[] zipBytes = (byte[]) ops.readObject();
            //对象流输入（赫夫曼编码表）
            Map<Byte, String> huffmanCodeMap = (Map<Byte, String>) ops.readObject();
            //进行解压处理
            Byte[] unzipBytes = HuffmanCode.decode(zipBytes, huffmanCodeMap);

            byte[] srcb = new byte[unzipBytes.length];
            int i=0;
            for(Byte b : unzipBytes) {
                srcb[i] = (byte) b;
                i++;
            }
            fos.write(srcb);

        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("解压成功");
    }
}
