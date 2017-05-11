package dc.visao.framework.component.export;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

class DeletingFileInputStream extends FileInputStream implements Serializable {

    /** The file. */
    protected File file = null;

    /**
     * Instantiates a new deleting file input stream.
     * 
     * @param file
     *            the file
     * @throws FileNotFoundException
     *             the file not found exception
     */
    public DeletingFileInputStream(final File file) throws FileNotFoundException {
        super(file);
        this.file = file;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.FileInputStream#close()
     */
    @Override
    public void close() throws IOException {
        super.close();
        file.delete();
    }

}
