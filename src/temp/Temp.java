package temp;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Temp {

    public static void main(String[] args) {
        byte[] bytes = new byte[] {-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 5, 28};
        bytesToBitString(bytes);

    }

    /**
     * 压缩的字节数组，还原成二进制字符串
     * @param huffmanBytes
     * @return
     */
    private static String bytesToBitString(byte[] huffmanBytes) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< huffmanBytes.length-2; i++) {
            int b = huffmanBytes[i] | 256;
            String s = Integer.toBinaryString(b);
            sb.append(s.substring(s.length() - 8));
        }
        int lastByteLength = huffmanBytes[huffmanBytes.length - 2];
        String lastByte = Integer.toBinaryString(huffmanBytes[huffmanBytes.length-1]);
        for (int i=0; i<(lastByteLength-lastByte.length()); i++ ) {
            lastByte = "0" + lastByte;
        }
        sb.append(lastByte);
        return sb.toString();
    }

    private static Byte[] decode(byte[] huffmanBytes, Map<Byte, String> huffmanCodes) {
        //压缩的字节数组，还原成二进制字符串
        String huffmanStr = bytesToBitString(huffmanBytes);

        //赫夫曼编码的键值对调
        Map<String, Byte> map = new HashMap<>();
        for(Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        StringBuilder sbd = new StringBuilder();
        List<Byte> list = new ArrayList();
        for(int i=0; i<huffmanStr.length(); i++) {
            sbd.append(huffmanStr.substring(i, i + 1));
            Byte aByte = map.get(sbd.toString());
            if (aByte != null) {
                list.add(aByte);
                sbd = new StringBuilder();
            }
        }

        return list.toArray(new Byte[list.size()]);
    }

    /**
     * 压缩文件
     * @param srcFile 源文件
     * @param destFile 压缩后的文件
     */
    private static void zipFile(String srcFile, String destFile) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream ops = null;
        try{
            fis = new FileInputStream(srcFile);
            byte[] b = new byte[fis.available()];
            fis.read(b);

            //文件读取到的数组，进行赫夫曼编码压缩

            fos = new FileOutputStream(destFile);
            ops = new ObjectOutputStream(fos);
            //ops.writeObject();
            //ops.writeObject();
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
    }


    /**
     * 解压缩文件
     * @param srcFile
     * @param destFile
     */
    private static void unZipFile(String srcFile, String destFile) {
        try (FileInputStream fis = new FileInputStream(srcFile);
                ObjectInputStream ops = new ObjectInputStream(fis);
                FileOutputStream fos = new FileOutputStream(destFile)) {
            //ops.readObject();
            //ops.readObject();

            //fos.write();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
