package ru.vmakarenko.rest;


import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dto.common.SectionDto;
import ru.vmakarenko.services.FileService;
import ru.vmakarenko.services.SectionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Path("/private/files")
@Consumes("application/json")
@Produces("application/json")
public class FileResource {
    @Inject
    private FileService service;


    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @POST
    public Response upload(@QueryParam("filename") String filename, MultipartFormDataInput input) throws IOException {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");
        for (InputPart inputPart : inputParts) {
            try {
                if(filename == null){
                    filename = "file.doc";
                }
                int indexOf = filename.lastIndexOf(".");
                service.upload(filename.substring(0, indexOf), filename.substring(indexOf + 1, filename.length()), IOUtils.toByteArray(inputPart.getBody(InputStream.class,
                        null)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Response.ok().build();

    }

    @GET
    public Response download(@QueryParam("id") UUID id) {
        service.download(id);
        return Response.ok().build();

    }
}
