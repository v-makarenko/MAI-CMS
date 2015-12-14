package ru.vmakarenko.rest;


import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dto.common.FileEntryDto;
import ru.vmakarenko.dto.common.SectionDto;
import ru.vmakarenko.services.FileService;
import ru.vmakarenko.services.SectionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
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
        UUID resultId = null;
        for (InputPart inputPart : inputParts) {
            try {
                filename = getFileName(inputPart.getHeaders());
                if(filename == null){
                    filename = "file.doc";
                }
                int indexOf = filename.lastIndexOf(".");
                resultId = service.upload(filename.substring(0, indexOf),
                        filename.substring(indexOf + 1, filename.length()), getContentType(inputPart.getHeaders()),
                        IOUtils.toByteArray(inputPart.getBody(InputStream.class,
                        null)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Response.ok(
                (resultId == null
                        ? RestResponse.createError("-1").errMsg("File not uploaded")
                : RestResponse.createOk().data(resultId))).build();

    }

    @GET
    public Response download(@QueryParam("id") UUID id) {
        FileEntryDto dto = service
                .download(id);

        return Response.ok(dto.getContent())
                .header("Content-Disposition", "attachment; filename="
                        + dto.getFilename() + "." + dto.getExtension())
        .build();

    }

    private String getFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {

                String[] name = filename.split("=");

                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }


    private String getContentType(MultivaluedMap<String, String> header) {
        return header.getFirst("Content-Type").split(";")[0];
    }
}
