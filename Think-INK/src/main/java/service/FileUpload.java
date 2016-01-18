package service;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ejb.GaleriaEJBLocal;
import ejb.UsuarioEJB;
import ejb.UsuarioEJBLocal;
import facade.FotoEJBFacade;
import facade.GaleriaEJBFacade;
import facade.UsuarioEJBFacade;
import models.Foto;
import models.Galeria;
import models.Usuario;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

@Path("/")
public class FileUpload {


	@EJB
	UsuarioEJBLocal usuarioEJB;
	@EJB
	UsuarioEJBFacade userFacade;
	@EJB
	FotoEJBFacade fotoFacade;
	@EJB
	GaleriaEJBFacade galeriaFacade;
	@EJB
	GaleriaEJBLocal galeriaEJB;
	//Maneja las imagenes en la url Think-INK/rest/fileupload
	//la parte de /rest/ esta en el archivo web.xml
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("fileupload")
    public Response doUpload(@Context HttpServletRequest request) {
        JsonArrayBuilder array = Json.createArrayBuilder();
		JsonArrayBuilder jsonArrBuilder = Json.createArrayBuilder();
		JsonObject json = null;
        try {
        	Date fecha = new Date();
            String direccion = "/ImagenesServer/"+fecha.getTime() +".jpg";
						String direccionResized = "/ImagenesServer/"+fecha.getTime() +"Resized.jpg";
            for (Part part : request.getParts()) {

                String name = null;
                long size = 0;
                try {
                	//Si es una imagen
                	if(part.getContentType() != null && (
                			part.getContentType().toLowerCase()
                            .startsWith("multipart/") || part.getContentType().toLowerCase()
                            .startsWith("image"))){
              		InputStream inputStream = part.getInputStream();
              		//Se selecciona el directorio donde se guardan las imagenes
              		File f = new File(System.getProperty("user.home")+"/ImagenesServer/");
              		// Crear
              		f.mkdirs();

					//Se transforma el inputStream a una "imagen"
					BufferedImage imBuff = ImageIO.read(inputStream);
					f = new File(System.getProperty("user.home")+direccion);
							FileOutputStream outputStream = new FileOutputStream(f);
					ImageIO.write(imBuff, "JPG", outputStream);
					outputStream.close();

					BufferedImage dest = new BufferedImage((800*imBuff.getWidth())/imBuff.getHeight(), 800 , BufferedImage.TYPE_INT_RGB);
					Graphics2D g = dest.createGraphics();

					g.drawImage(imBuff,0,0,(800*imBuff.getWidth())/imBuff.getHeight(),800,null);

					f = new File(System.getProperty("user.home")+direccionResized);
							FileOutputStream outputStream2 = new FileOutputStream(f);
					ImageIO.write(dest, "JPG", outputStream2);
					outputStream2.close();


    	        		/*int read = 0;
    	        		byte[] bytes = new byte[1024];

    	        		while ((read = resizeImg.read(bytes)) != -1) {
    	        			outputStream.write(bytes, 0, read);
    	        		}
    	        		outputStream.close();
									*/
                		name = getSubmittedFileName(part);
                        size = part.getSize();
                        //part.delete();
            		//Si es un dato adicional
                	}else{
                		//Leyendo valor
                		BufferedReader streamReader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
                		StringBuilder responseStrBuilder = new StringBuilder();

                		String inputStr;
                		while ((inputStr = streamReader.readLine()) != null)
                		    responseStrBuilder.append(inputStr);
                		//nombre valor
                		String nombreVar = part.getName();
                		String value = responseStrBuilder.toString();
                		Usuario u= usuarioEJB.obtenerUsuario(Integer.parseInt(value));
                		array.add(Json.createObjectBuilder().add("name", value));

                		for(Galeria g:u.getGaleriaCollection()){
                            array.add(Json.createObjectBuilder().add("name", g.getTipo()));

                			if(g.getTipo().equals("SUBIDA")){

                				Foto f = new Foto();
                				f.setImagen(System.getProperty("user.home")+direccion);
												f.setImagenResized(System.getProperty("user.home")+direccionResized);
                				f.setFechaSubida(fecha);
                				f.setIdGaleria(g);
                				g.getFotoCollection().add(f);
                				
                				f.setIdUsuario(u);
                				galeriaFacade.edit(g);
                				//fotoFacade.create(f);
                				List<Foto> fotosGaleria =  galeriaEJB.obtenerGaleria(g);
                				Collections.reverse(fotosGaleria);
                				f = fotosGaleria.get(0);
								json = Json.createObjectBuilder().add("idFoto", f.getIdFoto())
                						.add("fecha", f.getFechaSubida().toString())
                						.add("idUsuario", f.getIdUsuario().getIdUsuario())
                						.add("nombre", f.getIdUsuario().getNombreUsuario())
                						.add("cantidadMegusta", f.getCantMeGusta())
                						.add("likeAble", true).build();
                			}
                		}

            			//Para postman
                        array.add(Json.createObjectBuilder().add("name", nombreVar).add("value", value));

                        part.delete();
                	}

                //En caso de excepcion devuelve un json con el mensaje
                } catch (Exception e) {
                	e.printStackTrace();
                	array.add(Json.createObjectBuilder().add("name", e.getStackTrace().toString()));
                    //array.add(addError(e.getMessage(), 312, "ERROR"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch(ServletException e1){
        	e1.printStackTrace();
        }

        JsonObject ret = Json.createObjectBuilder().add("argumentos", array).build();
        return Response.status(201).entity(json).build();
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

}
