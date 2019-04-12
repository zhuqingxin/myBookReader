import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

public class PdfReader extends JFrame{

    //当前文件字符数组
    private byte[] bytes = null ;
    //文件名称
    private String fileName ;
    //当前pdf文件
    private PDFFile pdfFile ;

    public void initFrame(String title) throws Exception{

        JFrame rootJFrame = new JFrame(title);
        Container container = rootJFrame.getContentPane();

        JMenuItem openfile = new JMenuItem("openfile");
        JScrollPane contentPanel = new JScrollPane();

        container.add(openfile);
        container.add(contentPanel);

        readFile();

        PDFPage pdfPage = pdfFile.getPage(2);


        rootJFrame.setSize(800,600);
        rootJFrame.setVisible(true);
        rootJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }

    public void readFile(){
        try{
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(fileName));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int b ;
            while ((b = inputStream.read()) != -1){
                outputStream.write(b);
            }
            this.bytes = outputStream.toByteArray();
            pdfFile = new PDFFile(ByteBuffer.wrap(this.bytes));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        new PdfReader().initFrame("My Pdf Reader");
    }


}
