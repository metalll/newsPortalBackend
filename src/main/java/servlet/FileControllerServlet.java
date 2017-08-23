package servlet;

import com.google.gson.Gson;
import model.ImageResponceStructure;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;
/**
 * Created by nsd on 23.08.17.
 */
@WebServlet(name = "File",urlPatterns = "/API/File")
public class FileControllerServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "";
    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB


    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");


        boolean accepted = false;
        Map<String,String[]> paramMap = req.getParameterMap();
        String url=null;

        try{

            url = paramMap.get("url")[0];
        }catch (Exception e){



        }



       accepted = true;

        if(url!=null&&accepted){


            HttpClient httpclient = new DefaultHttpClient();

            HttpGet httpPost = new HttpGet(url);
            httpPost.addHeader(new Header() {
                public String getName() {
                   return "Content-Type";
                }

                public String getValue() {
                    return "image/png;charset=UTF-8";
                }

                public HeaderElement[] getElements() throws ParseException {
                    return new HeaderElement[0];
                }
            });



            final HttpResponse response = httpclient.execute(httpPost, new ResponseHandler<HttpResponse>() {
                public HttpResponse handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                    HttpEntity entity = httpResponse.getEntity();
                    InputStream inputStream;
                    OutputStream outputStream;

                    if (entity != null) {
                        long len = entity.getContentLength();
                        inputStream = entity.getContent();

                        outputStream = resp.getOutputStream();

                        int read = 0;
                        byte[] bytes = new byte[1024];

                        while ((read = inputStream.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, read);
                        }



                        outputStream.flush();
                        outputStream.close();
                    }

                    return httpResponse;
                }
            });

        }

    }

    @Override // putFile
    protected void doPost(HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        File retEndFile = null;

        req.setCharacterEncoding("UTF-8");
        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(req)) {
            // if not, we stop here
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            PrintWriter writer = resp.getWriter();
            writer.println("-1 Error: Form must has enctype=multipart/form-data.");
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
        //  String uploadPath = UPLOAD_DIRECTORY;
        // creates the directory if it does not exist
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdir();
//        }

        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(req);
            String GenFilePath = "";
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {

                        String fileName = new File(item.getName()).getName();
                        String filePath = System.getProperty("java.io.tmpdir") + File.separator + fileName;

                        retEndFile = new File(filePath);
                        retEndFile.setReadable(true, false);
                        retEndFile.setWritable(true, false);
                        // saves the file on disk
                        item.write(retEndFile);
                        GenFilePath = retEndFile.getCanonicalPath();
                    }

                }
            }
        } catch (Exception e) {


        }


        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://datadoctrado-sviasy.rhcloud.com/file");

        FileBody uploadFilePart = new FileBody(retEndFile);
        MultipartEntity reqEntity = new MultipartEntity();
        reqEntity.addPart("upload-file", uploadFilePart);
        httpPost.setEntity(reqEntity);


        HttpResponse response = httpclient.execute(httpPost, new ResponseHandler<HttpResponse>() {
            public HttpResponse handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                PrintWriter out = resp.getWriter();
                resp.setStatus(HttpServletResponse.SC_OK);


                String strResp = EntityUtils.toString(httpResponse.getEntity());

                ImageResponceStructure respImg = new ImageResponceStructure();
                respImg.location = strResp;
                Gson gson = new Gson();

                out.write(gson.toJson(respImg));
                out.flush();
                out.close();


                return httpResponse;
            }
        });


    }
}

