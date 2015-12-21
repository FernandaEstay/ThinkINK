package service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class FileUpload {
	
	//Maneja las imagenes en la url Think-INK/rest/fileupload
	//la parte de /rest/ esta en el archivo web.xml
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("fileupload")
    public Response doUpload(@Context HttpServletRequest request) {
        JsonArrayBuilder array = Json.createArrayBuilder();
        try {
        	
            for (Part part : request.getParts()) {
                String name = null;
                long size = 0;
                try {
                	//Si es una imagen
                	if(part.getContentType().toLowerCase()
                            .startsWith("multipart/") || part.getContentType().toLowerCase()
                            .startsWith("image")){
                		InputStream inputStream = part.getInputStream();
                		//Se selecciona el directorio donde se guardan las imagenes
                		File f = new File(System.getProperty("user.home")+"/ImagenesServer/");
                		// Crear
                		f.mkdirs();
                		//Archivo de salida (Falta cambiar nombre e ingresar a base de datos)
                		f = new File(System.getProperty("user.home")+"/ImagenesServer/imagenn.jpg");
                        FileOutputStream outputStream = new FileOutputStream(f);

    	        		int read = 0;
    	        		byte[] bytes = new byte[1024];
    	
    	        		while ((read = inputStream.read(bytes)) != -1) {
    	        			outputStream.write(bytes, 0, read);
    	        		}
    	        		outputStream.close();
            		//Si es un dato adicional
                	}else if(part.getContentType() != null){
                		name = getSubmittedFileName(part);
                        size = part.getSize();
                        
    	        		
                        array.add(addFile(name, size, "anId"));
                        part.delete();
                	}
                	
                //En caso de excepcion devuelve un json con el mensaje
                } catch (Exception e) {
                	
                    array.add(addError(e.getMessage(), 312, "ERROR"));
                }
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

        JsonObject ret = Json.createObjectBuilder().add("files", array).build();
        return Response.status(201).entity(ret).build();
    }
    //Funcion auxiliar
	private static String getSubmittedFileName(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}
    private JsonObjectBuilder addFile(String name, long size, String url) {
        return Json.createObjectBuilder().add("name", name).add("size", size)
                .add("lid", url);
    }

    private JsonObjectBuilder addError(String name, long size, String error) {
        return Json.createObjectBuilder().add("name", name).add("size", size)
                .add("error", error);
    }

}