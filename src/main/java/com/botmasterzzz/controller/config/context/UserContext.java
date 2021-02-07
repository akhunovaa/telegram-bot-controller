package com.botmasterzzz.controller.config.context;

import com.botmasterzzz.bot.api.impl.objects.Chat;
import com.botmasterzzz.bot.api.impl.objects.Update;
import com.botmasterzzz.bot.api.impl.objects.User;
import com.botmasterzzz.bot.api.impl.objects.replykeyboard.InlineKeyboardMarkup;
import com.botmasterzzz.controller.core.Language;
import com.botmasterzzz.controller.dto.CallBackData;
import com.botmasterzzz.controller.dto.ReceivedMediaFile;
import com.botmasterzzz.controller.dto.instagram.InstagramMediaDataDTO;
import com.botmasterzzz.controller.dto.youtube.YouTubeMediaDTO;
import com.botmasterzzz.controller.entity.TelegramUserMediaEntity;
import com.proto.instagram.LoginResponse;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class UserContext implements Serializable {

    private static final long serialVersionUID = 4L;

    List<ReceivedMediaFile> receivedMediaFileList;
    private Update update;
    private User user;
    private Chat chat;
    private InlineKeyboardMarkup inlineKeyboardMarkup;
    private CallBackData callBackData;
    private Long instanceId;
    private boolean remain;
    private boolean userBan;
    private boolean reset;
    private boolean info;
    private boolean commentRemain;
    private boolean queryAwait;
    private String attributeName;
    private String userForBan;
    private String chosenInlineMessageId;
    private String fromPageData;
    private int offs;
    private boolean anon;
    private long partId;
    private long todayPartId;
    private long cctualPartId;
    private long combinedPart;
    private long forUserId;
    private Long referralId;
    private Long mediaLinkId;
    private Language language;
    private String tag;
    private boolean tagRemain;
    private boolean tagInstRemain;
    private boolean youTubeQueryRemain;
    private boolean tiktokUrlRemain;
    private boolean kinoRemain;
    private boolean loading;
    private boolean instLoading;
    private boolean youTubeLoading;
    private boolean mailingSent;
    private boolean replyMarkupEditMailing;
    private boolean fromChannel;
    private boolean edit;
    private boolean nameEdit;
    private boolean captionEdit;
    private Long targetChannelForPosting;
    private LoginResponse loginResponse;
    private InstagramMediaDataDTO instagramMediaDataDTO;
    private List<YouTubeMediaDTO> youTubeMediaDTOList;
    private byte[] instagramMediaResponse;

    private boolean mailing;
    private int minCursor = 0;
    private boolean minCursorRemain;
    private int maxCursor = 12;
    private int currentCursor;
    private int currentInstCursor;
    private int currentYouTubeCursor;
    private String lastQuery;
    private boolean maxCursorRemain;
    private List<Integer> newForToday;
    private List<Integer> newForYesterday;
    private List<Integer> actual;
    private List<Integer> combined;
    private List<TelegramUserMediaEntity> telegramUserMediaEntityList;

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CallBackData getCallBackData() {
        return callBackData;
    }

    public void setCallBackData(CallBackData callBackData) {
        this.callBackData = callBackData;
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkup() {
        return inlineKeyboardMarkup;
    }

    public void setInlineKeyboardMarkup(InlineKeyboardMarkup inlineKeyboardMarkup) {
        this.inlineKeyboardMarkup = inlineKeyboardMarkup;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public boolean isRemain() {
        return remain;
    }

    public void setRemain(boolean remain) {
        this.remain = remain;
    }

    public long getPartId() {
        return partId;
    }

    public void setPartId(long partId) {
        this.partId = partId;
    }

    public boolean isAnon() {
        return anon;
    }

    public void setAnon(boolean anon) {
        this.anon = anon;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public boolean isCommentRemain() {
        return commentRemain;
    }

    public void setCommentRemain(boolean commentRemain) {
        this.commentRemain = commentRemain;
    }

    public long getForUserId() {
        return forUserId;
    }

    public void setForUserId(long forUserId) {
        this.forUserId = forUserId;
    }

    public String getFromPageData() {
        return fromPageData;
    }

    public void setFromPageData(String fromPageData) {
        this.fromPageData = fromPageData;
    }

    public int getOffs() {
        return offs;
    }

    public void setOffs(int offs) {
        this.offs = offs;
    }

    public Long getReferralId() {
        return referralId;
    }

    public void setReferralId(Long referralId) {
        this.referralId = referralId;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<Integer> getNewForToday() {
        return newForToday;
    }

    public void setNewForToday(List<Integer> newForToday) {
        this.newForToday = newForToday;
    }

    public List<Integer> getNewForYesterday() {
        return newForYesterday;
    }

    public void setNewForYesterday(List<Integer> newForYesterday) {
        this.newForYesterday = newForYesterday;
    }

    public List<Integer> getActual() {
        return actual;
    }

    public void setActual(List<Integer> actual) {
        this.actual = actual;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getMinCursor() {
        return minCursor;
    }

    public void setMinCursor(int minCursor) {
        this.minCursor = minCursor;
    }

    public int getMaxCursor() {
        return maxCursor;
    }

    public void setMaxCursor(int maxCursor) {
        this.maxCursor = maxCursor;
    }

    public boolean isTagRemain() {
        return tagRemain;
    }

    public void setTagRemain(boolean tagRemain) {
        this.tagRemain = tagRemain;
    }

    public boolean isMinCursorRemain() {
        return minCursorRemain;
    }

    public void setMinCursorRemain(boolean minCursorRemain) {
        this.minCursorRemain = minCursorRemain;
    }

    public boolean isMaxCursorRemain() {
        return maxCursorRemain;
    }

    public void setMaxCursorRemain(boolean maxCursorRemain) {
        this.maxCursorRemain = maxCursorRemain;
    }

    public boolean isQueryAwait() {
        return queryAwait;
    }

    public void setQueryAwait(boolean queryAwait) {
        this.queryAwait = queryAwait;
    }

    public String getChosenInlineMessageId() {
        return chosenInlineMessageId;
    }

    public void setChosenInlineMessageId(String chosenInlineMessageId) {
        this.chosenInlineMessageId = chosenInlineMessageId;
    }

    public boolean isMailing() {
        return mailing;
    }

    public void setMailing(boolean mailing) {
        this.mailing = mailing;
    }

    public List<ReceivedMediaFile> getReceivedMediaFileList() {
        return receivedMediaFileList;
    }

    public void setReceivedMediaFileList(List<ReceivedMediaFile> receivedMediaFileList) {
        this.receivedMediaFileList = receivedMediaFileList;
    }

    public int getCurrentCursor() {
        return currentCursor;
    }

    public void setCurrentCursor(int currentCursor) {
        this.currentCursor = currentCursor;
    }

    public String getLastQuery() {
        return lastQuery;
    }

    public void setLastQuery(String lastQuery) {
        this.lastQuery = lastQuery;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public boolean isInstLoading() {
        return instLoading;
    }

    public void setInstLoading(boolean instLoading) {
        this.instLoading = instLoading;
    }

    public List<TelegramUserMediaEntity> getTelegramUserMediaEntityList() {
        return telegramUserMediaEntityList;
    }

    public void setTelegramUserMediaEntityList(List<TelegramUserMediaEntity> telegramUserMediaEntityList) {
        this.telegramUserMediaEntityList = telegramUserMediaEntityList;
    }

    public boolean isMailingSent() {
        return mailingSent;
    }

    public void setMailingSent(boolean mailingSent) {
        this.mailingSent = mailingSent;
    }

    public boolean isFromChannel() {
        return fromChannel;
    }

    public void setFromChannel(boolean fromChannel) {
        this.fromChannel = fromChannel;
    }

    public Long getTargetChannelForPosting() {
        return targetChannelForPosting;
    }

    public void setTargetChannelForPosting(Long targetChannelForPosting) {
        this.targetChannelForPosting = targetChannelForPosting;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public boolean isInfo() {
        return info;
    }

    public void setInfo(boolean info) {
        this.info = info;
    }

    public boolean isReplyMarkupEditMailing() {
        return replyMarkupEditMailing;
    }

    public void setReplyMarkupEditMailing(boolean replyMarkupEditMailing) {
        this.replyMarkupEditMailing = replyMarkupEditMailing;
    }

    public boolean isTiktokUrlRemain() {
        return tiktokUrlRemain;
    }

    public void setTiktokUrlRemain(boolean tiktokUrlRemain) {
        this.tiktokUrlRemain = tiktokUrlRemain;
    }

    public boolean isKinoRemain() {
        return kinoRemain;
    }

    public void setKinoRemain(boolean kinoRemain) {
        this.kinoRemain = kinoRemain;
    }

    public boolean isNameEdit() {
        return nameEdit;
    }

    public void setNameEdit(boolean nameEdit) {
        this.nameEdit = nameEdit;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    public boolean isTagInstRemain() {
        return tagInstRemain;
    }

    public void setTagInstRemain(boolean tagInstRemain) {
        this.tagInstRemain = tagInstRemain;
    }

    public int getCurrentInstCursor() {
        return currentInstCursor;
    }

    public void setCurrentInstCursor(int currentInstCursor) {
        this.currentInstCursor = currentInstCursor;
    }

    public InstagramMediaDataDTO getInstagramMediaDataDTO() {
        return instagramMediaDataDTO;
    }

    public void setInstagramMediaDataDTO(InstagramMediaDataDTO instagramMediaDataDTO) {
        this.instagramMediaDataDTO = instagramMediaDataDTO;
    }

    public long getTodayPartId() {
        return todayPartId;
    }

    public void setTodayPartId(long todayPartId) {
        this.todayPartId = todayPartId;
    }

    public long getCctualPartId() {
        return cctualPartId;
    }

    public void setCctualPartId(long cctualPartId) {
        this.cctualPartId = cctualPartId;
    }

    public long getCombinedPart() {
        return combinedPart;
    }

    public void setCombinedPart(long combinedPart) {
        this.combinedPart = combinedPart;
    }

    public byte[] getInstagramMediaResponse() {
        return instagramMediaResponse;
    }

    public void setInstagramMediaResponse(byte[] instagramMediaResponse) {
        this.instagramMediaResponse = instagramMediaResponse;
    }

    public List<Integer> getCombined() {
        return combined;
    }

    public void setCombined(List<Integer> combined) {
        this.combined = combined;
    }

    public boolean isCaptionEdit() {
        return captionEdit;
    }

    public void setCaptionEdit(boolean captionEdit) {
        this.captionEdit = captionEdit;
    }

    public boolean isUserBan() {
        return userBan;
    }

    public void setUserBan(boolean userBan) {
        this.userBan = userBan;
    }

    public String getUserForBan() {
        return userForBan;
    }

    public void setUserForBan(String userForBan) {
        this.userForBan = userForBan;
    }

    public boolean isYouTubeQueryRemain() {
        return youTubeQueryRemain;
    }

    public void setYouTubeQueryRemain(boolean youTubeQueryRemain) {
        this.youTubeQueryRemain = youTubeQueryRemain;
    }

    public boolean isYouTubeLoading() {
        return youTubeLoading;
    }

    public void setYouTubeLoading(boolean youTubeLoading) {
        this.youTubeLoading = youTubeLoading;
    }

    public int getCurrentYouTubeCursor() {
        return currentYouTubeCursor;
    }

    public void setCurrentYouTubeCursor(int currentYouTubeCursor) {
        this.currentYouTubeCursor = currentYouTubeCursor;
    }

    public List<YouTubeMediaDTO> getYouTubeMediaDTOList() {
        return youTubeMediaDTOList;
    }

    public void setYouTubeMediaDTOList(List<YouTubeMediaDTO> youTubeMediaDTOList) {
        this.youTubeMediaDTOList = youTubeMediaDTOList;
    }

    public Long getMediaLinkId() {
        return mediaLinkId;
    }

    public void setMediaLinkId(Long mediaLinkId) {
        this.mediaLinkId = mediaLinkId;
    }

    @Override
    public String toString() {
        return "UserContext{" +
                "receivedMediaFileList=" + receivedMediaFileList +
                ", update=" + update +
                ", user=" + user +
                ", chat=" + chat +
                ", inlineKeyboardMarkup=" + inlineKeyboardMarkup +
                ", callBackData=" + callBackData +
                ", instanceId=" + instanceId +
                ", remain=" + remain +
                ", userBan=" + userBan +
                ", reset=" + reset +
                ", info=" + info +
                ", commentRemain=" + commentRemain +
                ", queryAwait=" + queryAwait +
                ", attributeName='" + attributeName + '\'' +
                ", userForBan='" + userForBan + '\'' +
                ", chosenInlineMessageId='" + chosenInlineMessageId + '\'' +
                ", fromPageData='" + fromPageData + '\'' +
                ", offs=" + offs +
                ", anon=" + anon +
                ", partId=" + partId +
                ", todayPartId=" + todayPartId +
                ", cctualPartId=" + cctualPartId +
                ", combinedPart=" + combinedPart +
                ", forUserId=" + forUserId +
                ", referralId=" + referralId +
                ", language=" + language +
                ", tag='" + tag + '\'' +
                ", tagRemain=" + tagRemain +
                ", tagInstRemain=" + tagInstRemain +
                ", youTubeQueryRemain=" + youTubeQueryRemain +
                ", tiktokUrlRemain=" + tiktokUrlRemain +
                ", kinoRemain=" + kinoRemain +
                ", loading=" + loading +
                ", instLoading=" + instLoading +
                ", youTubeLoading=" + youTubeLoading +
                ", mailingSent=" + mailingSent +
                ", replyMarkupEditMailing=" + replyMarkupEditMailing +
                ", fromChannel=" + fromChannel +
                ", edit=" + edit +
                ", nameEdit=" + nameEdit +
                ", captionEdit=" + captionEdit +
                ", targetChannelForPosting=" + targetChannelForPosting +
                ", loginResponse=" + loginResponse +
                ", instagramMediaDataDTO=" + instagramMediaDataDTO +
                ", youTubeMediaDTOList=" + youTubeMediaDTOList +
                ", instagramMediaResponse=" + Arrays.toString(instagramMediaResponse) +
                ", mailing=" + mailing +
                ", minCursor=" + minCursor +
                ", minCursorRemain=" + minCursorRemain +
                ", maxCursor=" + maxCursor +
                ", currentCursor=" + currentCursor +
                ", currentInstCursor=" + currentInstCursor +
                ", currentYouTubeCursor=" + currentYouTubeCursor +
                ", lastQuery='" + lastQuery + '\'' +
                ", maxCursorRemain=" + maxCursorRemain +
                ", newForToday=" + newForToday +
                ", newForYesterday=" + newForYesterday +
                ", actual=" + actual +
                ", combined=" + combined +
                ", mediaLinkId=" + mediaLinkId +
                ", telegramUserMediaEntityList=" + telegramUserMediaEntityList +
                '}';
    }
}
