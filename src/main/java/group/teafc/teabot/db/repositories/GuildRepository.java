package group.teafc.teabot.db.repositories;

import group.teafc.teabot.db.entities.GuildEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuildRepository extends JpaRepository<GuildEntity, Long> {
    GuildEntity findByGuildId(Long guildId);
}
