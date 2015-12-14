package ru.vmakarenko.services;

import ru.vmakarenko.dao.FileContentsEntryDao;
import ru.vmakarenko.dao.FileEntryDao;
import ru.vmakarenko.dto.common.FileEntryDto;
import ru.vmakarenko.entities.common.files.FileContentsEntry;
import ru.vmakarenko.entities.common.files.FileEntry;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class FileService {
    @Inject
    private FileEntryDao dao;

    @Inject
    private FileContentsEntryDao fceDao;

    @Inject
    private MapperService mapperService;

    public FileEntryDto download(UUID id){
        FileEntry fileEntry = dao.find(id);
        FileEntryDto dto = mapperService.map(fileEntry, FileEntryDto.class);
        dto.setContent(getContent(fileEntry.getContentId()));
        return dto;
    }

    public UUID upload(String filename, String extension, String contentType, byte[] content){
        FileEntry fileEntry = new FileEntry();
        fileEntry.setFilename(filename);
        fileEntry.setExtension(extension);
        fileEntry.setContentType(contentType);
        FileContentsEntry fce = new FileContentsEntry();
        fce.setContent(content);
        fce = fceDao.insert(fce);
        fileEntry.setContentId(fce.getId());
        return dao.insert(fileEntry).getId();
    }

    private byte[] getContent(UUID contentId){
        return fceDao.find(contentId).getContent();
    }
}
