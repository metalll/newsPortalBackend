package servlet;

import DBControllers.DBKeyCacheElem;
import model.KeyCacheElem;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by NSD on 06.03.17.
 */


@WebServlet(name = "FileController" , urlPatterns = {"/file"})
public class FileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // location to store file uploaded
    private static String UPLOAD_DIRECTORY = null;
    private static final String KEYNAME = "key";
    // upload settings

    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB


    //todo get File with File Name
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(UPLOAD_DIRECTORY == null){
            UPLOAD_DIRECTORY = "/app/" + "upload";
        }
        String key = req.getParameterMap().get(KEYNAME)[0];

        String path = "";
        path = DBKeyCacheElem.getInstance().getValueForKey(key);
        File file = new File(path);


        resp.setHeader("Content-Disposition", "inline; filename=\"..." + req.hashCode() + ":......+ "+ ".............-1 + " + " -1\".exe");
        resp.setHeader("Content-Type", getServletContext().getMimeType(file.getName()));
        resp.setHeader("Content-Length", String.valueOf(file.length()));
        if (!path.equals("")) {
            OutputStream out = resp.getOutputStream();
            FileInputStream in = new FileInputStream(path);
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);

            }
            in.close();
            out.flush();

        } else {

            OutputStream out = resp.getOutputStream();
            out.write(0);
            out.flush();

        }








    }

    //todo put File
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(UPLOAD_DIRECTORY == null){
            UPLOAD_DIRECTORY = "/app/" + "upload";}

        String PATH = null;
        String KEY = null;
        System.out.print("UPLOAD DIRECTORY ----------/ "+UPLOAD_DIRECTORY+" /-------");

        if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String uploadPath = UPLOAD_DIRECTORY;

        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {

                for(FileItem item : formItems){

                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;

                        File storeFile = new File(filePath);
                        storeFile.setReadable(true, false);
                        storeFile.setWritable(true, false);
                        // saves the file on disk
                        item.write(storeFile);
                        PATH = storeFile.getCanonicalPath();
                    }
                    else {
                        if(item.getFieldName().equals(KEYNAME)){
                            KEY = item.getString();
                        }
                    }


                }

                KeyCacheElem sharedElement = new KeyCacheElem();
                sharedElement.setKey(KEY);
                sharedElement.setValueOfKey(PATH);
                //System.out.print(sharedElement);
                DBKeyCacheElem.getInstance().AddOrUpdate(sharedElement);

                response.setStatus(HttpServletResponse.SC_OK);
                PrintWriter out = response.getWriter();
                out.write(String.valueOf(true));
                out.flush();
                out.close();


            }







        }
        catch (Exception e){

        }




    }
}
