package services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.vmakarenko.dto.common.MessageDto;
import ru.vmakarenko.entities.messages.InnerMessage;
import ru.vmakarenko.entities.users.User;
import ru.vmakarenko.services.MapperService;

import java.util.UUID;

/**
 * Created by VMakarenko on 7/21/2015.
 */
public class MapperServiceTest {
    MapperService service = new MapperService();
    @Before
    public void before(){
        service.init();
    }

    @Test
    public void messageMappingTestFromDto() {
        MessageDto dto = new MessageDto();
        dto.setFromName("Name");
        dto.setFrom(UUID.randomUUID());
        dto.setTo(UUID.randomUUID());
        dto.setText("text");
        InnerMessage msg = service.map(dto, InnerMessage.class);
        Assert.assertEquals(msg.getText(), dto.getText());
        Assert.assertNull(msg.getTo());
        Assert.assertNull(msg.getFrom());
    }

    @Test
    public void messageMappingTestToDto() {
        InnerMessage msg = new InnerMessage();
        User from = new User();
        User to = new User();
        from.setId(UUID.randomUUID());
        to.setId(UUID.randomUUID());
        from.setSurname("Фамилия");
        from.setName("Имя");
        msg.setFrom(from);
        msg.setTo(to);
        msg.setText("Text");

        MessageDto dto = service.map(msg, MessageDto.class);
        Assert.assertEquals(msg.getText(), dto.getText());
        Assert.assertEquals(msg.getTo().getId(), dto.getTo());
        Assert.assertEquals(msg.getFrom().getId(), dto.getFrom());
        Assert.assertEquals(msg.getFrom().getSurname() + " " + msg.getFrom().getFirstName(),
                dto.getFromName());
    }
}
