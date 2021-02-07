package com.botmasterzzz.controller.service.impl;

import com.botmasterzzz.controller.core.robot.Sender;
import com.botmasterzzz.controller.dto.KinoDTO;
import com.botmasterzzz.controller.dto.SearchKinoDTO;
import com.botmasterzzz.controller.service.KinoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class KinoServiceImpl extends Sender implements KinoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KinoServiceImpl.class);

    @Value("${multipart.file.upload.path}")
    private String path;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Cacheable(value = "kino-media", key = "#query", unless = "#result == null")
    public List<KinoDTO> searchAndMoviesList(String query) {
        List<KinoDTO> kinoDTOList = new ArrayList<>();
        String scrapperHost = "https://api.kinopub.link/v1.1/autocomplete?query=" + query;
        String response = execute(scrapperHost);
        List<SearchKinoDTO> searchKinoDTOList = null;
        try {
            searchKinoDTOList = Arrays.asList(objectMapper.readValue(response, SearchKinoDTO[].class));
        } catch (JsonProcessingException jsonProcessingException) {
            LOGGER.error("Error with json data read from TikTOK response with params", jsonProcessingException);
        }
        if (searchKinoDTOList != null) {
            for (SearchKinoDTO searchKinoDTO : searchKinoDTOList) {
                KinoDTO kinoDTO = new KinoDTO();
                kinoDTO.setId(searchKinoDTO.getId());
                kinoDTO.setName(searchKinoDTO.getValue());
                kinoDTO.setType(searchKinoDTO.getType());
                kinoDTO.setDescription("https://kino.pub/item/view/" + searchKinoDTO.getId());
                //String kinoResponse = executeKino(detailGetLink, detailGetLink);

                //Document doc = Jsoup.parseBodyFragment(kinoResponse);
                //Element body = doc.body();
                //Elements elements = body.getElementsByClass("img-responsive item-poster-relative");
                kinoDTO.setPosterLInk("https://cdn.service-kp.com/poster/item/big/" + searchKinoDTO.getId() + ".jpg");
                //String description = body.getElementById("plot").text();
                //kinoDTO.setDescription(description);
                kinoDTOList.add(kinoDTO);
            }
        }
        return kinoDTOList;
    }
}
