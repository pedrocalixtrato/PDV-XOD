package dc.visao.framework.component.upload;

import java.io.File;

public interface UploadFileListner {

	public void handleFile(File file, String fileName, String mimeType, long length);
}
