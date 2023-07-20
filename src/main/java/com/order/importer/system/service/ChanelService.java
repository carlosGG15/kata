package com.order.importer.system.service;

import com.order.importer.system.entity.Chanel;
import com.order.importer.system.repository.ChanelRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ChanelService {

    private final ChanelRepository chanelRepository;

    public ChanelService(ChanelRepository chanelRepository) {
        this.chanelRepository = chanelRepository;
    }

    public Chanel importChanel(final String name) {
        final List<Chanel> channels = chanelRepository.findByName(name);

        if (channels.isEmpty()) {
            Chanel chanel = new Chanel();
            chanel.setName(name);
            return chanelRepository.save(chanel);
        }

        return channels.stream().findFirst().get();
    }
}
