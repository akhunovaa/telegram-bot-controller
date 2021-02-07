package com.botmasterzzz.controller.service;

import com.botmasterzzz.controller.dto.KinoDTO;

import java.util.List;

public interface KinoService {

    List<KinoDTO> searchAndMoviesList(String query);

}
