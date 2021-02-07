package com.botmasterzzz.controller.dao;

import com.botmasterzzz.controller.entity.MediaCommentsDataEntity;
import com.botmasterzzz.controller.entity.TelegramBotUserEntity;
import com.botmasterzzz.controller.entity.TelegramUserMediaEntity;

import java.util.List;

public interface MediaCommentsDataDAO {

    void commentAdd(MediaCommentsDataEntity mediaCommentsDataEntity);

    List<MediaCommentsDataEntity> getCommentsForMedia(Long mediaFileId, int offset, int limit);

    List<TelegramBotUserEntity> getMediaCommentedUsers(TelegramUserMediaEntity telegramUserMediaEntity);
}
