package de.muv1n.theRealBot.privateChannel.customEvents;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.unions.IThreadContainerUnion;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.entities.sticker.StickerSnowflake;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.channel.concrete.ThreadChannelManager;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.CacheRestAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

public class VoiceChannelJoinEvent extends ListenerAdapter {
    Long categoryIDTestServer = 1013507144215175218L;

    @Override
    public void onGuildVoiceJoin(@NotNull GuildVoiceJoinEvent e) {
        if (!e.getChannelJoined().getName().equals("Create Private Channel"))return;
        if (e.getGuild().getVoiceChannelsByName(e.getMember().getEffectiveName(), false).get(0) == null) e.getGuild().moveVoiceMember(e.getMember(), e.getGuild().getVoiceChannelsByName(e.getMember().getEffectiveName(), false).get(0)).queue();

        Category category = e.getGuild().getCategoryById(categoryIDTestServer);
        e.getGuild().createVoiceChannel(e.getMember().getEffectiveName(), category).queue(channel ->{
            ThreadChannel threadChannel = new ThreadChannel() {
                @Override
                public long getLatestMessageIdLong() {
                    return 0;
                }

                @Override
                public int compareTo(@NotNull GuildChannel o) {
                    return 0;
                }

                @NotNull
                @Override
                public List<Member> getMembers() {
                    return null;
                }

                @Override
                public boolean canTalk(@NotNull Member member) {
                    return false;
                }

                @NotNull
                @Override
                public RestAction<Void> removeReactionById(@NotNull String s, @NotNull Emoji emoji, @NotNull User user) {
                    return null;
                }

                @NotNull
                @Override
                public RestAction<Void> deleteMessagesByIds(@NotNull Collection<String> collection) {
                    return null;
                }

                @NotNull
                @Override
                public RestAction<Void> clearReactionsById(@NotNull String s) {
                    return null;
                }

                @NotNull
                @Override
                public RestAction<Void> clearReactionsById(@NotNull String s, @NotNull Emoji emoji) {
                    return null;
                }

                @NotNull
                @Override
                public MessageAction sendStickers(@NotNull Collection<? extends StickerSnowflake> collection) {
                    return null;
                }

                @Override
                public int getMessageCount() {
                    return 0;
                }

                @Override
                public int getMemberCount() {
                    return 0;
                }

                @Override
                public boolean isLocked() {
                    return false;
                }

                @Override
                public boolean isInvitable() {
                    return false;
                }

                @NotNull
                @Override
                public IThreadContainerUnion getParentChannel() {
                    return null;
                }

                @NotNull
                @Override
                public RestAction<Message> retrieveParentMessage() {
                    return null;
                }

                @NotNull
                @Override
                public List<ThreadMember> getThreadMembers() {
                    return null;
                }

                @Nullable
                @Override
                public ThreadMember getThreadMemberById(long l) {
                    return null;
                }

                @NotNull
                @Override
                public CacheRestAction<ThreadMember> retrieveThreadMemberById(long l) {
                    return null;
                }

                @Override
                public RestAction<List<ThreadMember>> retrieveThreadMembers() {
                    return null;
                }

                @Override
                public long getOwnerIdLong() {
                    return 0;
                }

                @Override
                public boolean isArchived() {
                    return false;
                }

                @Override
                public OffsetDateTime getTimeArchiveInfoLastModified() {
                    return null;
                }

                @NotNull
                @Override
                public AutoArchiveDuration getAutoArchiveDuration() {
                    return null;
                }

                @Override
                public long getIdLong() {
                    return 0;
                }

                @NotNull
                @Override
                public OffsetDateTime getTimeCreated() {
                    return null;
                }

                @Override
                public int getSlowmode() {
                    return 0;
                }

                @Override
                public RestAction<Void> join() {
                    return null;
                }

                @Override
                public RestAction<Void> leave() {
                    return null;
                }

                @Override
                public RestAction<Void> addThreadMemberById(long l) {
                    return null;
                }

                @Override
                public RestAction<Void> removeThreadMemberById(long l) {
                    return null;
                }

                @NotNull
                @Override
                public Guild getGuild() {
                    return null;
                }

                @NotNull
                @Override
                public ThreadChannelManager getManager() {
                    return null;
                }

                @NotNull
                @Override
                public String getName() {
                    return null;
                }

                @NotNull
                @Override
                public ChannelType getType() {
                    return null;
                }

                @NotNull
                @Override
                public JDA getJDA() {
                    return null;
                }

                @NotNull
                @Override
                public AuditableRestAction<Void> delete() {
                    return null;
                }

                @Override
                public IPermissionContainer getPermissionContainer() {
                    return null;
                }
            };
        });

    }
}
