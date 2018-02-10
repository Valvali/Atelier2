package org.api.boundary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.provider.Secured;

@Path("file")
@Api(value = "API RESTful")
public class FileResource {
    
    final String uploadDir = "/opt/jboss/";
    
    @GET
    @ApiOperation(value = "Récupère toutes une image précedement uploadée sur le serveur", notes = "format: /file/sha1sum.extension")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "File not found")})
    
    @Path("{fileName}")
    public Response getFile(@PathParam("fileName") String fileName) {
        File file;
        file = new File(uploadDir + fileName);
        if (! file.canRead()) {
            return Response.status(Response.Status.NOT_FOUND).entity("404 File not found").build();
        }
      
      return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
      .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" ) //optional
      .build();
    
    }
    
    @POST
    @Secured
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Upload une image sur le serveur")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response uploadFichier(MultipartFormDataInput input) {
        
        Map<String, List<InputPart>> formulaire = input.getFormDataMap();
        List<InputPart> inputParts = formulaire.get("file");
        
        String finalName = "";
        
        for (InputPart ip : inputParts) {
            MultivaluedMap<String, String> headers = ip.getHeaders();
            String fileName = getFileName(headers);
            String extension = "";

            int i = fileName.lastIndexOf('.');
            if (i > 0) {
                extension = fileName.substring(i+1);
            }
            
            try {
                InputStream is = ip.getBody(InputStream.class,null);
                BytesAndSha1 bytesAndSha1 = FileResource.toByteArray(is);
                finalName = bytesAndSha1.sha1 + '.' + extension;
                writeFile(bytesAndSha1.bytes,uploadDir + finalName);
            } catch (IOException | NoSuchAlgorithmException ex) {
                Logger.getLogger(FileResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        JsonObject output;
        output = Json.createObjectBuilder().add("url", "/file/" + finalName).build();
        return Response.status(Response.Status.CREATED).entity(output).build();
    }

    public static BytesAndSha1 toByteArray(InputStream is) throws IOException, NoSuchAlgorithmException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        BytesAndSha1 ret = new BytesAndSha1();
        try {
            byte[] b = new byte[4096];
            int n = 0;
            while ((n = is.read(b)) != -1) {
                output.write(b, 0, n);
                digest.update(b, 0, n);
            }
            ret.bytes = output.toByteArray();
            ret.sha1 = new HexBinaryAdapter().marshal(digest.digest());
            return ret;
        } finally {
            output.close();
        }
    }
    
    private void writeFile(byte[] contenu, String filename) throws IOException {
        
        File file = new File(filename);
        
        FileOutputStream fop = new FileOutputStream(file);

        fop.write(contenu);
        fop.flush();
        fop.close();
    }

    private String getFileName(MultivaluedMap<String, String> headers) {

        String[] contenuHeader = headers.getFirst("Content-Disposition").split(";");
        
        for (String filename : contenuHeader) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                return name[1].trim().replaceAll("\"", "");
            }
        }
        
        return "inconnu";
    }
    
    private static class BytesAndSha1 {
        public byte[] bytes;
        public String sha1;
    }

            
}
