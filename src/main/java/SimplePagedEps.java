import org.apache.xmlgraphics.java2d.ps.EPSDocumentGraphics2D;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 */
public class SimplePagedEps {

    public static void main(String[] args) throws IOException {
        int pages = 5;
        if ( args.length > 0 ){
            pages = Integer.parseInt(args[0]);
        }
        for(int i = 1; i <= pages; i ++){
            writeOutPagedEps(i);
        }
    }

    private static void writeOutPagedEps(int pages) throws IOException {
        EPSDocumentGraphics2D g2d = new EPSDocumentGraphics2D(false);
        g2d.setGraphicContext(new org.apache.xmlgraphics.java2d.GraphicContext());
        File f = new File("simplePaged-" + pages + ".eps");
        OutputStream byteOut = new FileOutputStream(f);

        Font font = new Font("serif", Font.BOLD, 80);

        g2d.setupDocument(byteOut, 400, 200);

        for (int page = 1 ; page <= pages ; page ++ ) {
            g2d.setColor(Color.BLACK);
            g2d.setFont(font);
            g2d.drawString("Page " + page, 100, 100);
            g2d.nextPage();
        }
        g2d.finish();
        byteOut.close();
        System.out.println("Written :" + f.getCanonicalPath());
    }
}
